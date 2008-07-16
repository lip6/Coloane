package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.model.AbstractPropertyChange;
import fr.lip6.move.coloane.core.ui.commands.ArcDeleteCmd;
import fr.lip6.move.coloane.core.ui.commands.InflexCreateCmd;
import fr.lip6.move.coloane.core.ui.commands.InflexDeleteCmd;
import fr.lip6.move.coloane.core.ui.commands.InflexMoveCmd;
import fr.lip6.move.coloane.core.ui.dialogs.ColorsPrefs;
import fr.lip6.move.coloane.core.ui.figures.IArcFigure;
import fr.lip6.move.coloane.core.ui.figures.arcs.SimpleArc;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IElement;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
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

public class ArcEditPart extends AbstractConnectionEditPart implements ISelectionEditPartListener, PropertyChangeListener {
	/**
	 * Logger 'fr.lip6.move.coloane.core'.
	 */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private boolean isSelected = false;

	/**
	 * Dessin de l'arc
	 * @return IFigure
	 */
	@Override
	protected final IFigure createFigure() {
		IArc arc = (IArc) getModel();
		IArcFigure arcFigure = (IArcFigure) arc.getArcFormalism().getGraphicalDescription().getAssociatedFigure();
		if (arcFigure == null) {
			LOGGER.warning("Aucune figure trouvé, utilisation de la figure par défaut"); //$NON-NLS-1$
			arcFigure = new SimpleArc();
		}
		arcFigure.setForegroundColor(arc.getGraphicInfo().getColor());
		return arcFigure;
	}

	/**
	 * Met a jour la vue en fonction de la lecture du modele<br>
	 * Cette methode utilise les accesseurs de la vue pour la modifier
	 */
	@Override
	protected final void refreshVisuals() {
		super.refreshVisuals();
		IArc arcModel = (IArc) getModel();

		Connection connection = (Connection) getFigure();
		connection.getConnectionRouter();

		List<AbsoluteBendpoint> modelConstraint = arcModel.getInflexPoints();
		getConnectionFigure().setRoutingConstraint(modelConstraint);
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
					setSelect();
				} else {
					setUnselect();
				}
//				fireSelectionChanged();
			}

			// Comportement lors de la deselection de l'objet
			@Override
			protected void hideSelection() {
				setUnselect();
			}

			// Comportement lors de la selection de l'objet
			@Override
			protected void showSelection() {
				setSelect();
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
			setHighlight();
		} else if (IArc.SPECIAL_PROP.equals(prop)) {
			setSelectSpecial();
		} else if (IArc.UNSELECT_PROP.equals(prop)) {
			setUnselect();
		} else if (IArc.COLOR_PROP.equals(prop) && !isSelected) {
			((IArcFigure) getFigure()).setForegroundColor((Color) property.getNewValue());

		// Propriété de demande de création/suppression d'un AttributEditPart
		} else if (IElement.ATTRIBUTE_CHANGE.equals(prop)) {
			getSource().getParent().refresh(); // demande de refresh sur le GraphEditPart
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

	/**
	 * Mise en valeur du noeud (selection d'un attribut referent)
	 */
	public final void setHighlight() {
		getFigure().setForegroundColor(ColorsPrefs.setColor("COLORARC_HIGHLIGHT")); //$NON-NLS-1$
		((IArcFigure) getFigure()).setLineWidth(2);
		isSelected = true;
	}

	/**
	 * Modifie la figure lorsqu'elle est selectionee
	 * On definit ici le feedback visuel lors de la selection d'un objet Noeud
	 */
	public final void setSelect() {
		getFigure().setForegroundColor(ColorsPrefs.setColor("COLORARC")); //$NON-NLS-1$
		((IArcFigure) getFigure()).setLineWidth(2);
		isSelected = true;
	}

	/**
	 * Modifie la figure lorsqu'elle est mise en valeur par des retours de services
	 * On definit ici le feedback visuel lors de la selection d'un resultat qui met en valeur le noeud
	 */
	public final void setSelectSpecial() {
		getFigure().setForegroundColor(ColorConstants.red);
		((IArcFigure) getFigure()).setLineWidth(2);
		isSelected = true;
	}

	/**
	 * Modifie la figure lorsqu'elle est deselectionee
	 * Annulation du feedback visuel du a la selection d'un objet Noeud
	 */
	public final void setUnselect() {
		if (!isSelected) {
			return;
		}
		isSelected = false;
		getFigure().setForegroundColor(((IArc) getModel()).getGraphicInfo().getColor());
		((IArcFigure) getFigure()).setLineWidth(1);
	}

	public final void childAdded(EditPart child, int index) { }

	public final void partActivated(EditPart editpart) { }

	public final void partDeactivated(EditPart editpart) { }

	public final void removingChild(EditPart child, int index) { }

	public final void selectedStateChanged(EditPart editpart) {
		switch(editpart.getSelected()) {
		case EditPart.SELECTED:
		case EditPart.SELECTED_PRIMARY:
			setHighlight();
			break;
		case ISelectionEditPartListener.HIGHLIGHT:
			break;
		case ISelectionEditPartListener.SPECIAL:
			break;
		case EditPart.SELECTED_NONE:
		case ISelectionEditPartListener.HIGHLIGHT_NONE:
		case ISelectionEditPartListener.SPECIAL_NONE:
			setUnselect();
			break;
		default:
			break;
		}
	}
}
