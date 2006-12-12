package fr.lip6.move.coloane.ui;



import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import fr.lip6.move.coloane.interfaces.models.*;
import fr.lip6.move.coloane.motor.models.*;
import fr.lip6.move.coloane.ui.editpart.*;

/**
 * Cette Factory construit les EditPart pour les elements du modele.
 */
public class PartFactory implements EditPartFactory {

	/**
	 * Creation effective de l'EditPart
	 */
	public EditPart createEditPart(EditPart context, Object modelElement) {
		EditPart part = null;
		
		// Get EditPart for model element
		if (modelElement == null) {
			System.err.println("L'element est nul");
		} else {
			part = getPartForElement(modelElement); // Selon l'element on construit un EditPart different
			part.setModel(modelElement); // Associe l'element a l'EditPart
		}
		return part;
	}

	/**
	 * Selon le type de l'element... on choisit sont EditPart 
	 * @throws RuntimeException if no match was found (programming error)
	 */
	private EditPart getPartForElement(Object modelElement) {
		
		System.out.println("Mapping de l'element");
		
		if (modelElement instanceof NodeImplAdapter) {
			System.out.println("Creation de l'EditPart pour un noeud");
			return getPartForNode();
		} else if (modelElement instanceof ArcImplAdapter) {
			System.out.println("Creation de l'EditPart pour un arc");
			return getPartForArc((ArcImplAdapter)modelElement);
		} else if (modelElement instanceof ModelImplAdapter) {
			System.out.println("Creation de l'EditPart pour un modele");
			return new ModelEditPart();
		} else if (modelElement instanceof AttributeImplAdapter) {
			System.out.println("Creation de l'EditPart pour un attribut");
			return new AttributeEditPart();
		}		
		throw new RuntimeException("Impossible de creer l'EditPart pour l'element: "+ ((modelElement != null) ? modelElement.getClass().getName() : "null"));
	}
	
	/**
	 * Creation d'un EditPart pour un noeud
	 * @return EditPart pour le noeud
	 */
	private EditPart getPartForNode() {
		return new ElementEditPart();		
	}
	
	/**
	 * Creation d'un EditPart pour un arc
	 * @param arc L'arc pour lequel on cree l'EditPart
	 * @return EditPart de l'arc
	 */
	private EditPart getPartForArc(IArc arc) {
		ArcEditPart arcEditPart = new ArcEditPart();
		return arcEditPart;
	}

}
