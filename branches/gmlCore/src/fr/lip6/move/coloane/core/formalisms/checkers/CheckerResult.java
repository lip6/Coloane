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
package fr.lip6.move.coloane.core.formalisms.checkers;

import fr.lip6.move.coloane.interfaces.formalism.ICheckerResult;


/**
 * Basic implementation of an @see{ICheckerResult}.
 * @author Yann.
 *
 */
public final class CheckerResult implements ICheckerResult {

	/** 
	 * Verdict, true if the test detected a problem.
	 */
	private boolean isFailed;
	/**
	 * The message explaining the nature of the error.
	 */
	private String message;
	
	/**
	 * Basic ctor : pass verdict and message.
	 * @param isFailed true if test detected an issue
	 * @param message should be "" if (! isFailed).
	 */
	public CheckerResult(boolean isFailed, String message) {
		this.isFailed = isFailed;
		this.message = message;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasFailed() {
		return isFailed;
	}

}
