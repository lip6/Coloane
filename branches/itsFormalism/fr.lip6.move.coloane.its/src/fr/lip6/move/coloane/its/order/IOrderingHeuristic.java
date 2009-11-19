package fr.lip6.move.coloane.its.order;

import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.util.List;

/**
 * An interface for a BDD reordering heuristic.
 * @author Yann
 *
 */
public interface IOrderingHeuristic {
	/**
	 * Compute an order of the variables in the graph, return it as a set of integer id.
	 * @param graph the graph to test. Verify formalism type corresponds to chosen heuristic !!
	 * @return the new order
	 */
	List<Integer> computeOrder(IGraph graph);
}
