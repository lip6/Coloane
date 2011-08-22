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
package fr.lip6.move.coloane.api.alligator;

import fr.lip6.move.alligator.interfaces.DescriptionItem;
import fr.lip6.move.alligator.interfaces.Item;
import fr.lip6.move.alligator.interfaces.ItemType;
import fr.lip6.move.coloane.extensions.exporttogml.ExportToGML;
import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

/**
 * Dialog box built using the parameters of a service.
 *
 * @author Clément Démoulins
 */
public class ParametersDialog extends Dialog {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

	private static final ExportToGML GRAPH_TO_GML = new ExportToGML();

	private final IGraph model;
	private final List<DescriptionItem> descriptions;
	private final Map<String, Widget> data = new HashMap<String, Widget>();
	private final List<Item> params = new ArrayList<Item>();

	/**
	 * @param parentShell parent shell
	 * @param model current model
	 * @param descriptions list of description items to build the dialog box
	 */
	public ParametersDialog(Shell parentShell, IGraph model, List<DescriptionItem> descriptions) {
		super(parentShell);
		this.model = model;
		this.descriptions = descriptions;
	}

	/** {@inheritDoc}
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected final Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));

		GridLayout layout = new GridLayout(2, false);
		layout.marginLeft = 10;
		layout.horizontalSpacing = 10;
		composite.setLayout(layout);

		for (DescriptionItem description : descriptions) {
			if (description.getType() == ItemType.MODEL) {
				StringWriter writer = new StringWriter();
				try {
					GRAPH_TO_GML.export(model, writer, new NullProgressMonitor());
					params.add(new Item(ItemType.MODEL, description.getName(), writer.toString()));
				} catch (ExtensionException e) {
					LOGGER.warning("The model is not valid, it will not be sent");
				}

			} else if (description.getType() == ItemType.STRING) {
				Label label = new Label(composite, SWT.WRAP);
				label.setText(description.getName() + ":");
				label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));

				Text input = new Text(composite, SWT.BORDER | SWT.SINGLE);
				input.setText(description.getDefaultValue());
				input.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));

				data.put(description.getName(), input);

			} else if (description.getType() == ItemType.BOOLEAN) {
//				Label label = new Label(composite, SWT.WRAP);
//				label.setText(description.getName() + ":");
//				label.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, true, 1, 1));

				Button button = new Button(composite, SWT.CHECK);
				button.setSelection(Boolean.valueOf(description.getDefaultValue()));
				button.setText(description.getName());
				button.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 2, 1));
				data.put(description.getName(), button);

			} else if (description.getType() == ItemType.SINGLE_CHOICE) {
				Label label = new Label(composite, SWT.WRAP);
				label.setText(description.getName() + ":");
				label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));

				Combo combo = new Combo(composite, SWT.NONE);
				combo.setItems(description.getChoices().toArray(new String[0]));
				combo.select(Math.max(0, description.getChoices().indexOf(description.getDefaultValue())));
				combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
			}
		}

//		composite.pack();
		return composite;
	}

	/**
	 * @return list of items set by the user
	 */
	public final List<Item> getParams() {
		return params;
	}

	/** {@inheritDoc}
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	@Override
	protected final void okPressed() {
		for (Entry<String, Widget> entry : data.entrySet()) {
			if (entry.getValue() instanceof Text) {
				params.add(new Item(ItemType.STRING, entry.getKey(), ((Text) entry.getValue()).getText()));
			} else if (entry.getValue() instanceof Button && (entry.getValue().getStyle() & SWT.CHECK) == SWT.CHECK) {
				params.add(new Item(ItemType.BOOLEAN, entry.getKey(), String.valueOf(((Button) entry.getValue()).getSelection())));
			}
		}

		super.okPressed();
	}
}
