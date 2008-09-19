package fr.lip6.move.coloane.core.ui;

import fr.lip6.move.coloane.core.model.CoreTipModel;
import fr.lip6.move.coloane.core.model.StickyNoteModel;
import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.ui.editpart.ArcEditPart;
import fr.lip6.move.coloane.core.ui.editpart.AttributeEditPart;
import fr.lip6.move.coloane.core.ui.editpart.GraphEditPart;
import fr.lip6.move.coloane.core.ui.editpart.LinkEditPart;
import fr.lip6.move.coloane.core.ui.editpart.NodeEditPart;
import fr.lip6.move.coloane.core.ui.editpart.StickyEditPart;
import fr.lip6.move.coloane.core.ui.editpart.TipArcEditPart;
import fr.lip6.move.coloane.core.ui.editpart.TipEditPart;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.logging.Logger;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

/**
 * Cette Factory construit les EditParts pour chacun des elements du modele.
 */
public class PartFactory implements EditPartFactory {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/**
	 * Creation effective de l'EditPart
	 * @param context contexte
	 * @param modelElement l'element du modele pour lequel on doit construire un EditPart
	 * @return nouvelle EditPart
	 */
	public final EditPart createEditPart(EditPart context, Object modelElement) {
		EditPart part = null;

		// Si l'element est nul... Probleme
		if (modelElement == null) {
			LOGGER.warning("L'element est nul : La factory ne peut rien produire"); //$NON-NLS-1$
		} else {
			// Selon l'element on construit un EditPart different
			part = getPartForElement(modelElement);
			if (part != null) {
				part.setModel(modelElement);
			} else {
				LOGGER.warning("L'element n'est pas supporte par la factory"); //$NON-NLS-1$
			}
		}
		LOGGER.fine("Création d'un " + part.getClass().getSimpleName()); //$NON-NLS-1$
		return part;
	}

	/**
	 * Selon le type de l'element... on choisit sont EditPart
	 * @param modelElement l'element du modèle pour lequel on doit construire l'EditPart
	 * @return nouvelle EditPart
	 */
	private EditPart getPartForElement(Object modelElement) {
		if (modelElement instanceof INode) {
			return new NodeEditPart();
		} else if (modelElement instanceof IAttribute) {
			return new AttributeEditPart();
		} else if (modelElement instanceof IArc) {
			return new ArcEditPart();
		} else if (modelElement instanceof StickyNoteModel) {
			return new StickyEditPart();
		} else if (modelElement instanceof ICoreTip) {
			return new TipEditPart();
		} else if (modelElement instanceof ILink) {
			return new LinkEditPart();
		} else if (modelElement instanceof IGraph) {
			return new GraphEditPart();
		} else if (modelElement instanceof CoreTipModel.ArcTipModel) {
			return new TipArcEditPart();
		}
		return null;
	}
}
