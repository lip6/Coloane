package fr.lip6.move.coloane.core.formalisms.checkers;

import fr.lip6.move.coloane.interfaces.formalism.INodeChecker;
import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * Check that a {@link INode} is connected to at least one arc.<br>
 *
 * @author Jean-Baptiste Voron
 * @author Florian David
 */
public class ConnectionChecker implements INodeChecker {

	/**
	 * {@inheritDoc}
	 */
	public boolean performCheck(INode node) {
		// |Incoming| + |Outgoing| arcs must be > 0
		return (node.getOutgoingArcs().size() + node.getIncomingArcs().size()) > 0;
	}

}
