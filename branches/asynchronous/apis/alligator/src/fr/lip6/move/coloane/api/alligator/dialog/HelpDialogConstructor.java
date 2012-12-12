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
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scrollable;
import org.eclipse.swt.widgets.Text;

/**
 * @author Yann Thierry-Mieg, based on other Dialogs.
 */
public final class HelpDialogConstructor implements ItemDialogConstructor<SingleLineTextParameter> {

	private Label label;
	private Scrollable input;

	/** {@inheritDoc}
	 * @see fr.lip6.move.coloane.api.alligator.dialog.ItemDialogConstructor#create(org.eclipse.swt.widgets.Composite, fr.lip6.move.alligator.interfaces.DescriptionItem)
	 */
	public void create(Composite parent, SingleLineTextParameter parameter) {
		this.label = new Label(parent, SWT.WRAP);
		this.label.setText("Description " + ":");
		this.label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));

		try {
			Browser browser = new Browser(parent, SWT.BORDER | SWT.MULTI);
			browser.setText(parameter.getName());
			this.input = browser;
		} catch (SWTError e) {
			Text text = new Text(parent, SWT.BORDER | SWT.MULTI);
			text.setText(parameter.getName());
			text.setEditable(false);
			this.input = text;
		}
		GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		layoutData.heightHint = 50;
		this.input.setLayoutData(layoutData);
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public void reset() {		
	}

	@Override
	public void performFinish() {		
	}

}
