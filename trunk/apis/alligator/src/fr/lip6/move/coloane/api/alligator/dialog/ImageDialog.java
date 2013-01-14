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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public final class ImageDialog
    extends Dialog<FileParameter> {

    /** Logger */
    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

    private Label image;

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
            image.setText(null);
            image.setImage(Utility.getImage(parameter.getFile()));
            image.redraw();
        } else {
            image.setImage(null);
            image.setText(parameter.getName());
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
        image = new Label(parent, SWT.WRAP);
        image.setText(parameter.getName());
        image.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 5, 3));
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
