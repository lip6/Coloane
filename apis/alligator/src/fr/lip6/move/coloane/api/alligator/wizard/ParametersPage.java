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
package fr.lip6.move.coloane.api.alligator.wizard;

import fr.lip6.move.alligator.interfaces.DescriptionItem;
import fr.lip6.move.alligator.interfaces.Item;
import fr.lip6.move.alligator.interfaces.ItemType;
import fr.lip6.move.coloane.api.alligator.dialog.BooleanDialogConstructor;
import fr.lip6.move.coloane.api.alligator.dialog.HelpDialogConstructor;
import fr.lip6.move.coloane.api.alligator.dialog.ItemDialogConstructor;
import fr.lip6.move.coloane.api.alligator.dialog.MultiChoicesDialogConstructor;
import fr.lip6.move.coloane.api.alligator.dialog.SingleChoiceDialogConstructor;
import fr.lip6.move.coloane.api.alligator.dialog.StringDialogConstructor;
import fr.lip6.move.coloane.api.alligator.dialog.TextDialogConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * Wizard page to create a custom page from a list of DescriptionItem provided by an Alligator Service.
 * 
 * @author Clément Démoulins
 */
public class ParametersPage extends WizardPage implements IWizardPage {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

	private static final Map<ItemType, Class<? extends ItemDialogConstructor>> ITEM_TYPES = new HashMap<ItemType, Class<? extends ItemDialogConstructor>>();
	static {
		ITEM_TYPES.put(ItemType.STRING, StringDialogConstructor.class);
		ITEM_TYPES.put(ItemType.TEXT, TextDialogConstructor.class);
		ITEM_TYPES.put(ItemType.BOOLEAN, BooleanDialogConstructor.class);
		ITEM_TYPES.put(ItemType.SINGLE_CHOICE, SingleChoiceDialogConstructor.class);
		ITEM_TYPES.put(ItemType.MULTI_CHOICES, MultiChoicesDialogConstructor.class);
	}

	private final List<DescriptionItem> descriptions;
	private final List<ItemDialogConstructor> parts = new ArrayList<ItemDialogConstructor>();

	private String serviceDescription;
	
	private static Map<String, List<Item>> storedParameters = new HashMap<String, List<Item>>(); 
	
	/**
	 * @param pageName Name for this page
	 * @param descriptions list of DescriptionItem to create a custom page
	 * @param serviceDescription the short description of the service
	 */
	public ParametersPage(String pageName, List<DescriptionItem> descriptions, String serviceDescription) {
		super(pageName);
		setTitle(pageName);
		this.serviceDescription = serviceDescription;
		this.descriptions = descriptions;
	}

	/** {@inheritDoc}
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public final void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		GridLayout layout = new GridLayout(2, false);
		layout.marginLeft = 10;
		layout.marginRight = 10;
		layout.horizontalSpacing = 10;
		composite.setLayout(layout);

		for (DescriptionItem description : descriptions) {
			// Models are selected by the ChooseModelsPage wizard page
			if (description.getType() == ItemType.MODEL) {
				continue;
			}

			Class<? extends ItemDialogConstructor> partClass = ITEM_TYPES.get(description.getType());
			if (partClass != null) {
				try {
					ItemDialogConstructor part = partClass.newInstance();
					part.create(composite, description);
					parts.add(part);
				} catch (InstantiationException e) {
					LOGGER.warning("Cannot instantiate the class : " + partClass);
				} catch (IllegalAccessException e) {
					LOGGER.warning("Cannot access to the class : " + partClass);
				}
			} else {
				StringDialogConstructor part = new StringDialogConstructor();
				part.create(parent, description);
				parts.add(part);
			}
		}

		{
			// help zone
			HelpDialogConstructor hdc = new HelpDialogConstructor();
			hdc.create(composite, new DescriptionItem(ItemType.STRING, serviceDescription));
			parts.add(hdc);
		}
		
		List<Item> oldValues = storedParameters.get(getName());
		if (oldValues != null) {
			for (ItemDialogConstructor part : parts) {
				part.setParameterValues(oldValues);
			}
		}
		
		setControl(composite);
	}

	/**
	 * Get the list of parameters, this method must be called before the dispose method.
	 * @return list of the parameters
	 */
	public final List<Item> getParameters() {
		List<Item> params = new ArrayList<Item>();
		for (ItemDialogConstructor part : parts) {
			params.addAll(part.getParameters());
		}
		
		// store for possible further use.
		storedParameters.put(getName(),params);
		
		return params;
	}

	/** {@inheritDoc}
	 * @see org.eclipse.jface.dialogs.DialogPage#dispose()
	 */
	@Override
	public final void dispose() {
		LOGGER.finer("Dispose the ParametersPage");
		for (ItemDialogConstructor part : parts) {
			part.dispose();
		}
		super.dispose();
	}

}
