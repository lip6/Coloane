package fr.lip6.move.coloane.ui.commands;

import fr.lip6.move.coloane.ui.model.IArcImpl;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

public class InflexCreateCmd extends Command {

	private IArcImpl arc;
	private Point position;
	private int index;


	public InflexCreateCmd(IArcImpl arcModel, Point p, int i) {
		this.arc = arcModel;
		this.position = p;
		this.index = i;
	}

	public final void execute() {
		this.redo();
		super.execute();
	}

	public final void undo() {
		this.arc.removeInflexPoint(this.index);
	}

	public final void redo() {
		this.arc.addInflexPoint(this.position, this.index);
	}

}

