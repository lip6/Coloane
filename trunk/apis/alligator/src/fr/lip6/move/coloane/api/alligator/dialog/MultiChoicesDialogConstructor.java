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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Clément Démoulins
 */
public class MultiChoicesDialogConstructor implements ItemDialogConstructor {

	private DescriptionItem description;
	private org.eclipse.swt.widgets.List list;

	/** {@inheritDoc}
	 * @see fr.lip6.move.coloane.api.alligator.dialog.ItemDialogConstructor#create(org.eclipse.swt.widgets.Composite, fr.lip6.move.alligator.interfaces.DescriptionItem)
	 */
	public final void create(Composite parent, DescriptionItem description) {
		this.description = description;

		Label label = new Label(parent, SWT.WRAP);
		label.setText(description.getName() + ":");
		label.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));

		this.list = new org.eclipse.swt.widgets.List(parent, SWT.MULTI | SWT.BORDER);
		this.list.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1));
		for (String choice : description.getChoices()) {
			this.list.add(choice);
		}
	}

	/** {@inheritDoc}
	 * @see fr.lip6.move.coloane.api.alligator.dialog.ItemDialogConstructor#getParameters()
	 */
	public final List<Item> getParameters() {
		List<Item> parameters = new ArrayList<Item>();
		for (String selection : list.getSelection()) {
			parameters.add(new Item(description.getType(), description.getName(), selection));
		}
		return parameters;
	}

}
