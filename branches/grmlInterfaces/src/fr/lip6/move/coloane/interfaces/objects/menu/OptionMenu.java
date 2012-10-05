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
package fr.lip6.move.coloane.interfaces.objects.menu;

/**
 * Define an option.<br>
 * An option provide the same features as a <b>checkbox</b> in a form.
 *
 * @author Jean-Baptiste Voron
 */
public class OptionMenu extends ItemMenu implements IOptionMenu {
	/** Current state */
	private boolean checked;

	/**
	 * Constructor
	 * @param name The name of the option
	 * @param visible The visibility of the option
	 * @param helpMessage An help message associated with the option
	 * @param checked The state of the option
	 */
	public OptionMenu(String name, boolean visible, String helpMessage, boolean checked) {
		super(name, visible, helpMessage);
		this.checked = checked;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isChecked() {
		return this.checked;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getType() {
		return IOptionMenu.TYPE_CHECKBOX;
	}
}
