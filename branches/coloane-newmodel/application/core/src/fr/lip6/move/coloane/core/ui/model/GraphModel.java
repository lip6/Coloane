package fr.lip6.move.coloane.core.ui.model;

import java.util.HashMap;
import java.util.logging.Logger;

import fr.lip6.move.coloane.core.exceptions.BuildException;
import fr.lip6.move.coloane.core.motor.formalisms.Formalism;
import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.core.motor.formalisms.elements.Arc;
import fr.lip6.move.coloane.core.motor.formalisms.elements.FormalismElement;
import fr.lip6.move.coloane.core.motor.formalisms.elements.Node;
import fr.lip6.move.coloane.core.ui.model.interfaces.IArc;
import fr.lip6.move.coloane.core.ui.model.interfaces.IGraph;
import fr.lip6.move.coloane.core.ui.model.interfaces.INode;

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

	/** Formalisme de ce graphe */
	private Formalism formalism;

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
		super(FormalismManager.getInstance().getFormalismByName(formalismName).getMasterGraph().getAttributes());
		formalism = FormalismManager.getInstance().getFormalismByName(formalismName);
		id = getNewId();

		LOGGER.fine("Création du GraphModel à partir du formalisme : " + formalismName); //$NON-NLS-1$
	}

	/**
	 * @return un identifiant unique.
	 */
	private int getNewId() {
		return idCounter++;
	}

	/**
	 * Création d'un noeud attaché à ce graphe.
	 * @param nodeFormalismName type du noeud à créer.
	 * @return le noeud créé.
	 */
	public final INode createNode(String nodeFormalismName) {
		FormalismElement formalismElement = formalism.getFormalismElement(nodeFormalismName);
		if (!(formalismElement instanceof Node)) {
			throw new BuildException("Ce formalisme ne contient pas de noeud du type " + nodeFormalismName); //$NON-NLS-1$
		}
		INode node = new NodeModel((Node) formalismElement, getNewId());
		nodes.put(node.getId(), node);

		LOGGER.fine("Création d'un nouveau noeud de type " + nodeFormalismName); //$NON-NLS-1$
		return node;
	}

	/**
	 * Suppression d'un noeud
	 * @param node
	 */
	public final void deleteNode(INode node) {
		NodeModel nodeModel = (NodeModel) node;
		nodeModel.delete();
		nodes.remove(node.getId());
	}

	/**
	 * Suppression d'un noeud
	 * @param id identifiant du noeud à supprimer
	 */
	public final void deleteNode(int id) {
		INode node = nodes.get(id);
		if (node != null) {
			deleteNode(node);
		}
	}

	/**
	 * Création d'un arc attaché aux noeuds source et target.
	 * @param arcFormalismName type d'arc à créer.
	 * @param source
	 * @param target
	 * @return l'arc créé.
	 */
	public final IArc createArc(String arcFormalismName, INode source, INode target) {
		if (!nodes.containsKey(source.getId()) || !nodes.containsKey(target.getId())) {
			throw new BuildException("Un des noeuds de connexion n'est pas connu"); //$NON-NLS-1$
		}

		FormalismElement formalismElement = formalism.getFormalismElement(arcFormalismName);
		if (!(formalismElement instanceof Arc)) {
			throw new BuildException("Ce formalisme ne contient pas d'arc du type " + arcFormalismName); //$NON-NLS-1$
		}
		IArc arc = new ArcModel((Arc) formalismElement, getNewId(), source, target);
		return arc;
	}
	
	/**
	 * Suppression d'un arc
	 * @param arc
	 */
	public final void deleteArc(IArc arc) {
		((NodeModel) arc.getSource()).removeSourceArc(arc);
		((NodeModel) arc.getTarget()).removeTargetArc(arc);
		arcs.remove(arc.getId());
	}
	
	/**
	 * Suppression d'un arc
	 * @param id identifiant de l'arc à supprimer
	 */
	public final void deleteArc(int id) {
		IArc arc = arcs.get(id);
		if (arc != null) {
			deleteArc(arc);
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
	public Formalism getFormalism() {
		return formalism;
	}

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

	public final int getDate() {
		return date;
	}

	public final boolean isDirty() {
		return dirty;
	}

	public final void setDirty(boolean state) {
		if (state) {
			LOGGER.fine("Le modele est maintenant considere comme : SALE"); //$NON-NLS-1$
		} else {
			LOGGER.fine("Le modele est maintenant considere comme : PROPRE"); //$NON-NLS-1$
		}
		this.dirty = state;
	}
}
