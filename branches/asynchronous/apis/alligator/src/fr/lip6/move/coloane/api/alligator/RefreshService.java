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
package fr.lip6.move.coloane.api.alligator;

import fr.lip6.move.coloane.interfaces.api.services.IApiService;
import fr.lip6.move.coloane.interfaces.exceptions.ServiceException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IProgressMonitor;

public final class RefreshService implements IApiService {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

	private Connection alligator;

	public RefreshService(Connection alligator) {
		this.alligator = alligator;
	}

	@Override
	public List<IResult> run(IGraph model, IProgressMonitor monitor) throws ServiceException {
		alligator.wakeUp();
		return Collections.emptyList();
	}

	@Override
	public String getName() {
		return "Refresh menu";
	}

	@Override
	public String getDescription() {
		return "Refreshes this alligator menu";
	}

}
