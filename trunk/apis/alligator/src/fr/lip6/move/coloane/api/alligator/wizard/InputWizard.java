/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.wizard;

import fr.lip6.move.coloane.api.alligator.dialog.Dialog;
import fr.lip6.move.coloane.api.alligator.dialog.InputResourceDialog;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.cosyverif.alligator.service.Description;
import org.cosyverif.alligator.service.Parameter;

/**
 * Wizard to prepare an invocation of an Alligator service.
 */
public final class InputWizard
    extends Wizard {

    public InputWizard(Description service) {
        super(service, true);
    }

    @Override
        List<Set<Parameter<?>>> splitParameters(Description description) {
        Set<Parameter<?>> result = new HashSet<Parameter<?>>();
        for (Parameter<?> parameter : description.getParameters()) {
            if (parameter.isInput()) {
                result.add(parameter);
            }
        }
        return Collections.singletonList(result);
    }

    @Override
    public
        boolean performFinish() {
        for (Dialog<?> dialog : dialogs) {
            if (dialog instanceof InputResourceDialog<?>) {
                InputResourceDialog<?> theDialog = (InputResourceDialog<?>) dialog;
                Parameter<?> theParameter = theDialog.getParameter()
                                                     .clone();
                theParameter.unset();
                files.put(theParameter, theDialog.getSelectedFile());
            }
        }
        return true;
    }

}
