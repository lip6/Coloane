/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.dialog;

import fr.lip6.move.coloane.core.model.factory.FormalismManager;
import fr.lip6.move.coloane.core.ui.files.ModelWriter;
import fr.lip6.move.coloane.extensions.importExportCAMI.importFromCAMI.ImportFromImpl;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.nio.channels.FileChannel;
import java.util.logging.Logger;

import org.cosyverif.alligator.service.Parameter;
import org.cosyverif.alligator.service.parameter.FileParameter;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public final class OutputFileDialog
    extends Dialog<FileParameter> {

    /** Logger */
    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

    private Text input;
    private Label label;
    private Text help;
    private File file = null;
    private Button saveButton;

    public OutputFileDialog(FileParameter parameter) {
        super(parameter);
    }

    @Override
    public
        void
        updateDialog() {
        if (parameter.isActualParameter() && file != null) {
            input.setText(file.toString());
        } else {
            input.setText("");
        }
    }

    @Override
    public
        void
        updateParameter() {
    }

    @Override
    public
        int
        size() {
        return 3;
    }

    @Override
    public
        void
        create(final Composite parent) {
        // Label:
        label = new Label(parent, SWT.WRAP);
        label.setText(parameter.getName() + ":");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        // Input:
        input = new Text(parent, SWT.BORDER | SWT.SINGLE);
        input.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
        input.setText("");
        input.setEditable(false);
        // Help message:
        help = new Text(parent, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
        GridData data = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
        data.widthHint = width;
        help.setLayoutData(data);
        help.setText(parameter.getHelp());
        help.setEditable(false);
        // Button:
        saveButton = new Button(parent, SWT.PUSH);
        saveButton.setText("Save as…");
        saveButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 3, 1));
        saveButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public
                void
                widgetSelected(SelectionEvent e) {
                try {
                    FileDialog dialog = new FileDialog(parent.getShell(), SWT.SAVE);
                    dialog.setFilterPath(ResourcesPlugin.getWorkspace()
                                                        .getRoot()
                                                        .getLocation()
                                                        .toString());
                    String[] parts = parameter.getContentType()
                                              .split("/");
                    if (parts[parts.length - 1].equalsIgnoreCase("cami")) {
                        dialog.setFilterExtensions(new String[] {
                            "*.model"
                        });
                    } else {
                        dialog.setFilterExtensions(new String[] {
                            "*." + parts[parts.length - 1]
                        });
                    }
                    final String filePath = dialog.open();
                    new ProgressMonitorDialog(parent.getShell()).run(false, false, new IRunnableWithProgress() {

                        @Override
                        public
                            void
                            run(IProgressMonitor monitor)
                                throws InvocationTargetException, InterruptedException {
                            monitor.beginTask("Saving file as " + filePath + "...", 2);
                            if (filePath != null) {
                                file = new File(filePath);
                                monitor.worked(1);
                                copyFile();
                                monitor.worked(1);
                            }
                            monitor.done();
                        }

                    });
                    IWorkspace workspace = ResourcesPlugin.getWorkspace();
                    workspace.getRoot()
                             .refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
                    input.setBackground(null);
                    label.setBackground(null);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        saveButton.setEnabled(false);
        parameter.unset();
    }

    @Override
    public
        String
        errorMessage() {
        return null;
    }

    // http://stackoverflow.com/questions/300559/move-copy-file-operations-in-java
    public
        void
        copyFile() {
        String[] parts = parameter.getContentType()
                                  .split("/");
        file.delete();
        if (parts[parts.length - 1].equalsIgnoreCase("cami")) {
            ImportFromImpl importer = new ImportFromImpl();
            LOGGER.info("Importing file '" + file + "' from CAMI " + parameter.getFile() + "...");
            for (String id : new String[] {
                    "PT-Net",
                    "CPN"
            }) {
                LOGGER.fine("Trying '" + id + "' formalism...");
                IFormalism formalism = FormalismManager.getInstance()
                                                       .getFormalismById(id);
                try {
                    IGraph graph = importer.importFrom(parameter.getFile()
                                                                .getAbsolutePath(), formalism, new NullProgressMonitor());
                    Writer writer = new BufferedWriter(new FileWriter(file));
                    ModelWriter.translateToXML(graph, writer);
                    writer.flush();
                    LOGGER.fine("Import successful.");
                    input.setText(file.getAbsolutePath());
                    return;
                } catch (Exception e) {
                    LOGGER.fine("Import failed.");
                    e.printStackTrace();
                }
            }
            // If no import was successful, get the CAMI file.
            file = new File(file.getAbsolutePath() + ".cami");
            input.setText(file.getAbsolutePath());
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileChannel source = null;
            FileChannel destination = null;
            try {
                source = new FileInputStream(parameter.getFile()).getChannel();
                destination = new FileOutputStream(file).getChannel();
                // previous code: destination.transferFrom(source, 0, source.size());
                // to avoid infinite loops, should be:
                long count = 0;
                long size = source.size();
                while ((count += destination.transferFrom(source, count, size - count)) < size)
                    ;
            } finally {
                if (source != null) {
                    source.close();
                }
                if (destination != null) {
                    destination.close();
                }
            }
            input.setText(file.getAbsolutePath());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public
        void
        update(Parameter<?> p) {
        FileParameter that = (FileParameter) p;
        parameter.setFile(that.getFile());
        if (parameter.isActualParameter() && (parameter.getFile()
                                                       .length() != 0)) {
            saveButton.setEnabled(true);
        }
        if (parameter.isActualParameter() && (file == null)) {
            input.setBackground(updateColor);
            label.setBackground(updateColor);
        } else {
            input.setBackground(null);
            label.setBackground(null);
        }
        updateDialog();
    }

}
