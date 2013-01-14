/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.dialog;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.cosyverif.alligator.service.Parameter;

public final class OutputResourceDialog<P extends Parameter<P>>
    extends ValueDialog<P> {

    /** Logger */
    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

    private File file;

    public OutputResourceDialog(P parameter) {
        super(parameter);
        try {
            file = File.createTempFile(parameter.getName(), ".parameter");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected
        String _errorMessage() {
        return null;
    }

    @Override
    public
        void updateDialog() {
        if (parameter.isActualParameter()) {
            input.setText(file.toString());
        } else {
            input.setText("");
        }
    }

    @Override
    public
        void updateParameter() {
    }

}
