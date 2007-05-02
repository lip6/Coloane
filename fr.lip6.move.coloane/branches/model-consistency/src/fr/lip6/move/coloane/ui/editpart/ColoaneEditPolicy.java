package fr.lip6.move.coloane.ui.editpart;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import fr.lip6.move.coloane.ui.commands.NodeCreateCmd;
import fr.lip6.move.coloane.ui.commands.NodeSetConstraintCmd;
import fr.lip6.move.coloane.ui.model.IModelImpl;
import fr.lip6.move.coloane.ui.model.INodeImpl;
import fr.lip6.move.coloane.ui.model.NodeImplAdapter;

public class ColoaneEditPolicy extends XYLayoutEditPolicy {

	/**
	 * Le modele (en tant que conteneur) doit definir une politique vis a vis de ses enfants <br>
	 */
	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {

		/**
		 * Cette politique interdit aux enfants d'etre redimensionnes<br>
		 * La redefinition de la methode interne supprime toute trace de cadre de selection.
		 */
		return new NonResizableEditPolicy() {
			@Override
			protected List createSelectionHandles() {
				return new ArrayList(); // Doit retourner une arraylist vide et non null
			}
		};
	}

	/**
	 * TODO : ??
	 */
	protected Command createAddCommand(EditPart child, Object constraint) {
		return null;
	}
	
	/** 
	 * Traitement d'une demande d'ajout de noeud
	 */
	protected Command getCreateCommand(CreateRequest request) {
		Object childClass = request.getNewObjectType();

		// Si l'objet a ajouter est un noeud... OK
		if (childClass == NodeImplAdapter.class) {
			INodeImpl newNode = (INodeImpl)request.getNewObject();			
			IModelImpl model = (IModelImpl) getHost().getModel();
			
			// On applique la commande de creation du noeud
			return new NodeCreateCmd(newNode,model, (Rectangle) getConstraintFor(request));
		}
		return null;
	}

	/**
	 * @return A command for set location of node
	 */
	protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
		// Resize not permited
		if (child instanceof ElementEditPart && constraint instanceof Rectangle) {
			return new NodeSetConstraintCmd((NodeImplAdapter) child.getModel(), request, (Rectangle) constraint);

		}
		return super.createChangeConstraintCommand(request, child, constraint);
	}

	/**
	 * 
	 */
	protected Command getDeleteDependantCommand(Request request) {
		return null;
	}

	protected Command createChangeConstraintCommand(EditPart arg0, Object arg1) {
		return null;
	}
}