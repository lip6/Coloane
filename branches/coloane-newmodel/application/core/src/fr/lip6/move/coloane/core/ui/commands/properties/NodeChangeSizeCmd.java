package fr.lip6.move.coloane.core.ui.commands.properties;

import fr.lip6.move.coloane.interfaces.model.interfaces.INode;

import org.eclipse.gef.commands.Command;

/**
 * Commande pour modifier la taille d'un noeud
 */
public class NodeChangeSizeCmd extends Command {

	private INode node;
	private int newScale;
	private int oldScale;

	/**
	 * @param node Noeud à modifier
	 * @param scale Nouvelle taille exprimée en pourcentage de la taille d'origine
	 */
	public NodeChangeSizeCmd(INode node, int scale) {
		this.node = node;
		this.newScale = scale;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public final void execute() {
		oldScale = node.getGraphicInfo().getScale();
		redo();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	@Override
	public final void redo() {
		node.getGraphicInfo().setScale(newScale);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public final void undo() {
		node.getGraphicInfo().setScale(oldScale);
	}

}
