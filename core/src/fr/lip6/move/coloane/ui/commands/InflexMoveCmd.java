package fr.lip6.move.coloane.ui.commands;

import fr.lip6.move.coloane.ui.model.IArcImpl;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

public class InflexMoveCmd extends Command {

	private IArcImpl arc;
	private Point newPosition;
	private Point oldPosition;
	private int index;

	public InflexMoveCmd(IArcImpl arcModel, Point p, int i) {
		this.arc = arcModel;
		this.index = i;
		this.newPosition = p;
		this.oldPosition = this.arc.getInflexPoint(this.index);

	}

	public final void execute() {
		super.execute();
		this.redo();
	}

	public final void undo() {
		this.arc.modifyInflexPoint(this.index, this.oldPosition);
	}

	public final void redo() {
		this.arc.modifyInflexPoint(this.index, this.newPosition);
	}
}
