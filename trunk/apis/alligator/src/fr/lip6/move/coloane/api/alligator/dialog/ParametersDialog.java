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
import fr.lip6.move.alligator.interfaces.ItemType;
import fr.lip6.move.coloane.extensions.exporttogml.ExportToGML;
import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * Dialog box built using the parameters of a service.
 *
 * @author Clément Démoulins
 */
public class ParametersDialog extends Dialog {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

	private static final ExportToGML GRAPH_TO_GML = new ExportToGML();

	private static final Map<ItemType, Class<? extends ItemDialogConstructor>> ITEM_TYPES = new HashMap<ItemType, Class<? extends ItemDialogConstructor>>();
	static {
		ITEM_TYPES.put(ItemType.STRING, StringDialogConstructor.class);
		ITEM_TYPES.put(ItemType.TEXT, TextDialogConstructor.class);
		ITEM_TYPES.put(ItemType.BOOLEAN, BooleanDialogConstructor.class);
		ITEM_TYPES.put(ItemType.SINGLE_CHOICE, SingleChoiceDialogConstructor.class);
		ITEM_TYPES.put(ItemType.MULTI_CHOICES, MultiChoicesDialogConstructor.class);
	}

	private final IGraph model;
	private final List<DescriptionItem> descriptions;
	private final List<ItemDialogConstructor> parts = new ArrayList<ItemDialogConstructor>();
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
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		GridLayout layout = new GridLayout(2, false);
		layout.marginLeft = 10;
		layout.marginRight = 10;
		layout.horizontalSpacing = 10;
		composite.setLayout(layout);

		for (DescriptionItem description : descriptions) {
			if (ITEM_TYPES.containsKey(description.getType())) {
				try {
					ItemDialogConstructor part = ITEM_TYPES.get(description.getType()).newInstance();
					part.create(composite, description);
					parts.add(part);
				} catch (InstantiationException e) {
					LOGGER.warning("Cannot create the ItemDialogConstructor: " + ITEM_TYPES.get(description.getType()));
				} catch (IllegalAccessException e) {
					LOGGER.warning("Cannot create the ItemDialogConstructor: " + ITEM_TYPES.get(description.getType()));
				}
			} else if (description.getType() == ItemType.MODEL) {
				StringWriter writer = new StringWriter();
				try {
					GRAPH_TO_GML.export(model, writer, new NullProgressMonitor());
					params.add(new Item(ItemType.MODEL, description.getName(), writer.toString()));
				} catch (ExtensionException e) {
					LOGGER.warning("The model is not valid, it will not be sent");
				}
			} else {
				// Use StringDialogConstructor as default
				StringDialogConstructor part = new StringDialogConstructor();
				part.create(composite, description);
				parts.add(part);
			}
		}

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
		for (ItemDialogConstructor part : parts) {
			params.addAll(part.getParameters());
		}

		super.okPressed();
	}

	/** {@inheritDoc}
	 * @see org.eclipse.jface.dialogs.Dialog#close()
	 */
	@Override
	public final boolean close() {
		for (ItemDialogConstructor part : parts) {
			part.dispose();
		}
		return super.close();
	}
}
