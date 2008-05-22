package fr.lip6.move.coloane.core.ui.properties;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class LabelText {
	public static final int LABEL_WIDTH = 90;
	public static final int MAX_TEXT_HEIGHT = 4;

	private Text text;
	private CLabel label;
	private Composite parent;
	private ScrolledComposite sc;
	private int id;

	private ModifyListener listener = new ModifyListener() {
		private int height;

		@Override
		public void modifyText(ModifyEvent e) {
			if (text.getVerticalBar() == null) {
				return;
			}

			// On limite l'agrandissement
			if (text.getText().split(Text.DELIMITER, -1).length <= MAX_TEXT_HEIGHT) {
				height = text.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
				text.getVerticalBar().setVisible(false);
			} else {
				text.getVerticalBar().setVisible(true);
			}
			((FormData) text.getLayoutData()).height = height;
			redraw();
		}
	};

	public LabelText(Composite parent, TabbedPropertySheetWidgetFactory factory, int id, String label, String value, int style, FormAttachment top) {
		FormData data;
		this.parent = parent;
		this.id = id;

		this.label = factory.createCLabel(parent, label);
		data = new FormData();
		data.left = new FormAttachment(0, 5);
		data.right = new FormAttachment(0, LABEL_WIDTH);
		data.top = top;
		this.label.setLayoutData(data);

		this.text = factory.createText(parent, value, style | SWT.V_SCROLL);
		data = new FormData();
		data.left = new FormAttachment(this.label, 5);
		data.right = new FormAttachment(100, -5);
		data.top = top;
		data.height = 15;
		text.setLayoutData(data);
		text.addModifyListener(listener);

		if (text.getVerticalBar() != null) {
			text.getVerticalBar().setVisible(false);
		}

		redraw();
	}

	public LabelText(Composite parent, TabbedPropertySheetWidgetFactory factory, int id, String label, String value, int style) {
		this(parent, factory, id, label, value, style, new FormAttachment(0, 0));
	}

	public LabelText(Composite parent, TabbedPropertySheetWidgetFactory factory, int id, String label, String value, int style, LabelText top) {
		this(parent, factory, id, label, value, style, new FormAttachment(top.text, 0));
	}

	public final void redraw() {
		// Récupération du ScrolledComposite
		if (sc == null) {
			Composite tmp = parent;
			while (!(tmp instanceof ScrolledComposite)) {
				tmp = tmp.getParent();
			}
			sc = (ScrolledComposite) tmp;
		}
		sc.setMinSize(parent.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		Composite tmp = parent;
		for (int i = 0; i < 20 && tmp != null; i++) {
			tmp.layout();
			tmp.redraw();
			tmp = tmp.getParent();
		}
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

	public final Text getTextWidget() {
		return text;
	}

	public final int getId() {
		return id;
	}
}
