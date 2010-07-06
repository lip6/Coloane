package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.ui.checker.CheckableCmd;
import fr.lip6.move.coloane.interfaces.model.IAttribute;

/**
 * Change the value of an attribute
 * 
 * @author Jean-Baptiste Voron
 */
public class AttributeEditCmd extends CheckableCmd {

	/** New attribute value */
	private String newValue = ""; //$NON-NLS-1$

	/** Old value (backup in case of <i>undo</i>) */
	private String oldValue;

	/** The attribute */
	private IAttribute attribute;

	/**
	 * Constructor
	 * @param attribute The attribute to update
	 * @param value The new value of the attribute
	 */
	public AttributeEditCmd(IAttribute attribute, String value) {
		super(Messages.AttributeEditCommand_0);
		this.attribute = attribute;
		if (value != null) {
			newValue = value;
		}
		
		// Check the attribute locally
		addCheckableElement(attribute.getReference());
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		this.oldValue = this.attribute.getValue();
		this.redo();
	}
	
	@Override
	public void redo() {
		this.attribute.setValue(this.newValue);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		attribute.setValue(this.oldValue);
	}
}
