package fr.lip6.move.coloane.core.copypast;

import fr.lip6.move.coloane.core.motor.formalism.Formalism;
import fr.lip6.move.coloane.core.ui.model.IArcImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class ModelContainer {
	private final Formalism formalism;

	private final HashMap<Integer, NodeContainer> nodes = new HashMap<Integer, NodeContainer>();
	private final HashSet<ArcContainer> arcs = new HashSet<ArcContainer>();

	public ModelContainer(Formalism formalism) {
		this.formalism = formalism;
	}

	public final void addNode(INodeImpl node) {
		nodes.put(node.getId(), new NodeContainer(node));
	}

	public final NodeContainer getNode(int id) {
		return nodes.get(id);
	}

	public final Collection<NodeContainer> getNodes() {
		return nodes.values();
	}

	public final void addArc(IArcImpl arc) {
		arcs.add(new ArcContainer(arc, arc.getSource().getId(), arc.getTarget().getId()));
	}

	public final Collection<ArcContainer> getArcs() {
		return arcs;
	}

	public final boolean isEmpty() {
		return nodes.isEmpty() && arcs.isEmpty();
	}

	public final Formalism getFormalism() {
		return formalism;
	}
}
