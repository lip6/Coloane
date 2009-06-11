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
 * This class defines a node according to model considerations.
 *
 * @author Jean-Baptiste Voron
 */
public class NodeModel extends AbstractElement implements INode, ILocatedElement, ILinkableElement {
	/** Core Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The node formalism */
	private final INodeFormalism nodeFormalism;

	/** Graphical information associated to the node */
	private final INodeGraphicInfo graphicInfos;

	/** Guides where the node is stuck */
	private EditorGuide horizontalGuide;
	private EditorGuide verticalGuide;

	/** List of incoming and outgoing arcs */
	private List<IArc> outgoingArcs = new ArrayList<IArc>();
	private List<IArc> incomingArcs = new ArrayList<IArc>();

	/** Links (comments) associated to this node */
	private List<ILink> links = new ArrayList<ILink>();

	private boolean publicState = false;

	/**
	 * Constructor
	 * @param parent The parent of this node (often the graph itself)
	 * @param nodeFormalism The formalism description of the node
	 * @param id The identifier (unique) of the node
	 * @see {@link GraphModel#getId()} to get a new unique ID
	 */
	NodeModel(IElement parent, INodeFormalism nodeFormalism, int id) {
		super(id, parent, nodeFormalism.getAttributes());
		LOGGER.finest("Création d'un NodeModel(" + nodeFormalism.getName() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		this.nodeFormalism = nodeFormalism;
		this.graphicInfos = new NodeGraphicInfo(this);
	}

	/**
	 * Delete all input or output arcs and links from the node
	 */
	final void deleteArcsLinks() {
		LOGGER.finest("delete(" + getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		for (IArc arc : outgoingArcs) {
			((NodeModel) arc.getTarget()).removeIncomingArc(arc);
			((ArcModel) arc).delete();
		}
		for (IArc arc : incomingArcs) {
			((NodeModel) arc.getSource()).removeOutcomingArc(arc);
			((ArcModel) arc).delete();
		}
		for (ILink link : links) {
			link.getElement().removeLink(link);
		}
		outgoingArcs.clear();
		incomingArcs.clear();
		links.clear();
	}

	/** {@inheritDoc} */
	public final INodeFormalism getNodeFormalism() {
		return this.nodeFormalism;
	}

	/** {@inheritDoc} */
	public final INodeGraphicInfo getGraphicInfo() {
		return this.graphicInfos;
	}

	/** {@inheritDoc} */
	public final ILocationInfo getLocationInfo() {
		return this.getGraphicInfo();
	}

	/**
	 * Add an outgoing arc for the considered node
	 * @param outArc The arc to add to the list
	 */
	final void addOutgoingArc(IArc outArc) {
		LOGGER.finest("addOutgoingArc(" + outArc.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		outgoingArcs.add(outArc);
		firePropertyChange(INode.OUTGOING_ARCS_PROP, null, outArc);
	}

	/**
	 * Add an incoming arc for the considered node
	 * @param inArc The arc to add to the list
	 */
	final void addIncomingArc(IArc inArc) {
		LOGGER.finest("addIncomingArc(" + inArc.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		incomingArcs.add(inArc);
		firePropertyChange(INode.INCOMING_ARCS_PROP, null, inArc);
	}

	/**
	 * Remove an outgoing arc from the node
	 * @param outArc The arc to remove from the list
	 */
	final void removeOutcomingArc(IArc outArc) {
		LOGGER.finest("removeOutcomingArc(" + outArc.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		outgoingArcs.remove(outArc);
		firePropertyChange(INode.OUTGOING_ARCS_PROP, null, outArc);
	}

	/**
	 * Remove an incoming arc from the node
	 * @param inArc The arc to remove from the list
	 */
	final void removeIncomingArc(IArc inArc) {
		LOGGER.finest("removeIncomingArc(" + inArc.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		incomingArcs.remove(inArc);
		firePropertyChange(INode.INCOMING_ARCS_PROP, null, inArc);
	}

	/** {@inheritDoc} */
	public final List<IArc> getOutgoingArcs() {
		return Collections.unmodifiableList(outgoingArcs);
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
			// Value changes are sent to the parent level
			firePropertyChange(prop, evt.getOldValue(), evt.getNewValue());
		}
	}

	/** {@inheritDoc} */
	public final void addLink(ILink link) {
		links.add(link);
		firePropertyChange(INode.OUTGOING_ARCS_PROP, null, link);
	}

	/** {@inheritDoc} */
	public final List<ILink> getLinks() {
		return Collections.unmodifiableList(links);
	}

	/** {@inheritDoc} */
	public final boolean removeLink(ILink link) {
		boolean res = links.remove(link);
		firePropertyChange(INode.OUTGOING_ARCS_PROP, null, link);
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

	/** {@inheritDoc} */
	public final boolean isPublic() {
		return this.publicState;
	}

	/** {@inheritDoc} */
	public final void setPublic(boolean state) {
		LOGGER.fine(this + " setPublic(" + state + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		boolean oldValue = publicState;
		this.publicState = state;
		firePropertyChange(IAttribute.VALUE_PROP, oldValue, state);
	}
}
