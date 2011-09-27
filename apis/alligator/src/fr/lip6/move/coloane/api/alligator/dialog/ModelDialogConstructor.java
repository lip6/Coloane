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

import java.util.Collections;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

/**
 * @author Clément Démoulins
 */
public class ModelDialogConstructor implements ItemDialogConstructor {
	
	private Text input;
	private Label label;
	
	/** {@inheritDoc}
	 * @see fr.lip6.move.coloane.api.alligator.dialog.ItemDialogConstructor#create(org.eclipse.swt.widgets.Composite, fr.lip6.move.alligator.interfaces.DescriptionItem)
	 */
	public final void create(Composite parent, DescriptionItem description) {
		this.label = new Label(parent, SWT.WRAP);
		this.label.setText(description.getName() + ":");
		this.label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));

		this.input = new Text(parent, SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY);

		try {
			this.input.setText(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getEditorInput().getName());
		} catch (NullPointerException e) {
			this.input.setText("");
		}
		this.input.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	}

	/** {@inheritDoc}
	 * @see fr.lip6.move.coloane.api.alligator.dialog.ItemDialogConstructor#getParameters()
	 */
	public final List<Item> getParameters() {
		try {
			return Collections.emptyList();
		} finally {
			input.dispose();
		}
	}

	/** {@inheritDoc}
	 * @see fr.lip6.move.coloane.api.alligator.dialog.ItemDialogConstructor#dispose()
	 */
	public final void dispose() {
		label.dispose();
		input.dispose();
	}

}
