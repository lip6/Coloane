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
package fr.lip6.move.coloane.interfaces.exceptions;

/**
 * Service Exception.<br>
 * This exception is raised when something went wrong during a service execution.<br>
 * Such an exception has :
 * <ul>
 * 	<li>a type</li>
 * 	<li>a message</li>
 * </ul>
 * 
 * @author Jean-Baptiste Voron
 */
public class ServiceException extends Exception {
	
	/** When the problem is global */
	public static final int GENERAL_ERROR = 0;
	/** When the connection to the service provider is not possible */
	public static final int CONNECTION_ERROR = 1;
	/** When the connection is not possible due to an authentication error */
	public static final int AUTHENTICATION_ERROR = 2;
	
	/**
	 * Serialize
	 */
	private static final long serialVersionUID = 1L;

	/** The exception type */
	private int type = -1;
	
	/**
	 * Constructor
	 * @param type The exception type
	 * @param message The message that explains the problem
	 */
	public ServiceException(int type, String message) {
		super(message);
		this.type = type;
	}
	
	/**
	 * @param message The message that explains the problem
	 */
	public ServiceException(String message) {
		super(message);
	}
	
	/**
	 * @return The message that explains the problem
	 */
	public final String getMessage() {
		switch (this.type) {
		case GENERAL_ERROR:
			return "GENERAL ERROR: \n" + super.getMessage();
		case CONNECTION_ERROR:
			return "CONNECTION ERROR: \n" + super.getMessage();
		case AUTHENTICATION_ERROR:
			return "AUTHENTICATION ERROR: \n" + super.getMessage();
		default:
			return "UNKNOWN ERROR : " + super.getMessage();
		}
	}
}
