package fr.lip6.move.coloane.interfaces.model.command;

import fr.lip6.move.coloane.interfaces.model.IGraph;

/**
 * Commande de suppression de tous les points d'inflexion<br>
 *
 * @author Jean-Baptiste Voron
 */
public class DeleteInflexPointsCommand implements ICommand {
	/**
	 * Constructeur
	 */
	public DeleteInflexPointsCommand() { }

	/**
	 * {@inheritDoc}
	 */
	public void execute(IGraph graph) { }

	/**
	 * {@inheritDoc}
	 */
	public void redo(IGraph graph) { }

	/**
	 * {@inheritDoc}
	 */
	public void undo(IGraph graph) { }
}
