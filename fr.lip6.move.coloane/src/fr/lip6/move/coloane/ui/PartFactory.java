package fr.lip6.move.coloane.ui;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import fr.lip6.move.coloane.motor.models.*;
import fr.lip6.move.coloane.ui.editpart.*;

/**
 * Cette Factory construit les EditParts pour chacun des elements du modele.
 */
public class PartFactory implements EditPartFactory {

	/**
	 * Creation effective de l'EditPart
	 * @param context
	 * @param modelElement l'element du modele pour lequel on doit construire un EditPart
	 */
	public EditPart createEditPart(EditPart context, Object modelElement) {
		EditPart part = null;
		
		// Si l'elemen est nul... Probleme
		if (modelElement == null) {
			System.err.println("L'element est nul");
		} else {
			// Selon l'element on construit un EditPart different
			part = getPartForElement(modelElement); 
			if (part != null) {
				part.setModel(modelElement);
			}
		}
		return part;
	}

	/**
	 * Selon le type de l'element... on choisit sont EditPart 
	 * @param modelElement l'element du modele pour lequel on doit construire l'EditPart
	 */
	private EditPart getPartForElement(Object modelElement) {
		
		System.out.println("Mapping de l'element");
		
		if (modelElement instanceof NodeImplAdapter) {
			return new ElementEditPart();
		} else if (modelElement instanceof ArcImplAdapter) {
			return new ArcEditPart();
		} else if (modelElement instanceof ModelImplAdapter) {
			return new ModelEditPart();
		}
		
		return null;
	}
}
