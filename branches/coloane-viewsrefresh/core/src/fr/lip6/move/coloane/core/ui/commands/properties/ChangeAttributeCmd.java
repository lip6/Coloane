package fr.lip6.move.coloane.core.ui.commands.properties;

import fr.lip6.move.coloane.core.ui.model.IAttributeImpl;

import org.eclipse.gef.commands.Command;

/**
 * Commande pour changer la valeur d'un attribut.
 */
public class ChangeAttributeCmd extends Command {
	private IAttributeImpl attr;
	private String oldValue;
	private String newValue;

	/**
	 * @param attr Attribut à modifier
	 * @param newValue Nouvelle valeur
	 */
	public ChangeAttributeCmd(IAttributeImpl attr, String newValue) {
		this.attr = attr;
		this.newValue = newValue;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public final void execute() {
		if (!canExecute()) {
			return;
		}
		oldValue = attr.getValue();
		redo();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	@Override
	public final void redo() {
		attr.setValue(oldValue, newValue);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public final void undo() {
		attr.setValue(newValue, oldValue);
	}

}
