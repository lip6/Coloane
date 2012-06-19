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
package fr.lip6.move.coloane.api.alligator.dialog;

import fr.lip6.move.coloane.interfaces.exceptions.ServiceException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.Result;
import fr.lip6.move.coloane.interfaces.objects.result.SubResult;
import fr.lip6.move.coloane.interfaces.objects.services.IService;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

public class ServiceTestDialog implements IService {

	public ServiceTestDialog() {
	}

	public List<IResult> run(final IGraph model, IProgressMonitor monitor) throws ServiceException {
		List<IResult> results = new ArrayList<IResult>();
		IResult root = new Result("test");
		IArc arc = model.getArcs().iterator().next();
		root.addSubResult(new SubResult(arc.getId() + ""));
		root.addSubResult(new SubResult(arc.getSource().getId() + ""));
		root.addSubResult(new SubResult(arc.getTarget().getId() + ""));
		results.add(root);
		return results;
	}

}
