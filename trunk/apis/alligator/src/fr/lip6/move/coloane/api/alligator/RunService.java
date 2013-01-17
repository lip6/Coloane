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
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import org.cosyverif.alligator.service.Description;
import org.cosyverif.alligator.service.Identifier;
import org.cosyverif.alligator.service.Parameter;
import org.cosyverif.alligator.service.parameter.FileParameter;
import org.cosyverif.alligator.service.parameter.ModelParameter;
import org.cosyverif.alligator.util.FileSystem;
import org.cosyverif.alligator.util.ParameterConversion;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubMonitor;
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

    /**
     * Constructor
     * 
     * @param service
     *        Associated service
     * @param alligator
     *        Connection to an Alligator
     */
    public RunService(Description service, Connection alligator) {
        this.service = Descriptions.get(service);
        this.alligator = alligator;
    }

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
        this.service = Descriptions.get(new Description(service));
        this.alligator = alligatorConnection;
    }

    private final
        File getFile(IFile file) {
        return file.getRawLocation()
                   .makeAbsolute()
                   .toFile();
    }

    @Override
    public
        List<IResult> run(IGraph model, IProgressMonitor monitor)
            throws ServiceException {
        InputWizard wizard = new InputWizard(service);
        try {
            // Run wizard to get parameters:
            Display.getDefault()
                   .syncExec(wizard);
            if (wizard.isCanceled()) {
                LOGGER.info("Service wizard has been canceled.");
                return Collections.emptyList();
            }
            Descriptions.add(service);
            // Convert input parameters:
            for (Parameter<?> parameter : service.getParameters()) {
                try {
                    if (parameter.isInput()) {
                        if (parameter instanceof ModelParameter) {
                            IFile file = wizard.fileFor(parameter);
                            ModelParameter p = ModelParameter.of(parameter);
                            ExportToGrML exporter = new ExportToGrML();
                            LOGGER.info("Converting file '" + file + "' to GrML...");
                            IGraph graph = ModelLoader.loadGraphFromXML(file);
                            File temp = File.createTempFile("coloane-exporter", ".grml");
                            temp.deleteOnExit();
                            exporter.export(graph, temp.getAbsolutePath(), new NullProgressMonitor());
                            p.setModel(FileSystem.modelFromFile(temp));
                        } else if (parameter instanceof FileParameter && ((FileParameter) parameter).getContentType()
                                                                                                    .equalsIgnoreCase("lola")) {
                            IFile file = wizard.fileFor(parameter);
                            FileParameter p = FileParameter.of(parameter);
                            if (file.getFileExtension()
                                    .equalsIgnoreCase("lola")) {
                                p.setFile(getFile(file));
                            } else {
                                ExportToLola exporter = new ExportToLola();
                                LOGGER.info("Converting file '" + file + "' to LoLa...");
                                IGraph graph = ModelLoader.loadGraphFromXML(file);
                                File temp = File.createTempFile("coloane-exporter", ".lola");
                                temp.deleteOnExit();
                                exporter.export(graph, temp.getAbsolutePath(), new NullProgressMonitor());
                                p.setFile(temp);
                            }
                        } else if (parameter instanceof FileParameter && ((FileParameter) parameter).getContentType()
                                                                                                    .equalsIgnoreCase("cami")) {
                            IFile file = wizard.fileFor(parameter);
                            FileParameter p = FileParameter.of(parameter);
                            if (file.getFileExtension()
                                    .equalsIgnoreCase("cami")) {
                                p.setFile(getFile(file));
                            } else {
                                ExportToImpl exporter = new ExportToImpl();
                                LOGGER.info("Converting file '" + file + "' to CAMI... ");
                                IGraph graph = ModelLoader.loadGraphFromXML(file);
                                File temp = File.createTempFile("coloane-exporter", ".cami");
                                temp.deleteOnExit();
                                exporter.export(graph, temp.getAbsolutePath(), new NullProgressMonitor());
                                p.setFile(temp);
                            }
                        } else if (parameter instanceof FileParameter) {
                            IFile file = wizard.fileFor(parameter);
                            FileParameter p = FileParameter.of(parameter);
                            p.setFile(getFile(file));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (alligator.getServices() != null) {
                LOGGER.info("Launching service '" + service + "'...");
                Identifier identifier = alligator.getServices()
                                                 .asynchronousCall(service);
                Identifiers.add(identifier);
                alligator.runningDescriptions.put(identifier, service);
                alligator.wakeUp();
                IResult result = new Result(service.getName());
                ISubResult sub = new SubResult("Identifier of the service execution", identifier.toString());
                result.addSubResult(sub);
                return new ResultService(identifier, alligator).run(null, monitor);
            } else {
                IResult result = new Result(service.getName());
                LOGGER.info("Invoking service '" + service + "' (oldstyle)...");
                List<Item> params = ParameterConversion.valueFrom(service.getParameters());
                List<Item> resultItems = alligator.getOldServices()
                                                  .invoke(service.getIdentifier(), params);
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
        } catch (CancellationException e) {
            return Collections.emptyList();
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
