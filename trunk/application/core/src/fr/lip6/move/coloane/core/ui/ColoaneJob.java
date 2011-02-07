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
package fr.lip6.move.coloane.core.ui;

import fr.lip6.move.coloane.core.ui.actions.LocalAction;
import fr.lip6.move.coloane.interfaces.exceptions.ServiceException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.services.IService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * Jobs are action containers.<br>
 * To use with {@link LocalAction}
 *
 * @author Jean-Baptiste Voron
 */
public class ColoaneJob extends Job {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	
	/** The set of results */
	List<IResult> results;
	
	/** The action */
	IService action;
	
	/** The graph on which the action has to be performed */
	IGraph graph;

	/**
	 * Constructor
	 * @param name The name of the action
	 * @param graph The graph on which the action will be performed
	 * @param actionToPerform The action to run
	 * @see Job
	 */
	public ColoaneJob(String name, IGraph graph, IService actionToPerform) {
		super(name);
		setSystem(false);
		this.results = new ArrayList<IResult>();
		this.action = actionToPerform;
		this.graph = graph;
	}
	
	/**
	 * @return The result list after the job execution
	 */
	public List<IResult> getResults() {
		return this.results;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			this.results = this.action.run(this.graph,monitor);
			if (this.results == null) {
				return Status.CANCEL_STATUS;
			}
			return Status.OK_STATUS;
		} catch (ServiceException e) {
			LOGGER.warning(e.getMessage());
			return new Status(Status.ERROR, "fr.lip6.move.coloane.core", e.getMessage()); //$NON-NLS-1$
		}
	}

	/** {@inheritDoc} */
	@Override
	protected final void canceling() {
		LOGGER.warning("The job " + this.getName() + " has received an INTERRUPT request : "); //$NON-NLS-1$//$NON-NLS-2$
		getThread().interrupt();
		super.canceling();
	}
}
