package fr.lip6.move.coloane.interfaces.model.command;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import org.eclipse.draw2d.geometry.Point;

/**
 * Commande de création d'un point d'inflexion
 *
 * @author Jean-Baptiste Voron
 */
public class CreateInflexPointCommand implements ICommand {
	private int id;
	private int x;
	private int y;

	/**
	 * Constructeur
	 * @param id L'identifiant de l'arc concerné par la création de ce point d'inflexion
	 * @param x La position en x
	 * @param y La position en y
	 */
	public CreateInflexPointCommand(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void execute(IGraph graph) throws ModelException {
		IArc arc = graph.getArc(id);
		if (arc != null) {
			arc.addInflexPoint(new Point(x, y));
		} else {
			throw new ModelException("Arc identified by " + id + " does not exist in the model");
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
		return "Creation Inflex (arc: " + id + ") : x=" + x + "; y=" + y;
	}
}
