package fr.lip6.move.coloane.ui.commands;

import fr.lip6.move.coloane.exceptions.BuildException;
import fr.lip6.move.coloane.ui.model.IModelImpl;
import fr.lip6.move.coloane.ui.model.INodeImpl;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

/**
 * Commande pour ajouter un nouveau noeud
 */

public class NodeCreateCmd extends Command {

	/** Nouveau noeud a creer */
	private INodeImpl newNode;

	/** Model */
	private final IModelImpl model;

	/** Limite */
	private Rectangle bounds;

	/**
	 * Creer une commande qui ajoutera le noeud au modle
	 *
	 * @param node Le nouveau noeud ˆ ajouter
	 * @param model Le modle qui contiendra le noeud
	 * @param bound Les limites du noeud; (la taille peut tre (-1, -1))
	 */
	public NodeCreateCmd(INodeImpl node, IModelImpl m, Rectangle b) {
		this.newNode = node;
		this.model = m;
		this.newNode.setModelAdapter(model);
		this.bounds = b;
	}

	/**
	 * Savoir si on peux executer la commande ?
	 * --> Toujours OK
	 * @return booleen
	 */
	public final boolean canExecute() {
		return true;
	}

	/**
	 * Executer la commande
	 */
	public final void execute() {
		this.newNode.getGraphicInfo().setLocation(bounds.getLocation().x, bounds.getLocation().y);
		this.redo();
	}

	/**
	 * Refaire les actions de la methode Execute
	 * --> Revient a faire un nouvel ajout d'un noeud
	 */
	public final void redo() {
		try {
			model.addNode(newNode);
		} catch (BuildException e) {
			System.err.println("Impossible de creer un noeud");
		}
	}

	/**
	 * Annuler les modifications faites par la methode Execute
	 * --> Revient a supprimer le noeud cree
	 */
	public final void undo() {
		try {
			model.removeNode(newNode);
		} catch (BuildException e) {
			System.err.println("Impossible d'annuler la creation du noeud");
		}
	}
}
