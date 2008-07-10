package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphFormalism;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Modèle d'un graphe avec des méthodes permettant de gérer (création/suppression)
 * de noeuds et d'arcs.
 */
public class GraphModel extends AbstractElement implements IGraph {
	/**
	 * Logger 'fr.lip6.move.coloane.core'.
	 */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$


	/** Identifiant unique */
	private int id;

	/** Formalisme */
	private IFormalism formalism;

	/** Formalisme de ce graphe */
	private IGraphFormalism graphFormalism;

	/** Liste des noeuds rangé par id */
	private HashMap<Integer, INode>	nodes = new HashMap<Integer, INode>();

	/** Liste des arcs rangé par id */
	private HashMap<Integer, IArc> arcs = new HashMap<Integer, IArc>();

	/** variable locale pour la construction des identifiants */
	private int idCounter = 0;

	/** Date de derniere modification */
	private int date;

	/** Etat du modele par rapport a FK (true -> pas a jour) */
	private boolean dirty = false;


	/**
	 * Création d'un graphe à partir d'un nom de formalisme.
	 * @param formalismName
	 */
	public GraphModel(String formalismName) {
		super(null, FormalismManager.getInstance().getFormalismByName(formalismName).getMasterGraph().getAttributes());
		formalism = FormalismManager.getInstance().getFormalismByName(formalismName);
		graphFormalism = formalism.getMasterGraph();
		id = getNewId();

		LOGGER.fine("Création du GraphModel à partir du formalisme : " + formalismName); //$NON-NLS-1$
	}

	/**
	 * @return un identifiant unique.
	 */
	private int getNewId() {
		return idCounter++;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IGraph#createNode(java.lang.String)
	 */
	public final INode createNode(String nodeFormalismName) throws ModelException {
		IElementFormalism elementFormalism = graphFormalism.getElementFormalism(nodeFormalismName);
		if (elementFormalism == null || !(elementFormalism instanceof INodeFormalism)) {
			throw new ModelException("Ce formalisme ne contient pas de noeud du type " + nodeFormalismName); //$NON-NLS-1$
		}
		INode node = new NodeModel(this, (INodeFormalism) elementFormalism, getNewId());
		addNode(node);

		LOGGER.fine("Création d'un nouveau noeud de type " + nodeFormalismName); //$NON-NLS-1$
		return node;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IGraph#deleteNode(fr.lip6.move.coloane.core.ui.model.interfaces.INode)
	 */
	public final void deleteNode(INode node) {
		if (nodes.remove(node.getId()) != null) {
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

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IGraph#deleteNode(int)
	 */
	public final void deleteNode(int id) {
		INode node = nodes.get(id);
		if (node != null) {
			deleteNode(node);
		}
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IGraph#getNode(int)
	 */
	public final INode getNode(int id) {
		return nodes.get(id);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IGraph#getNodes()
	 */
	public final Collection<INode> getNodes() {
		return nodes.values();
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IGraph#addNode(fr.lip6.move.coloane.core.ui.model.interfaces.INode)
	 */
	public final void addNode(INode node) {
		if (nodes.containsKey(node.getId())) {
			LOGGER.warning("Ce noeud existe déjà."); //$NON-NLS-1$
		} else {
			nodes.put(node.getId(), node);
			firePropertyChange(NODE_ADDED_PROP, null, node);
		}
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IGraph#createArc(java.lang.String, fr.lip6.move.coloane.core.ui.model.interfaces.INode, fr.lip6.move.coloane.core.ui.model.interfaces.INode)
	 */
	public final IArc createArc(String arcFormalismName, INode source, INode target) throws ModelException {
		try {
		if (!nodes.containsKey(source.getId()) || !nodes.containsKey(target.getId())) {
			throw new ModelException("Un des noeuds de connexion n'est pas connu"); //$NON-NLS-1$
		}

		IElementFormalism elementFormalism = graphFormalism.getElementFormalism(arcFormalismName);
		if (elementFormalism == null || !(elementFormalism instanceof IArcFormalism)) {
			throw new ModelException("Ce formalisme ne contient pas d'arc du type " + arcFormalismName); //$NON-NLS-1$
		}
		IArc arc = new ArcModel(this, (IArcFormalism) elementFormalism, getNewId(), source, target);
		addArc(arc);

		LOGGER.fine("Création d'un nouveau arc de type " + arcFormalismName); //$NON-NLS-1$
		return arc;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IGraph#deleteArc(fr.lip6.move.coloane.core.ui.model.interfaces.IArc)
	 */
	public final void deleteArc(IArc arc) {
		if (arcs.remove(arc.getId()) != null) {
			((NodeModel) arc.getSource()).removeOutcomingArc(arc);
			((NodeModel) arc.getTarget()).removeIncomingArc(arc);
		}
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IGraph#deleteArc(int)
	 */
	public final void deleteArc(int id) {
		IArc arc = arcs.get(id);
		if (arc != null) {
			deleteArc(arc);
		}
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IGraph#getArc(int)
	 */
	public final IArc getArc(int id) {
		return arcs.get(id);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IGraph#getArcs()
	 */
	public final Collection<IArc> getArcs() {
		return arcs.values();
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IGraph#addArc(fr.lip6.move.coloane.core.ui.model.interfaces.IArc)
	 */
	public final void addArc(IArc arc) {
		if (arcs.containsKey(arc.getId())) {
			LOGGER.warning("Cet arc existe déjà."); //$NON-NLS-1$
		} else if (!nodes.containsKey(arc.getSource().getId()) || !nodes.containsKey(arc.getTarget().getId())) {
			LOGGER.warning("La source et/ou la cible de cet arc n'existe pas."); //$NON-NLS-1$
		} else if (!formalism.isLinkAllowed(arc.getSource(), arc.getTarget())) {
			LOGGER.warning("Cet arc n'est pas autorisé par ce formalisme."); //$NON-NLS-1$
		} else {
			arcs.put(arc.getId(), arc);
			((NodeModel) arc.getSource()).addOutcomingArc(arc);
			((NodeModel) arc.getTarget()).addIncomingArc(arc);
		}
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IElement#getId()
	 */
	public final int getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IGraph#getFormalism()
	 */
	public final IFormalism getFormalism() {
		return formalism;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IGraph#modifyDate()
	 */
	public final int modifyDate() {
		LOGGER.finest("Demande de mise a jour de la date du modele"); //$NON-NLS-1$
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

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IGraph#getDate()
	 */
	public final int getDate() {
		return date;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IGraph#isDirty()
	 */
	public final boolean isDirty() {
		return dirty;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IGraph#setDirty(boolean)
	 */
	public final void setDirty(boolean state) {
		if (state) {
			LOGGER.fine("Le modele est maintenant considere comme : SALE"); //$NON-NLS-1$
		} else {
			LOGGER.fine("Le modele est maintenant considere comme : PROPRE"); //$NON-NLS-1$
		}
		this.dirty = state;
	}
}
