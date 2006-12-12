package fr.lip6.move.coloane.ui.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;


import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.FanRouter;

import fr.lip6.move.coloane.motor.models.*;


/**
 * EditPart pour le modele
 */
public class ModelEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener {

	
	/**
	 * Construction de la figure root du modele.
	 * Cette figure est invisible mais sert de conteneur a tous les autres objets.
	 * @return IFigure
	 */
	protected IFigure createFigure() {
		System.out.println("Creation de la figure rootModel");
		Figure root = new FreeformLayer();
		root.setLayoutManager(new FreeformLayout()); 
		return root;
	}
	
	/**
	 * 
	 */
	protected void refreshVisuals () {
		ConnectionLayer connLayer = (ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER);
		connLayer.setConnectionRouter(new FanRouter());
	}
	
	/**
	 * Parcours des enfants du modele
	 * @return List
	 */
	protected List getModelChildren() {
		System.out.println("Recherche des enfants du noeud root");
		return ((ModelImplAdapter) getModel()).getChildren();
	}

	/**
	 * Creation des differentes regles d'edition pour le modele
	 */
	protected void createEditPolicies() {
		
		// Interdiction de suppression de l'objet modele
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());

		// Indique le comportement a adopter lors d'un ajour ou d'un modification d'un objet fils
		installEditPolicy(EditPolicy.LAYOUT_ROLE,new ColoaneEditPolicy());
		
		//installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, null);
	}

	/**
	 * Changement de proprietes dans le modele.
	 * Ces changements sont typiquement l'ajout ou la suppression d'un noeud
	 */
	public void propertyChange(PropertyChangeEvent event) {
		String prop = event.getPropertyName();
		if (ModelImplAdapter.NODE_ADDED_PROP.equals(prop) || ModelImplAdapter.NODE_REMOVED_PROP.equals(prop)) {
			refreshChildren();
		}
	}

	/**
	 * Mise sur ecoute d'element du modele.
	 * Si le noeud n'etait pas entrain d'ecouter... on ajoute un ecouteur
	 * TODO: A preciser...
	 */
	public void activate() {
		if (!isActive()) {
			super.activate();
			((AbstractModelElement) getModel()).addPropertyChangeListener(this);
		}
	}

	/**
	 * Desactive l'ecoute d'un noeud
	 * TODO: A preciser
	 */
	public void deactivate() {
		if (isActive()) {
			super.deactivate();
			((AbstractModelElement) getModel()).removePropertyChangeListener(this);
		}
	}
}
