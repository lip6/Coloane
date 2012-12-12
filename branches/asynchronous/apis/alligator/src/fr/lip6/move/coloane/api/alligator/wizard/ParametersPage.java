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

import fr.lip6.move.coloane.api.alligator.dialog.BooleanDialogConstructor;
import fr.lip6.move.coloane.api.alligator.dialog.FloatDialogConstructor;
import fr.lip6.move.coloane.api.alligator.dialog.HelpDialogConstructor;
import fr.lip6.move.coloane.api.alligator.dialog.IntegerDialogConstructor;
import fr.lip6.move.coloane.api.alligator.dialog.ItemDialogConstructor;
import fr.lip6.move.coloane.api.alligator.dialog.MultiChoicesDialogConstructor;
import fr.lip6.move.coloane.api.alligator.dialog.SingleChoiceDialogConstructor;
import fr.lip6.move.coloane.api.alligator.dialog.StringDialogConstructor;
import fr.lip6.move.coloane.api.alligator.dialog.TextDialogConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.cosyverif.alligator.service.Parameter;
import org.cosyverif.alligator.service.Parameter.Direction;
import org.cosyverif.alligator.service.Service.Description;
import org.cosyverif.alligator.service.parameter.BooleanParameter;
import org.cosyverif.alligator.service.parameter.FileParameter;
import org.cosyverif.alligator.service.parameter.FloatParameter;
import org.cosyverif.alligator.service.parameter.ForeignModelParameter;
import org.cosyverif.alligator.service.parameter.IntegerParameter;
import org.cosyverif.alligator.service.parameter.ModelParameter;
import org.cosyverif.alligator.service.parameter.MultiLineTextParameter;
import org.cosyverif.alligator.service.parameter.MultipleChoiceParameter;
import org.cosyverif.alligator.service.parameter.SingleChoiceParameter;
import org.cosyverif.alligator.service.parameter.SingleLineTextParameter;
import org.eclipse.jface.resource.ImageDescriptor;
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
public final class ParametersPage extends WizardPage implements IWizardPage {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

	@SuppressWarnings("rawtypes")
	private static final Map<Class<? extends Parameter>, Class<? extends ItemDialogConstructor>> DIALOGS = new HashMap<Class<? extends Parameter>, Class<? extends ItemDialogConstructor>>();
	static {
		DIALOGS.put(SingleLineTextParameter.class, StringDialogConstructor.class);
		DIALOGS.put(MultiLineTextParameter.class, TextDialogConstructor.class);
		DIALOGS.put(BooleanParameter.class, BooleanDialogConstructor.class);
		DIALOGS.put(IntegerParameter.class, IntegerDialogConstructor.class);
		DIALOGS.put(FloatParameter.class, FloatDialogConstructor.class);
		DIALOGS.put(SingleChoiceParameter.class, SingleChoiceDialogConstructor.class);
		DIALOGS.put(MultipleChoiceParameter.class, MultiChoicesDialogConstructor.class);
	}

	private static final Map<String, Description> STORED_PARAMETERS = new HashMap<String, Description>();
	private final List<ItemDialogConstructor<?>> dialogs = new ArrayList<ItemDialogConstructor<?>>();
	
	private Description description;
	private List<Parameter<?>> parameters = new ArrayList<Parameter<?>>();

	public ParametersPage(Description service) {
		super("Parameters", "Parameters for " + service.getName(), ImageDescriptor.createFromFile(ParametersPage.class, "alligator-logo.png"));
		this.description = service;
		// Retrieve previously set values:
		Description previous = STORED_PARAMETERS.get(service.getIdentifier());
		if (previous == null) {
			previous = service;
		}
		// Select only parameters that are neither files nor models.
		for (Parameter<?> parameter: description.getParameters()) {
			if (parameter.isInput() && !(parameter instanceof ModelParameter) && !(parameter instanceof ForeignModelParameter) && !(parameter instanceof FileParameter)) {
				this.parameters.add(parameter);
				// Fill with previously set value:
				for (Parameter<?> p: previous.getParameters()) {
					if ((parameter != p) && parameter.isSame(p) && p.isSet()) {
						parameter.populate(p);
					}
				}
			}
		}
		Comparator<Parameter<?>> comparator = new Comparator<Parameter<?>>() {
			@Override
			public int compare(Parameter<?> lhs, Parameter<?> rhs) {
				return lhs.getName().compareToIgnoreCase(rhs.getName());
			}
		};
		Collections.sort(this.parameters, comparator);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(2, false);
		layout.marginLeft = 10;
		layout.marginRight = 10;
		layout.horizontalSpacing = 10;
		composite.setLayout(layout);
		for (Parameter<?> parameter: parameters) {
			Class<? extends ItemDialogConstructor> dialogClass = DIALOGS.get(parameter.getClass());
			if (dialogClass != null) {
				try {
					ItemDialogConstructor dialog = dialogClass.newInstance();
					dialog.create(composite, parameter);
					dialogs.add(dialog);
				} catch (InstantiationException e) {
					LOGGER.warning("Cannot instantiate the class : " + dialogClass.getName());
				} catch (IllegalAccessException e) {
					LOGGER.warning("Cannot access to the class : " + dialogClass.getName());
				}
			} else {
				String message = "Cannot find dialog for parameter type '" + parameter.getClass().getName() + "'.";
				LOGGER.warning(message);
				throw new AssertionError(message);
			}
		}
		// help zone
		HelpDialogConstructor hdc = new HelpDialogConstructor();
		hdc.create(composite, new SingleLineTextParameter(description.getName(), Direction.IN));
		dialogs.add(hdc);
		setControl(composite);
	}
	
	@Override
	public boolean isPageComplete() {
		for (ItemDialogConstructor<?> dialog : dialogs) {
			if (!dialog.isValid()) {
				return false;
			}
		}
		return true;
	}
	
	public void performFinish() {
		LOGGER.finer("Dispose the ParametersPage");
		for (ItemDialogConstructor<?> dialog : dialogs) {
			dialog.performFinish();
		}
		// Store values:
		STORED_PARAMETERS.put(description.getIdentifier(), description);
	}

}
