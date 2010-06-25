package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.model.AbstractPropertyChange;
import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.core.model.interfaces.ISpecialState;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.ui.commands.ArcDeleteCmd;
import fr.lip6.move.coloane.core.ui.commands.InflexCreateCmd;
import fr.lip6.move.coloane.core.ui.commands.InflexDeleteCmd;
import fr.lip6.move.coloane.core.ui.commands.InflexMoveCmd;
import fr.lip6.move.coloane.core.ui.commands.LinkCompleteCommand;
import fr.lip6.move.coloane.core.ui.commands.LinkCreateCommand;
import fr.lip6.move.coloane.core.ui.commands.LinkReconnectCommand;
import fr.lip6.move.coloane.core.ui.figures.IArcFigure;
import fr.lip6.move.coloane.core.ui.figures.RoundedPolyline;
import fr.lip6.move.coloane.core.ui.figures.arcs.DirectedArc;
import fr.lip6.move.coloane.core.ui.prefs.ColorsPrefs;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.BendpointEditPolicy;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.requests.ReconnectRequest;

/**
 * EditPart pour les arcs (CONTROLEUR)
 */
public class ArcEditPart extends AbstractConnectionEditPart implements ISelectionEditPartListener, PropertyChangeListener, NodeEditPart {
	/**
	 * Logger 'fr.lip6.move.coloane.core'.
	 */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private static final ConnectionRouter CONNECTION_ROUTER = new BendpointConnectionRouter();

	private boolean select = false;
	private boolean special = false;
	private boolean attributSelect = false;

	/**
	 * Permet d'écouter les changements de sélections des attributs
	 */
	private EditPartListener editPartListener = new EditPartListener.Stub() {
		/** {@inheritDoc} */
		@Override
		public void selectedStateChanged(EditPart editpart) {
			switch(editpart.getSelected()) {
			case EditPart.SELECTED:
			case EditPart.SELECTED_PRIMARY:
				attributSelect = true;
				break;
			case EditPart.SELECTED_NONE:
				attributSelect = false;
				break;
			default:
				break;
			}
			refreshVisuals();
		}
	};

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
			arcFigure = new DirectedArc();
		}
		arcFigure.setModelElement(arc);
		arcFigure.setForegroundColor(arc.getGraphicInfo().getColor());
		arcFigure.setConnectionRouter(CONNECTION_ROUTER);
		return arcFigure;
	}

	/**
	 * Met a jour la vue en fonction de la lecture du modele<br>
	 * Cette methode utilise les accesseurs de la vue pour la modifier
	 */
	@Override
	protected final void refreshVisuals() {
		// Mise à jour de la figure (couleurs et taille)
		getFigure().setForegroundColor(((IArc) getModel()).getGraphicInfo().getColor());
		((IArcFigure) getFigure()).setLineWidth(1);
		if (special) {
			getFigure().setForegroundColor(ColorConstants.red);
			((IArcFigure) getFigure()).setLineWidth(2);
		}
		if (attributSelect) {
			getFigure().setForegroundColor(ColorsPrefs.getColor("COLORARC_HIGHLIGHT")); //$NON-NLS-1$
			((IArcFigure) getFigure()).setLineWidth(2);
		}
		if (select) {
			getFigure().setForegroundColor(ColorsPrefs.getColor("COLORARC")); //$NON-NLS-1$
			((IArcFigure) getFigure()).setLineWidth(2);
		}

		IArc arcModel = (IArc) getModel();

		Connection connection = (Connection) getFigure();
		connection.getConnectionRouter();
		// TODO : risque de NullPointer
		((RoundedPolyline) connection).setCurved(arcModel.getGraphicInfo().getCurve());

		List<AbsoluteBendpoint> modelConstraint = arcModel.getInflexPoints();
		getConnectionFigure().setRoutingConstraint(modelConstraint);
	}


	/** {@inheritDoc} */
	@Override
	protected final void createEditPolicies() {
		// Selection handle edit policy.
		// Makes the connection show a feedback, when selected by the user.
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());

		installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new BendpointEditPolicy() {
			@Override
			protected Command getCreateBendpointCommand(BendpointRequest request) {
				LOGGER.finest("Creation du point d'inflexion : " + request.getIndex()); //$NON-NLS-1$
				Point p = request.getLocation();
				getConnection().translateToRelative(p);
				InflexCreateCmd com = new InflexCreateCmd((IArc) getModel(), request.getLocation(), request.getIndex());
				return com;
			}

			@Override
			protected Command getDeleteBendpointCommand(BendpointRequest request) {
				LOGGER.finest("Suppression du point d'inflexion : " + request.getIndex()); //$NON-NLS-1$
				Point p = request.getLocation();
				getConnection().translateToRelative(p);
				InflexDeleteCmd com = new InflexDeleteCmd((IArc) getModel(), request.getIndex());
				return com;
			}
			@Override
			protected Command getMoveBendpointCommand(BendpointRequest request) {
				Point p = request.getLocation();
				LOGGER.finest("Mouvement de point d'inflexion (workspace) : " + p.x + "," + p.y); //$NON-NLS-1$ //$NON-NLS-2$
				getConnection().translateToRelative(p);
				LOGGER.finest("Mouvement de point d'inflexion (univers) : " + p.x + "," + p.y); //$NON-NLS-1$ //$NON-NLS-2$
				InflexMoveCmd com = new InflexMoveCmd((IArc) getModel(), request.getLocation(), request.getIndex());
				return com;
			}
		});

		/* Ensemble de regles concernant la selection/deselection de l'objet */
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new SelectionEditPolicy() {
			// Comportement lors de la deselection de l'objet
			@Override
			protected void hideSelection() {
				select = false;
				refreshVisuals();
			}

			// Comportement lors de la selection de l'objet
			@Override
			protected void showSelection() {
				select = true;
				refreshVisuals();
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

		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new GraphicalNodeEditPolicy() {
			@Override
			protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
				Command cmd = null;
				if (request.getStartCommand() instanceof LinkCreateCommand) {
					ILinkableElement source = ((LinkCreateCommand) request.getStartCommand()).getSource();
					if (source instanceof IStickyNote) {
						IGraph graph = (IGraph) ((IArc) getHost().getModel()).getParent();
						ILinkableElement target = (ILinkableElement) getHost().getModel();

						cmd = new LinkCompleteCommand(graph, source, target);
					}
				}
				return cmd;
			}

			@Override
			protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
				Command cmd = null;
				if (request.getNewObjectType() == ILink.class) {
					cmd = new LinkCreateCommand((ILinkableElement) getHost().getModel());
				}
				request.setStartCommand(cmd);
				return cmd;
			}

			@Override
			protected Command getReconnectSourceCommand(ReconnectRequest request) {
				Command cmd = null;
				if (request.getConnectionEditPart() instanceof LinkEditPart) {
					ILink link = (ILink) request.getConnectionEditPart().getModel();
					ILinkableElement newElement = (ILinkableElement) getHost().getModel();
					cmd = new LinkReconnectCommand(link, link.getNote(), newElement);
				}
				return cmd;
			}

			@Override
			protected Command getReconnectTargetCommand(ReconnectRequest request) {
				return null;
			}
		});
	}

	/**
	 * Traitements a effectuer lors de la reception d'un evenement sur l'EditPart
	 * @param property L'evenement qui a ete levee
	 */
	public final void propertyChange(PropertyChangeEvent property) {
		LOGGER.finest("propertyChange(" + property.getPropertyName() + ")");  //$NON-NLS-1$//$NON-NLS-2$
		String prop = property.getPropertyName();

		// demande de refresh sur le GraphEditPart
		if (ISpecialState.SPECIAL_STATE_CHANGE.equals(prop)) {
			special = (Boolean) property.getNewValue();
			refreshVisuals();
		} else if (INode.OUTGOING_ARCS_PROP.equals(prop)) {
			refreshSourceConnections();
		} else if (IArc.INFLEXPOINT_PROP.equals(prop)) {
			refreshVisuals();

			// If the user has curved an arc, visuals must be refreshed
		} else if (IArc.CURVE_PROP.equals(prop)) {
			refreshVisuals();
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

	/** {@inheritDoc} */
	public final EditPartListener getSelectionEditPartListener() {
		return editPartListener;
	}

	/** {@inheritDoc} */
	public final ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		return new ArcConnectionAnchor(this);
	}

	/** {@inheritDoc} */
	public final ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new ArcConnectionAnchor(this);
	}

	/** {@inheritDoc} */
	public final ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		return new ArcConnectionAnchor(this);
	}

	/** {@inheritDoc} */
	public final ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new ArcConnectionAnchor(this);
	}

	/**
	 * Retourne la liste des arcs entrants du noeud considere
	 * @return List of IArcImpl
	 */
	@Override
	protected final List<Object> getModelTargetConnections() {
		List<Object> targets = new ArrayList<Object>();
		for (ICoreTip tip : SessionManager.getInstance().getCurrentSession().getTip(((IArc) getModel()).getId())) {
			targets.add(((ICoreTip) tip).getArcModel());
		}
		return targets;
	}

	/** {@inheritDoc} */
	@Override
	protected final List<Object> getModelSourceConnections() {
		List<Object> sources = new ArrayList<Object>();
		sources.addAll(((ILinkableElement) getModel()).getLinks());
		return sources;
	}
}
