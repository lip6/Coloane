package fr.lip6.move.coloane.interfaces.model.command;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.draw2d.geometry.Point;

/**
 * Change the position of an object
 * @author Jean-Baptiste Voron
 */
public class ObjectPositionCommand implements ICommand {
	/** Id of the object to move */
	private int id;
	/** X coordinate */
	private int x;
	/** Y coordinate */
	private int y;

	/** Backup */
	private INode node;
	private int oldX;
	private int oldY;

	/**
	 * Constructor
	 * @param id Node ID to move
	 * @param x X coordinate
	 * @param y Y coordinate
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
		// If the designated node is the root node... Position command is not allowed
		if (this.id == 1) { return; }
		
		// Fetch the current note and change its position
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
