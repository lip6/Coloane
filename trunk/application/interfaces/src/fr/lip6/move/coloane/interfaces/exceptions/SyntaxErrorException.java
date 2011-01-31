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
 * Syntax Error Exception
 * <ul>
 *  <li>Erreur de syntaxe dans la reception ou l'envoi de commandes a la plateforme</li>
 *  <li>Erreur lors de la construction du modele</li>
 * </ul>
 */
public class SyntaxErrorException extends Exception {

	/** Pour la conformite */
	private static final long serialVersionUID = 1L;

	/** Message associee a l'exception */
	private String message;

	/** Constructeur */
	public SyntaxErrorException() {
		this.message = "Unknown Syntax Error";
	}

	/**
	 * Construteur
	 * Ce construteur permet d'associer un message d'erreur a l'exception qu'on lève
	 * @param message Message de détails
	 */
	public SyntaxErrorException(final String message) {
		this.message = "Syntax Error : " + message;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMessage() {
		return this.message;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String toString() {
		return this.message;
	}
}
