package fr.lip6.move.coloane.ui.commands;

import java.util.Iterator;
import java.util.List;


import org.eclipse.gef.commands.Command;

import fr.lip6.move.coloane.exceptions.BuildException;
import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.ui.model.IArcImpl;
import fr.lip6.move.coloane.ui.model.IModelImpl;
import fr.lip6.move.coloane.ui.model.INodeImpl;

/**
 * Commande de suppression d'un noeud du modele
 * @see fr.lip6.move.coloane.ui.model.INodeImpl
 */
public class NodeDeleteCmd extends Command {

	/** Noeud � retirer */
	private final INodeImpl node;

	/** Mod�le contenant le noeud */
	private final IModelImpl model;

	/** Garder une copie des connexions sortantes du noeud */
	private List sourceConnections;
	/** Garder une copie des connexions entrantes vers le noeud */
	private List targetConnections;

	/** True, si le noeud a �t� supprim� */
	private boolean wasRemoved = false;

	/**
	 * Constructeur 
	 * @param parent modele
	 * @param child noeud
	 * @throws BuildException 
	 */
	public NodeDeleteCmd(IModelImpl model, INodeImpl node) throws BuildException {
		if (model == null || node == null) {
			throw new BuildException(Coloane.traduction.getString("ui.commands.NodeDeleteCmd.0")); //$NON-NLS-1$
		}
		this.model = model;
		this.node = node;
		setLabel(Coloane.traduction.getString("ui.commands.NodeDeleteCmd.1")); //$NON-NLS-1$
	}

	public void execute() {
		// Sauvegarde une copie des listes d'arcs entrants et sortant au cas ou...
		// En cas d'annulation
		sourceConnections = node.getSourceArcs();
		targetConnections = node.getTargetArcs();
		this.redo();
	}

	/**
	 * Demande la suppression du noeud.
	 * La methode removeNode se charge de supprimer le noeud :
	 * <ul>
	 * 	<li>du modele generique</li>
	 * 	<li>du modele augemente</li>
	 * </ul>
	 * @throws BuildException 
	 */
	public void redo() {
		try {
			model.removeNode(node);
			removeConnections(sourceConnections);
			removeConnections(targetConnections);
		} catch (BuildException e) {
			e.printStackTrace();
			System.err.println("Echec ! : "+e.getMessage()); //$NON-NLS-1$
		}
	}

	/**
	 * Annuler l'action de cette commande
	 * --> Revient a ajouter le noeud qu'on vient de supprimer
	 */
	public void undo() {
		try {
			model.addNode(node);
			addConnections(sourceConnections);
			addConnections(targetConnections);
			this.wasRemoved = true;
		} catch (BuildException e) {
			e.printStackTrace();
			System.err.println("Echec ! : "+e.getMessage()); //$NON-NLS-1$
		}

	}

	/**
	 * Ajouter des connexions
	 * @param connections connexions � ajouter
	 */
	private void addConnections(List connections) {
		for (Iterator i = connections.iterator(); i.hasNext();) {
			IArcImpl arc = (IArcImpl) i.next();
			try {
				arc.reconnect();
			} catch (BuildException e) {
				System.err.println("Echec de la reconnexion de l'arc : "+arc.getId()); //$NON-NLS-1$
				System.err.println("Details : "+e.getMessage()); //$NON-NLS-1$
			}
		}
	}

	/**
	 * Retirer des connexions
	 * @param connections connexions � retirer, ne doit pas �tre nulle
	 */
	private void removeConnections(List connections) {
		for (Iterator i = connections.iterator(); i.hasNext();) {
			IArcImpl arc = (IArcImpl) i.next();
			arc.disconnect();
		}
	}

	/**
	 * Savoir si on peut annuler
	 * @return booleen
	 */
	public boolean canUndo() {
		return wasRemoved;
	}

}