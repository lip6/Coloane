package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.StickyNote;

import org.eclipse.gef.commands.Command;

public class StickyNoteEditCommand extends Command {

	private String newName, oldName;
	private StickyNote label;

	public StickyNoteEditCommand(StickyNote note, String value) {
		label = note;
		newName = "";
		if (value != null) {
			newName = value;
		}
	}

	public final void execute() {
		oldName = label.getLabelContents();
		label.setLabelContents(newName);
	}

	public final void undo() {
		label.setLabelContents(oldName);
	}
}
