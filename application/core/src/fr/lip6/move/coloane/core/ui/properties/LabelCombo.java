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
package fr.lip6.move.coloane.core.ui.properties;

import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * A class/widget to represent a label and a editor combo.
 * Handles pretty positions and alignment as required by LabelTextFactory
 * @author Yann TM
 */
public final class LabelCombo implements IAttributeLabel {

	/** The label */
	private CLabel label;
	/** The container */
	private Composite parent;
	/** The combo */
	private CCombo combo;

	/**
	 * The public constructor, not meant to be called by clients.
	 * Use {@link LabelTextFactory} instead.
	 * @param parent The containing composite.
	 * @param factory a factory for internal widgets used.
	 * @param label The label (text) of this attribute editor.
	 * @param value The current value of the cell.
	 * @param enumeration The possible values that can be proposed.
	 * @param top The widget just above this one, to grab position information from.
	 * 			   Use the other version without top for the first one.
	 */
	public LabelCombo(Composite parent,
			TabbedPropertySheetWidgetFactory factory, String label,
			String value, List<String> enumeration,
			IAttributeLabel top) {
		this(parent, factory, label, value, enumeration, new FormAttachment(top.getControl(), 0));
	}

	/**
	 * The public constructor, not meant to be called by clients.
	 * Use {@link LabelTextFactory} instead.
	 * @param parent The containing composite.
	 * @param factory a factory for internal widgets used.
	 * @param label The label (text) of this attribute editor.
	 * @param value The current value of the cell.
	 * @param enumeration The possible values that can be proposed.
	 */
	public LabelCombo(Composite parent,
			TabbedPropertySheetWidgetFactory factory, String label,
			String value, List<String> enumeration) {
		this(parent, factory, label, value, enumeration, new FormAttachment(0, 0));
	}

	/**
	 * The real constructor, invoked by the public constructors.
	 * Use {@link LabelTextFactory} instead.
	 * @param parent The containing composite.
	 * @param factory a factory for internal widgets used.
	 * @param label The label (text) of this attribute editor.
	 * @param value The current value of the cell.
	 * @param enumeration The possible values that can be proposed.
	 * @param top The widget just above this one, to grab position information from.
	 * 			   Use the other version without top for the first one.
	 */
	private LabelCombo(Composite parent,
			TabbedPropertySheetWidgetFactory factory, String label,
			String value, List<String> enumeration,
			FormAttachment top) {
		FormData data;
		this.parent = parent;

		// Handle Label
		this.label = factory.createCLabel(parent, label);
		GC gc = new GC(this.label);
		data = new FormData();
		data.left = new FormAttachment(0, 5);
		data.right = new FormAttachment(0, gc.stringExtent("EarliestFiringTime ").x);
		data.top = top;
		this.label.setLayoutData(data);

		// Handle Combo
		this.combo = factory.createCCombo(parent);
		String [] array = new String[enumeration.size()];
		array = enumeration.toArray(array);
		combo.setItems(array);
		data = new FormData();
		data.left = new FormAttachment(this.label, 5);
		data.right = new FormAttachment(100, -5);
		data.top = top;
		data.height = gc.stringExtent("Hello").y; // high enough for capital letters
		combo.setLayoutData(data);
		gc.dispose();
		redraw();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Composite getParent() {
		return parent;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getText() {
		return combo.getText();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isVisible() {
		Assert.isTrue(combo.isVisible() == label.isVisible());
		return combo.isVisible();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void redraw() {
		Composite tmp = parent;
		// TODO: WTF?? Copy/paste from Clement, it works but not very elegant...
		for (int i = 0; i < 20 && tmp != null; i++) {
			tmp.layout();
			tmp.redraw();
			tmp = tmp.getParent();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setText(String string) {
		combo.setText(string);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setVisible(boolean visible) {
		combo.setVisible(visible);
		label.setVisible(visible);
		combo.redraw();
		label.redraw();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Control getControl() {
		return combo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addModifyListener(ModifyListener listener) {
		combo.addModifyListener(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLabel() {
		return label.getText();
	}

}
