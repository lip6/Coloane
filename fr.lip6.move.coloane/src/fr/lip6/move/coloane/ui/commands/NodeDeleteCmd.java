package fr.lip6.move.coloane.ui.commands;

import java.util.Iterator;
import java.util.List;


import org.eclipse.gef.commands.Command;

import fr.lip6.move.coloane.motor.models.ArcImplAdapter;
import fr.lip6.move.coloane.motor.models.ModelImplAdapter;
import fr.lip6.move.coloane.motor.models.NodeImplAdapter;

/**
 * @author yutao
 *
 * Modified by Dzung NGUYEN
 */
public class NodeDeleteCmd extends Command {

	/** Noeud à retirer */
	private final NodeImplAdapter child;

	/** Modèle contenant le noeud */
	private final ModelImplAdapter parent;
	/** Garder une copie des connexions sortantes du noeud */
	private List sourceConnections;
	/** Garder une copie des connexions entrantes vers le noeud */
	private List targetConnections;
	/** True, si le noeud a été supprimé */
	private boolean wasRemoved;

	/**
	 * Constructeur 
	 * @param parent modèle
	 * @param child noeud
	 */
	public NodeDeleteCmd(ModelImplAdapter parent, NodeImplAdapter child) {
		if (parent == null || child == null) {
			throw new IllegalArgumentException();
		}
		setLabel("delete " + child.getElementBase().getName());
		this.parent = parent;
		this.child = child;
	}

	public void execute() {
		// store a copy of incoming & outgoing connections before proceeding 
		sourceConnections = child.getSourceArcs();
		targetConnections = child.getTargetArcs();
		redo();
	}

    /**
     * Refaire
     *
     */
	public void redo() {
		// remove the child and disconnect its connections
		wasRemoved = true;
		try {
			parent.removeChild(child);
		} catch (Exception e) {
			wasRemoved = false;
		}
		
		if (wasRemoved) {
			removeConnections(sourceConnections);
			removeConnections(targetConnections);
		}
	}

	/**
     * Annuler
     *
	 */
    public void undo() {
		parent.addChild(child);
		addConnections(sourceConnections);
		addConnections(targetConnections);
	}

    /**
     * Ajouter des connexions
     * @param connections connexions à ajouter
     */
    private void addConnections(List connections) {
		for (Iterator iter = connections.iterator(); iter.hasNext();) {
			ArcImplAdapter arc = (ArcImplAdapter) iter.next();
			arc.reconnect();
		}
	}

	/**
	 * Retirer des connections
	 * @param connections connexions à retirer, ne doit pas être nulle
	 */
	private void removeConnections(List connections) {
		
		for (Iterator iter = connections.iterator(); iter.hasNext();) {
			ArcImplAdapter arc = (ArcImplAdapter) iter.next();
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