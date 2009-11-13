package its.typesui;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import its.CompositeTypeDeclaration;
import its.Concept;
import its.TypeDeclaration;
import its.expression.IVariableBinding;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class TypeTreeLabelProvider extends LabelProvider implements
ILabelProvider {


	@Override
	public String getText(Object element) {
		if (element instanceof TypeDeclaration) {
			TypeDeclaration type = (TypeDeclaration) element;
			String ret = "";
			if (!type.isSatisfied())
				ret += "!!!  ";
			ret += type.getTypeName()+": "+type.getTypeType();
			return ret;
		} else if (element instanceof Concept) {
			Concept concept = (Concept) element;
			if (concept.getEffective()!=null) 
				return concept.getName()+": "+concept.getEffective().getTypeName();
			else
				return "!!!  " + concept.getName()+": ? ";
		} else if (element instanceof IVariableBinding) {
			IVariableBinding vb = (IVariableBinding) element;
			Integer val = vb.getVariableValue();
			if (val != null) 
				return vb.getVariableName()+":= "+val;
			else
				return "!!!  " + vb.getVariableName()+":= ?";				
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
		} else if (element instanceof Concept) {
			IFormalism f = FormalismManager.getInstance().getFormalismById("ITS Composite");
			String img = f.getMasterGraph().getElementFormalism("instance").getGraphicalDescription().getIcon24px();
			return ImageDescriptor.createFromFile(Coloane.class, img).createImage();
		} else if (element instanceof IVariableBinding) {
			IVariableBinding vb = (IVariableBinding) element;
			// TODO : discriminate icon depending on variable set or not
			return AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "$nl$/icons/full/elcl16/progress_rem.gif").createImage(); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return null;
	}


}
