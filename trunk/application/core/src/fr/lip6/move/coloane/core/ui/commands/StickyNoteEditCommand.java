package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.StickyNote;

import org.eclipse.gef.commands.Command;

/**
 * Commande d'édition d'une note sur l'éditeur
 */
public class StickyNoteEditCommand extends Command {

	/** Sauvegarde des ancinnes valeurs */
	private String newName;

	/** La nouvelle valeur */
	private String oldName;

	/** La note considérée */
	private StickyNote label;

	/**
	 * Constructeur de la commande
	 * @param note La note qui doit être éditée
	 * @param value La nouvelle valeur de la note
	 */
	public StickyNoteEditCommand(StickyNote note, String value) {
		super(Messages.StickyNoteEditCommand_0);
		label = note;
		newName = ""; //$NON-NLS-1$
		if (value != null) {
			newName = value;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public final void execute() {
		oldName = label.getLabelContents();
		label.setLabelContents(newName);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public final void undo() {
		label.setLabelContents(oldName);
	}
}
