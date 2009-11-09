package its.typesui;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import its.CompositeTypeDeclaration;
import its.Concept;
import its.TypeDeclaration;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class TypeTreeLabelProvider extends LabelProvider implements
ILabelProvider {


	@Override
	public String getText(Object element) {
		if (element instanceof TypeDeclaration) {
			TypeDeclaration type = (TypeDeclaration) element;
			return type.getTypeName();
		} else if (element instanceof Concept) {
			Concept concept = (Concept) element;
			return concept.getName();
		}
		return "Unrecognized type in TypeLabelProvider";
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof CompositeTypeDeclaration) {
			IFormalism f = FormalismManager.getInstance().getFormalismById("ITS Composite");
			return ImageDescriptor.createFromFile(Coloane.class, f.getImageName()).createImage();
		}
		if (element instanceof TypeDeclaration) {
			IFormalism f = FormalismManager.getInstance().getFormalismById("Time Petri Net");
			return ImageDescriptor.createFromFile(Coloane.class, f.getImageName()).createImage();
		}
		return null;
	}


}
