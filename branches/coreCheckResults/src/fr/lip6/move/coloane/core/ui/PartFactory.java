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
 * Factory that creates all EditParts according model element
 */
public class PartFactory implements EditPartFactory {
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/**
	 * {@inheritDoc}
	 */
	public final EditPart createEditPart(EditPart context, Object modelElement) {
		EditPart part = null;

		// If the model element is null, it could be problematic...
		if (modelElement == null) {
			LOGGER.warning("L'element de modele est null. Creation de l'edit part associe impossible"); //$NON-NLS-1$
			return null;
		}
		
		// The EditPart has to be built according to the model element
		part = getPartForElement(modelElement);
		if (part == null) {
			LOGGER.warning("L'element de modele est invalide. Creation de l'edit part associe impossible"); //$NON-NLS-1$
			return null;
		}

		// Associate the model element to the EditPart
		part.setModel(modelElement);
		LOGGER.fine("Creation d'un " + part.getClass().getSimpleName()); //$NON-NLS-1$
		return part;
	}

	/**
	 * Fetch the appropriate EditPart and create an instance of it.
	 * @param modelElement the model element for which an EditPart has to be created
	 * @return a new EditPart
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
		LOGGER.warning("Objet non reconnu"); //$NON-NLS-1$
		return null;
	}
}
