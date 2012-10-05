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
public interface IOptionMenu extends IItemMenu {
	/** Checkbox type for 0:N selection */
	int TYPE_CHECKBOX = 0;

	/** Radio type for 0:1 selection */
	int TYPE_RADIO = 1;

	/**
     * @return <code>true</code> if the option is checked. <code>false</code> otherwise
     */
    boolean isChecked();

    /**
     * @return the kind of the option
     */
    int getType();
}
