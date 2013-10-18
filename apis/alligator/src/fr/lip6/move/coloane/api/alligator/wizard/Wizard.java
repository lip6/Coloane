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
import fr.lip6.move.coloane.api.alligator.dialog.ImageDialog;
import fr.lip6.move.coloane.api.alligator.dialog.InputFileDialog;
import fr.lip6.move.coloane.api.alligator.dialog.InputModelDialog;
import fr.lip6.move.coloane.api.alligator.dialog.InputModelSetDialog;
import fr.lip6.move.coloane.api.alligator.dialog.IntegerDialog;
import fr.lip6.move.coloane.api.alligator.dialog.MultipleChoiceDialog;
import fr.lip6.move.coloane.api.alligator.dialog.MultipleLineTextDialog;
import fr.lip6.move.coloane.api.alligator.dialog.OutputFileDialog;
import fr.lip6.move.coloane.api.alligator.dialog.OutputModelDialog;
import fr.lip6.move.coloane.api.alligator.dialog.SingleChoiceDialog;
import fr.lip6.move.coloane.api.alligator.dialog.SingleLineTextDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

import org.cosyverif.alligator.service.Description;
import org.cosyverif.alligator.service.Parameter;
import org.cosyverif.alligator.service.parameter.BooleanParameter;
import org.cosyverif.alligator.service.parameter.FileParameter;
import org.cosyverif.alligator.service.parameter.FloatParameter;
import org.cosyverif.alligator.service.parameter.IntegerParameter;
import org.cosyverif.alligator.service.parameter.ModelParameter;
import org.cosyverif.alligator.service.parameter.ModelSetParameter;
import org.cosyverif.alligator.service.parameter.MultipleChoiceParameter;
import org.cosyverif.alligator.service.parameter.MultipleLineTextParameter;
import org.cosyverif.alligator.service.parameter.SingleChoiceParameter;
import org.cosyverif.alligator.service.parameter.SingleLineTextParameter;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

public abstract class Wizard
    extends org.eclipse.jface.wizard.Wizard
    implements Runnable {

    public static int PAGE_SIZE = 15;

    /** Logger */
    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

    public List<Dialog<?>> dialogs = new ArrayList<Dialog<?>>();

    public List<WizardPage> pages = new ArrayList<WizardPage>();

    protected Description description;

    protected Map<Parameter<?>, IFile[]> files = new HashMap<Parameter<?>, IFile[]>();

    protected boolean isCanceled;

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
            List<Dialog<?>> newDialogs = new ArrayList<Dialog<?>>();
            for (Parameter<?> parameter : parameters) {
                LOGGER.info("Creating dialog for " + parameter.getName() + "...");
                boolean editable = isInput && parameter.isInput();
                Dialog<?> newDialog;
                if (parameter instanceof BooleanParameter) {
                    newDialog = new BooleanDialog(BooleanParameter.of(parameter));
                } else if (parameter instanceof FloatParameter) {
                    newDialog = new FloatDialog(FloatParameter.of(parameter));
                } else if (parameter instanceof IntegerParameter) {
                    newDialog = new IntegerDialog(IntegerParameter.of(parameter));
                } else if (parameter instanceof MultipleLineTextParameter) {
                    newDialog = new MultipleLineTextDialog(MultipleLineTextParameter.of(parameter));
                } else if (parameter instanceof MultipleChoiceParameter) {
                    newDialog = new MultipleChoiceDialog(MultipleChoiceParameter.of(parameter));
                } else if (parameter instanceof SingleChoiceParameter) {
                    newDialog = new SingleChoiceDialog(SingleChoiceParameter.of(parameter));
                } else if (parameter instanceof SingleLineTextParameter) {
                    newDialog = new SingleLineTextDialog(SingleLineTextParameter.of(parameter));
                } else if (parameter instanceof FileParameter && isInput) {
                    FileParameter p = FileParameter.of(parameter);
                    newDialog = new InputFileDialog(p);
                } else if (parameter instanceof FileParameter && parameter.isOutput()) {
                    FileParameter p = FileParameter.of(parameter);
                    if (p.getContentType()
                         .startsWith("image/")) {
                        newDialog = new ImageDialog(p);
                    } else {
                        newDialog = new OutputFileDialog(p);
                    }
                } else if (parameter instanceof ModelParameter && isInput) {
                    newDialog = new InputModelDialog(ModelParameter.of(parameter));
                } else if (parameter instanceof ModelSetParameter && isInput) {
                    newDialog = new InputModelSetDialog(ModelSetParameter.of(parameter));
                } else if (parameter instanceof ModelParameter && parameter.isOutput()) {
                    ModelParameter p = ModelParameter.of(parameter);
                    newDialog = new OutputModelDialog(p);
                } else {
                    LOGGER.warning("Cannot find control for " + parameter + "!");
                    continue;
                }
                newDialog.setEditable(editable);
                newDialogs.add(newDialog);
            }
            dialogs.addAll(newDialogs);
            // Create pages using a simple algorithm:
            List<WizardPage> newPages = new ArrayList<WizardPage>();
            for (Dialog<?> dialog : newDialogs) {
                LOGGER.info("Adding dialog for parameter " + dialog.getParameter()
                                                                   .getName() + " to a page...");
                boolean added = false;
                for (WizardPage page : newPages) {
                    if (page.size() + dialog.size() <= PAGE_SIZE) {
                        dialog.setPage(page);
                        page.addDialog(dialog);
                        added = true;
                        break;
                    }
                }
                if (!added) {
                    WizardPage newPage = new WizardPage(this, description);
                    dialog.setPage(newPage);
                    newPage.addDialog(dialog);
                    newPages.add(newPage);
                }
            }
            pages.addAll(newPages);
        }
        LOGGER.info("Adding all wizard pages to the wizard...");
        for (WizardPage page : pages) {
            addPage(page);
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

    @Override
    public final
        void run() {
        this.setNeedsProgressMonitor(false);
        this.setHelpAvailable(false);
        this.setHelpAvailable(false);
        this.setWindowTitle("Parameters for the " + description.name() + " service");
        WizardDialog dialog = new WizardDialog(Display.getDefault()
                                                      .getActiveShell(), this);
        dialog.setBlockOnOpen(true);
        int result = dialog.open();
        if (result == Window.OK) {
            isCanceled = false;
        } else if (result == Window.CANCEL) {
            isCanceled = true;
        } else {
            throw new AssertionError();
        }
    }

    public
        IFile[] filesFor(Parameter<?> parameter) {
        for (Entry<Parameter<?>, IFile[]> e : files.entrySet()) {
            if (e.getKey()
                 .equalsUnset(parameter)) {
                return e.getValue();
            }
        }
        throw new IllegalArgumentException();
    }

    public
        boolean isCanceled() {
        return isCanceled;
    }

}
