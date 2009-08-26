package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IArcGraphicInfo;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

/**
 * Description d'un arc
 */
public class ArcModel extends AbstractElement implements IArc, ILinkableElement {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private final IArcFormalism arcFormalism;
	private final IArcGraphicInfo graphicInfo;

	private INode source;
	private INode target;
	private List<AbsoluteBendpoint> inflexPoints = new ArrayList<AbsoluteBendpoint>();

	/** Liste des liens */
	private List<ILink> links = new ArrayList<ILink>();

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
		if (source == null || target == null) {
			throw new NullPointerException("Argument must be not null"); //$NON-NLS-1$
		}
		this.arcFormalism = arcFormalism;
		this.source = source;
		this.target = target;
		this.graphicInfo = new ArcGraphicInfo(this);
	}

	/**
	 * Supprime les liens attachés à cet arc.
	 */
	final void delete() {
		for (ILink link : links) {
			link.getElement().removeLink(link);
		}
		links.clear();
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
		graphicInfo.updateMiddlePoint();
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	/** {@inheritDoc} */
	public final void addInflexPoint(Point p) {
		LOGGER.finest("addInflexPoint(" + p + ")");  //$NON-NLS-1$//$NON-NLS-2$
		inflexPoints.add(new AbsoluteBendpoint(p));
		graphicInfo.updateMiddlePoint();
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	/** {@inheritDoc} */
	public final void removeInflexPoint(int index) {
		LOGGER.finest("removeInflexPoint(" + index + ")");  //$NON-NLS-1$//$NON-NLS-2$
		inflexPoints.remove(index);
		graphicInfo.updateMiddlePoint();
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	/** {@inheritDoc} */
	public final void removeAllInflexPoints() {
		LOGGER.finest("removeAllInflexPoints()");  //$NON-NLS-1$
		inflexPoints.clear();
		graphicInfo.updateMiddlePoint();
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	/** {@inheritDoc} */
	public final void modifyInflexPoint(int index, Point p) {
		LOGGER.finest("modifyInflexPoint(" + p + ", " + index + ")");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		inflexPoints.get(index).setLocation(p);
		graphicInfo.updateMiddlePoint();
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	/** {@inheritDoc} */
	public final void modifyInflexPoints(int dx, int dy) {
		LOGGER.finest("modifyInflexPoint(" + dx + ", " + dy + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		for (AbsoluteBendpoint inflexPoint : inflexPoints) {
			inflexPoint.translate(dx, dy);
		}
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
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
		((NodeModel) source).addOutgoingArc(this);
		this.target = newTarget;
		((NodeModel) target).addIncomingArc(this);
	}

	/** {@inheritDoc} */
	public final void updateAttributesPosition() {
		// Position actuelle
		Point oldMiddlePoint = this.graphicInfo.getMiddlePoint();

		// Calcul du nouveau point milieu
		Point newMiddlePoint = this.graphicInfo.findMiddlePoint();

		// Calcul du decalage
		Dimension delta = newMiddlePoint.getDifference(oldMiddlePoint);

		// Mise a jour des coordonnees des attributs
		for (IAttribute attr : this.getDrawableAttributes()) {
			Point newAttrLocation = attr.getGraphicInfo().getLocation().getTranslated(delta);
			attr.getGraphicInfo().setLocation(newAttrLocation);
		}

		this.graphicInfo.updateMiddlePoint();
	}

	/** {@inheritDoc} */
	public final void updateTips() {
		firePropertyChange(INode.INCOMING_ARCS_PROP, null, null);
	}

	/** {@inheritDoc} */
	public final void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();

		if (IAttribute.VALUE_PROP.equals(prop)) {
			// On propage les changements de valeur des attributs au niveau supérieur
			firePropertyChange(prop, evt.getOldValue(), evt.getNewValue());
		}
	}

	/** {@inheritDoc} */
	public final void addLink(ILink link) {
		links.add(link);
		firePropertyChange(INode.OUTGOING_ARCS_PROP, null, link);
	}

	/** {@inheritDoc} */
	public final List<ILink> getLinks() {
		return Collections.unmodifiableList(links);
	}

	/** {@inheritDoc} */
	public final boolean removeLink(ILink link) {
		boolean res = links.remove(link);
		firePropertyChange(INode.OUTGOING_ARCS_PROP, null, link);
		return res;
	}

	/** {@inheritDoc} */
	@Override
	public final String toString() {
		return "Arc(" + getId() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
	}
}
