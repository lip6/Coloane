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
 * Exception that notifies something went wrong inside an extension.<br>
 * Two things are detailed:
 * <ul>
 * 	<li>The plugin name</li>
 * 	<li>The reason of the exception</li>
 * </ul>
 *
 * @author Jean-Baptiste Voron
 */
public class ExtensionException extends Exception {

	/** Serialization stuff */
	private static final long serialVersionUID = 1L;

	/** Message associated to the exception */
	private String message;

	/** Default constructor */
	public ExtensionException() {
		this.message = "Unknown Model Error"; //$NON-NLS-1$
	}

	/**
	 * Constructor
	 * @param message The exception reason
	 */
	public ExtensionException(final String message) {
		this.message = message; //$NON-NLS-1$
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMessage() {
		return this.message;
	}
}
