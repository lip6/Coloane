/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.wizard;

import fr.lip6.move.coloane.api.alligator.Utility;
import fr.lip6.move.coloane.api.alligator.dialog.Dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.cosyverif.alligator.service.Description;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public final class WizardPage
    extends org.eclipse.jface.wizard.WizardPage {

    /** Logger */
    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

    /** Dialogs */
    private List<Dialog<?>> dialogs = new ArrayList<Dialog<?>>();

    /** Errors */
    private Map<Dialog<?>, String> errors = new HashMap<Dialog<?>, String>();

    public WizardPage(Description description) {
        super("Parameters", "Parameters for " + description.getName(), Utility.getImage("alligator-logo.png"));
    }

    public
        void addDialog(Dialog<?> dialog) {
        dialogs.add(dialog);
    }

    public
        void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        GridLayout layout = new GridLayout(3, false);
        /* layout.marginLeft = 10; layout.marginRight = 10; layout.horizontalSpacing = 10; */
        composite.setLayout(layout);
        setControl(composite);
        for (Dialog<?> dialog : dialogs) {
            dialog.create(composite);
        }
    }

    @Override
    public
        boolean isPageComplete() {
        for (Dialog<?> dialog : dialogs) {
            String message = dialog.errorMessage();
            if (message != null) {
                errors.put(dialog, message);
            } else {
                errors.remove(dialog);
            }
        }
        setError();
        return errors.size() == 0;
    }

    private
        void setError() {
        if (errors.size() == 0) {
            this.setErrorMessage(null);
        } else {
            String message = "\t";
            for (Entry<Dialog<?>, String> e : errors.entrySet()) {
                message = message + "For parameter " + e.getKey()
                                                        .getParameter()
                                                        .getName() + ": " + e.getValue() + "\n\t";
            }
            setErrorMessage(message);
        }
    }

    public
        List<Dialog<?>> getDialogs() {
        return dialogs;
    }

    public
        int size() {
        int result = 0;
        for (Dialog<?> dialog : dialogs) {
            result += dialog.size();
        }
        return result;
    }

    private boolean inRefresh = false;

    public
        void refresh() {
        if (!inRefresh) {
            inRefresh = true;
            LOGGER.info("Refreshing wizard page...");
            setPageComplete(isPageComplete());
            inRefresh = false;
        }
    }

}
