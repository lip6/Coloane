package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.ui.model.AbstractModelElement;
import fr.lip6.move.coloane.core.ui.model.IElement;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;
import org.eclipse.gef.rulers.RulerProvider;

/**
 * EditPart pour le modele global
 */
public class ModelEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener {

	/**
	 * Creation des differentes regles d'edition pour le modele
	 */
	protected final void createEditPolicies() {
		installEditPolicy(EditPolicy.NODE_ROLE, null);
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, null);

		// Interdiction de suppression de l'objet modele
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());

		// Indique le comportement a adopter lors d'un ajout ou d'un modification d'un objet fils
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new ColoaneEditPolicy());

		// Impossible de selectionenr le modele
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, null);
		installEditPolicy("Snap Feedback", new SnapFeedbackPolicy()); //$NON-NLS-1$
	}

	@Override
	@SuppressWarnings("unchecked")
	public final Object getAdapter(Class adapter) {
		if (adapter == SnapToHelper.class) {
			List<SnapToHelper> snapStrategies = new ArrayList<SnapToHelper>();
			Boolean val = (Boolean) getViewer().getProperty(RulerProvider.PROPERTY_RULER_VISIBILITY);
			if (val != null && val.booleanValue()) {
				snapStrategies.add(new SnapToGuides(this));
			}
			val = (Boolean) getViewer().getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED);
			if (val != null && val.booleanValue()) {
				snapStrategies.add(new SnapToGeometry(this));
			}
			val = (Boolean) getViewer().getProperty(SnapToGrid.PROPERTY_GRID_ENABLED);
			if (val != null && val.booleanValue()) {
				snapStrategies.add(new SnapToGrid(this));
			}
			if (snapStrategies.size() == 0) {
				return null;
			}
			if (snapStrategies.size() == 1) {
				return snapStrategies.get(0);
			}
			SnapToHelper ss[] = new SnapToHelper[snapStrategies.size()];
			for (int i = 0; i < snapStrategies.size(); i++) {
				ss[i] = (SnapToHelper) snapStrategies.get(i);
			}
			return new CompoundSnapToHelper(ss);
		}
		return super.getAdapter(adapter);
	}

	/**
	 * Construction de la figure root du modele.
	 * Cette figure est invisible mais sert de conteneur a tous les autres objets.
	 * @return IFigure
	 */
	protected final IFigure createFigure() {
		Figure root = new FreeformLayer();
		root.setLayoutManager(new FreeformLayout());
		root.setBorder(new MarginBorder(5));
		return root;
	}

	/**
	 * Retourne la liste des enfants du modele
	 * @return List La liste des enfants dans la repr�sentation arborescente du modele
	 */
	protected final List<IElement> getModelChildren() {
		return ((IModelImpl) getModel()).getChildren();
	}

	/**
	 * Re-Tracage du modele.
	 * Ici, seule les connexions sont concern�es.
	 * Chaque objet-enfant se redessine lui-m�me
	 */
	protected final void refreshVisuals() {
		super.refreshVisuals();
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
	 * A partir de ce moment l�, il a un lien entre la vue et le modele
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
