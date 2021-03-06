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

import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * Link constraint.<br>
 * @author Jean-Baptiste Voron
 */
public interface IConstraintLink {

	/**
	 * Is the link creation allowed ?
	 * @param source The source node
	 * @param target The target node
	 * @param arcFormalism The arc formalism
	 * @return <code>true</code> if the connection is allowed. <code>false</code> otherwise.
	 */
	boolean isSatisfied(INode source, INode target, IArcFormalism arcFormalism);
}
