package fr.lip6.move.coloane.ui.editpart;

import fr.lip6.move.coloane.ui.model.AbstractModelElement;
import fr.lip6.move.coloane.ui.model.IElement;
import fr.lip6.move.coloane.ui.model.IModelImpl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;

/**
 * EditPart pour le modele global
 */
public class ModelEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener {

	/**
	 * Construction de la figure root du modele.
	 * Cette figure est invisible mais sert de conteneur a tous les autres objets.
	 * @return IFigure
	 */
	protected final IFigure createFigure() {
		Figure root = new FreeformLayer();
		root.setLayoutManager(new FreeformLayout());
		return root;
	}

	/**
	 * Retourne la liste des enfants du modele
	 * @return List La liste des enfants dans la représentation arborescente du modele
	 */
	protected final List<IElement> getModelChildren() {
		return ((IModelImpl) getModel()).getChildren();
	}

	/**
	 * Re-Tracage du modele.
	 * Ici, seule les connexions sont concernées.
	 * Chaque objet-enfant se redessine lui-même
	 */
	protected final void refreshVisuals() {
		super.refreshVisuals();
		//ConnectionLayer connLayer = (ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER);
		//connLayer.setConnectionRouter(new BendpointConnectionRouter());
	}



	/**
	 * Creation des differentes regles d'edition pour le modele
	 */
	protected final void createEditPolicies() {
		// Interdiction de suppression de l'objet modele
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());

		// Indique le comportement a adopter lors d'un ajout ou d'un modification d'un objet fils
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new ColoaneEditPolicy());

		// Impossible de selectionenr le modele
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, null);
	}


	/**
	 * Changement de proprietes dans le modele.
	 * Ces changements sont typiquement l'ajout ou la suppression d'un noeud
	 */
	public final void propertyChange(PropertyChangeEvent event) {
		String prop = event.getPropertyName();

		// Ajout/Suppression d'un noeud
		if (IModelImpl.NODE_ADDED_PROP.equals(prop) || IModelImpl.NODE_REMOVED_PROP.equals(prop)) {
			refreshChildren();
		}

		// Ajout d'un attribut
		if (IModelImpl.ATTRIBUTE_ADDED_PROP.equals(prop)) {
			refreshChildren();
		}
	}

	/**
	 * Mise en ecoute du modele.
	 * Installation des ecouteurs sur le modele.
	 * A partir de ce moment là, il a un lien entre la vue et le modele
	 */
	public final void activate() {
		if (!isActive()) {
			super.activate();
			((AbstractModelElement) getModel()).addPropertyChangeListener(this);
		}
	}

	/**
	 * Desactive l'ecoute du modele
	 * Le lien entre le modele et la vue est casse !
	 */
	public final void deactivate() {
		if (isActive()) {
			super.deactivate();
			((AbstractModelElement) getModel()).removePropertyChangeListener(this);
		}
	}
}
