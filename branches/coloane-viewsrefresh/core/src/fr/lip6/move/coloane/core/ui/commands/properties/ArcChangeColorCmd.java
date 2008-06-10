package fr.lip6.move.coloane.core.ui.commands.properties;

import fr.lip6.move.coloane.core.ui.model.IArcImpl;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;

public class ArcChangeColorCmd extends Command {
	private Color oldColor;
	private Color newColor;
	private IArcImpl arc;

	public ArcChangeColorCmd(IArcImpl arc, Color color) {
		this.arc = arc;
		this.newColor = color;
	}

	@Override
	public final void execute() {
		oldColor = arc.getGraphicInfo().getColor();
		redo();
	}

	@Override
	public final void redo() {
		arc.getGraphicInfo().setColor(newColor);
	}

	@Override
	public final void undo() {
		arc.getGraphicInfo().setColor(oldColor);
	}

}
