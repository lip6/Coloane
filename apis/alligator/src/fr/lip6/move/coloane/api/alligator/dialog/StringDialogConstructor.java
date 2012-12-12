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

import org.cosyverif.alligator.service.parameter.SingleLineTextParameter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Clément Démoulins
 */
public final class StringDialogConstructor implements ItemDialogConstructor<SingleLineTextParameter> {

	private Text input;
	private Label label;
	private SingleLineTextParameter parameter;

	@Override
	public void create(Composite parent, SingleLineTextParameter parameter) {
		this.parameter = parameter;

		this.label = new Label(parent, SWT.WRAP);
		this.label.setText(parameter.getName() + ":");
		this.label.setToolTipText(parameter.getHelp());
		this.label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));

		this.input = new Text(parent, SWT.BORDER | SWT.SINGLE);
		if (parameter.isSet()) {
			input.setText(parameter.getValue());
		} else if (parameter.hasDefaultValue()) {
			this.input.setText(parameter.getDefaultValue());
		}
		this.input.setToolTipText(parameter.getHelp());
		this.input.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public void reset() {
		input.setText(parameter.getDefaultValue());
	}

	@Override
	public void performFinish() {
		parameter.setValue(input.getText());
	}

}
