package fr.lip6.move.coloane.interfaces.model.impl;

import fr.lip6.move.coloane.core.motor.formalisms.elements.Node;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo;
import fr.lip6.move.coloane.interfaces.model.core.ICoreNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;

public class NodeModel extends AbstractElement implements ICoreNode {
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
		sourceArcs.clear();
		targetArcs.clear();
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

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.INode#updateAttributesPosition(int, int)
	 */
	public final void updateAttributesPosition(int deltaX, int deltaY) {
		Collection<IAttribute> collection = this.getDrawableAttributes();
		for (IAttribute att : collection) {
			Point loc = att.getGraphicInfo().getLocation();
			att.getGraphicInfo().setLocation(loc.x + deltaX, loc.y + deltaY);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeImpl#updateArcAttributesPosition()
	 */
	public final void updateArcAttributesPosition() {
		// Parcours des arcs sortants
		for (IArc arc : this.sourceArcs) {
			arc.updateAttributesPosition();
		}

		// Parcours des arcs entrants
		for (IArc arc : this.targetArcs) {
			arc.updateAttributesPosition();
		}
	}
}
