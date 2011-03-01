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
package fr.lip6.move.coloane.tools.layout;

import fr.lip6.move.coloane.interfaces.exceptions.ServiceException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.requests.IRequest;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.interfaces.objects.result.Result;
import fr.lip6.move.coloane.interfaces.objects.result.SubResult;
import fr.lip6.move.coloane.interfaces.objects.services.IService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * The action of laying out a graph.
 */
public class LayoutAction implements IService {
	/**
	 * {@inheritDoc}
	 */
	public final List<IResult> run(IGraph model, IProgressMonitor monitor)
	throws ServiceException {
		if (model != null) {
			List<IRequest> requests = null;
			try {
				requests = GraphLayout.layout(model, monitor);
			} catch (CoreException e) {
				throw new ServiceException(e.getLocalizedMessage());
			} catch (IOException e) {
				throw new ServiceException(e.getLocalizedMessage());
			}
			List<IResult> results = new ArrayList<IResult>();

			IResult result = new Result("Dot Layout");
			result.addDeltaRequests(requests);

			ISubResult subresult = new SubResult("Statistics about the execution");
			subresult.addTextualResult("Number of nodes :" + model.getNodes().size());
			subresult.addTextualResult("Number of arcs :" + model.getArcs().size());
			result.addSubResult(subresult);

			results.add(result);

			return results;
		}
		return null;
	}
}
