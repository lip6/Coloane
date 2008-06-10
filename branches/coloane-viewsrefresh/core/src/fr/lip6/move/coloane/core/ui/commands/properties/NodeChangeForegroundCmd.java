package fr.lip6.move.coloane.core.ui.commands.properties;

import fr.lip6.move.coloane.core.ui.model.INodeImpl;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;

public class NodeChangeForegroundCmd extends Command {
	private Color oldColor;
	private Color newColor;
	private INodeImpl node;

	public NodeChangeForegroundCmd(INodeImpl node, Color color) {
		this.node = node;
		this.newColor = color;
	}

	@Override
	public final void execute() {
		oldColor = node.getGraphicInfo().getForeground();
		redo();
	}

	@Override
	public final void redo() {
		node.getGraphicInfo().setForeground(newColor);
	}

	@Override
	public final void undo() {
		node.getGraphicInfo().setForeground(oldColor);
	}

}
