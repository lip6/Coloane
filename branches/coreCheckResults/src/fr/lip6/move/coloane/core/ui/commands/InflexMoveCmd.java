package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.model.IArc;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

/**
 * Move an inflex point
 */
public class InflexMoveCmd extends Command {

	/** The arc that holds the inflex point to move */
	private IArc arc;
	/** New position for the inflex point */
	private Point newPosition;
	/** Index of the inflex point to move */
	private int index;

	/** Old position (backup in case of undo) */
	private Point oldPosition;

	/**
	 * Constructor
	 * @param arc The arc that holds the inflex point
	 * @param position The new position for the inflex point
	 * @param index The current index of the inflex point
	 */
	public InflexMoveCmd(IArc arc, Point position, int index) {
		super(Messages.InflexMoveCmd_0);
		this.arc = arc;
		this.index = index;
		this.newPosition = position;
		this.newPosition.x = Math.max(this.newPosition.x, 0);
		this.newPosition.y = Math.max(this.newPosition.y, 0);
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		this.oldPosition = this.arc.getInflexPoint(this.index);
		this.redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		this.arc.modifyInflexPoint(this.index, this.oldPosition);
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		this.arc.modifyInflexPoint(this.index, this.newPosition);
	}
}
