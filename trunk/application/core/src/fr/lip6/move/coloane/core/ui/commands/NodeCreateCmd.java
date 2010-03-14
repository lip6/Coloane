package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.logging.Logger;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

/**
 * Actions needed to create a new node<br>
 * This command describes also how to undo/redo this set of actions
 */
public class NodeCreateCmd extends Command {
	private final Logger logger = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The new node */
	private INode node;

	/** The current graph */
	private final IGraph graph;

	/** The location of the new node */
	private Point location;

	/** The node formalism name */
	private String nodeFormalismName;

	/**
	 * Create a command that will create a node of a given formalism
	 * @param graph the parent graph
	 * @param nodeFormalismName the node formalism 
	 * @param location the location where the node will be displayed for the first time
	 */
	public NodeCreateCmd(IGraph graph, String nodeFormalismName, Rectangle location) {
		super(Messages.NodeCreateCmd_0);
		this.graph = graph;
		this.nodeFormalismName = nodeFormalismName;
		this.location = location.getLocation();
	}

	/** {@inheritDoc} */
	@Override
	public final boolean canExecute() {
		return graph != null;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		try {
			node = graph.createNode(nodeFormalismName);
			node.getGraphicInfo().setLocation(location);
			// Reset location of all attached attributes 
			for (IAttribute attribute : node.getAttributes()) {
				attribute.getGraphicInfo().resetLocation();
			}
		} catch (ModelException e) {
			logger.warning(e.toString());
			e.printStackTrace();
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		if (node != null) {
			graph.addNode(node);
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		if (node != null) {
			graph.deleteNode(node);
		}
	}
}
