package fr.lip6.move.coloane.core.formalisms.constraints;

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
