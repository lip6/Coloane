/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.dialog;

import fr.lip6.move.coloane.api.alligator.wizard.WizardPage;

import org.cosyverif.alligator.service.parameter.SingleLineTextParameter;

public final class SingleLineTextDialog
    extends ValueDialog<SingleLineTextParameter> {

    public SingleLineTextDialog(WizardPage page, SingleLineTextParameter parameter, boolean editable) {
        super(page, parameter, editable);
    }

    @Override
    protected
        String _errorMessage() {
        return null;
    }

    @Override
    protected
        void updateDialog() {
        if (parameter.isActualParameter()) {
            input.setText(parameter.getValue()
                                   .toString());
        } else {
            input.setText("");
        }
    }

    @Override
    protected
        void updateParameter() {
        parameter.setValue(input.getText());
    }

}
