/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.dialog;

import fr.lip6.move.coloane.api.alligator.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.logging.Logger;

import org.cosyverif.alligator.service.Parameter;
import org.cosyverif.alligator.service.parameter.FileParameter;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public final class ImageDialog
    extends Dialog<FileParameter> {

    /** Logger */
    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

    private Label image;
    private Label label;
    private Text help;
    private Button saveButton;
    private File file = null;
    private Composite container;

    public ImageDialog(FileParameter parameter) {
        super(parameter);
        assert parameter.getContentType()
                        .startsWith("image/");
    }

    @Override
    public
        void
        updateDialog() {
        if (parameter.isActualParameter()) {
            LOGGER.info("Setting image...");
            try {
                image.setImage(resize(Utility.getImage(parameter.getFile())));
                container.layout();
            } catch (Exception e) {
            }
        } else {
            image.setImage(null);
            container.layout();
        }
    }

    @Override
    public
        void
        updateParameter() {
        throw new AssertionError();
    }

    @Override
    public
        int
        size() {
        return 6;
    }

    @Override
    public
        void
        create(final Composite parent) {
        // Label:
        label = new Label(parent, SWT.WRAP);
        label.setText(parameter.getName() + ":");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        // Help message:
        help = new Text(parent, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
        GridData data = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
        data.widthHint = width;
        help.setLayoutData(data);
        help.setText(parameter.getHelp());
        help.setEditable(false);
        // Image:
        container = new Composite(parent, SWT.BORDER);
        container.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 3, 4));
        image = new Label(container, SWT.CENTER);
        image.setText("");
        image.setSize(width, height);
        // Button:
        saveButton = new Button(parent, SWT.PUSH);
        saveButton.setText("Save as…");
        saveButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 3, 1));
        saveButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public
                void
                widgetSelected(SelectionEvent e) {
                FileDialog dialog = new FileDialog(parent.getShell(), SWT.SAVE);
                dialog.setFilterPath(ResourcesPlugin.getWorkspace()
                                                    .getRoot()
                                                    .getLocation()
                                                    .toString());
                String[] parts = parameter.getContentType()
                                          .split("/");
                dialog.setFilterExtensions(new String[] {
                    "*." + parts[parts.length - 1]
                });
                final String filePath = dialog.open();
                if (filePath != null) {
                    file = new File(filePath);
                    copyFile();
                }
            }
        });
    }

    private
        Image
        resize(Image i) {
        // Keep aspect ratio:
        float ratio = (float) i.getBounds().width / (float) i.getBounds().height;
        int newWidth;
        int newHeight;
        if (ratio > (float) width / (float) height) {
            newWidth = width;
            newHeight = (int) ((float) newWidth / ratio);
        } else {
            newHeight = height;
            newWidth = (int) ((float) newHeight * ratio);
        }
        Image scaled = new Image(Display.getDefault(), newWidth, newHeight);
        GC gc = new GC(scaled);
        gc.setAntialias(SWT.ON);
        gc.setInterpolation(SWT.HIGH);
        gc.drawImage(i, 0, 0, i.getBounds().width, i.getBounds().height, 0, 0, newWidth, newHeight);
        gc.dispose();
        i.dispose(); // don't forget about me!
        return scaled;
    }

    @Override
    public
        String
        errorMessage() {
        return null;
    }

    @Override
    public
        void
        update(Parameter<?> that) {
        if (!this.equals(that)) {
            parameter.populateFrom(that);
            updateDialog();
        }
    }

    // http://stackoverflow.com/questions/300559/move-copy-file-operations-in-java
    public
        void
        copyFile() {
        String[] parts = parameter.getContentType()
                                  .split("/");
        file.delete();
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
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
