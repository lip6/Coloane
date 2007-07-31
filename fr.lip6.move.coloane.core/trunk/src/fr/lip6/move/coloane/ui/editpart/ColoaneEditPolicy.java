package fr.lip6.move.coloane.ui.editpart;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import fr.lip6.move.coloane.ui.commands.AttributeSetConstraintCmd;
import fr.lip6.move.coloane.ui.commands.NodeCreateCmd;
import fr.lip6.move.coloane.ui.commands.NodeSetConstraintCmd;
import fr.lip6.move.coloane.ui.model.IAttributeImpl;
import fr.lip6.move.coloane.ui.model.IModelImpl;
import fr.lip6.move.coloane.ui.model.INodeImpl;
import fr.lip6.move.coloane.ui.model.NodeImplAdapter;


/**
 * Ensemble de regles definissant le comportement du modele vis a vis de ses noeuds fils.
 * <i>Attention ! Le modele est l'unique conteneur de tous les autres objets.</i>
 * Les regles definies concernent :
 * <ul>
 * <li>La creation d'un noeud fils</li>
 * <li>Le deplacement d'un noeud fils</li>
 * </ul>
 */

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
	 * Traitement d'une demande d'ajout de noeud
	 * @param request La requete formulee
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
		
		// Sinon... On ne permet pas l'ajout !
		return null;
	}

	/**
	 * Traitement du deplacement d'un noeud
	 * <b>Attention ! Le redimensionnement n'est pas permis !</b>
	 * @param request La requete formulee
	 * @param child L'EditPart concernee
	 * @param constraint La nouvelle position demandee
	 */
	protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
		
		// Dans le cas d'un noeud
		if (child instanceof ElementEditPart && constraint instanceof Rectangle) {
			return new NodeSetConstraintCmd((INodeImpl) child.getModel(), (Rectangle) constraint);
		}
		
		// Dans le cas d'un attribut
		if (child instanceof AttributeEditPart) {
			return new AttributeSetConstraintCmd((IAttributeImpl) child.getModel(), (Rectangle) constraint);
		}
		
		// Dans tous les autres cas, on forwarde au pere
		return super.createChangeConstraintCommand(request, child, constraint);
	}

	@Override
	protected Command createChangeConstraintCommand(EditPart arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}
}