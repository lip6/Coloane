/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.core.ui.commands;

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
