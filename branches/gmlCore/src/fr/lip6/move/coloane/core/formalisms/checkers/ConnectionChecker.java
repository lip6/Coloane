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
import fr.lip6.move.coloane.interfaces.formalism.INodeChecker;
import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * Check that a {@link INode} is connected to at least one arc.<br>
 * 
 * @author Jean-Baptiste Voron
 * @author Florian David
 */
public final class ConnectionChecker implements INodeChecker {

	/**
	 * {@inheritDoc}
	 */
	public ICheckerResult performCheck(INode node) {
		// |Incoming| + |Outgoing| arcs must be > 0
		if (node.getOutgoingArcs().size() + node.getIncomingArcs().size() > 0) {
			return new CheckerResult(false, ""); //$NON-NLS-1$
		} else {
			return new CheckerResult(true, node.getNodeFormalism().getName() + " is disconnected in your model (no arcs to or from)"); //$NON-NLS-1$
		}
	}

}
