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
import fr.lip6.move.alligator.interfaces.ServiceDescription;
import fr.lip6.move.coloane.api.alligator.wizard.InputWizard;
import fr.lip6.move.coloane.core.model.factory.FormalismManager;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.extensions.exporttogrml.ExportToGrML;
import fr.lip6.move.coloane.extensions.importExportCAMI.exportToCAMI.ExportToImpl;
import fr.lip6.move.coloane.extensions.importExportCAMI.importFromCAMI.ImportFromImpl;
import fr.lip6.move.coloane.extensions.importExportLola.ExportToLola;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import org.cosyverif.alligator.service.Description;
import org.cosyverif.alligator.service.Identifier;
import org.cosyverif.alligator.service.Parameter;
import org.cosyverif.alligator.service.parameter.ForeignModelParameter;
import org.cosyverif.alligator.service.parameter.ModelParameter;
import org.cosyverif.alligator.util.ParameterConversion;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

/**
 * Implementation of IApiService to manage Alligator service.
 * 
 * @see {@link fr.lip6.move.alligator.interfaces.Service}
 * @author Clément Démoulins
 */
public final class RunService
    implements IApiService {
    /** Logger */
    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

    private Description service;

    private Connection alligator;

    private static Map<Description, Description> CONFIGURATIONS = new HashMap<Description, Description>();

    /**
     * Runnable to manage the dialog box
     */
    private static class ParametersRunnable
        implements Runnable {

        private int code;
        private final InputWizard wizard;

        public ParametersRunnable(InputWizard w) {
            wizard = w;
        }

        @Override
        public
            void run() {
            WizardDialog dialog = new WizardDialog(Display.getDefault()
                                                          .getActiveShell(), wizard);
            dialog.setBlockOnOpen(true);
            code = dialog.open();
        }

        public
            int getReturnedCode() {
            return code;
        }

    }

    /**
     * Constructor
     * 
     * @param service
     *        Associated service
     * @param alligatorConnection
     *        Connection to an Alligator
     */
    public RunService(Description service, Connection alligator) {
        this.service = service.clone();
        this.alligator = alligator;
    }

    /**
     * Constructor
     * 
     * @param service
     *        Associated service
     * @param alligatorConnection
     *        Connection to an Alligator
     */
    public RunService(Identifier identifier, Connection alligator) {
        LOGGER.info("Retrieving description of configured service '" + identifier + "' for cloning.");
        this.service = alligator.runningDescriptions.get(identifier);
        if (this.service == null) {
            if (alligator.getServices() == null) {
                throw new AssertionError();
            }
            try {
                LOGGER.info("Description of configured service '" + identifier +
                            "' is not locally available, asking alligator server.");
                this.service = alligator.getServices()
                                        .getDescription(identifier);
                alligator.runningDescriptions.put(identifier, this.service);
                LOGGER.info("Description of configured service '" + identifier + "' was taken from server.");
            } catch (IllegalStateException e) {
                throw new IllegalArgumentException(e);
            } catch (ExecutionException e) {
                throw new IllegalArgumentException(e);
            }
        }
        this.alligator = alligator;
    }

    /**
     * Constructor
     * 
     * @param service
     *        Associated service
     * @param alligatorConnection
     *        Connection to an Alligator
     */
    public RunService(ServiceDescription service, Connection alligatorConnection) {
        this.service = new Description(service);
        this.alligator = alligatorConnection;
    }

    private static
        IFile fromFile(File file) {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IPath location = Path.fromOSString(file.getAbsolutePath());
        return workspace.getRoot()
                        .getFileForLocation(location);
    }

    @Override
    public
        List<IResult> run(IGraph model, IProgressMonitor monitor)
            throws ServiceException {
        Description configured;
        Description copy = service.clone();
        copy.unset();
        if (CONFIGURATIONS.containsKey(copy)) {
            configured = CONFIGURATIONS.get(copy)
                                       .clone();
        } else {
            configured = service.clone();
        }
        // TODO: use default values
        // TODO retrieve & store description
        InputWizard wizard = new InputWizard(configured);
        try {
            // Run wizard to get parameters:
            if (!configured.getParameters()
                           .isEmpty()) {
                ParametersRunnable runnable = new ParametersRunnable(wizard);
                Display.getDefault()
                       .syncExec(runnable);
                if (runnable.getReturnedCode() == Dialog.CANCEL) {
                    return Collections.emptyList();
                }
            }
            CONFIGURATIONS.put(copy, configured);
            // Convert input parameters:
            for (Parameter<?> parameter : configured.getParameters()) {
                try {
                    if (parameter.isInput()) {
                        if (parameter instanceof ModelParameter) {
                            ModelParameter p = ModelParameter.of(parameter);
                            ExportToGrML exporter = new ExportToGrML();
                            IGraph graph = ModelLoader.loadGraphFromXML(fromFile(p.getFile()));
                            File temp = File.createTempFile("coloane-exporter", ".grml");
                            temp.deleteOnExit();
                            exporter.export(graph, temp.getAbsolutePath(), new NullProgressMonitor());
                            p.setFile(temp);
                        } else if (parameter instanceof ForeignModelParameter &&
                                   ((ForeignModelParameter) parameter).getType()
                                                                      .equalsIgnoreCase("lola")) {
                            ForeignModelParameter p = ForeignModelParameter.of(parameter);
                            ExportToLola exporter = new ExportToLola();
                            IGraph graph = ModelLoader.loadGraphFromXML(fromFile(p.getFile()));
                            File temp = File.createTempFile("coloane-exporter", ".lola");
                            temp.deleteOnExit();
                            exporter.export(graph, temp.getAbsolutePath(), new NullProgressMonitor());
                            p.setFile(temp);
                        } else if (parameter instanceof ForeignModelParameter &&
                                   ((ForeignModelParameter) parameter).getType()
                                                                      .equalsIgnoreCase("cami")) {
                            ForeignModelParameter p = ForeignModelParameter.of(parameter);
                            ExportToImpl exporter = new ExportToImpl();
                            IGraph graph = ModelLoader.loadGraphFromXML(fromFile(p.getFile()));
                            File temp = File.createTempFile("coloane-exporter", ".cami");
                            temp.deleteOnExit();
                            exporter.export(graph, temp.getAbsolutePath(), new NullProgressMonitor());
                            p.setFile(temp);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Expand all input parameters:
            for (Parameter<?> parameter : configured.getParameters()) {
                if (parameter.isInput()) {
                    parameter.expandForTransfer();
                }
            }
            if (alligator.getServices() != null) {
                LOGGER.info("Launching service '" + configured + "'...");
                Identifier identifier = alligator.getServices()
                                                 .asynchronousCall(configured);
                // Store identifier
                synchronized (Connection.STORE) {
                    LOGGER.info("Storing task identifier '" + identifier + "'...");
                    Connection.STORE.load();
                    Connection.STORE.setValue(identifier.toString(), identifier.toString());
                    Connection.STORE.save();
                }
                alligator.runningDescriptions.put(identifier, configured);
                alligator.wakeUp();
                return Collections.emptyList();
            } else {
                IResult result = new Result(configured.getName());
                LOGGER.info("Invoking service '" + configured + "' (oldstyle)...");
                List<Item> params = ParameterConversion.valueFrom(configured.getParameters());
                List<Item> resultItems = alligator.getOldServices()
                                                  .invoke(configured.getIdentifier(), params);
                LOGGER.fine("Getting " + resultItems.size() + " result items.");
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
                return Collections.singletonList(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public
        String getName() {
        return service.getName();
    }

    @Override
    public
        String getDescription() {
        return service.getHelp();
    }

}
