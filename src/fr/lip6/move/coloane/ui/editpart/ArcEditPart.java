package fr.lip6.move.coloane.ui.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import fr.lip6.move.coloane.motor.models.AbstractModelElement;
import fr.lip6.move.coloane.motor.models.ArcImplAdapter;
import fr.lip6.move.coloane.ui.commands.ArcDeleteCmd;
import fr.lip6.move.coloane.ui.views.ArcFigure;
import fr.lip6.move.coloane.ui.views.IArcFigure;

/**
 * EditPart pour les arcs (CONTROLEUR)
 */

public class ArcEditPart extends AbstractConnectionEditPart implements PropertyChangeListener {

	/**
	 * Dessin de l'arc
	 * @return IFigure
	 */
	protected IFigure createFigure() {
		IFigure connection = new ArcFigure();
		return connection;
	}
	
	/**
	 * Met a jour la vue en fonction de la lecture du modele<br>
	 * Cette methode utilise les accesseurs de la vue pour la modifier
	 */
	protected void refreshVisuals() {
		super.refreshVisuals();
		IArcFigure arcFigure = (IArcFigure) getFigure();
		ArcImplAdapter arcModel = (ArcImplAdapter)getModel();
		arcFigure.setLabelText(arcModel.getArcValue()); // Accesseur de la vue
	}

	/**
	 * Creation des regles d'edition
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
		
		// Allows the removal of the connection model element
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new ConnectionEditPolicy() {
			protected Command getDeleteCommand(GroupRequest request) {
				return new ArcDeleteCmd(getCastedModel());
			}
		});
	}

	/**
	 * A traiter lors d'un changement de propriete
	 * @param PropertyChangeEvent arg
	 */ 
	public void propertyChange(PropertyChangeEvent arg) {
		String prop = arg.getPropertyName();
		if (ArcImplAdapter.VALUE_PROP.equals(prop)) {
			refreshChildren();
		}
		refreshVisuals();
	}
	
	/**
	 * Retour l'element caste en ArcImplAdapter
	 * @return ArcImplAdapter l'element
	 */
	private ArcImplAdapter getCastedModel() {
		return (ArcImplAdapter)getModel();
	}
	
	/**
	 * Installation des ecouteurs de l'objet
	 */
	public void activate() {
		if (!isActive()) {
			super.activate();
			((AbstractModelElement) getModel()).addPropertyChangeListener(this);
		}
	}

	/**
	 * Mise en veille des ecouteurs de l'objet
	 */
	public void deactivate() {
		if (isActive()) {
			super.deactivate();
			((AbstractModelElement) getModel()).removePropertyChangeListener(this);
		}
	}
}
