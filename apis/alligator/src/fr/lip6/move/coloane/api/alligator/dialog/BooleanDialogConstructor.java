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

import org.cosyverif.alligator.service.parameter.BooleanParameter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Clément Démoulins
 */
public final class BooleanDialogConstructor implements ItemDialogConstructor<BooleanParameter> {

	private Button button;
	private BooleanParameter parameter;

	@Override
	public void create(Composite parent, BooleanParameter parameter) {
		this.parameter = parameter;
		this.button = new Button(parent, SWT.CHECK);
		if (parameter.isSet()) {
			button.setSelection(parameter.getValue());
		} else 	if (parameter.hasDefaultValue()) {
			this.button.setSelection(parameter.getDefaultValue());
		}
		this.button.setText(parameter.getName());
		this.button.setToolTipText(parameter.getHelp());
		this.button.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public void reset() {
		button.setSelection(parameter.getDefaultValue());
	}

	@Override
	public void performFinish() {
		parameter.setValue(button.getSelection());
	}
	
	

}
