package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.model.CoreTipModel;
import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.session.SessionManager;
import fr.lip6.move.coloane.core.ui.figures.TipFigure;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.result.Tip;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

/**
 * EditPart that manages Tips.
 * 
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 * 
 * @see Tip
 * @see ITip
 */
public class TipEditPart extends AbstractGraphicalEditPart implements NodeEditPart, PropertyChangeListener {
	/** Logger */
	private final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Maximum number of attempts for positioning a tip near its elements without overlapping with another figure */
	private static final int LOCATION_MAX_ATTEMPTS = 3;

	private ConnectionAnchor connectionAnchor;

	/** {@inheritDoc} */
	@Override
	protected final IFigure createFigure() {
		ICoreTip tip = (ICoreTip) getModel();
		IFigure figure = new TipFigure(tip);
		Point location = calculLocation(tip.getIdObject(), figure);
		figure.setLocation(location);
		if (tip instanceof ILocatedElement) {
			((ILocatedElement) tip).getLocationInfo().setLocation(location);
		}
		return figure;
	}

	/** {@inheritDoc} */
	@Override
	protected final void createEditPolicies() { }

	/**
	 * Compute the best location for a tip
	 * @param id The ID of the IElement concerned by the tip
	 * @param figure The figure that must be added to the model
	 * @return the "best" location around the IElement
	 */
	private Point calculLocation(int id, IFigure figure) {
		Point location = new Point(10, 10);
		int tipWidth = figure.getSize().width;
		int tipHeight = figure.getSize().height;
		IGraph graph = SessionManager.getInstance().getCurrentSession().getGraph();

		// If the element is a node, the tip is moved just above the node (center)
		INode node = graph.getNode(id);
		if (node != null) {
			location = node.getGraphicInfo().getLocation().getCopy();
			int nodeWidth = node.getGraphicInfo().getSize().width;
			location.translate(nodeWidth / 2 - tipWidth / 2, -(30 + tipHeight / 2));
		}

		// If the element is an arc, the tip is moved above the middle of the arc (center)
		// The middle is computed by the arc model graphical class
		IArc arc = graph.getArc(id);
		if (arc != null) {
			location = arc.getGraphicInfo().findMiddlePoint().getCopy();
			location.translate(-tipWidth / 2, -(30 + tipHeight / 2));
		}

		// Try to avoid tip overlapping.
		// The static variable is used to limit the number of attempts. 
		int attemps = 0;
		while (findUnderlyingFigure(new Rectangle(location, figure.getSize()))) {
			location.translate(20, 0);
			if (++attemps > LOCATION_MAX_ATTEMPTS) {
				LOGGER.fine("Correct tip location can not be computed"); //$NON-NLS-1$
				break;				
			}
		}
		return location;
	}

	/**
	 * Try to find an empty zone to put the tip figure into
	 * @param rectangle The size of the tip (tip area)
	 * @return <code>true</code> if an existing figure already exists somewhere under the tip area
	 */
	private boolean findUnderlyingFigure(Rectangle rectangle) {
		for (int x = 0; x < rectangle.width; x += 5) {
			for (int y = 0; y < rectangle.height; y += 5) {
				IFigure found = ((GraphEditPart) getParent()).getFigure().findFigureAt(rectangle.x + x, rectangle.y + y);
				if (found != null) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @return Connction anchors fo the tip
	 */
	private ConnectionAnchor getConnectionAnchor() {
		if (connectionAnchor == null) {
			connectionAnchor = new ChopboxAnchor(getFigure());
		}
		return connectionAnchor;
	}

	/** {@inheritDoc} */
	@Override
	protected final List<Object> getModelSourceConnections() {
		List<Object> sources = new ArrayList<Object>();
		sources.add(((ICoreTip) getModel()).getArcModel());
		return sources;
	}

	/** {@inheritDoc} */
	public final ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		return getConnectionAnchor();
	}

	/** {@inheritDoc} */
	public final ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return getConnectionAnchor();
	}

	/** {@inheritDoc} */
	public final ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		return getConnectionAnchor();
	}

	/** {@inheritDoc} */
	public final ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return getConnectionAnchor();
	}

	/** {@inheritDoc} */
	public final void propertyChange(PropertyChangeEvent evt) {
		if (ILocationInfo.LOCATION_PROP.equals(evt.getPropertyName())) {
			refreshVisuals();
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void activate() {
		super.activate();
		if (getModel() instanceof ICoreTip) {
			((CoreTipModel) getModel()).addPropertyChangeListener(this);
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void deactivate() {
		super.deactivate();
		if (getModel() instanceof ICoreTip) {
			((CoreTipModel) getModel()).removePropertyChangeListener(this);
		}
	}

	/** {@inheritDoc} */
	@Override
	protected final void refreshVisuals() {
		if (getModel() instanceof ILocatedElement) {
			ILocatedElement element = (ILocatedElement) getModel();
			Rectangle bounds = new Rectangle(element.getLocationInfo().getLocation(), getFigure().getSize());
			((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), bounds);
		}
		super.refreshVisuals();
	}
}
