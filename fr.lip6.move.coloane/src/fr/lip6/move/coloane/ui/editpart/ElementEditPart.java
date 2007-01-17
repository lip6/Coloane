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
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import fr.lip6.move.coloane.interfaces.models.INodeGraphicInfo;
import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.motor.formalism.ElementBase;
import fr.lip6.move.coloane.motor.models.AbstractModelElement;
import fr.lip6.move.coloane.motor.models.ArcImplAdapter;
import fr.lip6.move.coloane.motor.models.ModelImplAdapter;
import fr.lip6.move.coloane.motor.models.NodeImplAdapter;
import fr.lip6.move.coloane.ui.commands.ArcCompleteCmd;
import fr.lip6.move.coloane.ui.commands.ArcCreateCmd;
import fr.lip6.move.coloane.ui.commands.ArcReconnectCmd;
import fr.lip6.move.coloane.ui.commands.NodeDeleteCmd;
import fr.lip6.move.coloane.ui.views.INodeFigure;
import fr.lip6.move.coloane.ui.views.NodeFigure;

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
		NodeImplAdapter nodeModel = (NodeImplAdapter) getModel();
		INodeFigure nodeFigure = (INodeFigure)getFigure();
		
		// Modification du nom 
		nodeFigure.setNodeName(nodeModel.getNodeAttributeValue("name"));
		
		// Modification de la valeur
		if (nodeModel.getElementBase().getName().equalsIgnoreCase("place")) {
			nodeFigure.setNodeValue(nodeModel.getNodeAttributeValue("marking"));
		} else if (nodeModel.getElementBase().getName().equalsIgnoreCase("transition")) {
			nodeFigure.setNodeValue(nodeModel.getNodeAttributeValue("guard"));
		}
		
		// Modification du domaine
		if (nodeModel.getElementBase().getName().equalsIgnoreCase("place")) {
			nodeFigure.setNodeDomain(nodeModel.getNodeAttributeValue("domain"));
		}
		
		Rectangle bounds = new Rectangle(nodeModel.getGraphicInfo().getLocation(),nodeFigure.getPreferredSize());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this,getFigure(), bounds);
		
		// Il faut avertir FrameKit
		System.out.println("Avertissement de FK - Node");
		Coloane.notifyModelChange();
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
	 * Traitements a effectuer lors de la reception d'un evenement sur l'EditPart
	 * @param property L'evenemtn qui a ete levee
	 */
	public void propertyChange(PropertyChangeEvent property) {
		String prop = property.getPropertyName();	

		// Si la propriete est un changement de position
		if (NodeImplAdapter.SIZE_PROP.equals(prop) || NodeImplAdapter.LOCATION_PROP.equals(prop)) {
			refreshChildren();
		
		// Si c'est une propriete de connexion
		} else if (NodeImplAdapter.SOURCE_ARCS_PROP.equals(prop)) {
			refreshSourceConnections();
		} else if (NodeImplAdapter.TARGET_ARCS_PROP.equals(prop)) {
			refreshTargetConnections();
		} else if (NodeImplAdapter.VALUE_PROP.equalsIgnoreCase(prop)) {
			refreshChildren();
		}
		refreshVisuals();

	}

	
	/**
	 * Regles de gestion de l'objet
	 */
	protected void createEditPolicies() {

		// On autorise la suppression de l'element
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentEditPolicy() {
			protected Command createDeleteCommand(GroupRequest deleteRequest) {
            	Object parent = getHost().getParent().getModel();
            	Object child = getHost().getModel();
            	if (parent instanceof ModelImplAdapter && child instanceof NodeImplAdapter) {
            		return new NodeDeleteCmd((ModelImplAdapter) parent, (NodeImplAdapter) child);
            	}
            		
            	return super.createDeleteCommand(deleteRequest);
            }
		});
            
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new GraphicalNodeEditPolicy() {
        	
        	
        	/**
        	 * Methode appelee pour confirmer la connexion de l'arc
        	 * @return Command
        	 */
        	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        		ArcCreateCmd createCmd = (ArcCreateCmd)request.getStartCommand();
        		
        		// Autorise la connexion d'un arc
        		ArcCompleteCmd cmd = new ArcCompleteCmd(createCmd.getSource(),(NodeImplAdapter) getHost().getModel(), createCmd.getElementBase());
        		return cmd;       			
        	}
        		
        	
        	/**
        	 * Methode appelee lorsque le pointeur de la souris se trouve sur un objet
        	 * @return Command
        	 */
        	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        		NodeImplAdapter source = (NodeImplAdapter)getHost().getModel();
        		
        		// Autorise la creation d'un arc
        		ArcCreateCmd cmd = new ArcCreateCmd(source, (ElementBase)request.getNewObjectType());
        		request.setStartCommand(cmd);
        		return cmd;
        	}

        	
       		protected Command getReconnectSourceCommand(ReconnectRequest request) {
       			System.out.println("Reconnect");
       			ArcImplAdapter arc = (ArcImplAdapter)request.getConnectionEditPart().getModel();
       			NodeImplAdapter newSource = (NodeImplAdapter)getHost().getModel();
       			ArcReconnectCmd cmd = new ArcReconnectCmd(arc);
       			cmd.setNewSource(newSource);
        			
       			return cmd;
       		}

       		protected Command getReconnectTargetCommand(ReconnectRequest request) {
        		ArcImplAdapter arc = (ArcImplAdapter)request.getConnectionEditPart().getModel();
        		NodeImplAdapter newTarget = (NodeImplAdapter)getHost().getModel();
        		ArcReconnectCmd cmd = new ArcReconnectCmd(arc);
        		cmd.setNewTarget(newTarget);
        		
        		return cmd;
        	}
        });
    }


	protected ConnectionAnchor getConnectionAnchor() {
		if (anchor == null) {
			if (getModel() instanceof NodeImplAdapter) {
				INodeGraphicInfo nodeGraph = ((NodeImplAdapter) getModel()).getGraphicInfo();
				if (nodeGraph.getFigureStyle() == INodeGraphicInfo.FIG_CIRCLE || nodeGraph.getFigureStyle() == INodeGraphicInfo.FIG_DBLCIRCLE) {
					anchor = new EllipseAnchor(((INodeFigure) getFigure()).getSymbol());

				} else if (nodeGraph.getFigureStyle() == INodeGraphicInfo.FIG_RECT || nodeGraph.getFigureStyle() == INodeGraphicInfo.FIG_QUEUE) {
					anchor = new ChopboxAnchor(((INodeFigure) getFigure()).getSymbol());
				}
			}
		}
		return anchor;
	}


	protected List getModelSourceConnections() {
		return ((NodeImplAdapter) getModel()).getSourceArcs();
	}

	protected List getModelTargetConnections() {
		return ((NodeImplAdapter) getModel()).getTargetArcs();
	}

}
