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
