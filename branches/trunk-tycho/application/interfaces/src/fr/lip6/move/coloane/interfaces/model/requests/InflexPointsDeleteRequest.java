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
package fr.lip6.move.coloane.interfaces.model.requests;

import fr.lip6.move.coloane.interfaces.model.IArc;

/**
 * Ask the core project to remove all inflex points from a given arc.<br>
 *
 * @author Jean-Baptiste Voron
 */
public class InflexPointsDeleteRequest implements IRequest {

	/** The arc for which all inflex points should be removed */
	private IArc arc; 
	
	/**
	 * Constructor
	 */
	public InflexPointsDeleteRequest(IArc arc) {
		this.arc = arc;
	}
	
	/**
	 * @return the arc for which all inflex point should be removed
	 */
	public IArc getArc() {
		return arc;
	}
	
	/** {@inheritDoc} */
	public int getRequestType() {
		return IRequest.INFLEXPOINTS_DELETE_REQUEST;
	}
}
