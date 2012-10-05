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

import fr.lip6.move.coloane.interfaces.model.IArc;

/**
 * Define a method which checks some conditions defined by the user about an {@link IArc}.<br>
 * User <b>must only</b> tests the arc and <b>must not</b> tests anything else (included arc attributes).<br>
 * For attributes checking concerns, see {@link IAttributeChecker}.
 *
 * @author Florian David
 */
public interface IArcChecker {
	/**
	 * Method that has to be implemented by the user who wants to check some conditions on an {@link IArc}.
	 * @param arc the arc to be checked.
	 * @return a checker result with <code>hasFailed()</code> if a marker must be created.
	 */
	ICheckerResult performCheck(IArc arc);
}
