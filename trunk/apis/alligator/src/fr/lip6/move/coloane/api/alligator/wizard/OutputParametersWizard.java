/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.wizard;

import fr.lip6.move.coloane.api.alligator.Connection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.cosyverif.alligator.service.Description;
import org.cosyverif.alligator.service.Identifier;
import org.cosyverif.alligator.service.Parameter;
import org.cosyverif.alligator.service.parameter.FileParameter;
import org.cosyverif.alligator.service.parameter.ForeignModelParameter;
import org.cosyverif.alligator.service.parameter.ModelParameter;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

/**
 * Wizard to prepare an invocation of an Alligator Service.
 * 
 * @author Clément Démoulins
 */
public final class OutputParametersWizard
    extends Wizard {

    private List<IWizardPage> pages = new ArrayList<IWizardPage>();
    private final ParametersPage parametersPage;
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
                    parametersPage.setMessage("Service has finished.");
                } else {
                    parametersPage.setMessage("Service is still running.");
                }
                parametersPage.update(connection.getServices()
                                                .getResult(identifier));
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
                schedule(2000);
            }
            return Status.OK_STATUS;
        }

    };

    public OutputParametersWizard(Connection connection, Identifier identifier)
        throws IllegalArgumentException, ExecutionException {
        super();
        this.connection = connection;
        this.identifier = identifier;
        Description service = connection.getServices()
                                        .getDescription(identifier);
        boolean addParametersPage = false;
        Comparator<Parameter<?>> comparator = new Comparator<Parameter<?>>() {
            @Override
            public
                int compare(Parameter<?> lhs, Parameter<?> rhs) {
                return lhs.getName()
                          .compareToIgnoreCase(rhs.getName());
            }
        };
        List<Parameter<?>> parameters = new ArrayList<Parameter<?>>(service.getParameters());
        Collections.sort(parameters, comparator);
        for (Parameter<?> parameter : parameters) {
            if (parameter instanceof ModelParameter) {
            } else if (parameter instanceof ForeignModelParameter) {
            } else if (parameter instanceof FileParameter) {
            } else {
                addParametersPage = true;
            }
        }
        if (addParametersPage) {
            parametersPage = new ParametersPage(service, false);
            pages.add(parametersPage);
        } else {
            parametersPage = null;
        }
        for (IWizardPage page : pages) {
            this.addPage(page);
        }
        updater.schedule();
    }

    @Override
    public
        boolean performFinish() {
        for (IWizardPage page : pages) {
            if (page instanceof SelectResourcePage) {
                ((SelectResourcePage) page).performFinish();
            } else if (page instanceof ParametersPage) {
                ((ParametersPage) page).performFinish();
            }
        }
        updater.cancel();
        return true;
    }

    @Override
    public
        boolean performCancel() {
        updater.cancel();
        return true;
    }

}
