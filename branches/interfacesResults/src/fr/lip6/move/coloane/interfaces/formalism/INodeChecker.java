package fr.lip6.move.coloane.interfaces.formalism;

import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * Define a method which check some conditions defined by the user on an {@link INode}.
 * User <b>must only</b> test the node and <b>must not</b> test anything else (included node attributes).
 * An empty public constructor <b>must</b> be available.
 * For attributes, see {@link IAttributeChecker}.
 * @author Florian David
 */
public interface INodeChecker {
	/**
	 * Method implemented by the user who wants to check some conditions on an {@link INode}.
	 * @param node the node to be checked.
	 * @return <code>true</code> if a marker must be created, <code>false</code> otherwise.
	 */
	public boolean check(INode node);
}
