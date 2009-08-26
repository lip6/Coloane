package fr.lip6.move.coloane.interfaces.formalism;

import fr.lip6.move.coloane.interfaces.model.IGraph;

/**
 * Define a method which check some conditions defined by the user on an {@link IGraph}.
 * User <b>must only</b> test the graph and <b>must not</b> test anything else (included graph attributes).
 * An empty public constructor <b>must</b> be available.
 * For attributes, see {@link IAttributeChecker}.
 * @author Florian David
 */
public interface IGraphChecker {
	/**
	 * Method implemented by the user who wants to check some conditions on an {@link IGraph}.
	 * @param graph the graph to be checked.
	 * @return <code>true</code> if a marker must be created, <code>false</code> otherwise.
	 */
	public boolean check(IGraph graph);
}
