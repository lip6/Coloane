package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.model.IArc;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

/**
 * Delete an inflex point from an arc
 * 
 * @author Jean-Baptiste Voron
 */
public class InflexDeleteCmd extends Command {

	/** The arc from which the inflex point will be removed */
	private IArc arc;
	/** The index of the point inside the list of inflex points */
	private int index;
	/** The location of the inflex point that will be removed */
	private Point position;

	/**
	 * Constructor
	 * @param arc The arc from which the inflex point will be removed
	 * @param index the index of the inflex point in the list of arc inflex points
	 */
	public InflexDeleteCmd(IArc arc, int index) {
		super(Messages.InflexDeleteCmd_0);
		this.arc = arc;
		this.index = index;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		this.position = arc.getInflexPoint(index);
		this.redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		this.arc.addInflexPoint(this.position, this.index);
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		this.arc.removeInflexPoint(this.index);
	}
}
