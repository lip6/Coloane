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

import org.cosyverif.alligator.service.parameter.BooleanParameter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Clément Démoulins
 */
public final class BooleanDialogConstructor implements ItemDialog<BooleanParameter> {

	private final ParametersPage page;
	private final Button button;
	private final BooleanParameter parameter;

	public BooleanDialogConstructor(final ParametersPage page, final Composite parent, final BooleanParameter parameter) {
		this.parameter = parameter;
		this.page = page;
		this.button = new Button(parent, SWT.CHECK);
		this.button.setText(parameter.getName());
		this.button.setToolTipText(parameter.getHelp());
		this.button.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		SelectionListener listener = new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				page.setPageComplete(page.isPageComplete());
				parameter.setValue(button.getSelection());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				page.setPageComplete(page.isPageComplete());
				parameter.setValue(button.getSelection());
			}

		};
		this.button.addSelectionListener(listener);
		if (parameter.isActualParameter()) {
			button.setSelection(parameter.getValue());
		} else 	if (parameter.hasDefaultValue()) {
			parameter.setValue(parameter.getDefaultValue());
			this.button.setSelection(parameter.getDefaultValue());
		} else {
			parameter.setValue(false);
			this.button.setSelection(false);
		}
		button.setEnabled(page.enabled);
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public void reset() {
		parameter.unset();
		try {
			button.setSelection(parameter.getDefaultValue());
		} catch (IllegalArgumentException e) {
			button.setSelection(false);
		}
	}

	@Override
	public void performFinish() {
	}
	
	

}
