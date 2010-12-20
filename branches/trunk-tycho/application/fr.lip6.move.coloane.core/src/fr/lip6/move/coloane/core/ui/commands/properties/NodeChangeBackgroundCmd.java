package fr.lip6.move.coloane.core.ui.commands.properties;

import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;

/**
 * Commande pour la couleur de l'arrière plan d'un noeud
 */
public class NodeChangeBackgroundCmd extends Command {
	private Color oldColor;
	private Color newColor;
	private INode node;

	/**
	 * @param node Noeud à modifier
	 * @param color Nouvelle couleur
	 */
	public NodeChangeBackgroundCmd(INode node, Color color) {
		this.node = node;
		this.newColor = color;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		oldColor = node.getGraphicInfo().getBackground();
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		node.getGraphicInfo().setBackground(newColor);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		node.getGraphicInfo().setBackground(oldColor);
	}

}
