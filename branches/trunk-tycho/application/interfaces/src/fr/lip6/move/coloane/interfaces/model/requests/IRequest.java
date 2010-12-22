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


/**
 * When a user want to perform changes on the model, he/she has to use {@link IRequest}.<br>
 * Those objects hold the information about the desired change 
 * and will be used by the core project to operate model modifications. 
 *
 * @author Jean-Baptiste Voron
 */
public interface IRequest {
	
	static final int ARC_CREATE_REQUEST = 1;
	static final int ATTRIBUTE_CHANGEVALUE_REQUEST = 2;
	static final int ATTRIBUTE_POSITION_REQUEST = 3;
	static final int ATTRIBUTE_RESET_POSITION_REQUEST = 4;
	static final int INFLEXPOINT_CREATE_REQUEST = 5;
	static final int INFLEXPOINTS_DELETE_REQUEST = 6;
	static final int NODE_CREATE_REQUEST = 7;
	static final int NODE_DELETE_REQUEST = 8;
	static final int NODE_POSITION_REQUEST = 9;
	
	/**
	 * Gives the kind of request... <br>
	 * This method will be used by the core project to transform this request into real commands.<br>
	 * This type is not useful for tools developers.
	 *
	 * @return a RequestType among those defined in {@link IRequest}
	 */
	public int getRequestType();

}
