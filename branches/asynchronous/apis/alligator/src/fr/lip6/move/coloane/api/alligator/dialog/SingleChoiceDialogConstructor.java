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

import org.cosyverif.alligator.service.parameter.SingleChoiceParameter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Clément Démoulins
 */
public final class SingleChoiceDialogConstructor implements ItemDialogConstructor<SingleChoiceParameter> {

	private Combo combo;
	private SingleChoiceParameter parameter;
	private Label label;

	@Override
	public void create(Composite parent, SingleChoiceParameter parameter) {
		this.parameter = parameter;

		this.label = new Label(parent, SWT.WRAP);
		this.label.setText(parameter.getName() + ":");
		this.label.setToolTipText(parameter.getHelp());
		this.label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));

		this.combo = new Combo(parent, SWT.NONE);
		this.combo.setItems(parameter.getChoices().toArray(new String[0]));
		this.combo.select(Math.max(0, parameter.getChoices().indexOf(parameter.getDefaultValue())));
		this.combo.setToolTipText(parameter.getHelp());
		this.combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		if (parameter.isSet()) {
			combo.select(Math.max(0, parameter.getChoices().indexOf(parameter.getChosenValue())));
		} else if (parameter.hasDefaultSelection()) {
			combo.select(Math.max(0, parameter.getChoices().indexOf(parameter.getDefaultValue())));
		}
	}

	@Override
	public boolean isValid() {
		return !combo.getText().equals("");
	}

	@Override
	public void reset() {
		combo.select(Math.max(0, parameter.getChoices().indexOf(parameter.getDefaultValue())));
	}

	@Override
	public void performFinish() {
		parameter.setChosenValue(combo.getText());
	}

}
