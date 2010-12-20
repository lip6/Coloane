package fr.lip6.move.coloane.core.copypast.container;

import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class that contains node container and arcs container.<br>
 * This class is created when a copy action is performed by the user.
 * 
 * @author Clément Démoulins
 * @author Jean-Baptiste Voron
 * 
 * @see NodeContainer
 * @see ArcContainer
 */
public class GraphContainer {
	/** The formalism used by the graph */
	private final IFormalism formalism;

	/** A map of node container used to duplicate nodes */
	private final Map<Integer, NodeContainer> nodes = new HashMap<Integer, NodeContainer>();
	/** A set of arc container used to duplicate arcs */
	private final Set<ArcContainer> arcs = new HashSet<ArcContainer>();

	/**
	 * Constructor
	 * @param formalism The formalism used by the graph
	 */
	public GraphContainer(IFormalism formalism) {
		this.formalism = formalism;
	}

	/**
	 * Add node to the map of copied nodes
	 * @param node The node to add to the map
	 */
	public final void addNode(INode node) {
		nodes.put(node.getId(), new NodeContainer(node));
	}

	/**
	 * Fetch a node from the map of copied node
	 * @param id The ID of the desired node
	 * @return The node container or <code>null</code> if it does not exist
	 */
	public final NodeContainer getNode(int id) {
		return nodes.get(id);
	}

	/**
	 * @return All copied nodes
	 */
	public final Collection<NodeContainer> getNodes() {
		return nodes.values();
	}

	/**
	 * Add an arc to the set of copied arcs
	 * @param arc The copied arc
	 */
	public final void addArc(IArc arc) {
		arcs.add(new ArcContainer(arc));
	}

	/**
	 * @return All copied arcs
	 */
	public final Collection<ArcContainer> getArcs() {
		return arcs;
	}

	/**
	 * @return <code>true</code> if there is no copied nodes and copied arcs; <code>false</code> otherwise
	 */
	public final boolean isEmpty() {
		return nodes.isEmpty() && arcs.isEmpty();
	}

	/**
	 * @return The formalism used by the graph
	 */
	public final IFormalism getFormalism() {
		return formalism;
	}
}
