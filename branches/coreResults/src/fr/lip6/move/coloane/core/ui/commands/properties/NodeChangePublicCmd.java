package fr.lip6.move.coloane.core.ui.commands.properties;

import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.gef.commands.Command;

/**
 * Command to change public state of a node.
 *
 * @author Clément Démoulins
 */
public class NodeChangePublicCmd extends Command {

	private INode node;
	private boolean newState;
	private boolean oldState;

	/**
	 * @param node node
	 * @param state new state
	 */
	public NodeChangePublicCmd(INode node, boolean state) {
		this.node = node;
		this.newState = state;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		this.oldState = node.isInterface();
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		node.setInterface(newState);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		node.setInterface(oldState);
	}

}
