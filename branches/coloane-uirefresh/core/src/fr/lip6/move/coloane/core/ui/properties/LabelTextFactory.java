package fr.lip6.move.coloane.core.ui.properties;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class LabelTextFactory {
	private final Composite parent;
	private final TabbedPropertySheetWidgetFactory factory;

	private LabelText last;

	public LabelTextFactory(Composite parent, TabbedPropertySheetWidgetFactory factory) {
		this.parent = parent;
		this.factory = factory;
	}

	public final LabelText create(String label, String value, int style) {
		LabelText lt;
		if (last == null) {
			lt = new LabelText(
					parent,
					factory,
					label,
					value,
					style);
		} else {
			lt = new LabelText(
					parent,
					factory,
					label,
					value,
					style,
					last);
		}
		last = lt;
		return lt;
	}
}
