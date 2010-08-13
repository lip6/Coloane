package fr.lip6.move.coloane.core.ui.properties;

import org.eclipse.gef.EditPart;
import org.eclipse.ui.views.properties.tabbed.ITypeMapper;

/**
 * Classe permettant de faire le lien entre l'editPart sélectionné et la classe
 * définie dans le plugin.xml dans l'extensions définissant chaque section.
 */
public class TypeMapper implements ITypeMapper {

	/** {@inheritDoc} */
	@SuppressWarnings("rawtypes")
	public final Class mapType(Object object) {
		Class type = object.getClass();
		if (object instanceof EditPart) {
			type = ((EditPart) object).getModel().getClass();
		}
		return type;
	}
}
