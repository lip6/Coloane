package fr.lip6.move.coloane.core.ui.properties;

import org.eclipse.gef.EditPart;
import org.eclipse.ui.views.properties.tabbed.ITypeMapper;

public class TypeMapper implements ITypeMapper {
	@SuppressWarnings("unchecked")
	public final Class mapType(Object object) {
		Class type = object.getClass();
		if (object instanceof EditPart) {
			type = ((EditPart) object).getModel().getClass();
		}
		return type;
	}
}
