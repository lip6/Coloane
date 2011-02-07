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
package fr.lip6.move.coloane.interfaces.objects.services;

import fr.lip6.move.coloane.interfaces.exceptions.ServiceException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Interface that must be implemented by a <b>local tool</b> provider.
 *
 * @author Jean-Baptiste Voron
 */
public interface IService {

	/**
	 * Run the service !
	 * @param model The current session graph model
	 * @param monitor The progress monitor used to visualize the job progress
	 * @return A set of commands that will be executed by the core
	 * @throws ServiceException In case of exception during execution of the service
	 */
	List<IResult> run(IGraph model, IProgressMonitor monitor) throws ServiceException;
}
