package fr.lip6.move.coloane.core.ui.checkerCondition;

import fr.lip6.move.coloane.interfaces.formalism.INodeChecker;
import fr.lip6.move.coloane.interfaces.model.INode;

public class CheckerNode implements INodeChecker {

	public boolean check(INode node) {
		if (node.getIncomingArcs().size() == 0)
			return false;
		return true;
	}

}
