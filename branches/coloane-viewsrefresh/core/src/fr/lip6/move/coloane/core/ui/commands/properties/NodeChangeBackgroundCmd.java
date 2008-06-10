package fr.lip6.move.coloane.core.ui.commands.properties;

import fr.lip6.move.coloane.core.ui.model.INodeImpl;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;

public class NodeChangeBackgroundCmd extends Command {
	private Color oldColor;
	private Color newColor;
	private INodeImpl node;

	public NodeChangeBackgroundCmd(INodeImpl node, Color color) {
		this.node = node;
		this.newColor = color;
	}

	@Override
	public final void execute() {
		oldColor = node.getGraphicInfo().getBackground();
		redo();
	}

	@Override
	public final void redo() {
		try {
		node.getGraphicInfo().setBackground(newColor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public final void undo() {
		try {
		node.getGraphicInfo().setBackground(oldColor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
