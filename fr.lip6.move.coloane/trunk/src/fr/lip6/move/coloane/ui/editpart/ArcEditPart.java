package fr.lip6.move.coloane.ui.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.ui.commands.ArcDeleteCmd;
import fr.lip6.move.coloane.ui.figures.ArcFigure;
import fr.lip6.move.coloane.ui.model.AbstractModelElement;
import fr.lip6.move.coloane.ui.model.IArcImpl;

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
		IArcImpl arcModel = (IArcImpl)getModel();
				
		// Il faut avertir FrameKit
		Coloane.notifyModelChange(arcModel.getModelAdapter());
	}

	/**
	 * Creation des regles d'edition
	 */
	protected void createEditPolicies() {
		/* Ensemble de regles concernant la selection/deselection de l'objet */
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new SelectionEditPolicy() {			
			
			@Override
			protected void setSelectedState(int state) {
				// TODO Auto-generated method stub
				super.setSelectedState(state);
				if (state != 0) {
					((IArcImpl)getModel()).setAttributesSelected(true);
				} else {
					((IArcImpl)getModel()).setAttributesSelected(false);
				}
			}

			@Override
			protected void hideSelection() {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void showSelection() {
				// TODO Auto-generated method stub
				
			}
		});
		
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
		
		// Allows the removal of the connection model element
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new ConnectionEditPolicy() {
			protected Command getDeleteCommand(GroupRequest request) {
				return new ArcDeleteCmd((IArcImpl)getModel());
			}
		});
	}

	/**
	 * Traitements a effectuer lors de la reception d'un evenement sur l'EditPart
	 * @param property L'evenement qui a ete levee
	 */
	public void propertyChange(PropertyChangeEvent arg) {
		
		// Juste un rafraichissement des visuels
		refreshVisuals();
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
