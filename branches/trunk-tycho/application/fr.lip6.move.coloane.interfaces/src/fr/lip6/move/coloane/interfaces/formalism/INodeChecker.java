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

import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * Define a method that checks some conditions defined by the user about an {@link INode}.
 * User <b>must only</b> tests the node and <b>must not</b> tests anything else (included node attributes).
 * For attributes checking concerns, see {@link IAttributeChecker}.
 *
 * @author Florian David
 */
public interface INodeChecker {
	/**
	 * Method that has to be implemented by the user who wants to check some conditions on an {@link INode}.
	 * @param node the node to be checked.
	 * @return <code>true</code> if a marker must be created, <code>false</code> otherwise.
	 */
	boolean performCheck(INode node);
}
