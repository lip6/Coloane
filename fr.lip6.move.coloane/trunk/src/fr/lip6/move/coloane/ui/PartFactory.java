package fr.lip6.move.coloane.ui;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import fr.lip6.move.coloane.ui.editpart.ArcEditPart;
import fr.lip6.move.coloane.ui.editpart.AttributeEditPart;
import fr.lip6.move.coloane.ui.editpart.ElementEditPart;
import fr.lip6.move.coloane.ui.editpart.ModelEditPart;
import fr.lip6.move.coloane.ui.model.IArcImpl;
import fr.lip6.move.coloane.ui.model.IAttributeImpl;
import fr.lip6.move.coloane.ui.model.IModelImpl;
import fr.lip6.move.coloane.ui.model.INodeImpl;


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
			} else {
				System.err.println("Objet du modele non pris en charge");
			}
		}
		return part;
	}

	/**
	 * Selon le type de l'element... on choisit sont EditPart 
	 * @param modelElement l'element du modele pour lequel on doit construire l'EditPart
	 */
	private EditPart getPartForElement(Object modelElement) {
		if (modelElement instanceof INodeImpl) {
			return new ElementEditPart();
		} else if (modelElement instanceof IArcImpl) {
			return new ArcEditPart();
		} else if (modelElement instanceof IModelImpl) {
			return new ModelEditPart();
		} else if (modelElement instanceof IAttributeImpl) {
			return new AttributeEditPart();
		}
		
		return null;
	}
}
