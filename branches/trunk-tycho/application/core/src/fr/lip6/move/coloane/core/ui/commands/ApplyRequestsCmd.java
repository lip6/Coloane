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
package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.results.CommandFactory;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.requests.IRequest;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

import java.util.List;

import org.eclipse.gef.commands.CompoundCommand;

/**
 * Command that handles requests coming from a tool result
 * 
 * @author Jean-Baptiste Voron
 * 
 * @see IRequest
 * @see IResult
 */

public class ApplyRequestsCmd extends CompoundCommand {
	/** List of request to apply on the graph */
	List<IRequest> requests;
	
	/** The graph on which requests will be applied */
	IGraph graph;
	
	/**
	 * Constructor
	 * @param requests The list of requests to be applied on the graph
	 * @param graph The graph on which requests will be applied
	 */
	public ApplyRequestsCmd(List<IRequest> requests, IGraph graph) {
		this.requests = requests;
		this.graph = graph;

		for (IRequest request : this.requests) {
			if (request == null) { continue; }
			// Transform the request into a command...
			add(CommandFactory.createCommand(request, this.graph));
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canExecute() {
		return (this.requests.size() > 0);
	}
}
