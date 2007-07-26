package fr.lip6.move.coloane.ui.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import fr.lip6.move.coloane.ui.model.IArcImpl;

public class InflexMoveCmd extends Command {

	private IArcImpl arcModel;
	private Point newPosition;
	private Point oldPosition;
	private int index;

	public InflexMoveCmd(IArcImpl arcModel, Point position, int index) {
		this.arcModel = arcModel;
		this.index = index;	
		this.newPosition = position;
		this.oldPosition = this.arcModel.getInflexPoint(this.index);
				
	}

	public void execute() {
		this.redo();
		super.execute();
	}

	public void undo() {
		this.arcModel.modifyInflexPoint(this.index, this.oldPosition);
	}

	public void redo() {
		this.arcModel.modifyInflexPoint(this.index, this.newPosition);
	}
}
