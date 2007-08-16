package fr.lip6.move.coloane.ui.commands;

import fr.lip6.move.coloane.ui.model.INodeImpl;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

/**
 * Commande pour deplacer un noeud
 */
public class NodeSetConstraintCmd extends Command {

	/** Enregistre la nouvelle taille et le nouvel endroit */
	private final Rectangle newBounds;
	/** Enregistre l'ancienne taille et le nouvel endroit */
	private Rectangle oldBounds;

	/** Noeud � manipuler */
	private final INodeImpl node;

	/**
	 * Constructeur
	 * @param node noeud
	 * @param newBounds Nouvelles limites
	 */
	public NodeSetConstraintCmd(INodeImpl n, Rectangle bounds) {
		if (n == null || bounds == null) {
			throw new IllegalArgumentException();
		}
		this.node = n;
		this.newBounds = bounds.getCopy();
	}

	/**
	 * On peut toujours deplacer un noeud.
	 * Le redimensionnement est bloque automatiquement par les EditPolicy
	 * @return booleen
	 */
	public final boolean canExecute() {
		return true;
	}

	/**
	 * Executer
	 */
	public final void execute() {
		oldBounds = new Rectangle(node.getGraphicInfo().getLocation(), node.getGraphicInfo().getSize());
		redo();
	}

	/**
	 * Refaire
	 */
	public final void redo() {
		node.getGraphicInfo().setLocation(newBounds.getLocation().x, newBounds.getLocation().y);
		node.updateAttributesPosition(oldBounds.getLocation().x - newBounds.getLocation().x, oldBounds.getLocation().y - newBounds.getLocation().y);
		node.updateArcAttributesPosition();
	}

	/**
	 * Annuler
	 */
	public final void undo() {
		node.getGraphicInfo().setLocation(oldBounds.getLocation().x, oldBounds.getLocation().y);
		/* TODO : Mise a jour de la position des attributs */
	}

}
