package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.model.AbstractPropertyChange;
import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.core.model.interfaces.ICoreGraph;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.core.session.ISession;
import fr.lip6.move.coloane.core.session.SessionManager;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.XYLayout;
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

/**
 * EditPart dedicated to the global graph model
 * 
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class GraphEditPart extends AbstractGraphicalEditPart implements ISelectionEditPartListener, PropertyChangeListener {
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/**
	 * Allow to listen the modifications applied to the model object
	 */
	private EditPartListener editPartListener = new EditPartListener.Stub();

	private ISession session;

	/**
	 * Build set of editing rules for the model
	 */
	@Override
	protected final void createEditPolicies() {
		installEditPolicy(EditPolicy.NODE_ROLE, null);
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);

		// Do not remove the model object
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());

		// What to do when an object is created or modified
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new ColoaneEditPolicy((XYLayout) getContentPane().getLayoutManager()));

		// Cannot select the model
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
	 * Build the root model figure
	 * This figure is invisible but is used as a container for all other figures
	 * @return IFigure
	 */
	@Override
	protected final IFigure createFigure() {
		Figure root = new FreeformLayer();
		root.setLayoutManager(new FreeformLayout());
		root.setBorder(new MarginBorder(0));
		((ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER)).setAntialias(SWT.ON);
		return root;
	}

	/**
	 * Returns the list of all model children :
	 * <ul>
	 * 	<li>Graph attributes</li>
	 * 	<li>Nodes and their attributes</li>
	 * 	<li>Arcs' attributes</li>
	 * 	<li>Tips</li>
	 * 	<li>Sticky Notes</li>
	 * 	<li>All computed attributes</li>
	 * </ul>
	 * @return List the list described above
	 */
	@Override
	protected final List<Object> getModelChildren() {
		IGraph graph = (IGraph) getModel();
		List<Object> children = new ArrayList<Object>();

		// Graph attributes (computed attributes are already included)
		children.addAll(graph.getDrawableAttributes());

		// Nodes and their attributes (computed attributes are already included)
		children.addAll(graph.getNodes());
		for (INode node : graph.getNodes()) {
			children.addAll(node.getDrawableAttributes());
		}

		// Arcs' attributes ((computed attributes are already included)
		for (IArc arc : graph.getArcs()) {
			children.addAll(arc.getDrawableAttributes());
		}

		// Tips
		children.addAll(SessionManager.getInstance().getCurrentSession().getTips());

		// Sticky Notes
		for (IStickyNote sticky : ((GraphModel) graph).getStickyNotes()) {
			children.add(sticky);
		}

		return children;
	}

	/**
	 * Model Redrawing
	 * Only connections are targeted by this operation.
	 * Each child is able to redrawn itself.
	 */
	@Override
	protected final void refreshVisuals() {
		getFigure().repaint();
	}

	/** {@inheritDoc} */
	public final void propertyChange(PropertyChangeEvent event) {
		LOGGER.finest("Graph EditPart has received a new event: " + event.getPropertyName());  //$NON-NLS-1$
		String prop = event.getPropertyName();

		// Add/Delete a child
		if (IGraph.NODE_ADDED_PROP.equals(prop) || IGraph.NODE_REMOVED_PROP.equals(prop)
				|| IGraph.ARC_ADDED_PROP.equals(prop) || IGraph.ARC_REMOVED_PROP.equals(prop)
				|| ICoreGraph.STICKY_ADD_PROP.equals(prop) || ICoreGraph.STICKY_REMOVED_PROP.equals(prop)
				|| ISession.PROP_TIPS.equals(prop)) {
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
	 * {@inheritDoc}
	 */
	@Override
	public final void activate() {
		if (!isActive()) {
			super.activate();
			((AbstractPropertyChange) getModel()).addPropertyChangeListener(this);
			session = SessionManager.getInstance().getSession((IGraph) getModel());
			session.addPropertyChangeListener(this);
		}
	}

	/**
	 * {@inheritDoc}
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
	 * Fetch the editPart (graph, node or arc) associated to the given attributeEditPart
	 * @param attributeEditPart AttributEditPart
	 * @return the edit part of the parent object (according to the model hierarchy)
	 */
	public final EditPart getParentAttributeEditPart(AttributeEditPart attributeEditPart) {
		return (EditPart) getViewer().getEditPartRegistry().get(((IAttribute) attributeEditPart.getModel()).getReference());
	}

	/**
	 * Recursively print the textual tree that describes a figure
	 * @param s String to put in front of each line
	 * @param fig figure for which the textual description is needed
	 * @return the textual description
	 */
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
