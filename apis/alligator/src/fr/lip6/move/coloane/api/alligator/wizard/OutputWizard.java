/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.wizard;

import fr.lip6.move.coloane.api.alligator.Connection;
import fr.lip6.move.coloane.api.alligator.dialog.Dialog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.cosyverif.alligator.service.Description;
import org.cosyverif.alligator.service.Identifier;
import org.cosyverif.alligator.service.Parameter;
import org.cosyverif.alligator.service.parameter.ResourceParameter;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * Wizard to show the results of an Alligator service.
 */
public final class OutputWizard
    extends Wizard {

    private static Map<Identifier, Set<Parameter<?>>> SOURCES = new HashMap<Identifier, Set<Parameter<?>>>();

    private final Connection connection;
    private final Identifier identifier;

    private class GraphicsRunner
        implements Runnable {

        @Override
        public
            void run() {
            try {
                if (connection.getServices()
                              .isFinished(identifier)) {
                    for (WizardPage page : pages) {
                        page.setMessage("Service has finished.");
                    }
                } else {
                    for (WizardPage page : pages) {
                        page.setMessage("Service is still running.");
                    }
                }
                Description result = connection.getServices()
                                               .getResult(identifier);
                for (Dialog<?> dialog : dialogs.values()) {
                    for (Parameter<?> parameter : result.getParameters()) {
                        if (parameter.cloneUnset()
                                     .equals(dialog.getParameter()
                                                   .cloneUnset())) {
                            dialog.update(parameter);
                        }
                    }
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    private Job updater = new Job("Update results") {

        private GraphicsRunner runner = new GraphicsRunner();

        @Override
        protected
            IStatus run(IProgressMonitor monitor) {
            if (monitor.isCanceled()) {
                return Status.CANCEL_STATUS;
            }
            Display.getDefault()
                   .syncExec(runner);
            if (!connection.getServices()
                           .isFinished(identifier)) {
                schedule(2000); // TODO
            }
            return Status.OK_STATUS;
        }

    };

    public OutputWizard(Connection connection, Identifier identifier)
        throws IllegalArgumentException, ExecutionException {
        super(connection.getServices()
                        .getDescription(identifier), false);
        this.connection = connection;
        this.identifier = identifier;
        Set<Parameter<?>> parameters = SOURCES.get(identifier);
        if (parameters == null) {
            parameters = new HashSet<Parameter<?>>();
            SOURCES.put(identifier, parameters);
        }
        for (Parameter<?> parameter : description.getParameters()) {
            if (parameter.isOutput() && (parameter instanceof ResourceParameter)) {
                ResourceParameter<?, ?> theParameter = ResourceParameter.of(parameter);
                for (Parameter<?> p : parameters) {
                    if (parameter.cloneUnset()
                                 .equals(p.cloneUnset())) {
                        theParameter.setSource(ResourceParameter.of(p)
                                                                .getSource());
                        break;
                    }
                }
                try {
                    theParameter.getSource();
                } catch (IllegalArgumentException e) {
                    Display display = new Display();
                    Shell shell = new Shell(display);
                    shell.open();
                    FileDialog dialog = new FileDialog(shell, SWT.SAVE);
                    dialog.setText("Select resource to save parameter " + parameter.getName() + ". " + parameter.getHelp());
                    dialog.setOverwrite(true);
                    dialog.open();
                    theParameter.setSource(new File(dialog.getFileName()));
                    parameters.add(theParameter);
                    display.dispose();
                }
            }
        }
        updater.schedule();
    }

    @Override
    public
        boolean performFinish() {
        updater.cancel();
        return true;
    }

    @Override
    public
        boolean performCancel() {
        updater.cancel();
        return true;
    }

    @Override
        List<Set<Parameter<?>>> splitParameters(Description description) {
        Set<Parameter<?>> inputs = new HashSet<Parameter<?>>();
        Set<Parameter<?>> outputs = new HashSet<Parameter<?>>();
        for (Parameter<?> parameter : description.getParameters()) {
            if (parameter.isOutput()) {
                outputs.add(parameter);
            } else {
                inputs.add(parameter);
            }
        }
        List<Set<Parameter<?>>> result = new ArrayList<Set<Parameter<?>>>();
        result.add(inputs);
        result.add(outputs);
        return result;
    }

}
