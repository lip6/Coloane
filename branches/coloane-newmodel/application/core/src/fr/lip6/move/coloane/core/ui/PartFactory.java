package fr.lip6.move.coloane.core.ui;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.editpart.ArcEditPart;
import fr.lip6.move.coloane.core.ui.editpart.AttributeEditPart;
import fr.lip6.move.coloane.core.ui.editpart.GraphEditPart;
import fr.lip6.move.coloane.core.ui.editpart.NodeEditPart;
import fr.lip6.move.coloane.interfaces.model.interfaces.IArc;
import fr.lip6.move.coloane.interfaces.model.interfaces.IAttribute;
import fr.lip6.move.coloane.interfaces.model.interfaces.IGraph;
import fr.lip6.move.coloane.interfaces.model.interfaces.INode;

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

		// Si l'element est nul... Probleme
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
		if (modelElement instanceof INode) {
			return new NodeEditPart();
		} else if (modelElement instanceof IArc) {
			return new ArcEditPart();
		} else if (modelElement instanceof IGraph) {
			return new GraphEditPart();
		} else if (modelElement instanceof IAttribute) {
			return new AttributeEditPart();
		}

		return null;
	}
}
