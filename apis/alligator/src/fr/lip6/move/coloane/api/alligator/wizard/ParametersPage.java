/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.wizard;

import fr.lip6.move.coloane.api.alligator.Connection;
import fr.lip6.move.coloane.api.alligator.Utility;
import fr.lip6.move.coloane.api.alligator.dialog.BooleanDialogConstructor;
import fr.lip6.move.coloane.api.alligator.dialog.FloatDialogConstructor;
import fr.lip6.move.coloane.api.alligator.dialog.HelpDialogConstructor;
import fr.lip6.move.coloane.api.alligator.dialog.IntegerDialogConstructor;
import fr.lip6.move.coloane.api.alligator.dialog.ItemDialog;
import fr.lip6.move.coloane.api.alligator.dialog.MultiChoicesDialogConstructor;
import fr.lip6.move.coloane.api.alligator.dialog.SingleChoiceDialogConstructor;
import fr.lip6.move.coloane.api.alligator.dialog.StringDialogConstructor;
import fr.lip6.move.coloane.api.alligator.dialog.TextDialogConstructor;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.cosyverif.alligator.service.Description;
import org.cosyverif.alligator.service.Parameter;
import org.cosyverif.alligator.service.Parameter.Direction;
import org.cosyverif.alligator.service.parameter.BooleanParameter;
import org.cosyverif.alligator.service.parameter.FileParameter;
import org.cosyverif.alligator.service.parameter.FloatParameter;
import org.cosyverif.alligator.service.parameter.ForeignModelParameter;
import org.cosyverif.alligator.service.parameter.IntegerParameter;
import org.cosyverif.alligator.service.parameter.ModelParameter;
import org.cosyverif.alligator.service.parameter.MultiLineTextParameter;
import org.cosyverif.alligator.service.parameter.MultipleChoiceParameter;
import org.cosyverif.alligator.service.parameter.SingleChoiceParameter;
import org.cosyverif.alligator.service.parameter.SingleLineTextParameter;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.Bundle;

/**
 * Wizard page to create a custom page from a list of DescriptionItem provided by an Alligator Service.
 * 
 * @author Clément Démoulins
 */
public final class ParametersPage
    extends WizardPage {

    /** Logger */
    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

    /**
     * Errors
     */
    private Map<Object, String> errors = new HashMap<Object, String>();

    @SuppressWarnings("rawtypes")
    private static final Map<Class<? extends Parameter>, Class<? extends ItemDialog>> DIALOGS = new HashMap<Class<? extends Parameter>, Class<? extends ItemDialog>>();
    static {
        DIALOGS.put(SingleLineTextParameter.class, StringDialogConstructor.class);
        DIALOGS.put(MultiLineTextParameter.class, TextDialogConstructor.class);
        DIALOGS.put(BooleanParameter.class, BooleanDialogConstructor.class);
        DIALOGS.put(IntegerParameter.class, IntegerDialogConstructor.class);
        DIALOGS.put(FloatParameter.class, FloatDialogConstructor.class);
        DIALOGS.put(SingleChoiceParameter.class, SingleChoiceDialogConstructor.class);
        DIALOGS.put(MultipleChoiceParameter.class, MultiChoicesDialogConstructor.class);
    }

    private static final Map<String, Description> STORED_PARAMETERS = new HashMap<String, Description>();
    private final List<ItemDialog<?>> dialogs = new ArrayList<ItemDialog<?>>();

    private Description description;
    private List<Parameter<?>> parameters = new ArrayList<Parameter<?>>();
    public boolean enabled;

    public ParametersPage(Description service, boolean enabled) {
        super("Parameters", "Parameters for " + service.getName(), Utility.getImage("alligator-logo.png"));
        this.description = service;
        this.enabled = enabled;
        // Retrieve previously set values:
        Description previous = STORED_PARAMETERS.get(service);
        if (previous == null) {
            previous = service;
        }
        // Select only parameters that are neither files nor models.
        for (Parameter<?> parameter : description.getParameters()) {
            if (parameter.isInput() && !(parameter instanceof ModelParameter) &&
                !(parameter instanceof ForeignModelParameter) && !(parameter instanceof FileParameter)) {
                this.parameters.add(parameter);
                // Fill with previously set value:
                for (Parameter<?> p : previous.getParameters()) {
                    if ((parameter != p) && parameter.cloneUnset()
                                                     .equals(p.cloneUnset()) && p.isActualParameter()) {
                        parameter.populate(p);
                    }
                }
            }
        }
        Comparator<Parameter<?>> comparator = new Comparator<Parameter<?>>() {
            @Override
            public
                int compare(Parameter<?> lhs, Parameter<?> rhs) {
                return lhs.getName()
                          .compareToIgnoreCase(rhs.getName());
            }
        };
        Collections.sort(this.parameters, comparator);
    }

    @Override
    @SuppressWarnings({
            "rawtypes",
            "unchecked"
    })
    public
        void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        GridLayout layout = new GridLayout(2, false);
        layout.marginLeft = 10;
        layout.marginRight = 10;
        layout.horizontalSpacing = 10;
        composite.setLayout(layout);
        // help zone:
        HelpDialogConstructor hdc = new HelpDialogConstructor(this, composite, description);
        setControl(composite);
        for (Parameter<?> parameter : parameters) {
            Class<? extends ItemDialog> dialogClass = DIALOGS.get(parameter.getClass());
            if (dialogClass != null) {
                try {
                    ItemDialog dialog = dialogClass.getConstructor(this.getClass(), Composite.class, parameter.getClass())
                                                   .newInstance(this, composite, parameter);
                    dialogs.add(dialog);
                } catch (InstantiationException e) {
                    LOGGER.warning("Cannot instantiate the class : " + dialogClass.getName());
                } catch (IllegalAccessException e) {
                    LOGGER.warning("Cannot access to the class : " + dialogClass.getName());
                } catch (IllegalArgumentException e) {
                    LOGGER.warning("Cannot access to the class : " + dialogClass.getName());
                } catch (SecurityException e) {
                    LOGGER.warning("Cannot access to the class : " + dialogClass.getName());
                } catch (InvocationTargetException e) {
                    LOGGER.warning("Cannot access to the class : " + dialogClass.getName());
                } catch (NoSuchMethodException e) {
                    LOGGER.warning("Cannot access to the class : " + dialogClass.getName());
                }
            } else {
                String message = "Cannot find dialog for parameter type '" + parameter.getClass()
                                                                                      .getName() + "'.";
                LOGGER.warning(message);
                throw new AssertionError(message);
            }
        }
        if (enabled) {
            Button reset = new Button(composite, SWT.PUSH);
            reset.setText("Reset");
            reset.setToolTipText("Reset to default values.");
            reset.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, false, false, 2, 1));
            SelectionListener listener = new SelectionListener() {

                @Override
                public
                    void widgetSelected(SelectionEvent e) {
                    for (ItemDialog<?> dialog : dialogs) {
                        dialog.reset();
                    }
                    ParametersPage.this.setPageComplete(ParametersPage.this.isPageComplete());
                }

                @Override
                public
                    void widgetDefaultSelected(SelectionEvent e) {
                    for (ItemDialog<?> dialog : dialogs) {
                        dialog.reset();
                    }
                    ParametersPage.this.setPageComplete(ParametersPage.this.isPageComplete());
                }

            };
            reset.addSelectionListener(listener);
        }
    }

    @Override
    public
        boolean isPageComplete() {
        boolean result = true;
        // Keep this loop (no return inside) as it also fills the errors...
        for (ItemDialog<?> dialog : dialogs) {
            result = dialog.isValid() && result;
        }
        return result;
    }

    public
        void performFinish() {
        LOGGER.finer("Dispose the ParametersPage");
        for (ItemDialog<?> dialog : dialogs) {
            dialog.performFinish();
        }
        // Store values:
        STORED_PARAMETERS.put(description.getIdentifier(), description);
    }

    public
        void performCancel() {
    }

    private
        void setError() {
        String message = "";
        for (String msg : errors.values()) {
            message = message + msg + "\n";
        }
        setErrorMessage(message);
    }

    public final
        void addError(Object control, String message) {
        errors.put(control, message);
        setError();
    }

    public final
        void removeError(Object control) {
        errors.remove(control);
        if (errors.size() == 0) {
            this.setErrorMessage(null);
        } else {
            setError();
        }
    }

        void update(Description description) {
        for (ItemDialog<?> dialog : dialogs) {
            for (Parameter<?> parameter : description.getParameters()) {
                if (dialog.getParameter()
                          .cloneUnset()
                          .equals(parameter.cloneUnset()) && parameter.isActualParameter()) {
                    LOGGER.info("Updating parameter '" + parameter.getName() + "'.");
                    dialog.update(parameter);
                    break;
                }
            }
        }
    }

}
