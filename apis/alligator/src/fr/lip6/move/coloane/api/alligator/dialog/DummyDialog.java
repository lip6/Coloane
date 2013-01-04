/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.dialog;

import fr.lip6.move.coloane.api.alligator.wizard.WizardPage;

import org.cosyverif.alligator.service.Parameter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public final class DummyDialog<P extends Parameter<P>>
    extends Dialog<P> {

    private Label name;
    private Label help;

    public DummyDialog(WizardPage page, P parameter, boolean editable) {
        super(null, parameter, editable);
    }

    @Override
    public
        String errorMessage() {
        return null;
    }

    @Override
    public
        void update(Parameter<?> p) {
    }

    @Override
    public
        int size() {
        return 1;
    }

    @Override
    public
        void create(Composite parent) {
        // Name:
        name = new Label(parent, SWT.WRAP);
        name.setText(parameter.getName());
        name.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        // Help message:
        help = new Label(parent, SWT.WRAP);
        help.setText(parameter.getHelp());
        help.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
    }

    @Override
    protected
        void updateDialog() {
    }

    @Override
    protected
        void updateParameter() {
    }

}
