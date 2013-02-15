/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.dialog;

import org.cosyverif.alligator.service.Parameter;
import org.cosyverif.alligator.service.parameter.MultipleChoiceParameter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

public final class MultipleChoiceDialog
    extends Dialog<MultipleChoiceParameter> {

    private Label label;
    private List list;
    private Text help;

    public MultipleChoiceDialog(MultipleChoiceParameter parameter) {
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
        MultipleChoiceParameter that = (MultipleChoiceParameter) p;
        if (that != null) {
            if (parameter.equals(that)) {
                list.setBackground(null);
            } else {
                list.setBackground(updateColor);
                parameter.populateFrom(that);
                updateDialog();
            }
        }
        page.refresh();
    }

    @Override
    public
        int size() {
        return 6;
    }

    @Override
    public
        void create(Composite parent) {
        // Label:
        label = new Label(parent, SWT.WRAP);
        label.setText(parameter.getName() + ":");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
        // Help message:
        help = new Text(parent, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
        GridData data = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
        data.widthHint = width;
        help.setLayoutData(data);
        help.setText(parameter.getHelp());
        help.setEditable(false);
        // Choices:
        list = new org.eclipse.swt.widgets.List(parent, SWT.MULTI | SWT.BORDER);
        list.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 3, 4));
        for (String choice : parameter.getChoices()) {
            list.add(choice);
        }
        list.addSelectionListener(new SelectionListener() {

            @Override
            public
                void widgetSelected(SelectionEvent e) {
                String error = errorMessage();
                if (error == null) {
                    updateParameter();
                }
            }

            @Override
            public
                void widgetDefaultSelected(SelectionEvent e) {
                String error = errorMessage();
                if (error == null) {
                    updateParameter();
                }
            }

        });
        this.list.setEnabled(editable);
    }

    @Override
    public
        void updateDialog() {
        if (parameter.isActualParameter()) {
            for (String choice : parameter.getChoices()) {
                if (parameter.getSelection()
                             .contains(choice)) {
                    this.list.select(Math.max(0, parameter.getChoices()
                                                          .indexOf(choice)));
                } else {
                    this.list.deselect(Math.max(0, parameter.getChoices()
                                                            .indexOf(choice)));
                }
            }
        } else {
            list.deselectAll();
        }
    }

    @Override
    public
        void updateParameter() {
        parameter.resetSelection();
        for (String choice : list.getSelection()) {
            parameter.select(choice);
        }
        page.refresh();
    }

}
