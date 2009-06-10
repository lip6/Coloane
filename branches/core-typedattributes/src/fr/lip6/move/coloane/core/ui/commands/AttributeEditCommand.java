package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.model.IAttribute;

import org.eclipse.gef.commands.Command;

/**
 * Commande d'édition d'une note sur l'éditeur
 */
public class AttributeEditCommand extends Command {

	/** Sauvegarde des ancinnes valeurs */
	private String newValue;

	/** La nouvelle valeur */
	private String oldValue;

	/** La note considérée */
	private IAttribute attribute;

	/**
	 * Constructeur de la commande
	 * @param attribute La note qui doit être éditée
	 * @param value La nouvelle valeur de la note
	 */
	public AttributeEditCommand(IAttribute attribute, String value) {
		super(Messages.StickyNoteEditCommand_0);
		this.attribute = attribute;
		newValue = ""; //$NON-NLS-1$
		if (value != null) {
			newValue = value;
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		oldValue = attribute.getValue();
		attribute.setValue(newValue);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		attribute.setValue(oldValue);
	}
}
