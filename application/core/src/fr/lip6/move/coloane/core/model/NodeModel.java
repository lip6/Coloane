package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Description d'un noeud du modele
 */
public class NodeModel extends AbstractElement implements INode, ILocatedElement, ILinkableElement {
	/** Logger 'fr.lip6.move.coloane.core'. */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Formalisme associé au noeud */
	private final INodeFormalism nodeFormalism;

	/** Information graphique associé au noeud */
	private final INodeGraphicInfo graphicInfo;

	private EditorGuide horizontalGuide;
	private EditorGuide verticalGuide;

	private List<IArc> outcomingArcs = new ArrayList<IArc>();
	private List<IArc> incomingArcs = new ArrayList<IArc>();

	/** Liste des liens */
	private List<ILink> links = new ArrayList<ILink>();

	/**
	 * Constructeur d'un noeud de modèle
	 * @param parent Le parent du noeud
	 * @param nodeFormalism Le formalisme associé au noeud
	 * @param id L'identifiant du noeud dans le modèle
	 */
	NodeModel(IElement parent, INodeFormalism nodeFormalism, int id) {
		super(id, parent, nodeFormalism.getAttributes());
		LOGGER.finest("Création d'un NodeModel(" + nodeFormalism.getName() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		this.nodeFormalism = nodeFormalism;
		this.graphicInfo = new NodeGraphicInfo(this);
	}

	/**
	 * Supprime les arcs et les liens attachés au noeud
	 */
	final void delete() {
		LOGGER.finest("delete(" + getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		for (IArc arc : outcomingArcs) {
			((NodeModel) arc.getTarget()).removeIncomingArc(arc);
			((ArcModel) arc).delete();
		}
		for (IArc arc : incomingArcs) {
			((NodeModel) arc.getSource()).removeOutcomingArc(arc);
			((ArcModel) arc).delete();
		}
		for (ILink link : links) {
			link.getSource().removeLink(link);
		}
		outcomingArcs.clear();
		incomingArcs.clear();
		links.clear();
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
		LOGGER.finest("addOutcomingArc(" + outArc.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		outcomingArcs.add(outArc);
		firePropertyChange(INode.OUTCOMING_ARCS_PROP, null, outArc);
	}

	/**
	 * Ajoute un arc à la liste des arcs entrants.
	 * @param inArc L'arc à ajouter à la liste
	 */
	final void addIncomingArc(IArc inArc) {
		LOGGER.finest("addIncomingArc(" + inArc.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		incomingArcs.add(inArc);
		firePropertyChange(INode.INCOMING_ARCS_PROP, null, inArc);
	}

	/**
	 * Enlève un arc à la liste des arcs sortants.
	 * @param outArc L'arc sortant à supprimer de la liste
	 */
	final void removeOutcomingArc(IArc outArc) {
		LOGGER.finest("removeOutcomingArc(" + outArc.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		outcomingArcs.remove(outArc);
		firePropertyChange(INode.OUTCOMING_ARCS_PROP, null, outArc);
	}

	/**
	 * Enlève un arc à la liste des arcs entrants.
	 * @param inArc L'arc entrant à supprimer de la liste
	 */
	final void removeIncomingArc(IArc inArc) {
		LOGGER.finest("removeIncomingArc(" + inArc.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
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

	/** {@inheritDoc} */
	public final void updateTips() {
		firePropertyChange(INCOMING_ARCS_PROP, null, null);
	}

	/** {@inheritDoc} */
	public final void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();

		if (IAttribute.VALUE_PROP.equals(prop)) {
			// On propage les changements de valeur des attributs au niveau supérieur
			firePropertyChange(prop, evt.getOldValue(), evt.getNewValue());
		}
	}

	/** {@inheritDoc} */
	public final void addLink(ILink link) {
		links.add(link);
		firePropertyChange(INCOMING_ARCS_PROP, null, link);
	}

	/** {@inheritDoc} */
	public final List<ILink> getLinks() {
		return Collections.unmodifiableList(links);
	}

	/** {@inheritDoc} */
	public final boolean removeLink(ILink link) {
		boolean res = links.remove(link);
		firePropertyChange(INCOMING_ARCS_PROP, null, link);
		return res;
	}

	/** {@inheritDoc} */
	@Override
	public final String toString() {
		IAttribute attrName = getAttribute("name"); //$NON-NLS-1$
		String name = ""; //$NON-NLS-1$
		if (attrName != null) {
			name = attrName.getValue();
		}
		return "Node(" + getId() + ", " + name + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
}
