package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IArcGraphicInfo;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Point;

/**
 * Description d'un arc
 */
public class ArcModel extends AbstractElement implements IArc {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private final IArcFormalism arcFormalism;
	private final IArcGraphicInfo graphicInfo;

	private INode source;
	private INode target;
	private List<AbsoluteBendpoint> inflexPoints = new ArrayList<AbsoluteBendpoint>();

	/**
	 * Constructeur
	 * @param parent L'élément qui contient l'arc
	 * @param arcFormalism Le formalisme attaché à l'arc
	 * @param id L'identifiant de l'arc
	 * @param source La source de l'arc
	 * @param target La cible de l'arc
	 */
	ArcModel(IElement parent, IArcFormalism arcFormalism, int id, INode source, INode target) {
		super(id, parent, arcFormalism.getAttributes());
		LOGGER.finest("Création d'un ArcModel(" + arcFormalism.getName() + ", " + source.getId() + " -> " + target.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		this.arcFormalism = arcFormalism;
		this.source = source;
		this.target = target;
		this.graphicInfo = new ArcGraphicInfo(this);
	}

	/** {@inheritDoc} */
	public final INode getSource() {
		return source;
	}

	/** {@inheritDoc} */
	public final INode getTarget() {
		return target;
	}

	/** {@inheritDoc} */
	public final IArcFormalism getArcFormalism() {
		return arcFormalism;
	}

	/** {@inheritDoc} */
	public final IArcGraphicInfo getGraphicInfo() {
		return graphicInfo;
	}

	/** {@inheritDoc} */
	public final void addInflexPoint(Point p, int index) {
		LOGGER.finest("addInflexPoint(" + p + ", " + index + ")");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		inflexPoints.add(index, new AbsoluteBendpoint(p));
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	/** {@inheritDoc} */
	public final void addInflexPoint(Point p) {
		LOGGER.finest("addInflexPoint(" + p + ")");  //$NON-NLS-1$//$NON-NLS-2$
		inflexPoints.add(new AbsoluteBendpoint(p));
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	/** {@inheritDoc} */
	public final void removeInflexPoint(int index) {
		LOGGER.finest("removeInflexPoint(" + index + ")");  //$NON-NLS-1$//$NON-NLS-2$
		inflexPoints.remove(index);
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	/** {@inheritDoc} */
	public final void removeAllInflexPoints() {
		LOGGER.finest("removeAllInflexPoints()");  //$NON-NLS-1$
		inflexPoints.clear();
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	/** {@inheritDoc} */
	public final void modifyInflexPoint(int index, Point p) {
		LOGGER.finest("modifyInflexPoint(" + p + ", " + index + ")");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		inflexPoints.get(index).setLocation(p);
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	/** {@inheritDoc} */
	public final void modifyInflexPoints(int dx, int dy) {
		LOGGER.finest("modifyInflexPoint(" + dx + ", " + dy + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		for (AbsoluteBendpoint inflexPoint : inflexPoints) {
			inflexPoint.translate(dx, dy);
		}
	}

	/** {@inheritDoc} */
	public final AbsoluteBendpoint getInflexPoint(int index) {
		return inflexPoints.get(index);
	}

	/** {@inheritDoc} */
	public final List<AbsoluteBendpoint> getInflexPoints() {
		return Collections.unmodifiableList(inflexPoints);
	}

	/** {@inheritDoc} */
	public final void reconnect(INode newSource, INode newTarget) {
		LOGGER.finest("reconnect(" + newSource.getId() + " -> " + newTarget.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		((NodeModel) source).removeOutcomingArc(this);
		((NodeModel) target).removeIncomingArc(this);

		this.source = newSource;
		((NodeModel) source).addOutcomingArc(this);
		this.target = newTarget;
		((NodeModel) target).addIncomingArc(this);
	}

	/** {@inheritDoc} */
	public final void updateAttributesPosition() {
		// Calcul du nouveau point milieu
		Point newMiddlePoint = this.graphicInfo.findMiddlePoint();

		// Position actuelle
		Point oldMiddlePoint = this.graphicInfo.getMiddlePoint();

		// Calcul du decalage
		int deltaX = newMiddlePoint.x - oldMiddlePoint.x;
		int deltaY = newMiddlePoint.y - oldMiddlePoint.y;

		// Mise a jour des coordonnees des attributs
		for (IAttribute attr : this.getDrawableAttributes()) {
			Point attrLocation = attr.getGraphicInfo().getLocation();
			attr.getGraphicInfo().setLocation(new Point(attrLocation.x + deltaX, attrLocation.y + deltaY));
		}

		this.graphicInfo.updateMiddlePoint();
	}

	public final void updateTips() {
		firePropertyChange(INode.INCOMING_ARCS_PROP, null, null);
	}
}
