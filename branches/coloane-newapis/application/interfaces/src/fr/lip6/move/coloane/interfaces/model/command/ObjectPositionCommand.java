package fr.lip6.move.coloane.interfaces.model.command;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import org.eclipse.draw2d.geometry.Point;

/**
 * Commande de changement de position d'un noeud
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
	public final void execute(IGraph graph) throws ModelException {
		graph.getNode(id).getGraphicInfo().setLocation(new Point(x, y));
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
		return "Positionnement de l'objet " + id + " : x=" + x + "; y=" + y;
	}
}
