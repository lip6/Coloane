/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.dialog;

import java.math.BigInteger;

import org.cosyverif.alligator.service.parameter.IntegerParameter;

public final class IntegerDialog
    extends ValueDialog<IntegerParameter> {

    public IntegerDialog(IntegerParameter parameter, boolean editable) {
        super(parameter, editable);
    }

    @Override
    public
        String _errorMessage() {
        try {
            Integer.valueOf(input.getText());
            return null;
        } catch (NumberFormatException e) {
            return "Value '" + input.getText() + "' must be an integer.";
        }
    }

    @Override
    public
        void updateDialog() {
        if (parameter.isActualParameter()) {
            input.setText(parameter.getValue()
                                   .toString());
        } else {
            input.setText("");
        }
    }

    @Override
    public
        void updateParameter() {
        parameter.setValue(BigInteger.valueOf(Integer.valueOf(input.getText())));
    }

}
