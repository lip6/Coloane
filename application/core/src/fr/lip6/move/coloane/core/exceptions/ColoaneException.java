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
package fr.lip6.move.coloane.core.exceptions;

/**
 * Main exception used in case of unrecoverable error. 
 */
public class ColoaneException extends Exception {
	/** Serialize */
	private static final long serialVersionUID = 1L;

	/** Information message about the exception */
	private String message;

	/**
	 * Constructor
	 * @param message the information message about the exception
	 */
	public ColoaneException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * @return Return the error message
	 */
	@Override
	public final String getMessage() {
		return message;
	}

	/**
	 * @return Return the error message with an header
	 */
	public final String getLogMessage() {
		return Messages.ColoaneException_0 + message;
	}
}
