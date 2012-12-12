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

import org.cosyverif.alligator.service.parameter.IntegerParameter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Clément Démoulins
 */
public final class IntegerDialogConstructor implements ItemDialogConstructor<IntegerParameter> {

	private Text input;
	private Label label;
	private IntegerParameter parameter;

	@Override
	public void create(Composite parent, IntegerParameter parameter) {
		this.parameter = parameter;

		this.label = new Label(parent, SWT.WRAP);
		this.label.setText(parameter.getName() + ":");
		this.label.setToolTipText(parameter.getHelp());
		this.label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));

		this.input = new Text(parent, SWT.BORDER | SWT.SINGLE);
		if (parameter.isSet()) {
			input.setText(parameter.getValue().toString());
		} else if (parameter.hasDefaultValue()) {
			this.input.setText(parameter.getDefaultValue().toString());
		}
		this.input.setToolTipText(parameter.getHelp());
		this.input.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	}

	@Override
	public boolean isValid() {
		try {
			Integer.valueOf(input.getText());
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public void reset() {
		input.setText(parameter.getDefaultValue().toString());
	}

	@Override
	public void performFinish() {
		input.setText(parameter.getValue().toString());
	}
	
}
