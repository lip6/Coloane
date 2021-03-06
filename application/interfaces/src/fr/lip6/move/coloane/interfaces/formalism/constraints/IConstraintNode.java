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
package fr.lip6.move.coloane.interfaces.formalism.constraints;

import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * Node constraint
 * @author Jean-Baptiste Voron
 */
public interface IConstraintNode {

	/**
	 * Is the node constraint verified ?
	 * @param node The node on which the constraint is applied
	 * @return <code>true</code> if the constraint is verified. <code>false</code> otherwise.
	 */
	boolean isSatisfied(INode node);
}
