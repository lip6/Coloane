package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.ui.model.IArcImpl;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

/**
 * Commande de deplacement d'un point d'inflexion
 *
 */
public class InflexMoveCmd extends Command {

	private IArcImpl arc;
	private Point newPosition;
	private Point oldPosition;
	private int index;

	/**
	 * Constructeur
	 * @param arcModel L'arc concerne
	 * @param p La position
	 * @param i L'index
	 */
	public InflexMoveCmd(IArcImpl arcModel, Point p, int i) {
		this.arc = arcModel;
		this.index = i;
		this.newPosition = p;
		this.oldPosition = this.arc.getInflexPoint(this.index);

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public final void execute() {
		super.execute();
		this.redo();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public final void undo() {
		this.arc.modifyInflexPoint(this.index, this.oldPosition);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public final void redo() {
		this.arc.modifyInflexPoint(this.index, this.newPosition);
	}
}
