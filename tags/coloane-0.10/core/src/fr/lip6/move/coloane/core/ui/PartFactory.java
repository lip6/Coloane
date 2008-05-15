package fr.lip6.move.coloane.core.ui;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.editpart.ArcEditPart;
import fr.lip6.move.coloane.core.ui.editpart.AttributeEditPart;
import fr.lip6.move.coloane.core.ui.editpart.ElementEditPart;
import fr.lip6.move.coloane.core.ui.editpart.ModelEditPart;
import fr.lip6.move.coloane.core.ui.model.IArcImpl;
import fr.lip6.move.coloane.core.ui.model.IAttributeImpl;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

/**
 * Cette Factory construit les EditParts pour chacun des elements du modele.
 */
public class PartFactory implements EditPartFactory {

	/**
	 * Creation effective de l'EditPart
	 * @param context
	 * @param modelElement l'element du modele pour lequel on doit construire un EditPart
	 */
	public final EditPart createEditPart(EditPart context, Object modelElement) {
		EditPart part = null;

		// Si l'elemen est nul... Probleme
		if (modelElement == null) {
			Coloane.getLogger().warning("L'element est nul : La factory ne peut rien produire"); //$NON-NLS-1$
		} else {
			// Selon l'element on construit un EditPart different
			part = getPartForElement(modelElement);
			if (part != null) {
				part.setModel(modelElement);
			} else {
				Coloane.getLogger().warning("L'element n'est pas supporte par la factory"); //$NON-NLS-1$
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
