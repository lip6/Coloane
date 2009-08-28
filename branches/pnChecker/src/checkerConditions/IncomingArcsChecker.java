package checkerConditions;

import fr.lip6.move.coloane.interfaces.formalism.INodeChecker;
import fr.lip6.move.coloane.interfaces.model.INode;

public class IncomingArcsChecker implements INodeChecker {

	public boolean check(INode node) {
		if (node.getIncomingArcs().size() > 2) {
			// S'il y a plus de 2 arcs entrants, on signale une erreur.
			return false;
		}
		// Sinon pas de problème.
		return true;
	}

}
