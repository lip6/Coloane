/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.wizard;

import fr.lip6.move.coloane.api.alligator.dialog.BooleanDialog;
import fr.lip6.move.coloane.api.alligator.dialog.Dialog;
import fr.lip6.move.coloane.api.alligator.dialog.FloatDialog;
import fr.lip6.move.coloane.api.alligator.dialog.InputFileDialog;
import fr.lip6.move.coloane.api.alligator.dialog.InputForeignModelDialog;
import fr.lip6.move.coloane.api.alligator.dialog.InputModelDialog;
import fr.lip6.move.coloane.api.alligator.dialog.IntegerDialog;
import fr.lip6.move.coloane.api.alligator.dialog.MultiLineTextDialog;
import fr.lip6.move.coloane.api.alligator.dialog.MultipleChoiceDialog;
import fr.lip6.move.coloane.api.alligator.dialog.OutputResourceDialog;
import fr.lip6.move.coloane.api.alligator.dialog.SingleChoiceDialog;
import fr.lip6.move.coloane.api.alligator.dialog.SingleLineTextDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.cosyverif.alligator.service.Description;
import org.cosyverif.alligator.service.Parameter;
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

public abstract class Wizard
    extends org.eclipse.jface.wizard.Wizard {

    public static int PAGE_SIZE = 10;

    /** Logger */
    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

    protected Map<Parameter<?>, Dialog<?>> dialogs = new HashMap<Parameter<?>, Dialog<?>>();

    protected List<WizardPage> pages = new ArrayList<WizardPage>();

    protected Description description;

    public Wizard(Description description, boolean isInput) {
        this.description = description;
        // Sort parameters by name:
        Comparator<Parameter<?>> comparator = new Comparator<Parameter<?>>() {
            @Override
            public
                int compare(Parameter<?> lhs, Parameter<?> rhs) {
                return lhs.getName()
                          .compareToIgnoreCase(rhs.getName());
            }
        };
        // For each set of parameters, create dialogs and wizard pages:
        for (Set<Parameter<?>> ps : splitParameters(description)) {
            List<Parameter<?>> parameters = new ArrayList<Parameter<?>>(ps);
            Collections.sort(parameters, comparator);
            // Create dialogs:
            for (Parameter<?> parameter : parameters) {
                boolean editable = isInput && parameter.isInput();
                if (parameter instanceof BooleanParameter) {
                    dialogs.put(parameter, new BooleanDialog(BooleanParameter.of(parameter), editable));
                } else if (parameter instanceof FloatParameter) {
                    dialogs.put(parameter, new FloatDialog(FloatParameter.of(parameter), editable));
                } else if (parameter instanceof IntegerParameter) {
                    dialogs.put(parameter, new IntegerDialog(IntegerParameter.of(parameter), editable));
                } else if (parameter instanceof MultiLineTextParameter) {
                    dialogs.put(parameter, new MultiLineTextDialog(MultiLineTextParameter.of(parameter), editable));
                } else if (parameter instanceof MultipleChoiceParameter) {
                    dialogs.put(parameter, new MultipleChoiceDialog(MultipleChoiceParameter.of(parameter), editable));
                } else if (parameter instanceof SingleChoiceParameter) {
                    dialogs.put(parameter, new SingleChoiceDialog(SingleChoiceParameter.of(parameter), editable));
                } else if (parameter instanceof SingleLineTextParameter) {
                    dialogs.put(parameter, new SingleLineTextDialog(SingleLineTextParameter.of(parameter), editable));
                } else if (parameter instanceof FileParameter) {
                    if (isInput && parameter.isInput()) {
                        dialogs.put(parameter, new InputFileDialog(FileParameter.of(parameter)));
                    } else if (!isInput) {
                        dialogs.put(parameter, new OutputResourceDialog<FileParameter>(FileParameter.of(parameter)));
                    } else {
                        throw new AssertionError();
                    }
                } else if (parameter instanceof ModelParameter) {
                    if (isInput && parameter.isInput()) {
                        dialogs.put(parameter, new InputModelDialog(ModelParameter.of(parameter)));
                    } else if (!isInput) {
                        dialogs.put(parameter, new OutputResourceDialog<ModelParameter>(ModelParameter.of(parameter)));
                    } else {
                        throw new AssertionError();
                    }
                } else if (parameter instanceof ForeignModelParameter) {
                    if (isInput && parameter.isInput()) {
                        dialogs.put(parameter, new InputForeignModelDialog(ForeignModelParameter.of(parameter)));
                    } else if (!isInput) {
                        dialogs.put(parameter,
                                    new OutputResourceDialog<ForeignModelParameter>(ForeignModelParameter.of(parameter)));
                    } else {
                        throw new AssertionError();
                    }
                }
            }
            // Create pages using a simple algorithm:
            int currentSize = 0;
            WizardPage currentPage = null;
            for (Dialog<?> dialog : dialogs.values()) {
                if (currentPage == null) {
                    currentPage = new WizardPage(description);
                    currentSize = 0;
                } else if ((currentSize != 0) && (currentSize + dialog.size() > PAGE_SIZE)) {
                    pages.add(currentPage);
                    currentPage = new WizardPage(description);
                    currentSize = 0;
                }
                currentPage.addDialog(dialog);
                currentSize += dialog.size();
            }
            // Add last page:
            if ((currentPage == null) && (currentSize != 0)) {
                pages.add(currentPage);
            }
        }
    }

    /**
     * Filter and split parameters in logical groups.
     * 
     * @param description
     *        the service description
     * @return the filtered and split parameters
     */
    abstract
        List<Set<Parameter<?>>> splitParameters(Description description);

}
