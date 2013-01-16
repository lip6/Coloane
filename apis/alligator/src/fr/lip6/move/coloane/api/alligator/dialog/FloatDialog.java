/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.dialog;

import org.cosyverif.alligator.service.parameter.FloatParameter;

public final class FloatDialog
    extends ValueDialog<FloatParameter> {

    public FloatDialog(FloatParameter parameter) {
        super(parameter);
    }

    @Override
    public
        String _errorMessage() {
        try {
            Float.valueOf(input.getText());
            return null;
        } catch (NumberFormatException e) {
            return "Value '" + input.getText() + "' must be a float.";
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
        if (_errorMessage() == null) {
            parameter.setValue(Float.valueOf(input.getText()));
            page.refresh();
        }
    }

}
