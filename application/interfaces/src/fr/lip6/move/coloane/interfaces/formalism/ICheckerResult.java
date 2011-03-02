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

package fr.lip6.move.coloane.interfaces.formalism;


/**
 * The result of running an Attribute/Node/Arc/Graph checker.
 * A small data structure bearing a boolean (pass or fail) and a message to display.
 * 
 * @author Yann Thierry-Mieg
 *
 */
public interface ICheckerResult {

	/**
	 * The string representing the informative message to be displayed.
	 * @return an error message or "" if (!hasFailed()).
	 */
	String getMessage();
	
	/**
	 * The verdict : hasFailed is true if the check detected a problem.
	 * @return true if the check has detected an error.
	 */
	boolean hasFailed();
}
