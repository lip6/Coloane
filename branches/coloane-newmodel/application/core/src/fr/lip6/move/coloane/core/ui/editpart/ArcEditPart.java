package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.commands.ArcDeleteCmd;
import fr.lip6.move.coloane.core.ui.commands.InflexCreateCmd;
import fr.lip6.move.coloane.core.ui.commands.InflexDeleteCmd;
import fr.lip6.move.coloane.core.ui.commands.InflexMoveCmd;
import fr.lip6.move.coloane.core.ui.figures.ArcFigure;
import fr.lip6.move.coloane.core.ui.figures.IArcFigure;
import fr.lip6.move.coloane.core.ui.model.AbstractPropertyChange;
import fr.lip6.move.coloane.core.ui.model.interfaces.IArc;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
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
import org.eclipse.swt.graphics.Color;

/**
 * EditPart pour les arcs (CONTROLEUR)
 */

public class ArcEditPart extends AbstractConnectionEditPart implements PropertyChangeListener {

	/**
	 * Dessin de l'arc
	 * @return IFigure
	 */
	@Override
	protected final IFigure createFigure() {
		IFigure connection = new ArcFigure((IArc) getModel());
		return connection;
	}

	/**
	 * Met a jour la vue en fonction de la lecture du modele<br>
	 * Cette methode utilise les accesseurs de la vue pour la modifier
	 */
	@Override
	protected final void refreshVisuals() {
		super.refreshVisuals();
		IArc arcModel = (IArc) getModel();

		IArcFigure connection = (IArcFigure) getFigure();
		connection.getConnectionRouter();

		List<AbsoluteBendpoint> modelConstraint = arcModel.getInflexPoints();
		getConnectionFigure().setRoutingConstraint(modelConstraint);

		// Il faut avertir FrameKit
		Coloane.notifyModelChange(arcModel);
	}


	@Override
	protected final void createEditPolicies() {
		// Selection handle edit policy.
		// Makes the connection show a feedback, when selected by the user.
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());

		installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new BendpointEditPolicy() {
			@Override
			protected Command getCreateBendpointCommand(BendpointRequest request) {
				Coloane.getLogger().finest("Creation du point d'inflexion : " + request.getIndex()); //$NON-NLS-1$
				Point p = request.getLocation();
				getConnection().translateToRelative(p);
				InflexCreateCmd com = new InflexCreateCmd((IArc) getModel(), request.getLocation(), request.getIndex());
				return com;
			}

			@Override
			protected Command getDeleteBendpointCommand(BendpointRequest request) {
				Coloane.getLogger().finest("Suppression du point d'inflexion : " + request.getIndex()); //$NON-NLS-1$
				Point p = request.getLocation();
				getConnection().translateToRelative(p);
				InflexDeleteCmd com = new InflexDeleteCmd((IArc) getModel(), request.getLocation(), request.getIndex());
				return com;
			}
			@Override
			protected Command getMoveBendpointCommand(BendpointRequest request) {
				Point p = request.getLocation();
				Coloane.getLogger().finest("Mouvement de point d'inflexion (workspace) : " + p.x + "," + p.y); //$NON-NLS-1$ //$NON-NLS-2$
				getConnection().translateToRelative(p);
				Coloane.getLogger().finest("Mouvement de point d'inflexion (univers) : " + p.x + "," + p.y); //$NON-NLS-1$ //$NON-NLS-2$
				InflexMoveCmd com = new InflexMoveCmd((IArc) getModel(), request.getLocation(), request.getIndex());
				return com;
			}
		});

		/* Ensemble de regles concernant la selection/deselection de l'objet */
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new SelectionEditPolicy() {

			@Override
			protected void setSelectedState(int state) {
				super.setSelectedState(state);
				if (state != 0) {
					((IArc) getModel()).setAttributesSelected(false, true);
					((IArcFigure) getFigure()).setSelect();
				} else {
					((IArc) getModel()).setAttributesSelected(false, false);
					((IArcFigure) getFigure()).setUnselect();
				}
			}

			// Comportement lors de la deselection de l'objet
			@Override
			protected void hideSelection() {
				IArcFigure arcFigure = (IArcFigure) getFigure();
				arcFigure.setUnselect();
			}

			// Comportement lors de la selection de l'objet
			@Override
			protected void showSelection() {
				IArcFigure arcFigure = (IArcFigure) getFigure();
				arcFigure.setSelect();
			}
		});

		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());

		// Allows the removal of the connection model element
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new ConnectionEditPolicy() {
			@Override
			protected Command getDeleteCommand(GroupRequest request) {
				return new ArcDeleteCmd((IArc) getModel());
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
		if (IArc.INFLEXPOINT_PROP.equals(prop)) {
			refreshVisuals();
		} else if (IArc.SELECT_PROP.equals(prop)) {
			((IArcFigure) getFigure()).setHighlight();
		} else if (IArc.SPECIAL_PROP.equals(prop)) {
			((IArcFigure) getFigure()).setSelectSpecial();
		} else if (IArc.UNSELECT_PROP.equals(prop)) {
			((IArcFigure) getFigure()).setUnselect();
		} else if (IArc.COLOR_PROP.equals(prop)) {
			((IArcFigure) getFigure()).setForegroundColor((Color) property.getNewValue());
		}
	}


	/**
	 * Installation des ecouteurs de l'objet
	 */
	@Override
	public final void activate() {
		if (!isActive()) {
			super.activate();
			((AbstractPropertyChange) getModel()).addPropertyChangeListener(this);
		}
	}

	/**
	 * Mise en veille des ecouteurs de l'objet
	 */
	@Override
	public final void deactivate() {
		if (isActive()) {
			super.deactivate();
			((AbstractPropertyChange) getModel()).removePropertyChangeListener(this);
		}
	}
}
