package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphFormalism;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Modèle d'un graphe avec des méthodes permettant de gérer (création/suppression)
 * de noeuds et d'arcs.
 */
public class GraphModel extends AbstractElement implements IGraph {
	/** Logger 'fr.lip6.move.coloane.core'. */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Identifiant unique */
	private int id;

	/** Formalisme */
	private IFormalism formalism;

	/** Formalisme de ce graphe */
	private IGraphFormalism graphFormalism;

	/** Liste des noeuds rangé par id */
	private Map<Integer, INode>	nodes = new HashMap<Integer, INode>();

	/** Liste des arcs rangé par id */
	private Map<Integer, IArc> arcs = new HashMap<Integer, IArc>();

	/** Liste des stickyNote */
	private List<IStickyNote> stickys = new ArrayList<IStickyNote>();

	/** variable locale pour la construction des identifiants */
	private int idCounter = 2;

	/** Date de derniere modification */
	private int date;

	/** Etat du modele par rapport a FK (true -> pas a jour) */
	private boolean dirty = false;

	/** Ensemble des propriétés de l'éditeur auquel est attaché ce graphe */
	private GraphEditorProperties editorProperties = null;


	/**
	 * Création d'un graphe à partir d'un nom de formalisme.
	 * @param formalismName Le nom du formalisme du modèle
	 */
	public GraphModel(String formalismName) {
		super(null, FormalismManager.getInstance().getFormalismByName(formalismName).getMasterGraph().getAttributes());
		LOGGER.fine("Création d'un GraphModel(" + formalismName + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		this.formalism = FormalismManager.getInstance().getFormalismByName(formalismName);
		this.graphFormalism = formalism.getMasterGraph();
		this.id = 1;

		// Creation des propriétés de l'éditeur
		this.editorProperties = new GraphEditorProperties();
	}

	/**
	 * @return un identifiant unique.
	 */
	private int getNewId() {
		return idCounter++;
	}

	/** {@inheritDoc} */
	public final INode createNode(String nodeFormalismName) throws ModelException {
		LOGGER.fine("Création d'un nouveau noeud de type " + nodeFormalismName); //$NON-NLS-1$
		IElementFormalism elementFormalism = graphFormalism.getElementFormalism(nodeFormalismName);
		if (elementFormalism == null || !(elementFormalism instanceof INodeFormalism)) {
			throw new ModelException("Ce formalisme ne contient pas de noeud du type " + nodeFormalismName); //$NON-NLS-1$
		}
		INode node = new NodeModel(this, (INodeFormalism) elementFormalism, getNewId());
		addNode(node);

		return node;
	}

	/** {@inheritDoc} */
	public final void deleteNode(INode node) {
		if (nodes.remove(node.getId()) != null) {
			LOGGER.finest("deleteNode(" + node.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
			for (IArc arc : node.getOutcomingArcs()) {
				arcs.remove(arc.getId());
			}
			for (IArc arc : node.getIncomingArcs()) {
				arcs.remove(arc.getId());
			}
			((NodeModel) node).delete();
			firePropertyChange(NODE_REMOVED_PROP, null, node);
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

	/**
	 * @return La liste des toutes les notes du graphe
	 */
	public final List<IStickyNote> getStickyNotes() {
		return Collections.unmodifiableList(stickys);
	}

	/** {@inheritDoc} */
	public final void addNode(INode node) {
		if (nodes.containsKey(node.getId())) {
			LOGGER.warning("Ce noeud existe déjà."); //$NON-NLS-1$
		} else {
			nodes.put(node.getId(), node);
			LOGGER.finest("addNode(" + node.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
			firePropertyChange(NODE_ADDED_PROP, null, node);
		}
	}

	/**
	 * Création d'une note
	 * @return la note créée
	 */
	public final IStickyNote createStickyNote() {
		LOGGER.fine("Création d'une nouvelle note"); //$NON-NLS-1$
		IStickyNote note = new StickyNote();
		addSticky(note);

		return note;
	}

	/**
	 * Ajoute la note au graphe courant
	 * @param sticky La stickyNote à ajouter
	 */
	public final void addSticky(IStickyNote sticky) {
		stickys.add(sticky);
		LOGGER.finest("addSticky(" + sticky.getLocation() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		firePropertyChange(STICKY_ADD_PROP, null, sticky);
	}

	/**
	 * Supprime la note du graphe courante
	 * @param note La StickyNote à supprimer
	 * @return <tt>false</tt> si aucune note n'a été supprimée, <tt>true</tt> sinon
	 */
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
		LOGGER.fine("Création d'un nouveau arc de type " + arcFormalismName); //$NON-NLS-1$
		if (!nodes.containsKey(source.getId()) || !nodes.containsKey(target.getId())) {
			throw new ModelException("Un des noeuds de connexion n'est pas connu"); //$NON-NLS-1$
		}

		IElementFormalism elementFormalism = graphFormalism.getElementFormalism(arcFormalismName);
		if (elementFormalism == null || !(elementFormalism instanceof IArcFormalism)) {
			throw new ModelException("Ce formalisme ne contient pas d'arc du type " + arcFormalismName); //$NON-NLS-1$
		}
		IArc arc = new ArcModel(this, (IArcFormalism) elementFormalism, getNewId(), source, target);
		addArc(arc);

		return arc;
	}

	/** {@inheritDoc} */
	public final void deleteArc(IArc arc) {
		if (arcs.remove(arc.getId()) != null) {
			LOGGER.finest("deleteArc(" + arc.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
			((NodeModel) arc.getSource()).removeOutcomingArc(arc);
			((NodeModel) arc.getTarget()).removeIncomingArc(arc);
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
		if (arcs.containsKey(arc.getId())) {
			LOGGER.warning("Cet arc existe déjà."); //$NON-NLS-1$
		} else if (!nodes.containsKey(arc.getSource().getId()) || !nodes.containsKey(arc.getTarget().getId())) {
			LOGGER.warning("La source et/ou la cible de cet arc n'existe pas."); //$NON-NLS-1$
		} else if (!formalism.isLinkAllowed(arc.getSource(), arc.getTarget())) {
			LOGGER.warning("Cet arc n'est pas autorisé par ce formalisme."); //$NON-NLS-1$
		} else {
			LOGGER.finest("addArc(" + arc.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
			arcs.put(arc.getId(), arc);
			((NodeModel) arc.getSource()).addOutcomingArc(arc);
			((NodeModel) arc.getTarget()).addIncomingArc(arc);
		}
	}

	/** {@inheritDoc} */
	public final int getId() {
		return id;
	}

	/** {@inheritDoc} */
	public final IElement getObject(int id) {
		IElement obj = this.getNode(id);
		if (obj != null) {
			return this.getNode(id);
		} else {
			return this.getArc(id);
		}
	}

	/** {@inheritDoc} */
	public final IFormalism getFormalism() {
		return formalism;
	}

	/** {@inheritDoc} */
	public final int modifyDate() {
		date = (int) System.currentTimeMillis();
		// Si le modele n'etait pas marque comme sale, on le marque
		if (!dirty) {
			setDirty(true);
			return date;
			// Sinon le modele etait deja sale (on a juste mis a jour la date)
		} else {
			return 0;
		}
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
		if (state) {
			LOGGER.fine("Le modele est maintenant considere comme : SALE"); //$NON-NLS-1$
		} else {
			LOGGER.fine("Le modele est maintenant considere comme : PROPRE"); //$NON-NLS-1$
		}
		this.dirty = state;
	}

	/**
	 * @return Les propriétés de l'éditeur auquel est attaché ce graphe
	 */
	public final GraphEditorProperties getEditorProperties() {
		return editorProperties;
	}
}
