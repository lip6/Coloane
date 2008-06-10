package fr.lip6.move.coloane.core.ui.commands.properties;

import fr.lip6.move.coloane.core.ui.model.INodeImpl;

import org.eclipse.gef.commands.Command;

public class NodeChangeSizeCmd extends Command {

	private INodeImpl node;
	private int newValue;
	private int oldValue;

	public NodeChangeSizeCmd(INodeImpl node, int newValue) {
		this.node = node;
		this.newValue = newValue;
	}

	@Override
	public final void execute() {
		oldValue = node.getGraphicInfo().getZoom();
		redo();
	}

	@Override
	public final void redo() {
		node.getGraphicInfo().setZoom(newValue);
	}

	@Override
	public final void undo() {
		node.getGraphicInfo().setZoom(oldValue);
	}

}
