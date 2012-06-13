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
package fr.lip6.move.coloane.api.alligator.dialog;

import fr.lip6.move.alligator.interfaces.DescriptionItem;
import fr.lip6.move.alligator.interfaces.Item;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

/**
 * @author Clément Démoulins
 */
public interface ItemDialogConstructor {

	/**
	 * Create a part of the {@link ParametersDialog}
	 * @param parent parent composite
	 * @param description description of the item
	 */
	void create(Composite parent, DescriptionItem description);

	/**
	 * @return list of parameters for this item
	 */
	List<Item> getParameters();

	/**
	 * Dispose this object
	 */
	void dispose();

	/**
	 * Update the values shown for the parameters using previous values.
	 * @param oldValues previously provided values (may include Items that are not those of this ItemDialog)
	 */
	void setParameterValues(List<Item> oldValues);
}
