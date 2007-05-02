package fr.lip6.move.coloane.ui.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import fr.lip6.move.coloane.ui.model.NodeImplAdapter;

/**
 * Commande pour deplacer un noeud
 */
public class NodeSetConstraintCmd extends Command {

	/** Enregistre la nouvelle taille et le nouvel endroit */
	private final Rectangle newBounds;
    /** Enregistre l'ancienne taille et le nouvel endroit */
	private Rectangle oldBounds;

	/** Noeud ˆ manipuler */
	private final NodeImplAdapter node;

    /**
     * Constructeur
     * @param node noeud
     * @param req requete
     * @param newBounds Nouvelles limites
     */
    public NodeSetConstraintCmd(NodeImplAdapter node, Rectangle newBounds) {
		if (node == null || newBounds == null) {
			throw new IllegalArgumentException();
		}
		this.node = node;
		this.newBounds = newBounds.getCopy();
		setLabel("Move a node");
	}

    /**
     * On peut toujours deplacer un noeud.
     * Le redimensionnement est bloque automatiquement par les EditPolicy
     * @return booleen
     */
	public boolean canExecute() {
		return true;
	}

	/**
     * Executer
	 */
	public void execute() {
		oldBounds = new Rectangle(node.getGraphicInfo().getLocation(), node.getGraphicInfo().getSize());
		redo();
	}

	/**
     * Refaire
	 */
	public void redo() {
		node.getGraphicInfo().setLocation(newBounds.getLocation().x,newBounds.getLocation().y);
	}

	/**
     * Annuler
	 */
	public void undo() {
		node.getGraphicInfo().setLocation(oldBounds.getLocation().x,oldBounds.getLocation().y);
	}

}
