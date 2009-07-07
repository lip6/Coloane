package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.model.IArc;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

/**
 * Commande de deplacement d'un point d'inflexion
 *
 */
public class InflexMoveCmd extends Command {

	private IArc arc;
	private Point newPosition;
	private Point oldPosition;
	private int index;

	/**
	 * Constructeur
	 * @param arcModel L'arc concerne
	 * @param p La position
	 * @param i L'index
	 */
	public InflexMoveCmd(IArc arcModel, Point p, int i) {
		super(Messages.InflexMoveCmd_0);
		this.arc = arcModel;
		this.index = i;
		this.newPosition = p;
		this.newPosition.x = Math.max(this.newPosition.x, 0);
		this.newPosition.y = Math.max(this.newPosition.y, 0);
		this.oldPosition = this.arc.getInflexPoint(this.index);

	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		super.execute();
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
