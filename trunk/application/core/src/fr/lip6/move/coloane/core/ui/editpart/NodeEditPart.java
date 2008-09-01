package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.model.AbstractPropertyChange;
import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.model.interfaces.ISpecialState;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.ui.commands.ArcCompleteCmd;
import fr.lip6.move.coloane.core.ui.commands.ArcCreateCmd;
import fr.lip6.move.coloane.core.ui.commands.ArcReconnectCmd;
import fr.lip6.move.coloane.core.ui.commands.NodeDeleteCmd;
import fr.lip6.move.coloane.core.ui.figures.INodeFigure;
import fr.lip6.move.coloane.core.ui.figures.nodes.RectangleNode;
import fr.lip6.move.coloane.core.ui.prefs.ColorsPrefs;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.swt.graphics.Color;

/**
 * EditPart pour les noeuds
 */
public class NodeEditPart extends AbstractGraphicalEditPart implements ISelectionEditPartListener, PropertyChangeListener, org.eclipse.gef.NodeEditPart {
	/**
	 * Logger 'fr.lip6.move.coloane.core'.
	 */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private boolean select = false;
	private boolean special = false;
	private boolean highlight = false;
	private boolean attributSelect = false;

	private ConnectionAnchor connectionAnchor;

	/**
	 * Permet d'écouter les changements de sélections des attributs
	 */
	private EditPartListener editPartListener = new EditPartListener.Stub() {
		/** {@inheritDoc} */
		@Override
		public void selectedStateChanged(EditPart editPart) {
			switch(editPart.getSelected()) {
			case EditPart.SELECTED:
			case EditPart.SELECTED_PRIMARY:
				attributSelect = true;
				refreshVisuals();
				break;
			case EditPart.SELECTED_NONE:
				attributSelect = false;
				refreshVisuals();
				break;
			default:
				break;
			}
		}
	};

	/**
	 * Creation de la figure associee (VUE)
	 * @return IFigure
	 */
	@Override
	protected final IFigure createFigure() {
		INode node = (INode) getModel();
		INodeFigure nodeFigure = (INodeFigure) node.getNodeFormalism().getGraphicalDescription().getAssociatedFigure();
		if (nodeFigure == null) {
			LOGGER.warning("Aucune figure trouvé, utilisation de la figure par défaut"); //$NON-NLS-1$
			nodeFigure = new RectangleNode();
		}
		nodeFigure.setSize(node.getGraphicInfo().getSize());
		nodeFigure.setForegroundColor(node.getGraphicInfo().getForeground());
		nodeFigure.setBackgroundColor(node.getGraphicInfo().getBackground());
		return nodeFigure;
	}


	/**
	 * Mise a jour de la vue a partir des informations du modele<br>
	 * La mise a jour utilise des methodes de parcours du modele et de moficiation de la vue
	 */
	@Override
	protected final void refreshVisuals() {
		// Mise à jour de la figure (couleurs et taille)
		getFigure().setForegroundColor(((INode) getModel()).getGraphicInfo().getForeground());
		getFigure().setBackgroundColor(((INode) getModel()).getGraphicInfo().getBackground());
		((INodeFigure) getFigure()).setLineWidth(1);
		if (special) {
			getFigure().setForegroundColor(ColorConstants.red);
			((INodeFigure) getFigure()).setLineWidth(3);
		}
		if (attributSelect) {
			getFigure().setBackgroundColor(ColorsPrefs.getColor("COLORNODE_HIGHLIGHT")); //$NON-NLS-1$
		}
		if (select) {
			getFigure().setForegroundColor(ColorsPrefs.getColor("COLORNODE")); //$NON-NLS-1$
			((INodeFigure) getFigure()).setLineWidth(3);
		}
		if (highlight) {
			figure.setBackgroundColor(ColorsPrefs.getColor("COLORNODE_MOUSE")); //$NON-NLS-1$
		}

		INode nodeModel = (INode) getModel();

		Rectangle bounds = new Rectangle(nodeModel.getGraphicInfo().getLocation(), nodeModel.getGraphicInfo().getSize());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), bounds);
	}

	/**
	 * Traitements a effectuer lors de la reception d'un evenement sur l'EditPart
	 * @param property L'evenement qui a ete levee
	 */
	public final void propertyChange(PropertyChangeEvent property) {
		LOGGER.finest("propertyChange(" + property.getPropertyName() + ")");  //$NON-NLS-1$//$NON-NLS-2$
		String prop = property.getPropertyName();

		// Propriete de connexion
		if (INode.INCOMING_ARCS_PROP.equals(prop)) {
			LOGGER.finest("Mise à jour des arcs entrants."); //$NON-NLS-1$
			refreshTargetConnections();
		} else if (INode.OUTCOMING_ARCS_PROP.equals(prop)) {
			LOGGER.finest("Mise à jour des arcs sortants."); //$NON-NLS-1$
			refreshSourceConnections();

		// Propriété de changement de couleur
		} else if (INode.FOREGROUND_COLOR_PROP.equalsIgnoreCase(prop)) {
			((INodeFigure) getFigure()).setForegroundColor((Color) property.getNewValue());
			refreshVisuals();
		} else if (INode.BACKGROUND_COLOR_PROP.equalsIgnoreCase(prop)) {
			((INodeFigure) getFigure()).setBackgroundColor((Color) property.getNewValue());
			refreshVisuals();

		// Propriété de changement de taille
		} else if (INode.RESIZE_PROP.equalsIgnoreCase(prop)) {
			INodeFigure nodeFigure = (INodeFigure) getFigure();
			Rectangle oldRect = nodeFigure.getClientArea();
			nodeFigure.setSize((Dimension) property.getNewValue());
			((GraphEditPart) getParent()).getFigure().repaint(oldRect);
			refreshVisuals();

		// Propriété pour une demande de changement de l'état "special" (mise en valeur)
		} else if (ISpecialState.SPECIAL_STATE_CHANGE.equals(prop)) {
			special = (Boolean) property.getNewValue();
			refreshVisuals();

		// Propriété de changement des coordonnées
		} else if (ILocationInfo.LOCATION_PROP.equals(prop)) {
			refreshVisuals();
		}
	}

	/**
	 * Regles de gestion de l'objet
	 */
	@Override
	protected final void createEditPolicies() {
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

		/* Ensemble des regles concernant le role profond de l'element du modele */
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentEditPolicy() {

			// On autorise la suppression de l'element
			@Override
			protected Command createDeleteCommand(GroupRequest deleteRequest) {
				IGraph parent = (IGraph) getHost().getParent().getModel();
				INode child = (INode) getHost().getModel();
				Command cmd =  new NodeDeleteCmd(parent, child);
				return cmd;
			}

		});

		/* Ensembles de regles gouvernant la creation et le maintient des connexions inter-noeuds */
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new GraphicalNodeEditPolicy() {

			/**
			 * Premiere etape de la creation d'un lien.<br>
			 * Lorsque l'utilisateur clique sur le noeud de depart, la commande CREATE est appelee.
			 * @see getConnectionCompleteCommand
			 */
			@Override
			protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
				INode source = (INode) getHost().getModel();

				// Demande la creation d'un arc (1ere etape)
				ArcCreateCmd cmd = new ArcCreateCmd(source, (IArcFormalism) request.getNewObjectType());
				request.setStartCommand(cmd);
				return cmd;
			}

			/**
			 * Deuxieme etape de la creation d'un lien.<br>
			 * Lorsque l'utilisateur clique sur le noeud d'arrivee, la commande COMPLETE est appelee.
			 */
			@Override
			protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {

				// !! Recupere le noeud source depuis la premiere phase !!
				ArcCreateCmd createCmd = (ArcCreateCmd) request.getStartCommand();

				// Autorise la connexion d'un arc
				ArcCompleteCmd cmd = new ArcCompleteCmd(createCmd.getSource(), (INode) getHost().getModel(), createCmd.getArcFormalism());
				return cmd;
			}

			@Override
			protected Command getReconnectSourceCommand(ReconnectRequest request) {
				IArc arc = (IArc) request.getConnectionEditPart().getModel();
				INode newSource = (INode) getHost().getModel();
				ArcReconnectCmd cmd = new ArcReconnectCmd(arc);
				cmd.setNewSource(newSource);

				return cmd;
			}

			@Override
			protected Command getReconnectTargetCommand(ReconnectRequest request) {
				IArc arc = (IArc) request.getConnectionEditPart().getModel();
				INode newTarget = (INode) getHost().getModel();
				ArcReconnectCmd cmd = new ArcReconnectCmd(arc);
				cmd.setNewTarget(newTarget);

				return cmd;
			}
		});

		getFigure().addMouseMotionListener(new MouseMotionListener.Stub() {
			@Override
			public void mouseEntered(MouseEvent me) {
				highlight = true;
				int previousState = getSelected();
				setSelected(ISelectionEditPartListener.HIGHLIGHT);
				setSelected(previousState);
			}

			@Override
			public void mouseExited(MouseEvent me) {
				highlight = false;
				int previousState = getSelected();
				setSelected(ISelectionEditPartListener.HIGHLIGHT_NONE);
				setSelected(previousState);
			}
		});
	}



	/**
	 * Creation des ancres pour attacher les connexions
	 * @return ConnectionAnchor
	 */
	protected final ConnectionAnchor getConnectionAnchor() {
		if (connectionAnchor == null) {
			connectionAnchor = ((INodeFigure) getFigure()).getConnectionAnchor();
		}
		return connectionAnchor;
	}

	/**
	 * Retourne la liste des arcs sortant du noeud considéré
	 * @return List of IArcImpl
	 */
	@Override
	protected final List<IArc> getModelSourceConnections() {
		return ((INode) getModel()).getOutcomingArcs();
	}

	/**
	 * Retourne la liste des arcs entrants du noeud considéré
	 * @return List of IArcImpl
	 */
	@Override
	protected final List<Object> getModelTargetConnections() {
		List<Object> targets = new ArrayList<Object>(((INode) getModel()).getIncomingArcs());
		for (ICoreTip tip : SessionManager.getInstance().getCurrentSession().getTip(((INode) getModel()).getId())) {
			targets.add(((ICoreTip) tip).getArcModel());
		}
		return targets;
	}

	/** {@inheritDoc} */
	public final ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return getConnectionAnchor();
	}

	/** {@inheritDoc} */
	public final ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return getConnectionAnchor();
	}

	/** {@inheritDoc} */
	public final ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return getConnectionAnchor();
	}

	/** {@inheritDoc} */
	public final ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return getConnectionAnchor();
	}


	/** {@inheritDoc} */
	@Override
	public final void activate() {
		if (!isActive()) {
			super.activate();
			((AbstractPropertyChange) getModel()).addPropertyChangeListener(this);
		}
	}

	/** {@inheritDoc} */
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
}
