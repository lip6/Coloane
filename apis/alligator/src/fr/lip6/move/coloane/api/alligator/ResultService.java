/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator;

import fr.lip6.move.alligator.interfaces.Item;
import fr.lip6.move.alligator.interfaces.ItemType;
import fr.lip6.move.coloane.api.alligator.wizard.OutputWizard;
import fr.lip6.move.coloane.core.model.factory.FormalismManager;
import fr.lip6.move.coloane.extensions.importExportCAMI.importFromCAMI.ImportFromImpl;
import fr.lip6.move.coloane.interfaces.api.services.IApiService;
import fr.lip6.move.coloane.interfaces.exceptions.ServiceException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.interfaces.objects.result.Result;
import fr.lip6.move.coloane.interfaces.objects.result.SubResult;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.cosyverif.alligator.service.Description;
import org.cosyverif.alligator.service.Identifier;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

public final class ResultService
    implements IApiService {
    /** Logger */
    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

    private Identifier identifier;

    private Connection alligator;

    public ResultService(Identifier identifier, Connection alligator) {
        this.identifier = identifier;
        this.alligator = alligator;
    }

    @Override
    public
        List<IResult> run(IGraph model, IProgressMonitor monitor)
            throws ServiceException {
        try {
            List<IResult> results = new ArrayList<IResult>();
            if (alligator.getServices() != null) {
                LOGGER.info("Obtaining results from service '" + identifier.key() + "' on '" + identifier.server() +
                            "'...");
                boolean kill = true;
                Description description = alligator.getServices()
                                                   .getCurrentState(identifier);
                if (description == null) {
                    throw new AssertionError();
                }
                if (description.isOldService()) {
                    IResult result = new Result(description.name());
                    Item[] resultItems = alligator.getServices()
                                                      .getOldResult(identifier);
                    LOGGER.fine("Getting " + resultItems.length + " result items.");
                    // For all result items give the better feedback to the user
                    for (Item item : resultItems) {
                        // Create a new model from CAMI
                        if (item.getType() == ItemType.CAMI_MODEL) {
                            try {
                                File tmp = File.createTempFile("alligator", ".cami");
                                BufferedWriter writer = new BufferedWriter(new FileWriter(tmp));
                                writer.append(item.getValue());
                                writer.close();
                                ImportFromImpl camiImport = new ImportFromImpl();
                                IGraph newGraph = camiImport.importFrom(tmp.getAbsolutePath(),
                                                                        FormalismManager.getInstance()
                                                                                        .getFormalismById("PT-Net"),
                                                                        SubMonitor.convert(null));
                                result.setNewGraph(newGraph);
                            } catch (IOException e) {
                                LOGGER.warning(e.getMessage());
                            }
                            // Add a textual result in the result view
                        } else {
                            ISubResult sub = new SubResult(item.getName(), item.getValue());
                            result.addSubResult(sub);
                        }
                    }
                    results.add(result);
                } else {
                    Description serviceResult = alligator.getServices()
                                                         .getCurrentState(identifier);
                    IResult result = new Result(identifier.key());
                    // Run wizard to get parameters:
                    if (serviceResult.parameters().length != 0) {
                        OutputWizard wizard = new OutputWizard(alligator, identifier);
                        Display.getDefault()
                               .syncExec(wizard);
                        if (wizard.isCanceled()) {
                            kill = false;
                        }
                    }
                }
                if (kill && alligator.getServices()
                                     .isFinished(identifier)) {
                    new KillService(identifier, alligator).run(model, monitor);
                }
                return results;
            } else {
                throw new AssertionError();
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public
        String getName() {
        return "Get results";
    }

    @Override
    public
        String getDescription() {
        return "Obtain final results from the service.";
    }

}
