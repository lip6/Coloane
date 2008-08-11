package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.model.AbstractPropertyChange;
import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
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
import org.eclipse.swt.graphics.Image;

/**
 * EditPart pour le modele global
 */
public class GraphEditPart extends AbstractGraphicalEditPart implements ISelectionEditPartListener, PropertyChangeListener {
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/**
	 * Permet d'écouter les changements de sélections, pour l'instant ne fait rien.
	 */
	private EditPartListener editPartListener = new EditPartListener.Stub();

	private ISession session;

	/**
	 * Creation des differentes regles d'edition pour le modele
	 */
	@Override
	protected final void createEditPolicies() {
		installEditPolicy(EditPolicy.NODE_ROLE, null);
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);

		// Interdiction de suppression de l'objet modele
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());

		// Indique le comportement a adopter lors d'un ajout ou d'un modification d'un objet fils
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new ColoaneEditPolicy((XYLayout) getContentPane().getLayoutManager()));

		// Impossible de sélectionner le modele
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, null);
		installEditPolicy("Snap Feedback", new SnapFeedbackPolicy()); //$NON-NLS-1$
	}

	/** {@inheritDoc} */
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
		Figure root = new FreeformLayer() {
			@Override
			protected void paintFigure(Graphics graphics) {
				graphics.drawImage(new Image(Coloane.getParent().getDisplay(), Coloane.class.getResourceAsStream("/resources/icons/coloane_transparent.png")), new Point(10, 10)); //$NON-NLS-1$
			}
		};

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
		List<Object> children = new ArrayList<Object>();

		// Ajout des attributs du graphe
		children.addAll(graph.getDrawableAttributes());

		// Ajout des noeuds et de leurs attributs
		children.addAll(graph.getNodes());
		for (INode node : graph.getNodes()) {
			children.addAll(node.getDrawableAttributes());
		}

		// Ajout des attributs des arcs
		for (IArc arc : graph.getArcs()) {
			children.addAll(arc.getDrawableAttributes());
		}

		// Ajout des tips
		children.addAll(SessionManager.getInstance().getCurrentSession().getTips());

		// Ajout des notes
		for (IStickyNote sticky : ((GraphModel) graph).getStickyNotes()) {
			children.add(sticky);
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

	/** {@inheritDoc} */
	public final void propertyChange(PropertyChangeEvent event) {
		String prop = event.getPropertyName();

		// Ajout/Suppression
		if (IGraph.NODE_ADDED_PROP.equals(prop) || IGraph.NODE_REMOVED_PROP.equals(prop) || IGraph.STICKY_ADD_PROP.equals(prop) || IGraph.STICKY_REMOVED_PROP.equals(prop)) {
			refreshChildren();
		} else if (ISession.PROP_TIPS.equals(prop)) {
			refreshChildren();
		}

		if (LOGGER.isLoggable(Level.FINEST)) {
			IFigure fig = getFigure();
			while (fig.getParent() != null && fig != fig.getParent()) {
				fig = fig.getParent();
			}
			LOGGER.finest(treeToString("*** ", fig)); //$NON-NLS-1$
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
			session = SessionManager.getInstance().getCurrentSession();
			session.addPropertyChangeListener(this);
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
			session.removePropertyChangeListener(this);
		}
	}

	/**
	 * Permet de récupérer l'editPart associé (graphe, noeud ou arc) à l'attributeEditPart
	 * @param attributeEditPart AttributEditPart
	 * @return L'EditPart "parent" (dans le sens du modèle) de l'AttributeEditPart passé en paramètre.
	 */
	public final EditPart getParentAttributeEditPart(AttributeEditPart attributeEditPart) {
		Set<Object> editParts = new HashSet<Object>();
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

	/**
	 * Méthode récursive qui va afficher l'arbre d'une figure
	 * @param s en tête de chaque ligne, une chaine convient très bien
	 * @param fig figure qui doit être afficher
	 * @return String de l'arbre
	 */
	@SuppressWarnings("unused")
	private static String treeToString(String s, IFigure fig) {
		StringBuilder sb = new StringBuilder();

		String name = fig.getClass().getSimpleName();
		if (name.equals("")) { //$NON-NLS-1$
			name = fig.getClass().getName();
		}
		sb.append(s).append(name).append(" [").append(fig.getBounds()).append("]\n"); //$NON-NLS-1$ //$NON-NLS-2$
		for (Object obj : fig.getChildren()) {
			sb.append(treeToString(s + "| ", (IFigure) obj)); //$NON-NLS-1$
		}
		return sb.toString();
	}

	/** {@inheritDoc} */
	public final EditPartListener getSelectionEditPartListener() {
		return editPartListener;
	}
}
