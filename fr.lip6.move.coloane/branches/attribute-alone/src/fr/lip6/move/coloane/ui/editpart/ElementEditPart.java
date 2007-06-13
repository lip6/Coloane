package fr.lip6.move.coloane.ui.editpart;

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

import fr.lip6.move.coloane.exceptions.BuildException;
import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.motor.formalism.ElementBase;
import fr.lip6.move.coloane.ui.commands.ArcCompleteCmd;
import fr.lip6.move.coloane.ui.commands.ArcCreateCmd;
import fr.lip6.move.coloane.ui.commands.ArcReconnectCmd;
import fr.lip6.move.coloane.ui.commands.NodeDeleteCmd;
import fr.lip6.move.coloane.ui.figures.INodeFigure;
import fr.lip6.move.coloane.ui.figures.NodeFigure;
import fr.lip6.move.coloane.ui.model.AbstractModelElement;
import fr.lip6.move.coloane.ui.model.IArcImpl;
import fr.lip6.move.coloane.ui.model.IModelImpl;
import fr.lip6.move.coloane.ui.model.INodeGraphicInfo;
import fr.lip6.move.coloane.ui.model.INodeImpl;

/**
 * EditPart pour les noeuds
 */
public class ElementEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener, NodeEditPart {

	private ConnectionAnchor anchor = null;

	/**
	 * Creation de la figure associee (VUE)
	 * @return IFigure
	 */
	protected IFigure createFigure() {
		IFigure figure = new NodeFigure((AbstractModelElement)getModel());
		return figure;
	}


	/** 
	 * Mise a jour de la vue a partir des informations du modele<br>
	 * La mise a jour utilise des methodes de parcours du modele et de moficiation de la vue
	 */
	protected void refreshVisuals() {
		INodeImpl nodeModel = (INodeImpl) getModel();
		INodeFigure nodeFigure = (INodeFigure)getFigure();

		// Modification du nom 
		nodeFigure.setNodeName(nodeModel.getNodeAttributeValue("name"));

		// Modification de la valeur
		if (nodeModel.getElementBase().getName().equalsIgnoreCase("place")) {
			nodeFigure.setNodeValue(nodeModel.getNodeAttributeValue("marking"));
		} else if (nodeModel.getElementBase().getName().equalsIgnoreCase("transition")) {
			if (!nodeModel.getNodeAttributeValue("guard").equalsIgnoreCase("true"))
				nodeFigure.setNodeValue(nodeModel.getNodeAttributeValue("guard"));
		} else if (nodeModel.getElementBase().getName().equalsIgnoreCase("state")) {
			nodeFigure.setNodeValue(nodeModel.getNodeAttributeValue("value"));
		} else if (nodeModel.getElementBase().getName().equalsIgnoreCase("initial_state")) {
			nodeFigure.setNodeValue(nodeModel.getNodeAttributeValue("value"));
		} else if (nodeModel.getElementBase().getName().equalsIgnoreCase("terminal_state")) {
			nodeFigure.setNodeValue(nodeModel.getNodeAttributeValue("value"));
		}

		// Modification du domaine
		if (nodeModel.getElementBase().getName().equalsIgnoreCase("place")) {
			nodeFigure.setNodeDomain(nodeModel.getNodeAttributeValue("domain"));
		}

		Rectangle bounds = new Rectangle(nodeModel.getGraphicInfo().getLocation(),nodeFigure.getPreferredSize());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this,getFigure(), bounds);

		// Il faut avertir FrameKit
		Coloane.notifyModelChange(nodeModel.getModelAdapter());
	}
	
	/**
	 * Traitements a effectuer lors de la reception d'un evenement sur l'EditPart
	 * @param property L'evenemtn qui a ete levee
	 */
	public void propertyChange(PropertyChangeEvent property) {
		String prop = property.getPropertyName();	

		// Si la propriete est un changement de position
		if (INodeImpl.LOCATION_PROP.equals(prop)) {
			refreshChildren();
		// Si c'est une propriete de connexion
		} else if (INodeImpl.SOURCE_ARCS_PROP.equals(prop)) {
			refreshSourceConnections();
		} else if (INodeImpl.TARGET_ARCS_PROP.equals(prop)) {
			refreshTargetConnections();
		} else if (INodeImpl.VALUE_PROP.equalsIgnoreCase(prop)) {
			refreshChildren();
		} else if (INodeImpl.SELECT_PROP.equalsIgnoreCase(prop)) {
			System.out.println("Recepetion de l'evenement special");
			INodeFigure nodeFigure = (INodeFigure)getFigure();
			nodeFigure.setSelectSpecial();
			refreshChildren();
		} else if (INodeImpl.UNSELECT_PROP.equalsIgnoreCase(prop)) {
			System.out.println("Recepetion de l'evenement unspecial");
			INodeFigure nodeFigure = (INodeFigure)getFigure();
			nodeFigure.unsetSelectSpecial();
			refreshChildren();
		}
		refreshVisuals();

	}


	/**
	 * Regles de gestion de l'objet
	 */
	protected void createEditPolicies() {

		/* Ensemble de regles concernant la selection/deselection de l'objet */
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new SelectionEditPolicy() {

			// Comportement lors de la deselection de l'objet
			@Override
			protected void hideSelection() {
				INodeFigure nodeFigure = (INodeFigure)getFigure();
				nodeFigure.setUnselect();
			}

			// Comportement lors de la selection de l'objet
			@Override
			protected void showSelection() {
				INodeFigure nodeFigure = (INodeFigure)getFigure();
				nodeFigure.setSelect();
			}
		});
		
		//installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new NonResizableEditPolicy());
		
		/* Ensemble des regles concernant le role profond de l'element du modele */
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentEditPolicy() {

			// On autorise la suppression de l'element
			protected Command createDeleteCommand(GroupRequest deleteRequest) {
				Object parent = getHost().getParent().getModel();
				Object child = getHost().getModel();
				if (parent instanceof IModelImpl && child instanceof INodeImpl) {
					try {
						return new NodeDeleteCmd((IModelImpl) parent, (INodeImpl) child);
					} catch (BuildException e) {
						System.err.println("Echec : "+e.getMessage());
					}
				}

				return super.createDeleteCommand(deleteRequest);
			}
		});

		/* Ensembles de regles gouvernant la creation et le maintient des connexions inter-noeuds */
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new GraphicalNodeEditPolicy() {

			/**
			 * Premiere etape de la creation d'un lien.<br>
			 * Lorsque l'utilisateur clique sur le noeud de depart, la commande CREATE est appelee.
			 */
			protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
				INodeImpl source = (INodeImpl)getHost().getModel();

				// Demande la creation d'un arc (1ere etape)
				ArcCreateCmd cmd = new ArcCreateCmd(source, (ElementBase)request.getNewObjectType());
				request.setStartCommand(cmd);
				return cmd;
			}

			/**
			 * Deuxieme etape de la creation d'un lien.<br>
			 * Lorsque l'utilisateur clique sur le noeud d'arrivee, la commande COMPLETE est appelee.
			 */
			protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {

				// Recupere le noeud source depuis la premiere phase
				ArcCreateCmd createCmd = (ArcCreateCmd)request.getStartCommand();

				// Autorise la connexion d'un arc
				ArcCompleteCmd cmd = new ArcCompleteCmd(createCmd.getSource(),(INodeImpl) getHost().getModel(), createCmd.getElementBase());
				return cmd;       			
			}

			protected Command getReconnectSourceCommand(ReconnectRequest request) {
				IArcImpl arc = (IArcImpl)request.getConnectionEditPart().getModel();
				INodeImpl newSource = (INodeImpl)getHost().getModel();
				ArcReconnectCmd cmd = new ArcReconnectCmd(arc);
				cmd.setNewSource(newSource);

				return cmd;
			}

			protected Command getReconnectTargetCommand(ReconnectRequest request) {
				IArcImpl arc = (IArcImpl)request.getConnectionEditPart().getModel();
				INodeImpl newTarget = (INodeImpl)getHost().getModel();
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
	protected ConnectionAnchor getConnectionAnchor() {

		// Il n'y a creation que la premiere fois
		if (anchor == null) {
			if (getModel() instanceof INodeImpl) {
				INodeGraphicInfo nodeGraph = ((INodeImpl) getModel()).getGraphicInfo();

				// Si le noeud est un cercle ou un double cercle
				if (nodeGraph.getFigureStyle() == INodeGraphicInfo.FIG_CIRCLE || nodeGraph.getFigureStyle() == INodeGraphicInfo.FIG_DBLCIRCLE) {
					anchor = new EllipseAnchor(((INodeFigure) getFigure()).getSymbol());

					// Si le noeud est un rectangle
				} else if (nodeGraph.getFigureStyle() == INodeGraphicInfo.FIG_RECT || nodeGraph.getFigureStyle() == INodeGraphicInfo.FIG_QUEUE) {
					anchor = new ChopboxAnchor(((INodeFigure) getFigure()).getSymbol());
				}
			}
		}
		return anchor;
	}

	/**
	 * Retourne la liste des arcs sortant du noeud considere
	 * @return List of IArcImpl
	 */
	protected List getModelSourceConnections() {
		return ((INodeImpl) getModel()).getSourceArcs();
	}

	/**
	 * Retourne la liste des arcs entrants du noeud considere
	 * @return List of IArcImpl
	 */
	protected List getModelTargetConnections() {
		return ((INodeImpl) getModel()).getTargetArcs();
	}

	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return getConnectionAnchor();
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return getConnectionAnchor();
	}

	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return getConnectionAnchor();
	}

	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return getConnectionAnchor();
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
