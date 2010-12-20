package fr.lip6.move.coloane.interfaces.formalism;

import fr.lip6.move.coloane.interfaces.model.IGraph;

/**
 * Define a method that checks some conditions defined by the user about an {@link IGraph}.
 * User <b>must only</b> tests the graph and <b>must not</b> tests anything else (included graph attributes).
 * For attributes checking concerns, see {@link IAttributeChecker}.
 *
 * @author Florian David
 */
public interface IGraphChecker {
	/**
	 * Method that has to be implemented by the user who wants to check some conditions on an {@link IGraph}.
	 * @param graph the graph to be checked.
	 * @return <code>true</code> if a marker must be created, <code>false</code> otherwise.
	 */
	boolean performCheck(IGraph graph);
}
