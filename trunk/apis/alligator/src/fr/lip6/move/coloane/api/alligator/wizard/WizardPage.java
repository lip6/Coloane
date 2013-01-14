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
import fr.lip6.move.coloane.api.alligator.dialog.ResetDialog;
import fr.lip6.move.coloane.api.alligator.dialog.SetDefaultDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private final Wizard wizard;

    /** Dialogs */
    private final List<Dialog<?>> dialogs = new ArrayList<Dialog<?>>();

    /** Errors */
    private final Map<Dialog<?>, String> errors = new HashMap<Dialog<?>, String>();

    public WizardPage(Wizard wizard, Description description) {
        super("Parameters", "Parameters for the " + description.getName() + " service", Utility.getImage("alligator-logo.png"));
        this.wizard = wizard;
        this.setMessage(description.getHelp());
    }

    public
        void addDialog(Dialog<?> dialog) {
        dialogs.add(dialog);
    }

    public
        void createControl(Composite parent) {
        LOGGER.info("Creating controls in wizard page...");
        inRefresh = true;
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(3, false));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, Wizard.PAGE_SIZE));
        setControl(composite);
        for (Dialog<?> dialog : dialogs) {
            dialog.create(composite);
        }
        if (wizard instanceof InputWizard) {
            new ResetDialog(wizard).create(composite);
            new SetDefaultDialog(wizard).create(composite);
        }
        inRefresh = false;
        refresh();
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
            String parameters = null;
            for (Dialog<?> dialog : errors.keySet()) {
                if (parameters == null) {
                    parameters = dialog.getParameter()
                                       .getName();
                } else {
                    parameters = parameters + ", " + dialog.getParameter()
                                                           .getName();
                }
            }
            setErrorMessage("Some parameters are not set (" + parameters.trim() + ").");
        }
    }

    public
        List<Dialog<?>> getDialogs() {
        return dialogs;
    }

    public
        int size() {
        int result = 1;
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
            for (Dialog<?> dialog : dialogs) {
                dialog.updateDialog();
            }
            setPageComplete(isPageComplete());
            setError();
            inRefresh = false;
        }
    }

}
