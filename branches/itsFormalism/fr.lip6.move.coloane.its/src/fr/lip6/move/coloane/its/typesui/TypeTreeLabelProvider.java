package fr.lip6.move.coloane.its.typesui;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.its.CompositeTypeDeclaration;
import fr.lip6.move.coloane.its.Concept;
import fr.lip6.move.coloane.its.TypeDeclaration;
import fr.lip6.move.coloane.its.expression.IVariableBinding;


import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * A nice tree label provider for the Types tree view.
 * Handles type declarations, concepts, variable bindings
 * @author Yann
 *
 */
public final class TypeTreeLabelProvider
			extends LabelProvider
			implements ILabelProvider {


	/**
	 * Return a nice formatted text for this element.
	 * The text includes some markers of being unsatisfied (per the model)
	 * @param element to display (TypeDeclaration, concept or VariableBinding)
	 * @return a nice formatted string
	 */
	@Override
	public String getText(Object element) {
		if (element instanceof TypeDeclaration) {
			TypeDeclaration type = (TypeDeclaration) element;
			String ret = "";
			if (!type.isSatisfied()) {
				ret += "!!!  ";
			}
			ret += type.getTypeName() + ": " + type.getTypeType();
			return ret;
		} else if (element instanceof Concept) {
			Concept concept = (Concept) element;
			if (concept.getEffective() != null) {
				return concept.getName() + ": " + concept.getEffective().getTypeName();
			} else {
				return "!!!  " + concept.getName() + ": ? ";
			}
		} else if (element instanceof IVariableBinding) {
			IVariableBinding vb = (IVariableBinding) element;
			Integer val = vb.getVariableValue();
			if (val != null) {
				return vb.getVariableName() + ":= " + val;
			} else {
				return "!!!  " + vb.getVariableName() + ":= ?";
			}
		}
		return "Unrecognized type in TypeLabelProvider";
	}

	/**
	 * Return a nice graphic fro the item.
	 * @param element to get an icon from
	 * @return an image (uniquely) loaded from the Resources
	 */
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
//			IVariableBinding vb = (IVariableBinding) element;
			// TODO : discriminate icon depending on variable set or not
			return AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "$nl$/icons/full/elcl16/progress_rem.gif").createImage(); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return null;
	}


}
