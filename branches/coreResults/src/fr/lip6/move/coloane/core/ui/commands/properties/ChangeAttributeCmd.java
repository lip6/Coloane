package fr.lip6.move.coloane.core.ui.commands.properties;

import fr.lip6.move.coloane.core.ui.checker.CheckableCmd;
import fr.lip6.move.coloane.interfaces.model.IAttribute;

/**
 * Commande pour changer la valeur d'un attribut.
 */
public class ChangeAttributeCmd extends CheckableCmd {
	private IAttribute attribute;
	private String oldValue;
	private String newValue;

	/**
	 * @param attribute attribut à modifier
	 * @param newValue Nouvelle valeur
	 */
	public ChangeAttributeCmd(IAttribute attribute, String newValue) {
		this.attribute = attribute;
		this.newValue = newValue;
		addCheckableElement(attribute.getReference());
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		if (!canExecute()) {
			return;
		}
		oldValue = attribute.getValue();
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		attribute.setValue(newValue);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		attribute.setValue(oldValue);
	}
}
