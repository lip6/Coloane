package fr.lip6.move.coloane.interfaces.model.command;

import fr.lip6.move.coloane.interfaces.model.IGraph;

/**
 * Commande de changement de position d'un noeud ou d'un arc
 *
 * @author Jean-Baptiste Voron
 */
public class ObjectPositionCommand implements ICommand {
	private int id;
	private int x;
	private int y;

	/**
	 * Constructeur
	 * @param id L'identifiant du noeud ou de l'arc concerné par le placement
	 * @param x La position en x
	 * @param y La position en y
	 */
	public ObjectPositionCommand(int id, int x, int y) {
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
}
