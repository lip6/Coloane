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
import fr.lip6.move.coloane.api.alligator.dialog.OutputFileDialog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import org.cosyverif.alligator.service.Description;
import org.cosyverif.alligator.service.Identifier;
import org.cosyverif.alligator.service.Parameter;
import org.cosyverif.alligator.service.parameter.FileParameter;
import org.cosyverif.alligator.service.parameter.ModelParameter;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;

/**
 * Wizard to show the results of an Alligator service.
 */
public final class OutputWizard
    extends Wizard {

    /** Logger */
    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$
    private static int DELAY = 5;

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
                                               .getCurrentState(identifier);
                for (Dialog<?> dialog : dialogs) {
                    for (Parameter<?> parameter : result.getParameters()) {
                        if (parameter.equalsUnset(dialog.getParameter())) {
                            try {
                                dialog.update(parameter);
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            } catch (Exception e) {
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
                LOGGER.info("Scheduling updater...");
                schedule(DELAY * 1000); // TODO
            }
            return Status.OK_STATUS;
        }

    };

    public OutputWizard(Connection connection, Identifier identifier)
        throws IllegalArgumentException, ExecutionException {
        super(connection.getServices()
                        .getCurrentState(identifier), false);
        this.connection = connection;
        this.identifier = identifier;
        Set<Parameter<?>> parameters = SOURCES.get(identifier);
        if (parameters == null) {
            parameters = new HashSet<Parameter<?>>();
            SOURCES.put(identifier, parameters);
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
        if (!outputs.isEmpty()) {
            result.add(outputs);
        }
        if (!inputs.isEmpty()) {
            result.add(inputs);
        }
        return result;
    }

}
