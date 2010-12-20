package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.logging.Logger;

import org.eclipse.draw2d.geometry.Point;

/**
 * Create a new node.
 */
public class NodeCreateCmd extends CheckableCmd {
	private final Logger logger = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The new node */
	private INode node;

	/** The current graph */
	private final IGraph graph;

	/** The location of the new node */
	private Point location;

	/** The node formalism name */
	private INodeFormalism nodeFormalism;

	/**
	 * Create a command that will create a node of a given formalism
	 * @param graph the parent graph
	 * @param nodeFormalismName the node formalism 
	 * @param location the location where the node will be displayed for the first time
	 */
	public NodeCreateCmd(IGraph graph, INodeFormalism nodeFormalism, Point location) {
		super(Messages.NodeCreateCmd_0);
		this.graph = graph;
		this.nodeFormalism = nodeFormalism;
		this.location = location;
		
		// The graph must be "checked" when such a command is executed
		// But the graph is always checked... Thus, no need to explicitly ask for a check
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
			node = graph.createNode(nodeFormalism);
			node.getGraphicInfo().setLocation(location);
			// Reset location of all attached attributes 
			for (IAttribute attribute : node.getDrawableAttributes()) {
				attribute.getGraphicInfo().resetLocation();
			}
			addCheckableElement(node);
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
