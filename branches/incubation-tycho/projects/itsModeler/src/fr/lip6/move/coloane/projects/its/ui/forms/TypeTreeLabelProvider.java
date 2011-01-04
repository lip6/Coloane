package fr.lip6.move.coloane.projects.its.ui.forms;

import fr.lip6.move.coloane.projects.its.CompositeTypeDeclaration;
import fr.lip6.move.coloane.projects.its.Concept;
import fr.lip6.move.coloane.projects.its.TypeDeclaration;
import fr.lip6.move.coloane.projects.its.expression.IVariableBinding;


import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * A nice tree label provider for the Types tree view.
 * Handles type declarations, concepts, variable bindings
 * @author Yann
 *
 */
public class TypeTreeLabelProvider
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
			return ITSEditorPlugin.getDefault().getImage(ITSEditorPlugin.IMG_COMPOSITE);
		}
		if (element instanceof TypeDeclaration) {
			return ITSEditorPlugin.getDefault().getImage(ITSEditorPlugin.IMG_TPNFORM);			
		} else if (element instanceof Concept) {
			return ITSEditorPlugin.getDefault().getImage(ITSEditorPlugin.IMG_INSTANCE);
		} else if (element instanceof IVariableBinding) {
			IVariableBinding vb = (IVariableBinding) element;
			if (vb.getVariableValue() != null)
				return ITSEditorPlugin.getDefault().getImage(ITSEditorPlugin.IMG_SETVAR);
			else
				return ITSEditorPlugin.getDefault().getImage(ITSEditorPlugin.IMG_USETVAR);				
		}
		return null;
	}


}
