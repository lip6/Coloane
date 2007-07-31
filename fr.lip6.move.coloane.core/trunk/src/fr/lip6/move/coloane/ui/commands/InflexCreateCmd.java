package fr.lip6.move.coloane.ui.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import fr.lip6.move.coloane.ui.model.IArcImpl;

public class InflexCreateCmd extends Command {
	
		private IArcImpl arcModel;
		private Point position;
		private int index;
		
	
	public InflexCreateCmd(IArcImpl arcModel, Point position, int index) {
		this.arcModel = arcModel;
		this.position = position;
		this.index = index;			
	}

	public void execute() {
		this.redo();
		super.execute();
	}

	public void undo() {
		this.arcModel.removeInflexPoint(this.index);
	}
	
	public void redo() {
		this.arcModel.addInflexPoint(this.position, this.index);
	}

}

