/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.dialog;

import org.cosyverif.alligator.service.Parameter;
import org.cosyverif.alligator.service.parameter.BooleanParameter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public final class BooleanDialog
    extends Dialog<BooleanParameter> {

    private Button button;
    private Text help;

    public BooleanDialog(BooleanParameter parameter) {
        super(parameter);
    }

    @Override
    public
        String errorMessage() {
        return null;
    }

    @Override
    public
        void update(Parameter<?> p) {
        BooleanParameter that = (BooleanParameter) p;
        if (parameter.equals(that)) {
            button.setBackground(null);
        } else {
            button.setBackground(updateColor);
            parameter.populateFrom(that);
            updateDialog();
        }
        page.refresh();
    }

    @Override
    public
        int size() {
        return 2;
    }

    @Override
    public
        void create(Composite parent) {
        // Button:
        button = new Button(parent, SWT.CHECK);
        button.setText(parameter.getName());
        button.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        button.setSelection(false);
        button.addSelectionListener(new SelectionListener() {

            @Override
            public
                void widgetSelected(SelectionEvent e) {
                updateParameter();
            }

            @Override
            public
                void widgetDefaultSelected(SelectionEvent e) {
                updateParameter();
            }

        });
        // Help message:
        help = new Text(parent, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
        GridData data = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
        data.widthHint = width;
        help.setLayoutData(data);
        help.setText(parameter.getHelp());
        help.setEditable(false);
        //
        button.setEnabled(editable);
        if (!parameter.isActualParameter()) {
            parameter.setValue(false);
        }
    }

    @Override
    public
        void updateDialog() {
        if (parameter.isActualParameter()) {
            button.setSelection(parameter.getValue());
        } else {
            button.setSelection(false);
        }
    }

    @Override
    public
        void updateParameter() {
        parameter.setValue(button.getSelection());
    }

}
