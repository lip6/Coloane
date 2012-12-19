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

import org.cosyverif.alligator.service.Description;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Yann Thierry-Mieg, based on other Dialogs.
 */
public final class HelpDialogConstructor {

	private final ParametersPage page;
	private final Label label;
	private final Text input;

	public HelpDialogConstructor(final ParametersPage page, final Composite parent, final Description description) {
		this.page = page;
		this.label = new Label(parent, SWT.WRAP);
		this.label.setText("Service " + description.getName() + ":");
		this.label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		this.input = new Text(parent, SWT.BORDER | SWT.MULTI);
		this.input.setText(description.getHelp());
		this.input.setEditable(false);
		GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		layoutData.heightHint = 50;
		this.input.setLayoutData(layoutData);
		this.input.setEnabled(false);
	}

}
