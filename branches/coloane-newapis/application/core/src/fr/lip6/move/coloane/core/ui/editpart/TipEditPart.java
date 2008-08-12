package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.model.CoreTipModel;
import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.ui.figures.TipFigure;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

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
 * EditPart pour les tips.
 * @see ITip
 */
public class TipEditPart extends AbstractGraphicalEditPart implements NodeEditPart, PropertyChangeListener {

	private ConnectionAnchor connectionAnchor;

	/** {@inheritDoc} */
	@Override
	protected final IFigure createFigure() {
		ITip tip = (ITip) getModel();
		IFigure figure = new TipFigure(tip);
		Point location = calculLocation(tip.getIdObject());
		figure.setLocation(location);
		if (tip instanceof ILocatedElement) {
			((ILocatedElement) tip).getLocationInfo().setLocation(location);
		}
		return figure;
	}

	/** {@inheritDoc} */
	@Override
	protected final void createEditPolicies() {
	}

	/**
	 * @param id id of the IElement concerned
	 * @return empty location around the IElement
	 */
	private Point calculLocation(int id) {
		Point location;
		int dx = 10;
		int dy = 10;
		IGraph graph = SessionManager.getInstance().getCurrentSession().getGraph();
		INode node = graph.getNode(id);
		if (node != null) {
			location = node.getGraphicInfo().getLocation();
			location.translate(
					node.getNodeFormalism().getGraphicalDescription().getWidth() / 2,
					node.getNodeFormalism().getGraphicalDescription().getHeight() / 2);
			dx += node.getNodeFormalism().getGraphicalDescription().getWidth() / 2;
			dy += node.getNodeFormalism().getGraphicalDescription().getHeight() / 2;
			return location.getTranslated(15, 15);
		}
		IArc arc = graph.getArc(id);
		if (arc != null) {
			location = arc.getGraphicInfo().findMiddlePoint();
			return location.getTranslated(15, 15);
		}
		return new Point(10, 10);
	}

	/**
	 * @return l'unique ConnectionAnchor pour cette editPart
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
		if (getModel() instanceof CoreTipModel) {
			sources.add(((CoreTipModel) getModel()).getArcModel());
		}
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
		if (getModel() instanceof CoreTipModel) {
			((CoreTipModel) getModel()).addPropertyChangeListener(this);
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void deactivate() {
		super.deactivate();
		if (getModel() instanceof CoreTipModel) {
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
