package fr.lip6.move.coloane.ui.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import fr.lip6.move.coloane.ui.model.AbstractModelElement;
import fr.lip6.move.coloane.ui.model.IAttributeImpl;
import fr.lip6.move.coloane.ui.model.INodeImpl;

/**
 * Cet EditPart est responsable de la gestion des attributs.
 */
public class AttributeEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener, NodeEditPart {

	/**
	 * Creation de la figure associee
	 * Pour les attribut, on considere que la vue doit affiche un Label
	 * @return IFigure
	 */
	protected IFigure createFigure() {
		Label figure = new Label();
		figure.setBorder(new LineBorder(1));
		
		// Localisation
		IAttributeImpl attribut = (IAttributeImpl)getModel();
		
		// Si le referent est un noeud, on agit sur la position de l'attribut
		Point loc;
		if (attribut.getReference() instanceof INodeImpl) {
			Point refLocation = ((INodeImpl)attribut.getReference()).getGraphicInfo().getLocation();
			loc = new Point(refLocation.x+20,refLocation.y-20);
		} else {
			loc = new Point(0,0);
		}

		// Recupere la figure du modele
		ModelEditPart modelEditPart = (ModelEditPart)getParent();
		
		// On doit maintenant veririfer qu'aucune autre figure ne se trouve a proximite		
		while (modelEditPart.getFigure().findFigureAt(loc) != null) {
			loc.y = loc.y+20;			
		}
		
		// Stocke les information de positionnement
		attribut.getGraphicInfo().setLocation(loc.x, loc.y);
		
		// Positionnement graphique
		figure.setLocation(loc);
		return figure;
	}


	/** 
	 * Mise a jour de la vue a partir des informations du modele<br>
	 * La mise a jour utilise des methodes de parcours du modele et de moficiation de la vue
	 */
	protected void refreshVisuals() {
		System.out.println("Rafraichissement de l'attribut");
		
		IAttributeImpl attribut = (IAttributeImpl)getModel();
		Label attributeFigure = (Label)getFigure();
		
		// Affichage du texte dans le Label
		attributeFigure.setText(attribut.getDisplayName()+":"+attribut.getValue());
			
		// On doit creer l'espace pour l'attribut 
		Rectangle bounds = new Rectangle(attribut.getGraphicInfo().getLocation(),new Dimension(attributeFigure.getTextBounds().width+5,attributeFigure.getTextBounds().height+5));
		((GraphicalEditPart) getParent()).setLayoutConstraint(this,getFigure(), bounds);

		// Il faut avertir FrameKit
		// Coloane.notifyModelChange(attrModel.getModelAdapter());
	}
	
	/**
	 * Traitements a effectuer lors de la reception d'un evenement sur l'EditPart
	 * @param property L'evenement qui a ete leve
	 */
	public void propertyChange(PropertyChangeEvent property) {
		String prop = property.getPropertyName();	
		System.out.println("[Attribut] Reception de l'evenement : "+prop);
		refreshVisuals();
	}


	/**
	 * Regles de gestion de l'objet
	 */
	protected void createEditPolicies() {
		
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new GraphicalNodeEditPolicy(){

			@Override
			protected Command getConnectionCompleteCommand(CreateConnectionRequest arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected Command getConnectionCreateCommand(CreateConnectionRequest arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected Command getReconnectSourceCommand(ReconnectRequest arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected Command getReconnectTargetCommand(ReconnectRequest arg0) {
				// TODO Auto-generated method stub
				return null;
			}});

		
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


	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	public ConnectionAnchor getSourceConnectionAnchor(Request arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	public ConnectionAnchor getTargetConnectionAnchor(Request arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
