package fr.lip6.move.coloane.core.ui.model;

import fr.lip6.move.coloane.core.motor.formalisms.elements.Node;
import fr.lip6.move.coloane.core.ui.model.interfaces.IArc;
import fr.lip6.move.coloane.core.ui.model.interfaces.IElement;
import fr.lip6.move.coloane.core.ui.model.interfaces.INode;
import fr.lip6.move.coloane.core.ui.model.interfaces.INodeGraphicInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NodeModel extends AbstractElement implements INode {
	private int id;
	private Node nodeFormalism;
	private INodeGraphicInfo graphicInfo = new NodeGraphicInfo(this);

	private ArrayList<IArc> sourceArcs = new ArrayList<IArc>();
	private ArrayList<IArc> targetArcs = new ArrayList<IArc>();

	NodeModel(IElement parent, Node nodeFormalism, int id) {
		super(parent, nodeFormalism.getAttributes());
		this.id = id;
		this.nodeFormalism = nodeFormalism;
	}

	/**
	 * Supprime les arcs attachés.
	 */
	final void delete() {
		for (IArc arc : sourceArcs) {
			((NodeModel) arc.getTarget()).removeTargetArc(arc);
		}
		for (IArc arc : targetArcs) {
			((NodeModel) arc.getSource()).removeSourceArc(arc);
		}
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IElement#getId()
	 */
	public final int getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.INode#getNodeFormalism()
	 */
	public final Node getNodeFormalism() {
		return nodeFormalism;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.INode#getGraphicInfo()
	 */
	public final INodeGraphicInfo getGraphicInfo() {
		return graphicInfo;
	}

	/**
	 * Ajoute un arc à la liste des arcs sortants.
	 * @param sourceArc
	 */
	final void addSourceArc(IArc sourceArc) {
		sourceArcs.add(sourceArc);
	}

	/**
	 * Ajoute un arc à la liste des arcs entrants.
	 * @param targetArc
	 */
	final void addTargetArc(IArc targetArc) {
		targetArcs.add(targetArc);
	}

	/**
	 * Enlève un arc à la liste des arcs sortants.
	 * @param sourceArc
	 */
	final void removeSourceArc(IArc sourceArc) {
		sourceArcs.remove(sourceArc);
	}

	/**
	 * Enlève un arc à la liste des arcs entrants.
	 * @param targetArc
	 */
	final void removeTargetArc(IArc targetArc) {
		targetArcs.remove(targetArc);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.INode#getSourceArcs()
	 */
	public final List<IArc> getSourceArcs() {
		return Collections.unmodifiableList(sourceArcs);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.INode#getTargetArcs()
	 */
	public final List<IArc> getTargetArcs() {
		return Collections.unmodifiableList(targetArcs);
	}
}
