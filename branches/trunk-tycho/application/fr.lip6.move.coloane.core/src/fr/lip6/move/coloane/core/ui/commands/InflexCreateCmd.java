package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.model.IArc;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

/**
 * Create a new <b>inflex point</b> (aka. <i>bendpoint</i>)
 * 
 * @author Jean-Baptiste Voron
 */
public class InflexCreateCmd extends Command {

	/** The arc to which the inflex point will be added */
	private IArc arc;

	/** The location of the inflex point */
	private Point position;

	/** The index if the point in the list of inflex point of this arc */
	private int index;

	/**
	 * Constructor
	 * @param arc The arc
	 * @param location The inflex point location
	 * @param index The index in the list of inflex points
	 */
	public InflexCreateCmd(IArc arc, Point location, int index) {
		super(Messages.InflexCreateCmd_0);
		this.arc = arc;
		this.position = location;
		this.position.x = Math.max(this.position.x, 0);
		this.position.y = Math.max(this.position.y, 0);
		this.index = index;
	}

	/**
	 * Constructor (will create an inflex point with the last index)
	 * @param arc The arc
	 * @param location The inflex point location
	 */
	public InflexCreateCmd(IArc arc, Point location) {
		new InflexCreateCmd(arc, location, arc.getInflexPoints().size());
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		this.redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		this.arc.removeInflexPoint(this.index);
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		this.arc.addInflexPoint(this.position, this.index);
	}
}

