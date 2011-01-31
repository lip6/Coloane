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
package fr.lip6.move.coloane.core.ui.actions;

import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;

import java.util.logging.Logger;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;

/**
 * Define an action that will manage the state of an option.<br>
 */
public class OptionAction extends Action {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/**
	 * Constructor
	 * @param optionDescription The description of the option
	 */
	public OptionAction(IOptionMenu optionDescription) {
		super(optionDescription.getName(), convertStyle(optionDescription.getType()));
		setId(optionDescription.getName());
		setChecked(optionDescription.isVisible());
	}

	/**
	 * Convert a IOptionType to a type understandable by an IAction
	 * @param style option style
	 * @return a IAction style 
	 */
	private static int convertStyle(int style) {
		if (style == IOptionMenu.TYPE_CHECKBOX) {
			return IAction.AS_CHECK_BOX;
		} else if (style == IOptionMenu.TYPE_RADIO) {
			return IAction.AS_RADIO_BUTTON;
		} else {
			return IAction.AS_UNSPECIFIED;
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void run() {
		LOGGER.fine("Option switch : " + getId() + " = " + isChecked()); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
