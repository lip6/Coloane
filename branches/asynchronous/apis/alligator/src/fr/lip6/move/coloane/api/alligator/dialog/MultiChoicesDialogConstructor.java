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

import org.cosyverif.alligator.service.parameter.MultipleChoiceParameter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Clément Démoulins
 */
public final class MultiChoicesDialogConstructor implements ItemDialogConstructor<MultipleChoiceParameter> {

	private MultipleChoiceParameter parameter;
	private org.eclipse.swt.widgets.List list;
	private Label label;

	@Override
	public void create(Composite parent, MultipleChoiceParameter parameter) {
		this.parameter = parameter;

		this.label = new Label(parent, SWT.WRAP);
		this.label.setText(parameter.getName() + ":");
		this.label.setToolTipText(parameter.getHelp());
		this.label.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));

		this.list = new org.eclipse.swt.widgets.List(parent, SWT.MULTI | SWT.BORDER);
		this.list.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		for (String choice : parameter.getChoices()) {
			this.list.add(choice);
		}
		if (parameter.isSet()) {
			for (String choice : parameter.getChoices()) {
				if (parameter.getChosenValues().contains(choice)) {
					this.list.select(Math.max(0, parameter.getChoices().indexOf(choice)));
				} else {
					this.list.deselect(Math.max(0, parameter.getChoices().indexOf(choice)));
				}
			}
		} else if (parameter.hasDefaultSelection()) {
			for (String choice : parameter.getChoices()) {
				if (parameter.getDefaultValues().contains(choice)) {
					this.list.select(Math.max(0, parameter.getChoices().indexOf(choice)));
				} else {
					this.list.deselect(Math.max(0, parameter.getChoices().indexOf(choice)));
				}
			}
		}
		this.list.setToolTipText(parameter.getHelp());
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public void reset() {
		for (String choice : parameter.getChoices()) {
			if (parameter.hasDefaultSelection() && (parameter.getDefaultValues().contains(choice))) {
				this.list.select(Math.max(0, parameter.getChoices().indexOf(choice)));
			} else {
				this.list.deselect(Math.max(0, parameter.getChoices().indexOf(choice)));
			}
		}
	}

	@Override
	public void performFinish() {
		for (String selected: list.getSelection()) {
			parameter.addChosenValue(selected);
		}
	}

}
