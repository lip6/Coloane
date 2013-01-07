/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.dialog;

import fr.lip6.move.coloane.api.alligator.Utility;

import org.cosyverif.alligator.service.Parameter;
import org.cosyverif.alligator.service.parameter.FileParameter;
import org.cosyverif.alligator.service.parameter.ResourceParameter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public final class ImageDialog
    extends Dialog<FileParameter> {

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
            image.setText(null);
            image.setImage(Utility.getImage(parameter.getFile()));
        } else {
            image.setImage(null);
            image.setText("No image yet.");
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
        image.setText(parameter.getName() + ":");
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
            page.refresh();
        }
    }

}
