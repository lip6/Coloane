/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.dialog;

import fr.lip6.move.coloane.api.alligator.wizard.Wizard;

import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public final class ErrorDialog {

    private Label label;
    private Text input;
    private final Wizard wizard;

    public ErrorDialog(final Wizard wizard) {
        this.wizard = wizard;
    }

    public
        void
        create(final Composite parent) {
        // Label:
        label = new Label(parent, SWT.WRAP);
        label.setText("Errors:");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        // Input:
        GridData layoutData = new GridData(SWT.FILL, SWT.FILL, false, false, 2, 4);
        layoutData.heightHint = 50;
        input = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
        input.setLayoutData(layoutData);
        input.setEditable(false);
        input.addModifyListener(new ModifyListener() {
            @Override
            public
                void
                modifyText(ModifyEvent e) {
                if (input.getText()
                         .isEmpty()) {
                    input.setBackground(null);
                } else {
                    input.setBackground(new Color(Display.getCurrent(), 240, 128, 128));
                }
            }
        });
    }

    public
        void
        setErrors(String[] errors) {
        if (errors != null) {
            System.err.println("Errors: " + Arrays.toString(errors));
            String text = null;
            for (String error : errors) {
                if (text == null) {
                    text = error;
                } else {
                    text = text + "\n" + error;
                }
            }
            input.setText(text);
        }
    }

}
