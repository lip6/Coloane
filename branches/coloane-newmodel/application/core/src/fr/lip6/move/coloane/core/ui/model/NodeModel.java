package fr.lip6.move.coloane.core.ui.model;

import fr.lip6.move.coloane.core.motor.formalisms.elements.Node;
import fr.lip6.move.coloane.core.ui.model.interfaces.IArc;
import fr.lip6.move.coloane.core.ui.model.interfaces.INode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NodeModel extends AbstractElement implements INode {
	private int id;
	private ArrayList<IArc> sourceArcs = new ArrayList<IArc>();
	private ArrayList<IArc> targetArcs = new ArrayList<IArc>();

	NodeModel(Node nodeFormalism, int id) {
		super(nodeFormalism.getAttributes());
		this.id = id;
	}

	final void delete() {
		for (IArc arc : sourceArcs) {
			((NodeModel) arc.getTarget()).removeTargetArc(arc);
		}
		for (IArc arc : targetArcs) {
			((NodeModel) arc.getSource()).removeSourceArc(arc);
		}
	}

	public final int getId() {
		return id;
	}

	final void addSourceArc(IArc sourceArc) {
		sourceArcs.add(sourceArc);
	}

	final void addTargetArc(IArc targetArc) {
		targetArcs.add(targetArc);
	}

	final void removeSourceArc(IArc sourceArc) {
		sourceArcs.remove(sourceArc);
	}

	final void removeTargetArc(IArc targetArc) {
		targetArcs.remove(targetArc);
	}

	public final List<IArc> getSourceArcs() {
		return Collections.unmodifiableList(sourceArcs);
	}

	public final List<IArc> getTargetArcs() {
		return Collections.unmodifiableList(targetArcs);
	}
}
