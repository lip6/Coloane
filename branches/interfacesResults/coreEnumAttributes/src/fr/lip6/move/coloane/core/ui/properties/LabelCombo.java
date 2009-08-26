package fr.lip6.move.coloane.core.ui.properties;

import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyListener;
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
		data = new FormData();
		data.left = new FormAttachment(0, 5);
		data.right = new FormAttachment(0, LABEL_WIDTH);
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
		data.height = 15;
		combo.setLayoutData(data);

		redraw();
	}

	/**
	 * {@inheritDoc}
	 */
	public Composite getParent() {
		return parent;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getText() {
		return combo.getText();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isVisible() {
		Assert.isTrue(combo.isVisible() == label.isVisible());
		return combo.isVisible();
	}

	/**
	 * {@inheritDoc}
	 */
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
	public void setText(String string) {
		combo.setText(string);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setVisible(boolean visible) {
		combo.setVisible(visible);
		label.setVisible(visible);
		combo.redraw();
		label.redraw();
	}

	/**
	 * {@inheritDoc}
	 */
	public Control getControl() {
		return combo;
	}

	/**
	 * {@inheritDoc}
	 */
	public void addModifyListener(ModifyListener listener) {
		combo.addModifyListener(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getLabel() {
		return label.getText();
	}

}
