package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalisms.elements.Arc;
import fr.lip6.move.coloane.core.ui.commands.ArcCompleteCmd;
import fr.lip6.move.coloane.core.ui.commands.ArcCreateCmd;
import fr.lip6.move.coloane.core.ui.commands.ArcReconnectCmd;
import fr.lip6.move.coloane.core.ui.commands.NodeDeleteCmd;
import fr.lip6.move.coloane.core.ui.figures.INodeFigure;
import fr.lip6.move.coloane.core.ui.figures.NodeFigure;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo;
import fr.lip6.move.coloane.interfaces.model.impl.AbstractElement;
import fr.lip6.move.coloane.interfaces.model.impl.AbstractPropertyChange;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
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
public class NodeEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener, org.eclipse.gef.NodeEditPart {

	private ConnectionAnchor anchor = null;

	/**
	 * Creation de la figure associee (VUE)
	 * @return IFigure
	 */
	@Override
	protected final IFigure createFigure() {
		IFigure figure = new NodeFigure((AbstractElement) getModel());
		return figure;
	}


	/**
	 * Mise a jour de la vue a partir des informations du modele<br>
	 * La mise a jour utilise des methodes de parcours du modele et de moficiation de la vue
	 */
	@Override
	protected final void refreshVisuals() {
		INode nodeModel = (INode) getModel();

		Rectangle bounds = new Rectangle(nodeModel.getGraphicInfo().getLocation(), nodeModel.getGraphicInfo().getSize());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), bounds);

		// Il faut avertir FrameKit
		Coloane.notifyModelChange(nodeModel);
	}

	/**
	 * Traitements a effectuer lors de la reception d'un evenement sur l'EditPart
	 * @param property L'evenement qui a ete levee
	 */
	public final void propertyChange(PropertyChangeEvent property) {
		String prop = property.getPropertyName();

		// Propriete de connexion
		if (INode.SOURCE_ARCS_PROP.equals(prop)) {
			refreshSourceConnections();
		} else if (INode.TARGET_ARCS_PROP.equals(prop)) {
			refreshTargetConnections();

		// Propriete de selection speciale
		} else if (INode.SPECIAL_PROP.equalsIgnoreCase(prop)) {
			((INodeFigure) getFigure()).setSelectSpecial();
			return;
		} else if (INode.UNSPECIAL_PROP.equalsIgnoreCase(prop)) {
			((INodeFigure) getFigure()).setUnselect();
			return;

		// Propriete de selection
		} else if (INode.SELECT_PROP.equalsIgnoreCase(prop)) {
			((INodeFigure) getFigure()).setHighlight();
			return;
			//((INodeImpl) getModel()).setAttributesSelected(false, true);
		} else if (INode.UNSELECT_PROP.equalsIgnoreCase(prop)) {
			((INodeFigure) getFigure()).setUnselect();
			return;

		// Propriété de changement de couleur
		} else if (INode.FOREGROUND_COLOR_PROP.equalsIgnoreCase(prop)) {
			((INodeFigure) getFigure()).setForegroundColor((Color) property.getNewValue());
		} else if (INode.BACKGROUND_COLOR_PROP.equalsIgnoreCase(prop)) {
			((INodeFigure) getFigure()).setBackgroundColor((Color) property.getNewValue());

		// Propriété de changement de taille
		} else if (INode.RESIZE_PROP.equalsIgnoreCase(prop)) {
			INodeFigure nodeFigure = (INodeFigure) getFigure();
			Rectangle oldRect = nodeFigure.getClientArea();
			nodeFigure.setSize((Dimension) property.getNewValue());
			((GraphEditPart) getParent()).getFigure().repaint(oldRect);
		}

		refreshVisuals();
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
				INodeFigure nodeFigure = (INodeFigure) getFigure();
				nodeFigure.setUnselect();
			}

			// Comportement lors de la selection de l'objet
			@Override
			protected void showSelection() {
				INodeFigure nodeFigure = (INodeFigure) getFigure();
				nodeFigure.setSelect();
			}

			// Comportement lorsque l'objet est selectionne
			@Override
			protected void setSelectedState(int state) {
				super.setSelectedState(state);
				if (state != 0) {
					((INode) getModel()).setAttributesSelected(false, true);
				} else {
					((INode) getModel()).setAttributesSelected(false, false);
				}
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
				ArcCreateCmd cmd = new ArcCreateCmd(source, (Arc) request.getNewObjectType());
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
				ArcCompleteCmd cmd = new ArcCompleteCmd(createCmd.getSource(), (INode) getHost().getModel(), createCmd.getElementBase());
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
	}



	/**
	 * Creation des ancres pour attacher les connexions
	 * @return ConnectionAnchor
	 */
	protected final ConnectionAnchor getConnectionAnchor() {

		// Il n'y a creation que la premiere fois
		if (anchor == null) {
			if (getModel() instanceof INode) {
				INodeGraphicInfo nodeGraph = ((INode) getModel()).getGraphicInfo();

				// Si le noeud est un cercle ou un double cercle
				if (nodeGraph.getFigureStyle() == INodeGraphicInfo.FIG_CIRCLE || nodeGraph.getFigureStyle() == INodeGraphicInfo.FIG_DBLCIRCLE) {
					anchor = new EllipseAnchor((INodeFigure) getFigure());

					// Si le noeud est un rectangle
				} else if (nodeGraph.getFigureStyle() == INodeGraphicInfo.FIG_RECT || nodeGraph.getFigureStyle() == INodeGraphicInfo.FIG_QUEUE) {
					anchor = new ChopboxAnchor((INodeFigure) getFigure());
				}
			}
		}
		return anchor;
	}

	/**
	 * Retourne la liste des arcs sortant du noeud considere
	 * @return List of IArcImpl
	 */
	@Override
	protected final List<IArc> getModelSourceConnections() {
		return ((INode) getModel()).getSourceArcs();
	}

	/**
	 * Retourne la liste des arcs entrants du noeud considere
	 * @return List of IArcImpl
	 */
	@Override
	protected final List<IArc> getModelTargetConnections() {
		return ((INode) getModel()).getTargetArcs();
	}

	public final ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return getConnectionAnchor();
	}

	public final ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return getConnectionAnchor();
	}

	public final ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return getConnectionAnchor();
	}

	public final ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return getConnectionAnchor();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#activate()
	 */
	@Override
	public final void activate() {
		if (!isActive()) {
			super.activate();
			((AbstractPropertyChange) getModel()).addPropertyChangeListener(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#deactivate()
	 */
	@Override
	public final void deactivate() {
		if (isActive()) {
			super.deactivate();
			((AbstractPropertyChange) getModel()).removePropertyChangeListener(this);
		}
	}

}
