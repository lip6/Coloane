package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalism.ElementBase;
import fr.lip6.move.coloane.core.ui.commands.ArcCompleteCmd;
import fr.lip6.move.coloane.core.ui.commands.ArcCreateCmd;
import fr.lip6.move.coloane.core.ui.commands.ArcReconnectCmd;
import fr.lip6.move.coloane.core.ui.commands.NodeDeleteCmd;
import fr.lip6.move.coloane.core.ui.figures.INodeFigure;
import fr.lip6.move.coloane.core.ui.figures.NodeFigure;
import fr.lip6.move.coloane.core.ui.model.AbstractModelElement;
import fr.lip6.move.coloane.core.ui.model.IArcImpl;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.requests.ReconnectRequest;

/**
 * EditPart pour les noeuds
 */
public class ElementEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener, NodeEditPart {

	private ConnectionAnchor anchor = null;

	/**
	 * Creation de la figure associee (VUE)
	 * @return IFigure
	 */
	protected final IFigure createFigure() {
		IFigure figure = new NodeFigure((AbstractModelElement) getModel());
		return figure;
	}


	/**
	 * Mise a jour de la vue a partir des informations du modele<br>
	 * La mise a jour utilise des methodes de parcours du modele et de moficiation de la vue
	 */
	protected final void refreshVisuals() {
		INodeImpl nodeModel = (INodeImpl) getModel();

		Rectangle bounds = new Rectangle(nodeModel.getGraphicInfo().getLocation(), nodeModel.getGraphicInfo().getSize());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), bounds);

		// Il faut avertir FrameKit
		Coloane.notifyModelChange(nodeModel.getModelAdapter());
	}

	/**
	 * Traitements a effectuer lors de la reception d'un evenement sur l'EditPart
	 * @param property L'evenement qui a ete levee
	 */
	public final void propertyChange(PropertyChangeEvent property) {
		String prop = property.getPropertyName();

		// Propriete de connexion
		if (INodeImpl.SOURCE_ARCS_PROP.equals(prop)) {
			refreshSourceConnections();
		} else if (INodeImpl.TARGET_ARCS_PROP.equals(prop)) {
			refreshTargetConnections();

		// Propriete de selection speciale
		} else if (INodeImpl.SPECIAL_PROP.equalsIgnoreCase(prop)) {
			((INodeFigure) getFigure()).setSelectSpecial();
			return;
		} else if (INodeImpl.UNSPECIAL_PROP.equalsIgnoreCase(prop)) {
			((INodeFigure) getFigure()).unsetSelectSpecial();
			return;

			// Propriete de selection
		} else if (INodeImpl.SELECT_PROP.equalsIgnoreCase(prop)) {
			((INodeFigure) getFigure()).setHighlight();
			return;
			//((INodeImpl) getModel()).setAttributesSelected(false, true);
		} else if (INodeImpl.UNSELECT_PROP.equalsIgnoreCase(prop)) {
			((INodeFigure) getFigure()).setUnselect();
			return;
		}

		refreshVisuals();
	}

	/**
	 * Regles de gestion de l'objet
	 */
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
				// TODO Auto-generated method stub
				super.setSelectedState(state);
				if (state != 0) {
					((INodeImpl) getModel()).setAttributesSelected(false, true);
				} else {
					((INodeImpl) getModel()).setAttributesSelected(false, false);
				}
			}
		});

		/* Ensemble des regles concernant le role profond de l'element du modele */
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentEditPolicy() {

			// On autorise la suppression de l'element
			protected Command createDeleteCommand(GroupRequest deleteRequest) {
				IModelImpl parent = (IModelImpl) getHost().getParent().getModel();
				INodeImpl child = (INodeImpl) getHost().getModel();
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
			protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
				INodeImpl source = (INodeImpl) getHost().getModel();

				// Demande la creation d'un arc (1ere etape)
				ArcCreateCmd cmd = new ArcCreateCmd(source, (ElementBase) request.getNewObjectType());
				request.setStartCommand(cmd);
				return cmd;
			}

			/**
			 * Deuxieme etape de la creation d'un lien.<br>
			 * Lorsque l'utilisateur clique sur le noeud d'arrivee, la commande COMPLETE est appelee.
			 */
			protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {

				// !! Recupere le noeud source depuis la premiere phase !!
				ArcCreateCmd createCmd = (ArcCreateCmd) request.getStartCommand();

				// Autorise la connexion d'un arc
				ArcCompleteCmd cmd = new ArcCompleteCmd(createCmd.getSource(), (INodeImpl) getHost().getModel(), createCmd.getElementBase());
				return cmd;
			}

			protected Command getReconnectSourceCommand(ReconnectRequest request) {
				IArcImpl arc = (IArcImpl) request.getConnectionEditPart().getModel();
				INodeImpl newSource = (INodeImpl) getHost().getModel();
				ArcReconnectCmd cmd = new ArcReconnectCmd(arc);
				cmd.setNewSource(newSource);

				return cmd;
			}

			protected Command getReconnectTargetCommand(ReconnectRequest request) {
				IArcImpl arc = (IArcImpl) request.getConnectionEditPart().getModel();
				INodeImpl newTarget = (INodeImpl) getHost().getModel();
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
			if (getModel() instanceof INodeImpl) {
				INodeGraphicInfo nodeGraph = ((INodeImpl) getModel()).getGraphicInfo();

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
	protected final List<IArcImpl> getModelSourceConnections() {
		return ((INodeImpl) getModel()).getSourceArcs();
	}

	/**
	 * Retourne la liste des arcs entrants du noeud considere
	 * @return List of IArcImpl
	 */
	protected final List<IArcImpl> getModelTargetConnections() {
		return ((INodeImpl) getModel()).getTargetArcs();
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
	public final void activate() {
		if (!isActive()) {
			super.activate();
			((AbstractModelElement) getModel()).addPropertyChangeListener(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#deactivate()
	 */
	public final void deactivate() {
		if (isActive()) {
			super.deactivate();
			((AbstractModelElement) getModel()).removePropertyChangeListener(this);
		}
	}

}
