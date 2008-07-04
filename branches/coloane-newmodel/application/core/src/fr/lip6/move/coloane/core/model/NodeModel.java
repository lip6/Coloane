package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo;
import fr.lip6.move.coloane.interfaces.model.core.ICoreLocationInfo;
import fr.lip6.move.coloane.interfaces.model.core.ICoreNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;

/**
 * Description d'un noeud du modele
 */
public class NodeModel extends AbstractElement implements ICoreNode, ILocatedElement {

	/** Identifiant du noeud */
	private int id;

	/** Formalisme associé au noeud */
	private INodeFormalism nodeFormalism;

	/** Information graphique associé au noeud */
	private INodeGraphicInfo graphicInfo = new NodeGraphicInfo(this);


	private ArrayList<IArc> outcomingArcs = new ArrayList<IArc>();
	private ArrayList<IArc> incomingArcs = new ArrayList<IArc>();

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

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IElement#getId()
	 */
	public final int getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.INode#getNodeFormalism()
	 */
	public final INodeFormalism getNodeFormalism() {
		return nodeFormalism;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.INode#getGraphicInfo()
	 */
	public final INodeGraphicInfo getGraphicInfo() {
		return graphicInfo;
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.model.ILocatedElement#getLocationInfo()
	 */
	public ICoreLocationInfo getLocationInfo() {
		return (ICoreLocationInfo) this.getGraphicInfo();
	}

	/**
	 * Ajoute un arc à la liste des arcs sortants.
	 * @param outArc L'arc sortant à ajouter à la liste
	 */
	final void addOutcomingArc(IArc outArc) {
		outcomingArcs.add(outArc);
	}

	/**
	 * Ajoute un arc à la liste des arcs entrants.
	 * @param inArc L'arc à ajouter à la liste
	 */
	final void addIncomingArc(IArc inArc) {
		incomingArcs.add(inArc);
	}

	/**
	 * Enlève un arc à la liste des arcs sortants.
	 * @param outArc L'arc sortant à supprimer de la liste
	 */
	final void removeOutcomingArc(IArc outArc) {
		outcomingArcs.remove(outArc);
	}

	/**
	 * Enlève un arc à la liste des arcs entrants.
	 * @param inArc L'arc entrant à supprimer de la liste
	 */
	final void removeIncomingArc(IArc inArc) {
		incomingArcs.remove(inArc);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.INode#getSourceArcs()
	 */
	public final List<IArc> getOutcomingArcs() {
		return Collections.unmodifiableList(outcomingArcs);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.INode#getTargetArcs()
	 */
	public final List<IArc> getIncomingArcs() {
		return Collections.unmodifiableList(incomingArcs);
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
		for (IArc arc : this.outcomingArcs) {
			arc.updateAttributesPosition();
		}

		// Parcours des arcs entrants
		for (IArc arc : this.incomingArcs) {
			arc.updateAttributesPosition();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.model.ILocatedElement#getHorizontalGuide()
	 */
	public EditorGuide getHorizontalGuide() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.model.ILocatedElement#getVerticalGuide()
	 */
	public EditorGuide getVerticalGuide() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.model.ILocatedElement#setHorizontalGuide(fr.lip6.move.coloane.core.ui.rulers.EditorGuide)
	 */
	public void setHorizontalGuide(EditorGuide guide) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.model.ILocatedElement#setVerticalGuide(fr.lip6.move.coloane.core.ui.rulers.EditorGuide)
	 */
	public void setVerticalGuide(EditorGuide guide) {
		// TODO Auto-generated method stub
		
	}
}
