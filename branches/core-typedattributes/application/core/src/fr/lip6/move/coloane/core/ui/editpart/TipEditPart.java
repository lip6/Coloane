package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.model.CoreTipModel;
import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.ui.figures.TipFigure;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;
import fr.lip6.move.coloane.interfaces.model.INode;

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
	protected final void createEditPolicies() {
	}

	/**
	 * @param id id of the IElement concerned
	 * @param figure figure pour laquelle on cherche une position
	 * @return the "best" location around the IElement
	 */
	private Point calculLocation(int id, IFigure figure) {
		Point location = new Point(10, 10);
		int tipWidth = figure.getSize().width;
		int tipHeight = figure.getSize().height;
		IGraph graph = SessionManager.getInstance().getCurrentSession().getGraph();

		// Cas d'un noeud, on centre au dessus du noeud
		INode node = graph.getNode(id);
		if (node != null) {
			location = node.getGraphicInfo().getLocation().getCopy();
			int nodeWidth = node.getGraphicInfo().getSize().width;
			location.translate(nodeWidth / 2 - tipWidth / 2, -(30 + tipHeight / 2));
		}

		// Cas d'un arc, on centre au dessus du milieu de l'arc
		// le milieu de l'arc est calculé par le modèle
		IArc arc = graph.getArc(id);
		if (arc != null) {
			location = arc.getGraphicInfo().findMiddlePoint().getCopy();
			location.translate(-tipWidth / 2, -(30 + tipHeight / 2));
		}

		// Ici on utilise la variable bound pour limiter la migration des rectangles dans les grands reseaux
		// AU bout de 2 déplacements, on arrete et on pose la tip
		int bound = 2;
		while (findFigureInto(new Rectangle(location, figure.getSize())) != null) {
			location.translate(20, 0);
			if (--bound == 0) {
				break;
			}
		}
		return location;
	}

	/**
	 * @param rectangle zone de recherche
	 * @return la première figure trouvée ou <code>null</code>
	 */
	private IFigure findFigureInto(Rectangle rectangle) {
		List<IFigure> exclude = new ArrayList<IFigure>(1);
		exclude.add(figure);
		for (int x = 0; x < rectangle.width; x++) {
			for (int y = 0; y < rectangle.height; y++) {
				IFigure found = ((GraphEditPart) getParent()).getFigure().findFigureAt(rectangle.x + x, rectangle.y + y);
				if (found != null) {
					return found;
				}
			}
		}
		return null;
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
