package fr.lip6.move.coloane.ui;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class AttributePropertyDescriptor extends PropertyDescriptor {
	private String title;
	private String help;
	private String byDefault;
	

	public AttributePropertyDescriptor(Object id, String displayName, String help, String byDefault) {
		super(id, displayName);
		this.title = displayName;
		this.help = help;
		this.byDefault = byDefault;
	}
	
	public CellEditor createPropertyEditor(Composite parent) {
        CellEditor editor = new AttributeCellEditor(parent,title,this.help,this.byDefault);
        if (getValidator() != null)
           editor.setValidator(getValidator());
        return editor;
     }
}
