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

import fr.lip6.move.coloane.api.alligator.wizard.ParametersPage;

import org.cosyverif.alligator.service.parameter.SingleChoiceParameter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Clément Démoulins
 */
public final class SingleChoiceDialogConstructor implements ItemDialog<SingleChoiceParameter> {

	private final ParametersPage page;
	private final Combo combo;
	private final SingleChoiceParameter parameter;
	private final Label label;

	public SingleChoiceDialogConstructor(final ParametersPage page, final Composite parent, final SingleChoiceParameter parameter) {
		this.parameter = parameter;
		this.page = page;
		this.label = new Label(parent, SWT.WRAP);
		this.label.setText(parameter.getName() + ":");
		this.label.setToolTipText(parameter.getHelp());
		this.label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));

		this.combo = new Combo(parent, SWT.NONE);
		this.combo.setItems(parameter.getChoices().toArray(new String[0]));
		this.combo.setToolTipText(parameter.getHelp());
		this.combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		ModifyListener listener = new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				page.setPageComplete(page.isPageComplete());
				if (isValid()) {
					parameter.setValue(combo.getText());
				}
			}

		};
		this.combo.addModifyListener(listener);
		if (parameter.isActualParameter()) {
			combo.select(Math.max(0, parameter.getChoices().indexOf(parameter.getValue())));
		} else if (parameter.hasDefaultSelection()) {
			combo.select(Math.max(0, parameter.getChoices().indexOf(parameter.getDefaultValue())));
		}
		this.combo.setEnabled(page.enabled);
	}

	@Override
	public boolean isValid() {
		if (combo.getText().equals("")) {
			page.addError(this, "For parameter '" + parameter.getName() + "', value is not selected.");
			return false;
		} else {
			page.removeError(this);
			return true;
		}
	}

	@Override
	public void reset() {
		parameter.unset();
		try {
			combo.select(Math.max(0, parameter.getChoices().indexOf(parameter.getDefaultValue())));
		} catch (IllegalArgumentException e) {
			combo.select(-1);
		}
	}

	@Override
	public void performFinish() {
	}

}
