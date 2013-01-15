/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.dialog;

import fr.lip6.move.coloane.api.alligator.Utility;

import java.util.logging.Logger;

import org.cosyverif.alligator.service.Parameter;
import org.cosyverif.alligator.service.parameter.FileParameter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public final class ImageDialog
    extends Dialog<FileParameter> {

    /** Logger */
    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

    private Label image;
    private Label label;
    private Label help;

    private Composite container;

    private int width = 500;
    private int height = 200;

    public ImageDialog(FileParameter parameter) {
        super(parameter);
        assert parameter.getContentType()
                        .startsWith("image/");
    }

    @Override
    public
        void updateDialog() {
        if (parameter.isActualParameter()) {
            LOGGER.info("Setting image...");
            image.setImage(resize(Utility.getImage(parameter.getFile())));
            container.layout();
        } else {
            image.setImage(null);
            container.layout();
        }
    }

    @Override
    public
        void updateParameter() {
        throw new AssertionError();
    }

    @Override
    public
        int size() {
        return 5;
    }

    @Override
    public
        void create(Composite parent) {
        // Label:
        label = new Label(parent, SWT.WRAP);
        label.setText(parameter.getName() + ":");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        // Help message:
        help = new Label(parent, SWT.WRAP);
        help.setText(parameter.getHelp());
        help.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
        // Image:
        container = new Composite(parent, SWT.BORDER);
        container.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 3, 4));
        image = new Label(container, SWT.CENTER);
        image.setText("");
        image.setSize(width, height);
    }

    private
        Image resize(Image i) {
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
        String errorMessage() {
        return null;
    }

    @Override
    public
        void update(Parameter<?> that) {
        if (!this.equals(that)) {
            parameter.copy(that);
            updateDialog();
        }
    }

}
