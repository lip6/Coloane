/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.dialog;

import org.cosyverif.alligator.service.Parameter;
import org.cosyverif.alligator.service.parameter.SingleChoiceParameter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public final class SingleChoiceDialog
    extends Dialog<SingleChoiceParameter> {

    private Label label;
    private Combo combo;
    private Text help;
    private Label error;

    public SingleChoiceDialog(SingleChoiceParameter parameter) {
        super(parameter);
    }

    @Override
    public
        String errorMessage() {
        if (editable) {
            String result;
            if (combo.getText()
                     .equals("")) {
                result = "Value is not selected.";
                error.setText(result);
                combo.setBackground(errorColor);
            } else {
                result = null;
                combo.setBackground(null);
                error.setText("");
            }
            return result;
        } else {
            return null;
        }
    }

    @Override
    public
        void update(Parameter<?> p) {
        SingleChoiceParameter that = (SingleChoiceParameter) p;
        if (that != null) {
            if (parameter.equals(that)) {
                combo.setBackground(null);
            } else {
                combo.setBackground(updateColor);
                parameter.populateFrom(that);
                updateDialog();
            }
        }
        page.refresh();
    }

    @Override
    public
        int size() {
        return 3;
    }

    @Override
    public
        void create(Composite parent) {
        // Label:
        this.label = new Label(parent, SWT.WRAP);
        this.label.setText(parameter.getName() + ":");
        this.label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        // Combo:
        this.combo = new Combo(parent, SWT.NONE);
        this.combo.setItems(parameter.getChoices()
                                     .toArray(new String[0]));
        this.combo.setToolTipText(parameter.getHelp());
        this.combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
        this.combo.addModifyListener(new ModifyListener() {

            @Override
            public
                void modifyText(ModifyEvent e) {
                String error = errorMessage();
                if (error == null) {
                    updateParameter();
                }
            }

        });
        this.combo.setEnabled(editable);
        // Help message:
        help = new Text(parent, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
        GridData data = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
        data.widthHint = width;
        help.setLayoutData(data);
        help.setText(parameter.getHelp());
        help.setEditable(false);
        // Error:
        error = new Label(parent, SWT.WRAP);
        error.setText("");
        error.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
        error.setForeground(errorFontColor);
    }

    @Override
    public
        void updateDialog() {
        if (parameter.isActualParameter()) {
            combo.select(Math.max(0, parameter.getChoices()
                                              .indexOf(parameter.getSelection())));
        } else {
            combo.deselectAll();
        }
    }

    @Override
    public
        void updateParameter() {
        parameter.select(combo.getText());
        page.refresh();
    }

}
