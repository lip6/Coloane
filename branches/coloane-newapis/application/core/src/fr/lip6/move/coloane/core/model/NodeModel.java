package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;

/**
 * Description d'un noeud du modele
 */
public class NodeModel extends AbstractElement implements INode, ILocatedElement {

	/** Identifiant du noeud */
	private int id;

	/** Formalisme associé au noeud */
	private final INodeFormalism nodeFormalism;

	/** Information graphique associé au noeud */
	private final INodeGraphicInfo graphicInfo;

	private EditorGuide horizontalGuide;
	private EditorGuide verticalGuide;

	private List<IArc> outcomingArcs = new ArrayList<IArc>();
	private List<IArc> incomingArcs = new ArrayList<IArc>();

	/**
	 * Constructeur d'un noeud de modèle
	 * @param parent Le parent du noeud
	 * @param nodeFormalism Le formalisme associé au noeud
	 * @param id L'identifiant du noeud dans le modèle
	 */
	NodeModel(IElement parent, INodeFormalism nodeFormalism, int id) {
		super(parent, nodeFormalism.getAttributes());
		this.id = id;
		this.nodeFormalism = nodeFormalism;
		this.graphicInfo = new NodeGraphicInfo(this);
	}

	/**
	 * Supprime les arcs attachés au noeud
	 */
	final void delete() {
		for (IArc arc : outcomingArcs) {
			((NodeModel) arc.getTarget()).removeIncomingArc(arc);
		}
		for (IArc arc : incomingArcs) {
			((NodeModel) arc.getSource()).removeOutcomingArc(arc);
		}
		outcomingArcs.clear();
		incomingArcs.clear();
	}

	/** {@inheritDoc} */
	public final int getId() {
		return id;
	}

	/** {@inheritDoc} */
	public final INodeFormalism getNodeFormalism() {
		return nodeFormalism;
	}

	/** {@inheritDoc} */
	public final INodeGraphicInfo getGraphicInfo() {
		return graphicInfo;
	}

	/** {@inheritDoc} */
	public final ILocationInfo getLocationInfo() {
		return this.getGraphicInfo();
	}

	/**
	 * Ajoute un arc à la liste des arcs sortants.
	 * @param outArc L'arc sortant à ajouter à la liste
	 */
	final void addOutcomingArc(IArc outArc) {
		outcomingArcs.add(outArc);
		firePropertyChange(INode.OUTCOMING_ARCS_PROP, null, outArc);
	}

	/**
	 * Ajoute un arc à la liste des arcs entrants.
	 * @param inArc L'arc à ajouter à la liste
	 */
	final void addIncomingArc(IArc inArc) {
		incomingArcs.add(inArc);
		firePropertyChange(INode.INCOMING_ARCS_PROP, null, inArc);
	}

	/**
	 * Enlève un arc à la liste des arcs sortants.
	 * @param outArc L'arc sortant à supprimer de la liste
	 */
	final void removeOutcomingArc(IArc outArc) {
		outcomingArcs.remove(outArc);
		firePropertyChange(INode.OUTCOMING_ARCS_PROP, null, outArc);
	}

	/**
	 * Enlève un arc à la liste des arcs entrants.
	 * @param inArc L'arc entrant à supprimer de la liste
	 */
	final void removeIncomingArc(IArc inArc) {
		incomingArcs.remove(inArc);
		firePropertyChange(INode.INCOMING_ARCS_PROP, null, inArc);
	}

	/** {@inheritDoc} */
	public final List<IArc> getOutcomingArcs() {
		return Collections.unmodifiableList(outcomingArcs);
	}

	/** {@inheritDoc} */
	public final List<IArc> getIncomingArcs() {
		return Collections.unmodifiableList(incomingArcs);
	}

	/** {@inheritDoc} */
	public final void updateAttributesPosition(int deltaX, int deltaY) {
		Collection<IAttribute> collection = this.getDrawableAttributes();
		for (IAttribute att : collection) {
			Point loc = att.getGraphicInfo().getLocation();
			att.getGraphicInfo().setLocation(new Point(loc.x + deltaX, loc.y + deltaY));
		}
	}

	/** {@inheritDoc} */
	public final void updateArcAttributesPosition() {
		// Parcours des arcs sortants
		for (IArc arc : this.outcomingArcs) {
			arc.updateAttributesPosition();
		}

		// Parcours des arcs entrants
		for (IArc arc : this.incomingArcs) {
			arc.updateAttributesPosition();
		}
	}

	/** {@inheritDoc} */
	public final EditorGuide getHorizontalGuide() {
		return this.horizontalGuide;
	}

	/** {@inheritDoc} */
	public final EditorGuide getVerticalGuide() {
		return this.verticalGuide;
	}

	/** {@inheritDoc} */
	public final void setHorizontalGuide(EditorGuide guide) {
		this.horizontalGuide = guide;
	}

	/** {@inheritDoc} */
	public final void setVerticalGuide(EditorGuide guide) {
		this.verticalGuide = guide;
	}
}
