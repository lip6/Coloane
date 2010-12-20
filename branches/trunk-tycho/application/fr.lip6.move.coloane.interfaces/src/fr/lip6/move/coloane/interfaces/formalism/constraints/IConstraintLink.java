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
