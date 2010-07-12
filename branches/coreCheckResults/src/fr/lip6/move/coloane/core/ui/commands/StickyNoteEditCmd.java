package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;

import org.eclipse.gef.commands.Command;

/**
 * Edit a sticky note (change its value)
 */
public class StickyNoteEditCmd extends Command {

	/** The note */
	private IStickyNote note;

	/** The new value for the sticky note */
	private String newValue;

	/** The old note */
	private String oldValue;

	/**
	 * Constructor
	 * @param note The sticky note
	 * @param newValue The new value for the sticky note
	 */
	public StickyNoteEditCmd(IStickyNote note, String newValue) {
		super(Messages.StickyNoteEditCommand_0);
		this.note = note;
		this.newValue = ""; //$NON-NLS-1$
		if (newValue != null) {
			this.newValue = newValue;
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		oldValue = note.getLabelContents();
		note.setLabelContents(newValue);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		note.setLabelContents(oldValue);
	}
}
