package fr.lip6.move.coloane.ui.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
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
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;

import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.ui.model.AbstractModelElement;
import fr.lip6.move.coloane.ui.model.IArcImpl;
import fr.lip6.move.coloane.ui.model.IAttributeGraphicInfo;
import fr.lip6.move.coloane.ui.model.IAttributeImpl;
import fr.lip6.move.coloane.ui.model.IElement;
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
		figure.setOpaque(true);
		
	
		// Localisation
		IAttributeImpl attribut = (IAttributeImpl)getModel();
		
		// Si le referent est un noeud, on agit sur la position de l'attribut
		Point attributePosition;
		if (attribut.getReference() instanceof INodeImpl) {
			
			// Deux possibilites :
			// Pas d'information de positionnement -> on utilise les indication du noeud
			// Information de positionnement -> on les utilise
			
			// Cas 1
			if ((attribut.getGraphicInfo().getLocation().x == 0) && (attribut.getGraphicInfo().getLocation().y == 0)) {
				Point refLocation = ((INodeImpl)attribut.getReference()).getGraphicInfo().getLocation();
				attributePosition = new Point(refLocation.x+20,refLocation.y-20);
			
			// Cas 2
			} else {
				attributePosition = new Point(attribut.getGraphicInfo().getLocation().x,attribut.getGraphicInfo().getLocation().y);
			}			
		} else if (attribut.getReference() instanceof IArcImpl) {
			if ((attribut.getGraphicInfo().getLocation().x == 0) && (attribut.getGraphicInfo().getLocation().y == 0)) {
				attributePosition = ((IArcImpl)attribut.getReference()).getGraphicInfo().findMiddlePoint();			
			// Cas 2
			} else {
				attributePosition = new Point(attribut.getGraphicInfo().getLocation().x,attribut.getGraphicInfo().getLocation().y);
			}			
			
		} else {
			attributePosition = new Point(0,0);
		}

		// Recupere la figure du modele
		ModelEditPart modelEditPart = (ModelEditPart)getParent();
		
		// On doit maintenant veririfer qu'aucune autre figure ne se trouve a proximite	
		
		// Comme aucun texte n'est ajoute dans la figure pour le moment... verifie que le point x+5 et y+5 est libre aussi
		Point attributePositionZone = new Point(attributePosition.x+5,attributePosition.y+5);
		
		while ((modelEditPart.getFigure().findFigureAt(attributePosition) != null) || (modelEditPart.getFigure().findFigureAt(attributePositionZone) != null)) {
			attributePosition.y = attributePosition.y+5; // Deplacement de 5 vers le bas si une figure est deja disposee 		
			attributePositionZone.y = attributePositionZone.y+5;
		}
		
		// Stocke les information de positionnement
		attribut.getGraphicInfo().setLocation(attributePosition.x, attributePosition.y);
		
		// Positionnement graphique
		figure.setLocation(attributePosition);
		
		return figure;
	}


	/** 
	 * Mise a jour de la vue a partir des informations du modele<br>
	 * La mise a jour utilise des methodes de parcours du modele et de moficiation de la vue
	 */
	protected void refreshVisuals() {
		
		IAttributeImpl attribut = (IAttributeImpl)getModel();
		Label attributeFigure = (Label)getFigure();
		
		// Affichage du texte dans le Label
		Font f;
		switch (attribut.getType()) {
		case IAttributeGraphicInfo.L1:
			f = new Font(null,"arial",11,SWT.BOLD);
			break;
		case IAttributeGraphicInfo.L2:
			f = new Font(null,"arial",10,SWT.ITALIC);
			break;
		case IAttributeGraphicInfo.NOR:
			f = new Font(null,"arial",9,SWT.NORMAL);
			break;

		default:
			f = new Font(null,"arial",9,SWT.NORMAL);
			break;
		}
		
		attributeFigure.setText(attribut.getValue());
		attributeFigure.setFont(f);
		
		// On doit creer l'espace pour l'attribut 
		Rectangle bounds = new Rectangle(attribut.getGraphicInfo().getLocation(),new Dimension(attributeFigure.getTextBounds().width,attributeFigure.getTextBounds().height));
		((GraphicalEditPart) getParent()).setLayoutConstraint(this,getFigure(), bounds);

		// Il faut avertir FrameKit
		Coloane.notifyModelChange(((IElement)attribut).getModelAdapter());
	}
	
	/**
	 * Traitements a effectuer lors de la reception d'un evenement sur l'EditPart
	 * @param property L'evenement qui a ete leve
	 */
	public void propertyChange(PropertyChangeEvent property) {
		String prop = property.getPropertyName();	

		if (IAttributeImpl.SELECT_LIGHT_PROP.equals(prop)) {
			((Label)getFigure()).setBackgroundColor(ColorConstants.lightGray);
		} else if (IAttributeImpl.SELECT_HEAVY_PROP.equals(prop)) {
			((Label)getFigure()).setForegroundColor(ColorConstants.blue);
		} else if (IAttributeImpl.UNSELECT_LIGHT_PROP.equals(prop)) {
			((Label)getFigure()).setBackgroundColor(ColorConstants.white);
		} else if (IAttributeImpl.UNSELECT_HEAVY_PROP.equals(prop)) {
			((Label)getFigure()).setForegroundColor(ColorConstants.black);		
		}
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
