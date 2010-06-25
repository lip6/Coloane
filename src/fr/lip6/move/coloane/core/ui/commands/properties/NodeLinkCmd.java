package fr.lip6.move.coloane.core.ui.commands.properties;

import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.gef.commands.Command;

/**
 * Command to change nodeLink of a node.
 * 
 * @author Clément Démoulins
 */
public class NodeLinkCmd extends Command {

	private INode node;
	private String newLink;
	private String oldLink;

	/**
	 * Change the link of a node
	 * @param node node
	 * @param link new link
	 */
	public NodeLinkCmd(INode node, String link) {
		this.node = node;
		this.newLink = link;
	}
	
	/** {@inheritDoc} */
	@Override
	public final void execute() {
		this.oldLink = node.getNodeLink();
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		node.setNodeLink(newLink);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		node.setNodeLink(oldLink);
	}

}
