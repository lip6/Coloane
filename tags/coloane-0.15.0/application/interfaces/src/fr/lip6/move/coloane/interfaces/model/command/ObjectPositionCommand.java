package fr.lip6.move.coloane.interfaces.model.command;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.draw2d.geometry.Point;

/**
 * Commande de changement de position d'un noeud
 *
 * @author Jean-Baptiste Voron
 */
public class ObjectPositionCommand implements ICommand {
	/** L'identifiant de l'objet à déplacer */
	private int id;
	/** La position en X */
	private int x;
	/** La position en Y */
	private int y;

	private INode node;
	private int oldX;
	private int oldY;

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
		node = graph.getNode(id);
		if (node != null) {
			this.oldX = node.getGraphicInfo().getLocation().x;
			this.oldY = node.getGraphicInfo().getLocation().y;
			node.getGraphicInfo().setLocation(new Point(x, y));
		} else {
			throw new ModelException("The node " + id + " does not exist in the model");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final void redo(IGraph graph) throws ModelException {
		this.execute(graph);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void undo(IGraph graph) throws ModelException {
		if (node != null) {
			node.getGraphicInfo().setLocation(new Point(oldX, oldY));
		} else {
			throw new ModelException("The node " + id + " does not exist in the model");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		return "Positionnement de l'objet " + id + " : x=" + x + "; y=" + y;
	}
}
