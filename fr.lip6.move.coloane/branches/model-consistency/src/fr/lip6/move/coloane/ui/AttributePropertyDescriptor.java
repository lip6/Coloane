package fr.lip6.move.coloane.ui;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class AttributePropertyDescriptor extends PropertyDescriptor {
	private String title;
	

	public AttributePropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
		this.title = displayName;
	}
	
	public CellEditor createPropertyEditor(Composite parent) {
        CellEditor editor = new AttributeCellEditor(parent,title,"Valeur :");
        if (getValidator() != null)
           editor.setValidator(getValidator());
        return editor;
     }

}
