package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.model.interfaces.ICoreGraph;
import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphFormalism;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Graph Model Object.<br>
 * This object is a container of all other objects (node, arcs, sticky notes...).
 * Moreover, it also contains attributes.
 * 
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class GraphModel extends AbstractElement implements IGraph, ICoreGraph {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Main formalism used by this graph */
	private IFormalism formalism;

	/** Set of properties given by the formalism for this graph */
	private IGraphFormalism graphFormalism;

	/** Nodes list (ordered by ID) */
	private Map<Integer, INode>	nodes = new HashMap<Integer, INode>();

	/** Arcs list (ordered by ID) */
	private Map<Integer, IArc> arcs = new HashMap<Integer, IArc>();

	/** Sticky Notes list */
	private List<IStickyNote> stickys = new ArrayList<IStickyNote>();

	/** Link list */
	private List<ILink> links = new ArrayList<ILink>();

	/** Local counter used to compute ID for new objects */
	private int idCounter = 2;

	/** Last modification */
	private int date = (int) System.currentTimeMillis();

	/** FrameKit status (<code>true</code> -> dirty state... needs to be updated) */
	private boolean dirty = false;

	/** Graphical properties associated to this graph */
	private GraphEditorProperties editorProperties = null;

	/**
	 * Build a new graph according to a formalism name 
	 * @param formalismName Formalism name used to create the new graph model
	 * @throws IllegalArgumentException If no such formalism exists in FormalismManager list.
	 */
	public GraphModel(String formalismName) throws IllegalArgumentException {
		super(1, null, FormalismManager.getInstance().getFormalismByName(formalismName).getMasterGraph().getAttributes() , FormalismManager.getInstance().getFormalismByName(formalismName).getMasterGraph().getComputedAttributes());
		LOGGER.fine("Création d'un GraphModel(" + formalismName + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		this.formalism = FormalismManager.getInstance().getFormalismByName(formalismName);
		this.graphFormalism = formalism.getMasterGraph();

		// Create graphical properties
		this.editorProperties = new GraphEditorProperties();

		this.addPropertyChangeListener(this);
	}

	/**
	 * @return A new and unique identifier.
	 */
	private int getNewId() {
		int proposal = idCounter + 1;
		while ((nodes.get(proposal) != null) || (arcs.get(proposal) != null)) {
			proposal++;
		}
		idCounter = proposal;
		return idCounter;
	}

	/** {@inheritDoc} */
	public final INode createNode(String nodeFormalismName) throws ModelException {
		return createNode(nodeFormalismName, getNewId());
	}

	/** {@inheritDoc} */
	public final INode createNode(String nodeFormalismName, int id) throws ModelException {
		LOGGER.fine("Création d'un nouveau noeud de type " + nodeFormalismName); //$NON-NLS-1$
		IElementFormalism elementFormalism = graphFormalism.getElementFormalism(nodeFormalismName);
		if (elementFormalism == null || !(elementFormalism instanceof INodeFormalism)) {
			throw new ModelException("Ce formalisme ne contient pas de noeud du type " + nodeFormalismName); //$NON-NLS-1$
		}

		INode node = new NodeModel(this, (INodeFormalism) elementFormalism, id);
		addNode(node);

		return node;
	}

	/** {@inheritDoc} */
	public final void deleteNode(INode node) {
		if (nodes.remove(node.getId()) != null) {
			LOGGER.finest("deleteNode(" + node.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
			for (IArc arc : node.getOutgoingArcs()) {
				arcs.remove(arc.getId());
			}
			for (IArc arc : node.getIncomingArcs()) {
				arcs.remove(arc.getId());
			}
			for (ILink link : ((ILinkableElement) node).getLinks()) {
				links.remove(link);
			}
			((NodeModel) node).deleteArcsLinks();
			firePropertyChange(NODE_REMOVED_PROP, null, node);
			node.removePropertyChangeListener(this);
		}
	}

	/** {@inheritDoc} */
	public final void deleteNode(int id) {
		INode node = nodes.get(id);
		if (node != null) {
			deleteNode(node);
		}
	}

	/** {@inheritDoc} */
	public final INode getNode(int id) {
		return nodes.get(id);
	}

	/** {@inheritDoc} */
	public final Collection<INode> getNodes() {
		return nodes.values();
	}

	/** {@inheritDoc} */
	public final List<IStickyNote> getStickyNotes() {
		return Collections.unmodifiableList(stickys);
	}

	/** {@inheritDoc} */
	public final void addNode(INode node) {
		if (arcs.containsKey(node.getId()) || nodes.containsKey(node.getId())) {
			LOGGER.warning("Ce noeud existe déjà."); //$NON-NLS-1$
		} else {
			nodes.put(node.getId(), node);
			node.addPropertyChangeListener(this);
			LOGGER.finest("addNode(" + node.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
			firePropertyChange(NODE_ADDED_PROP, null, node);
		}
	}

	/** {@inheritDoc} */
	public final IStickyNote createStickyNote() {
		LOGGER.fine("Création d'une nouvelle note"); //$NON-NLS-1$
		IStickyNote note = new StickyNoteModel();
		addSticky(note);

		return note;
	}

	/** {@inheritDoc} */
	public final void addSticky(IStickyNote sticky) {
		stickys.add(sticky);
		LOGGER.finest("addSticky(" + sticky.getLocation() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		firePropertyChange(STICKY_ADD_PROP, null, sticky);
	}

	/** {@inheritDoc} */
	public final boolean deleteSticky(IStickyNote note) {
		boolean delete = stickys.remove(note);
		if (delete) {
			LOGGER.finest("deleteSticky(" + note.getLocation() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
			firePropertyChange(STICKY_REMOVED_PROP, null, note);
		}
		return delete;
	}

	/** {@inheritDoc} */
	public final IArc createArc(String arcFormalismName, INode source, INode target) throws ModelException {
		return this.createArc(arcFormalismName, source, target, getNewId());
	}

	/** {@inheritDoc} */
	public final IArc createArc(String arcFormalismName, INode source, INode target, int id) throws ModelException {
		LOGGER.fine("Création d'un nouveau arc de type " + arcFormalismName); //$NON-NLS-1$
		if (!nodes.containsKey(source.getId()) || !nodes.containsKey(target.getId())) {
			throw new ModelException("Un des noeuds de connexion n'est pas connu"); //$NON-NLS-1$
		}

		IElementFormalism elementFormalism = graphFormalism.getElementFormalism(arcFormalismName);
		if (elementFormalism == null || !(elementFormalism instanceof IArcFormalism)) {
			throw new ModelException("Ce formalisme ne contient pas d'arc du type " + arcFormalismName); //$NON-NLS-1$
		}
		IArc arc = new ArcModel(this, (IArcFormalism) elementFormalism, id, source, target);
		addArc(arc);

		return arc;
	}

	/** {@inheritDoc} */
	public final void deleteArc(IArc arc) {
		if (arcs.remove(arc.getId()) != null) {
			LOGGER.finest("deleteArc(" + arc.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
			((NodeModel) arc.getSource()).removeOutcomingArc(arc);
			((NodeModel) arc.getTarget()).removeIncomingArc(arc);
			firePropertyChange(ARC_REMOVED_PROP, null, arc);
			arc.removePropertyChangeListener(this);
			((ArcModel) arc).delete();
		}
	}

	/** {@inheritDoc} */
	public final void deleteArc(int id) {
		IArc arc = arcs.get(id);
		if (arc != null) {
			deleteArc(arc);
		}
	}

	/** {@inheritDoc} */
	public final IArc getArc(int id) {
		return arcs.get(id);
	}

	/** {@inheritDoc} */
	public final Collection<IArc> getArcs() {
		return arcs.values();
	}

	/** {@inheritDoc} */
	public final void addArc(IArc arc) {
		if (arcs.containsKey(arc.getId()) || nodes.containsKey(arc.getId())) {
			LOGGER.warning("Cet id existe déjà."); //$NON-NLS-1$
		} else if (!nodes.containsKey(arc.getSource().getId()) || !nodes.containsKey(arc.getTarget().getId())) {
			LOGGER.warning("La source et/ou la cible de cet arc n'existe pas."); //$NON-NLS-1$
		} else if (!formalism.isLinkAllowed(arc.getSource(), arc.getTarget(), arc.getArcFormalism())) {
			LOGGER.warning("Cet arc n'est pas autorisé par ce formalisme."); //$NON-NLS-1$
		} else {
			LOGGER.finest("addArc(" + arc.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
			arcs.put(arc.getId(), arc);
			((NodeModel) arc.getSource()).addOutgoingArc(arc);
			((NodeModel) arc.getTarget()).addIncomingArc(arc);
			arc.addPropertyChangeListener(this);
			firePropertyChange(ARC_ADDED_PROP, null, arc);
		}
	}

	/** {@inheritDoc} */
	public final IElement getObject(int id) {
		IElement obj = this.getNode(id);
		if (obj == null) {
			obj = this.getArc(id);
		}
		return obj;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void deleteObject(int id) throws ModelException {
		INode node = this.getNode(id);
		if (node != null) { this.deleteNode(node); return; }
		IArc arc = this.getArc(id);
		if (arc != null) { this.deleteArc(arc); return; }
		LOGGER.warning("L'object id=" + id + " n'existe pas dans le modele... Aucune suppression effectuee"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/** {@inheritDoc} */
	public final IFormalism getFormalism() {
		return formalism;
	}

	/**
	 * Refresh the last update date
	 */
	final void updateDate() {
		date = (int) System.currentTimeMillis();
	}

	/** {@inheritDoc} */
	public final int getDate() {
		return date;
	}

	/** {@inheritDoc} */
	public final boolean isDirty() {
		return dirty;
	}

	/** {@inheritDoc} */
	public final void setDirty(boolean state) {
		if (state != dirty) {
			if (state) {
				LOGGER.fine("Le modele est maintenant considere comme : SALE"); //$NON-NLS-1$
			} else {
				LOGGER.fine("Le modele est maintenant considere comme : PROPRE"); //$NON-NLS-1$
			}
			this.dirty = state;
		}
	}

	/**
	 * @return the graphical properties
	 */
	public final GraphEditorProperties getEditorProperties() {
		return editorProperties;
	}

	/** {@inheritDoc} */
	public final void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();

		if (NODE_ADDED_PROP.equals(prop)
				|| NODE_REMOVED_PROP.equals(prop)
				|| INode.INCOMING_ARCS_PROP.equals(prop)
				|| INode.OUTGOING_ARCS_PROP.equals(prop)
				|| INode.PUBLIC_PROP.equals(prop)
				|| IAttribute.VALUE_PROP.equals(prop)) {
			updateDate();
			setDirty(true);
		}
	}

	/** {@inheritDoc} */
	public final void addLink(ILink link) {
		links.add(link);
		link.getElement().addLink(link);
		link.getNote().addLink(link);
		firePropertyChange(LINK_ADD_PROP, null, link);
	}

	/** {@inheritDoc} */
	public final ILink createLink(IStickyNote note, ILinkableElement element) {
		ILink link = new LinkModel(note, element);
		addLink(link);
		return link;
	}

	/** {@inheritDoc} */
	public final boolean deleteLink(ILink link) {
		boolean res = links.remove(link);
		if (res) {
			link.getElement().removeLink(link);
			link.getNote().removeLink(link);
			firePropertyChange(LINK_REMOVED_PROP, null, link);
		}
		return res;
	}

	/** {@inheritDoc} */
	public final List<ILink> getLinks() {
		return Collections.unmodifiableList(links);
	}

	/** {@inheritDoc} */
	public final void addGraph(IGraph graph) {
		if (!formalism.getId().equals(graph.getFormalism().getId())) {
			LOGGER.warning("Les formalismes sont différents [" + formalism.getId() + " ≠ " + graph.getFormalism().getId() + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			return;
		}
		LOGGER.fine("Ajout de tous les éléments du graphe " + graph.getId()); //$NON-NLS-1$
		for (INode node : graph.getNodes()) {
			NodeModel nodeModel = (NodeModel) node;
			for (IArc arc : new ArrayList<IArc>(node.getIncomingArcs())) {
				nodeModel.removeIncomingArc(arc);
			}
			for (IArc arc : new ArrayList<IArc>(node.getOutgoingArcs())) {
				nodeModel.removeOutcomingArc(arc);
			}
			((AbstractElement) node).setId(getNewId());
			addNode(node);
		}
		for (IArc arc : graph.getArcs()) {
			((AbstractElement) arc).setId(getNewId());
			addArc(arc);
		}
		for (IStickyNote sticky : stickys) {
			addSticky(sticky);
		}
	}
}
