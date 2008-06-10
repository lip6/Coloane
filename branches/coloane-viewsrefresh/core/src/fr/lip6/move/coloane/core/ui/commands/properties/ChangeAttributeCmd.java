package fr.lip6.move.coloane.core.ui.commands.properties;

import fr.lip6.move.coloane.core.ui.model.IAttributeImpl;

public class ChangeAttributeCmd extends AbstractPropertieCmd {
	private IAttributeImpl attr;
	private String oldValue;
	private String newValue;

	public ChangeAttributeCmd(IAttributeImpl attr, String oldValue, String newValue) {
		this.attr = attr;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	@Override
	public final void execute() {
		if (!canExecute()) {
			return;
		}
		redo();
	}

	@Override
	public final void redo() {
		attr.setValue(oldValue, newValue);
	}

	@Override
	public final void undo() {
		attr.setValue(newValue, oldValue);
	}

}
