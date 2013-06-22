/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.dialog;

import fr.lip6.move.alligator.interfaces.Item;
import fr.lip6.move.coloane.core.main.Coloane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.cosyverif.alligator.service.Parameter;
import org.cosyverif.alligator.service.parameter.ItemsParameter;
import org.cosyverif.alligator.service.parameter.MultipleLineTextParameter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public final class ItemsDialog
    extends Dialog<ItemsParameter> {

    private Label label;
    private Text help;
    private Text input;

    public ItemsDialog(ItemsParameter parameter) {
        super(parameter);
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
        update(Parameter<?> p) {
        ItemsParameter that = (ItemsParameter) p;
        if (that != null) {
            if (parameter.equals(that)) {
                input.setBackground(null);
            } else {
                input.setBackground(updateColor);
                parameter.populateFrom(that);
                updateDialog();
            }
        }
    }

    @Override
    public
        int
        size() {
        return 10;
    }

    @Override
    public
        void
        create(final Composite parent) {
        // Label:
        label = new Label(parent, SWT.WRAP);
        label.setText(parameter.getName() + ":");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
        // Help message:
        help = new Text(parent, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
        GridData data = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
        data.widthHint = width;
        help.setLayoutData(data);
        help.setText(parameter.getHelp());
        help.setEditable(false);
        // Input:
        GridData layoutData = new GridData(SWT.FILL, SWT.FILL, false, false, 3, 8);
        layoutData.heightHint = 200;
        input = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
        input.setLayoutData(layoutData);
        input.addModifyListener(new ModifyListener() {

            @Override
            public
                void
                modifyText(ModifyEvent e) {
                String error = errorMessage();
                if (error == null) {
                    updateParameter();
                }
            }

        });
        input.setEditable(false);
    }

    @Override
    public
        void
        updateDialog() {
        if (parameter.isActualParameter()) {
            for (Item item : parameter.getItems()) {
                input.setText(item.getValue());
            }
        } else {
            input.setText("");
        }
    }

    @Override
    public
        void
        updateParameter() {
    }

}
