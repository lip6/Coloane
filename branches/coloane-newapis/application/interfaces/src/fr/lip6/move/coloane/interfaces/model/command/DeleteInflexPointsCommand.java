package fr.lip6.move.coloane.interfaces.model.command;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;
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
	public final void execute(IGraph graph) throws ModelException {
		for (IArc arc : graph.getArcs()) {
			arc.removeAllInflexPoints();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void redo(IGraph graph) { }

	/**
	 * {@inheritDoc}
	 */
	public void undo(IGraph graph) { }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		return "Suppression de tous les points d'inlfexion";
	}
}
