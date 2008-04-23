package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.commands.ArcDeleteCmd;
import fr.lip6.move.coloane.core.ui.commands.InflexCreateCmd;
import fr.lip6.move.coloane.core.ui.commands.InflexDeleteCmd;
import fr.lip6.move.coloane.core.ui.commands.InflexMoveCmd;
import fr.lip6.move.coloane.core.ui.figures.ArcFigure;
import fr.lip6.move.coloane.core.ui.figures.IArcFigure;
import fr.lip6.move.coloane.core.ui.model.AbstractModelElement;
import fr.lip6.move.coloane.core.ui.model.IArcImpl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.BendpointEditPolicy;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.requests.GroupRequest;

/**
 * EditPart pour les arcs (CONTROLEUR)
 */

public class ArcEditPart extends AbstractConnectionEditPart implements PropertyChangeListener {

	/**
	 * Dessin de l'arc
	 * @return IFigure
	 */
	protected final IFigure createFigure() {
		IFigure connection = new ArcFigure((IArcImpl) getModel());
		return connection;
	}

	/**
	 * Met a jour la vue en fonction de la lecture du modele<br>
	 * Cette methode utilise les accesseurs de la vue pour la modifier
	 */
	protected final void refreshVisuals() {
		super.refreshVisuals();
		IArcImpl arcModel = (IArcImpl) getModel();

		IArcFigure connection = (IArcFigure) getFigure();
		connection.getConnectionRouter();

		List<Bendpoint> modelConstraint = ((IArcImpl) getModel()).getInflexPoints();
		getConnectionFigure().setRoutingConstraint(modelConstraint);

		// Il faut avertir FrameKit
		Coloane.notifyModelChange(arcModel.getModelAdapter());
	}


	protected final void createEditPolicies() {
		// Selection handle edit policy.
		// Makes the connection show a feedback, when selected by the user.
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());

		installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new BendpointEditPolicy() {
			protected Command getCreateBendpointCommand(BendpointRequest request) {
				Coloane.getLogger().finest("Creation du point d'inflexion : " + request.getIndex());
				Point p = request.getLocation();
				getConnection().translateToRelative(p);
				InflexCreateCmd com = new InflexCreateCmd((IArcImpl) getModel(), request.getLocation(), request.getIndex());
				return com;
			}

			protected Command getDeleteBendpointCommand(BendpointRequest request) {
				Coloane.getLogger().finest("Suppression du point d'inflexion : " + request.getIndex());
				Point p = request.getLocation();
				getConnection().translateToRelative(p);
				InflexDeleteCmd com = new InflexDeleteCmd((IArcImpl) getModel(), request.getLocation(), request.getIndex());
				return com;
			}

			protected Command getMoveBendpointCommand(BendpointRequest request) {
				Point p = request.getLocation();
				Coloane.getLogger().finest("Mouvement de point d'inflexion (workspace) : " + p.x + "," + p.y);
				getConnection().translateToRelative(p);
				Coloane.getLogger().finest("Mouvement de point d'inflexion (univers) : " + p.x + "," + p.y);
				InflexMoveCmd com = new InflexMoveCmd((IArcImpl) getModel(), request.getLocation(), request.getIndex());
				return com;
			}
		});

		/* Ensemble de regles concernant la selection/deselection de l'objet */
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new SelectionEditPolicy() {

			@Override
			protected void setSelectedState(int state) {
				super.setSelectedState(state);
				if (state != 0) {
					((IArcImpl) getModel()).setAttributesSelected(true);
					((IArcFigure) getFigure()).setSelect();
				} else {
					((IArcImpl) getModel()).setAttributesSelected(false);
					((IArcFigure) getFigure()).unsetSelect();
				}
			}

			@Override
			protected void hideSelection() { }

			@Override
			protected void showSelection() { }
		});

		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());

		// Allows the removal of the connection model element
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new ConnectionEditPolicy() {
			protected Command getDeleteCommand(GroupRequest request) {
				return new ArcDeleteCmd((IArcImpl) getModel());
			}
		});
	}

	/**
	 * Traitements a effectuer lors de la reception d'un evenement sur l'EditPart
	 * @param property L'evenement qui a ete levee
	 */
	public final void propertyChange(PropertyChangeEvent property) {

		String prop = property.getPropertyName();

		// Propriete de modification/suppression/ajout de point d'inflexion
		if (IArcImpl.INFLEXPOINT_PROP.equals(prop)) {
			refreshVisuals();
		} else if (IArcImpl.SETSELECT_PROP.equals(prop)) {
			((IArcFigure) getFigure()).setHighlight();
		} else if (IArcImpl.SETUNSELECT_PROP.equals(prop)) {
			((IArcFigure) getFigure()).unsetSelect();
		}
	}


	/**
	 * Installation des ecouteurs de l'objet
	 */
	public final void activate() {
		if (!isActive()) {
			super.activate();
			((AbstractModelElement) getModel()).addPropertyChangeListener(this);
		}
	}

	/**
	 * Mise en veille des ecouteurs de l'objet
	 */
	public final void deactivate() {
		if (isActive()) {
			super.deactivate();
			((AbstractModelElement) getModel()).removePropertyChangeListener(this);
		}
	}
}
