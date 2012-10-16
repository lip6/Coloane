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
import fr.lip6.move.coloane.core.main.Coloane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Clément Démoulins
 */
public class TextDialogConstructor implements ItemDialogConstructor {

	private DescriptionItem description;
	private Label label;
	private Text input;
	private Button importButton;

	/** {@inheritDoc}
	 * @see fr.lip6.move.coloane.api.alligator.dialog.ItemDialogConstructor#create(org.eclipse.swt.widgets.Composite, fr.lip6.move.alligator.interfaces.DescriptionItem)
	 */
	public final void create(final Composite parent, DescriptionItem description) {
		this.description = description;

		this.label = new Label(parent, SWT.WRAP);
		this.label.setText(description.getName() + ":");
		this.label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));

		final Text inputText = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		inputText.setText(description.getDefaultValue());
		GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		layoutData.heightHint = 50;
		inputText.setLayoutData(layoutData);
		this.input = inputText;

		this.importButton = new Button(parent, SWT.PUSH);
		this.importButton.setText("Import…");
		this.importButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 2, 1));
		this.importButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(parent.getShell(), SWT.OPEN);
				final String filePath = dialog.open();
				if (filePath != null) {
					parent.getDisplay().asyncExec(new Runnable() {
						public void run() {
							try {
								BufferedReader reader = new BufferedReader(new FileReader(filePath));
								StringBuilder sb = new StringBuilder();
								int c;
								while ((c = reader.read()) != -1) {
									sb.append((char) c);
								}
								inputText.setText(sb.toString());
							} catch (IOException e) {
								Coloane.showErrorMsg(e.getLocalizedMessage());
							}
						}
					});
				}
			}
		});
	}

	/** {@inheritDoc}
	 * @see fr.lip6.move.coloane.api.alligator.dialog.ItemDialogConstructor#setParameterValues(java.util.List)
	 */
	public final void setParameterValues(List<Item> oldValues) {
		for (Item item : oldValues) {
			if (item.getName().equals(description.getName())) {
				input.setText(item.getValue());
				return;
			}
		}
	}
	
	/** {@inheritDoc}
	 * @see fr.lip6.move.coloane.api.alligator.dialog.ItemDialogConstructor#getParameters()
	 */
	public final List<Item> getParameters() {
		return Collections.singletonList(new Item(description.getType(), description.getName(), input.getText()));
	}

	/** {@inheritDoc}
	 * @see fr.lip6.move.coloane.api.alligator.dialog.ItemDialogConstructor#dispose()
	 */
	public final void dispose() {
		label.dispose();
		input.dispose();
	}

}
