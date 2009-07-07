package fr.lip6.move.coloane.core.ui.commands.properties;

import fr.lip6.move.coloane.interfaces.model.IAttribute;

import org.eclipse.gef.commands.Command;

/**
 * Commande pour changer la valeur d'un attribut.
 */
public class ChangeAttributeCmd extends Command {
	private IAttribute attr;
	private String oldValue;
	private String newValue;

	/**
	 * @param attr Attribut à modifier
	 * @param newValue Nouvelle valeur
	 */
	public ChangeAttributeCmd(IAttribute attr, String newValue) {
		this.attr = attr;
		this.newValue = newValue;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		if (!canExecute()) {
			return;
		}
		oldValue = attr.getValue();
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		attr.setValue(newValue);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		attr.setValue(oldValue);
	}

}
