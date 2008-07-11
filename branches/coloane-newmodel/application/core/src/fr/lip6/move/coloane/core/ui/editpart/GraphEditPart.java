package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.model.AbstractPropertyChange;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.swt.SWT;

/**
 * EditPart pour le modele global
 */
public class GraphEditPart extends AbstractGraphicalEditPart implements ISelectionEditPartListener, PropertyChangeListener {

	/**
	 * Creation des differentes regles d'edition pour le modele
	 */
	@Override
	protected final void createEditPolicies() {
		installEditPolicy(EditPolicy.NODE_ROLE, null);
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, null);
		installEditPolicy("Snap Feedback", new SnapFeedbackPolicy()); //$NON-NLS-1$


		// Interdiction de suppression de l'objet modele
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());

		// Indique le comportement a adopter lors d'un ajout ou d'un modification d'un objet fils
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new ColoaneEditPolicy((XYLayout) getContentPane().getLayoutManager()));

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
	@Override
	protected final IFigure createFigure() {
		Figure root = new FreeformLayer();
		root.setLayoutManager(new FreeformLayout());
		root.setBorder(new MarginBorder(5));
		((ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER)).setAntialias(SWT.ON);
		return root;
	}

	/**
	 * Retourne la liste des enfants du modèle : les attributs du graphe, les noeuds
	 * et les attributs des noeuds.
	 * @return List La liste des enfants dans la représentation graphique, les attributs
	 * des noeuds ne sont pas des fils des noeuds par exemple.
	 */
	@Override
	protected final List<Object> getModelChildren() {
		IGraph graph = (IGraph) getModel();
		ArrayList<Object> children = new ArrayList<Object>();
		children.addAll(graph.getDrawableAttributes());

		children.addAll(graph.getNodes());
		for (INode node : graph.getNodes()) {
			children.addAll(node.getDrawableAttributes());
		}

		for (IArc arc : graph.getArcs()) {
			children.addAll(arc.getDrawableAttributes());
		}

		return children;
	}

	/**
	 * Re-Tracage du modele.
	 * Ici, seule les connexions sont concernées.
	 * Chaque objet-enfant se redessine lui-même
	 */
	@Override
	protected final void refreshVisuals() {
		getFigure().repaint();
	}


	/**
	 * Changement de proprietes dans le modele.
	 * Ces changements sont typiquement l'ajout ou la suppression d'un noeud
	 */
	public final void propertyChange(PropertyChangeEvent event) {
		String prop = event.getPropertyName();

		// Ajout/Suppression d'un noeud
		if (IGraph.NODE_ADDED_PROP.equals(prop) || IGraph.NODE_REMOVED_PROP.equals(prop)) {
			refreshChildren();

		// Ajout/Suppression d'un attribut
		} else if (IElement.ATTRIBUTE_CHANGE.equals(prop)) {
			refreshChildren();
		}

	}

	/**
	 * Mise en ecoute du modele.
	 * Installation des ecouteurs sur le modele.
	 * A partir de ce moment là, il a un lien entre la vue et le modele
	 */
	@Override
	public final void activate() {
		if (!isActive()) {
			super.activate();
			((AbstractPropertyChange) getModel()).addPropertyChangeListener(this);
		}
	}

	/**
	 * Desactive l'ecoute du modele
	 * Le lien entre le modele et la vue est casse !
	 */
	@Override
	public final void deactivate() {
		if (isActive()) {
			super.deactivate();
			((AbstractPropertyChange) getModel()).removePropertyChangeListener(this);
		}
	}

	/**
	 * @param attributeEditPart
	 * @return L'EditPart "parent" (dans le sens du modèle) de l'AttributeEditPart passé en paramètre.
	 */
	public final EditPart getParentAttributeEditPart(AttributeEditPart attributeEditPart) {
		HashSet<Object> editParts = new HashSet<Object>();
		editParts.add(this);
		editParts.addAll((List< ? >) getChildren());
		for (Object obj : getChildren()) {
			if (obj instanceof NodeEditPart) {
				NodeEditPart nodeEditPart = (NodeEditPart) obj;
				editParts.addAll((List< ? >) nodeEditPart.getSourceConnections());
				editParts.addAll((List< ? >) nodeEditPart.getTargetConnections());
			}
		}
		for (Object obj : editParts) {
			if (obj instanceof GraphEditPart || obj instanceof NodeEditPart || obj instanceof ArcEditPart) {
				EditPart parent = (EditPart) obj;
				IAttribute attributeModel = (IAttribute) attributeEditPart.getModel();
				if (parent.getModel().equals(attributeModel.getReference())) {
					return parent;
				}
			}
		}
		return null;
	}

	public final void childAdded(EditPart child, int index) { }

	public final void partActivated(EditPart editpart) { }

	public final void partDeactivated(EditPart editpart) { }

	public final void removingChild(EditPart child, int index) { }

	public final void selectedStateChanged(EditPart editpart) {
		switch(editpart.getSelected()) {
		case EditPart.SELECTED:
		case EditPart.SELECTED_PRIMARY:
			break;
		case EditPart.SELECTED_NONE:
			break;
		case ISelectionEditPartListener.HIGHLIGHT:
			break;
		case ISelectionEditPartListener.HIGHLIGHT_NONE:
			break;
		case ISelectionEditPartListener.SPECIAL:
			break;
		case ISelectionEditPartListener.SPECIAL_NONE:
			break;
		default:
			break;
		}
	}
}
