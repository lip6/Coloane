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
 * Model Exception<br>
 * Exception levée lors de la manipulation du modèle :
 * <ul>
 * 	<li>Ajout</li>
 *  <li>Modification</li>
 *  <li>Suppression</li>
 *  <li>Déplacement</li>
 *  <li>etc...</li>
 * </ul>
 */
public class ModelException extends Exception {

	/** Pour la conformité */
	private static final long serialVersionUID = 1L;

	/** Message associee a l'exception */
	private String message;

	/** Constructeur */
	public ModelException() {
		this.message = "Unknown Model Error"; //$NON-NLS-1$
	}

	/**
	 * Consutructeur de l'exception
	 * @param message Information sur l'exception
	 */
	public ModelException(final String message) {
		this.message = "Model Error : " + message; //$NON-NLS-1$
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getMessage() {
		return this.message;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String toString() {
		return message;
	}
}
