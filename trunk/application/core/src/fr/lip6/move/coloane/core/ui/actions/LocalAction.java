/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.core.ui.actions;

import fr.lip6.move.coloane.core.session.ISession;
import fr.lip6.move.coloane.core.session.SessionManager;
import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.core.ui.ColoaneJob;
import fr.lip6.move.coloane.core.ui.commands.ApplyRequestsCmd;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.services.IService;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * This class defines an action associated to a local tool.
 *
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class LocalAction extends Action {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The name of the action */
	private final String name;

	/** The description of the action */
	private final String description;

	/** The icon associated to the action */
	private final ImageDescriptor icon;

	/** The effective ColoaneAction */
	private final IService action;

	/**
	 * Constructor
	 * @param name The action name
	 * @param description The action description
	 * @param icon The icon associated to the action
	 * @param action The effective action to be run
	 */
	public LocalAction(String name, String description, ImageDescriptor icon, IService action) {
		this.name = name;
		this.icon = icon;
		this.description = description;
		this.action = action;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getDescription() {
		return this.description;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getToolTipText() {
		return this.description;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getText() {
		return this.name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ImageDescriptor getImageDescriptor() {
		return this.icon;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void run() {
		final ISession currentSession = SessionManager.getInstance().getCurrentSession();
		IGraph currentGraph = currentSession.getGraph();
		LOGGER.fine("Building the external coloane job"); //$NON-NLS-1$		
		ColoaneJob job = new ColoaneJob(this.name, currentGraph, this.action);
		LOGGER.finer("Executing the external coloane job"); //$NON-NLS-1$

		job.addJobChangeListener(new JobChangeAdapter() {
			@Override
	        public void done(IJobChangeEvent event) {
				if (event.getResult().isOK()) {
					List<IResult> results = ((ColoaneJob) event.getJob()).getResults();
					LOGGER.fine("Browsing results..."); //$NON-NLS-1$		
					for (IResult result : results) {
						currentSession.getResultManager().add(description, result);
						// Create a new special command to apply incoming request
						LOGGER.finer("Taking into account all requests for the current graph..."); //$NON-NLS-1$		
						final ApplyRequestsCmd command = new ApplyRequestsCmd(result.getDeltaRequestsList(), currentSession.getGraph());
						Display.getDefault().asyncExec(new Runnable() {
							public void run() {
								LOGGER.finer("Applying the delta command..."); //$NON-NLS-1$		
								((ColoaneEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).executeCommand(command);
							}
						});

					}
				}
			}
		});

		job.setRule(ResourcesPlugin.getWorkspace().getRoot());
		job.schedule();
	}
}
