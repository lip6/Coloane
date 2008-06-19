package fr.lip6.move.coloane.core.copypast.container;

import fr.lip6.move.coloane.core.motor.formalisms.Formalism;
import fr.lip6.move.coloane.core.ui.model.IArcImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Classe conteneur de NodeContainer et d'ArcContainer
 */
public class ModelContainer {
	private final Formalism formalism;

	private final HashMap<Integer, NodeContainer> nodes = new HashMap<Integer, NodeContainer>();
	private final HashSet<ArcContainer> arcs = new HashSet<ArcContainer>();

	/**
	 * @param formalism fomrmalisme du model
	 */
	public ModelContainer(Formalism formalism) {
		this.formalism = formalism;
	}

	/**
	 * @param node
	 */
	public final void addNode(INodeImpl node) {
		nodes.put(node.getId(), new NodeContainer(node));
	}

	/**
	 * @param id
	 * @return
	 */
	public final NodeContainer getNode(int id) {
		return nodes.get(id);
	}

	/**
	 * @return
	 */
	public final Collection<NodeContainer> getNodes() {
		return nodes.values();
	}

	/**
	 * @param arc
	 */
	public final void addArc(IArcImpl arc) {
		arcs.add(new ArcContainer(arc, arc.getSource().getId(), arc.getTarget().getId()));
	}

	/**
	 * @return
	 */
	public final Collection<ArcContainer> getArcs() {
		return arcs;
	}

	/**
	 * @return
	 */
	public final boolean isEmpty() {
		return nodes.isEmpty() && arcs.isEmpty();
	}

	/**
	 * @return
	 */
	public final Formalism getFormalism() {
		return formalism;
	}
}
