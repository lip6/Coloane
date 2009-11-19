package fr.lip6.move.coloane.its.typesui;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.its.CompositeTypeDeclaration;
import fr.lip6.move.coloane.its.Concept;
import fr.lip6.move.coloane.its.TypeDeclaration;


import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Provides labels ofr objects in the tree view (type declarations, concepts, parameters)
 * @author Yann
 *
 */
public final class TypeLabelProvider extends LabelProvider implements
ITableLabelProvider {


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Image getColumnImage(Object obj, int index) {
		if (index == 0) {
			if (obj instanceof CompositeTypeDeclaration) {
				IFormalism f = FormalismManager.getInstance().getFormalismById("ITS Composite");
				return ImageDescriptor.createFromFile(Coloane.class, f.getImageName()).createImage();
			}
			if (obj instanceof TypeDeclaration) {
				IFormalism f = FormalismManager.getInstance().getFormalismById("Time Petri Net");
				return ImageDescriptor.createFromFile(Coloane.class, f.getImageName()).createImage();
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof TypeDeclaration) {
			TypeDeclaration type = (TypeDeclaration) element;
			switch (columnIndex) {
			case 0 :
				return type.getTypeName();
			case 1 :
				return type.getTypeType();
			case 2 :
				return type.getTypePath();
			case 3 :
				return Boolean.toString(type.isSatisfied());
			default :
				throw new RuntimeException("Column index out of bounds in TypeLabelProvider.");
			}
		} else if (element instanceof Concept) {
			Concept concept = (Concept) element;
			return concept.getName();
		} else {
			return "Unrecognized type in TypeLabelProvider";
		}
	}


}
