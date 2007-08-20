package fr.lip6.move.coloane.ui;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class AttributePropertyDescriptor extends PropertyDescriptor {
	private String title;
	private String help;
	private String byDefault;


	public AttributePropertyDescriptor(Object id, String displayName, String h, String d) {
		super(id, displayName);
		this.title = displayName;
		this.help = h;
		this.byDefault = d;
	}

	public final CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new AttributeCellEditor(parent, this.title, this.help, this.byDefault);
		if (getValidator() != null) {
			editor.setValidator(getValidator());
		}
		return editor;
	}
}
