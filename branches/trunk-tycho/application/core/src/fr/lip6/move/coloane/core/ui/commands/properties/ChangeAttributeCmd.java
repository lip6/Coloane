package fr.lip6.move.coloane.core.ui.commands.properties;

import fr.lip6.move.coloane.core.ui.commands.CheckableCmd;
import fr.lip6.move.coloane.interfaces.model.IAttribute;

/**
 * Ask to change the value of an attribute
 */
public class ChangeAttributeCmd extends CheckableCmd {
	private IAttribute attribute;
	private String oldValue;
	private String newValue;

	/**
	 * @param attribute the attribute to be modified
	 * @param newValue the new value
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
