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

import org.cosyverif.alligator.service.parameter.MultipleChoiceParameter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Clément Démoulins
 */
public final class MultiChoicesDialogConstructor implements ItemDialog<MultipleChoiceParameter> {

	private final ParametersPage page;
	private final MultipleChoiceParameter parameter;
	private final org.eclipse.swt.widgets.List list;
	private final Label label;

	public MultiChoicesDialogConstructor(final ParametersPage page, final Composite parent, final MultipleChoiceParameter parameter) {
		this.parameter = parameter;
		this.page = page;
		this.label = new Label(parent, SWT.WRAP);
		this.label.setText(parameter.getName() + ":");
		this.label.setToolTipText(parameter.getHelp());
		this.label.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));

		this.list = new org.eclipse.swt.widgets.List(parent, SWT.MULTI | SWT.BORDER);
		this.list.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		for (String choice : parameter.getChoices()) {
			this.list.add(choice);
		}
		SelectionListener listener = new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				page.setPageComplete(page.isPageComplete());
				parameter.resetValues();
				for (String selected: list.getSelection()) {
					parameter.selectValue(selected);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				page.setPageComplete(page.isPageComplete());
				parameter.resetValues();
				for (String selected: list.getSelection()) {
					parameter.selectValue(selected);
				}
			}

		};
		list.addSelectionListener(listener);
		if (parameter.isActualParameter()) {
			for (String choice : parameter.getChoices()) {
				if (parameter.getValues().contains(choice)) {
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
		} else {
			parameter.resetValues();
		}
		this.list.setToolTipText(parameter.getHelp());
		this.list.setEnabled(page.enabled);
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public void reset() {
		parameter.resetValues();
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
	}

}
