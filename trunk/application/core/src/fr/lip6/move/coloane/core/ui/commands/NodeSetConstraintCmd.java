package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.model.INode;

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

	/** Noeud a manipuler */
	private final INode node;

	/**
	 * Constructeur
	 * @param node noeud
	 * @param newBounds Nouvelles limites
	 */
	public NodeSetConstraintCmd(INode n, Rectangle bounds) {
		super(Messages.NodeSetConstraintCmd_0);
		if (n == null || bounds == null) {
			throw new IllegalArgumentException();
		}
		this.node = n;
		this.newBounds = bounds.getCopy();
		this.newBounds.x = Math.max(this.newBounds.x, 0);
		this.newBounds.y = Math.max(this.newBounds.y, 0);
	}

	/**
	 * On peut toujours deplacer un noeud.
	 * Le redimensionnement est bloque automatiquement par les EditPolicy
	 * @return true
	 */
	@Override
	public final boolean canExecute() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		oldBounds = new Rectangle(node.getGraphicInfo().getLocation(), node.getGraphicInfo().getSize());
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		node.getGraphicInfo().setLocation(newBounds.getLocation());
		node.updateArcAttributesPosition();
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		node.getGraphicInfo().setLocation(oldBounds.getLocation());
		node.updateArcAttributesPosition();
	}

}
