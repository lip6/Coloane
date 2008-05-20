package fr.lip6.move.coloane.core.ui.properties;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class LabelText {
	private static final int LABEL_WIDTH = 90;
	private Text text;
	private CLabel label;
	private Composite parent;


	public LabelText(Composite parent, TabbedPropertySheetWidgetFactory factory, String label, String value, int style, FormAttachment top) {
		FormData data;
		this.parent = parent;

		this.label = factory.createCLabel(parent, label);
		data = new FormData();
		data.left = new FormAttachment(0, 5);
		data.right = new FormAttachment(0, LABEL_WIDTH);
		data.top = top;
		this.label.setLayoutData(data);

		this.text = factory.createText(parent, value, style);
		data = new FormData();
		data.left = new FormAttachment(this.label, 5);
		data.right = new FormAttachment(100, -5);
		data.top = top;
		text.setLayoutData(data);
	}

	public LabelText(Composite parent, TabbedPropertySheetWidgetFactory factory, String label, String value, int style) {
		this(parent, factory, label, value, style, new FormAttachment(0, 0));
	}

	public LabelText(Composite parent, TabbedPropertySheetWidgetFactory factory, String label, String value, int style, LabelText top) {
		this(parent, factory, label, value, style, new FormAttachment(top.label, 0));
	}

	public final boolean isVisible() {
		Assert.isTrue(text.isVisible() == label.isVisible());
		return text.isVisible();
	}

	public final void setVisible(boolean visible) {
		text.setVisible(visible);
		label.setVisible(visible);
		text.redraw();
		label.redraw();
	}

	public final String getText() {
		return text.getText();
	}

	public final void setText(String string) {
		text.setText(string);
	}

	public final String getLabel() {
		return label.getText();
	}

	public final Composite getParent() {
		return parent;
	}
}
