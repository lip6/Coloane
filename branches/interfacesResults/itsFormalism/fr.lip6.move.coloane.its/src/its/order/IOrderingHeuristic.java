package its.order;

import java.util.List;

import fr.lip6.move.coloane.interfaces.model.IGraph;

public interface IOrderingHeuristic {
	List<Integer> computeOrder (IGraph graph);
}
