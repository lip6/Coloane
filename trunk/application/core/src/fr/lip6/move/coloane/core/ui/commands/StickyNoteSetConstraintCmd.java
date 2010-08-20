package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

/**
 * Move a stocky note
 */
public class StickyNoteSetConstraintCmd extends Command {

	/** The note */
	private final IStickyNote note;

	/** The new size and position */
	private final Rectangle newBounds;

	/** The old size and positions */
	private Rectangle oldBounds;

	/**
	 * Constructor
	 * @param note The note
	 * @param newBounds New size and location
	 */
	public StickyNoteSetConstraintCmd(IStickyNote note, Rectangle newBounds) {
		if (note == null || newBounds == null) {
			throw new IllegalArgumentException();
		}

		this.note = note;
		this.newBounds = newBounds.getCopy();
		this.newBounds.x = Math.max(this.newBounds.x, 0);
		this.newBounds.y = Math.max(this.newBounds.y, 0);
	}

	/** {@inheritDoc} */
	@Override
	public final boolean canExecute() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		oldBounds = new Rectangle(note.getLocation(), note.getSize());
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		note.setLocation(newBounds.getLocation());
		note.setSize(newBounds.getSize());
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		note.setLocation(oldBounds.getLocation());
		note.setSize(oldBounds.getSize());
	}
}
