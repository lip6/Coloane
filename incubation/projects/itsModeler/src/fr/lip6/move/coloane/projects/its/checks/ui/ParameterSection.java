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
 *   Yann THIERRY-MIEG (LIP6)
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.projects.its.checks.ui;

import fr.lip6.move.coloane.projects.its.checks.ParameterList;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

public class ParameterSection {

	ParameterList input;
	private Map<Text, String> params = new HashMap<Text, String>();
	private Map<Button, String> bparams = new HashMap<Button, String>();
	private FormToolkit toolkit;
	private Section section;
	private Composite parent;
	private boolean isEditable;
	private String title;

	public ParameterSection(String title, final FormToolkit formToolkit,
			Composite parent, boolean isEditable) {
		toolkit = formToolkit;
		this.parent = parent;
		this.isEditable = isEditable;
		this.title = title;
	}

	public ParameterList getInput() {
		return input;
	}

	public void setInput(ParameterList input) {
		if (input != this.input) {
			this.input = input;
			createDetails(parent);
		}
		update();
	}

	public void update() {
		for (Entry<Text, String> entry : params.entrySet()) {
			String s = (input != null && input.getParameterValue(entry
					.getValue()) != null) ? getInput().getParameterValue(
					entry.getValue()) : "";
			entry.getKey().setText(s);
		}
		for (Entry<Button, String> entry : bparams.entrySet()) {
			Boolean val = (input != null && input.getBoolParameterValue(entry
					.getValue()) != null) ? getInput().getBoolParameterValue(
					entry.getValue()) : true;
			entry.getKey().setSelection(val);
		}
	}

	protected void createDetails(Composite parent) {
		if (section != null) {
			section.dispose();
			params.clear();
			bparams.clear();
			section = null;
		}
		if (!input.getParameters().isEmpty()) {
			section = toolkit.createSection(parent,
					ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE
							| ExpandableComposite.EXPANDED);
			section.marginWidth = 4;
			section.marginHeight = 4;
			section.setText(title);
			TableWrapData td = new TableWrapData(TableWrapData.FILL,
					TableWrapData.TOP);
			td.grabHorizontal = true;
			section.setLayoutData(td);
			Composite client = toolkit.createComposite(section);
			GridLayout glayout = new GridLayout();
			glayout.marginWidth = 10;
			glayout.marginHeight = 5;
			glayout.numColumns = 2;
			client.setLayout(glayout);
			for (String param : input.getParameters()) {
				Label lab = toolkit.createLabel(client, param);
				lab.setToolTipText(input.getToolTip(param));
				Text tf = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
				tf.setEditable(isEditable);
				GridData gd = new GridData(GridData.FILL_HORIZONTAL
						| GridData.VERTICAL_ALIGN_BEGINNING);
				gd.widthHint = 10;
				tf.setLayoutData(gd);
				tf.setToolTipText(input.getToolTip(param));
				// store this param
				params.put(tf, param);
				if (isEditable) {
					tf.addModifyListener(new ParamListener(param));
				}
			}
			for (String param : input.getBoolParameters()) {
				//toolkit.createLabel(client, param); //$NON-NLS-1$
				Button b = toolkit.createButton(client, param, SWT.CHECK);
				b.setEnabled(isEditable);
				b.setToolTipText(input.getToolTip(param));
				// store this param
				bparams.put(b, param);
				if (isEditable) {
					b.addSelectionListener(new BoolParamListener(param));
				}
			}

			toolkit.paintBordersFor(section);
			toolkit.paintBordersFor(client);
			section.setClient(client);
			parent.pack();
		}
	}

	private class BoolParamListener implements SelectionListener {

		private String param;

		public BoolParamListener(String param) {
			this.param = param;
		}

		public void widgetDefaultSelected(SelectionEvent e) {

		}

		public void widgetSelected(SelectionEvent e) {
			if (getInput() != null) {
				for (Entry<Button, String> entry : bparams.entrySet()) {
					if (entry.getValue().equals(param)) {
						Boolean b = entry.getKey().getSelection();
						getInput().setBoolParameterValue(param, b);
						break;
					}
				}
			}
		}

	}

	private class ParamListener implements ModifyListener {

		private String param;

		public ParamListener(String param) {
			this.param = param;
		}

		public void modifyText(ModifyEvent e) {
			if (getInput() != null) {
				for (Entry<Text, String> entry : params.entrySet()) {
					if (entry.getValue().equals(param)) {
						String s = entry.getKey().getText();
						getInput().setParameterValue(param, s);
						break;
					}
				}
			}
		}
	}

}
