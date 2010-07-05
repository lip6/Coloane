package checkerConditions;

import fr.lip6.move.coloane.interfaces.formalism.INodeChecker;
import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * Check that a place/transition is connected to something
 *
 * @author Jean-Baptiste Voron
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
