package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

/**
 * Commande pour deplacer une note
 */
public class StickyNoteSetConstraintCmd extends Command {

	/** La nouvelle position et taille de la note */
	private final Rectangle newBounds;

	/** L'ancienne taille et position utilisée pour les <i>undo</i> */
	private Rectangle oldBounds;

	/** Le modèle de la note */
	private final IStickyNote note;

	/**
	 * Constructeur de la commande
	 * @param note Le modèle de la note manipulée
	 * @param newBounds Nouvelle taille et position de la note
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
