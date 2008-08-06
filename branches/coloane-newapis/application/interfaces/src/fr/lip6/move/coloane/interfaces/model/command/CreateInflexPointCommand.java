package fr.lip6.move.coloane.interfaces.model.command;

import fr.lip6.move.coloane.interfaces.model.IGraph;

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
	public void execute(IGraph graph) { }

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
