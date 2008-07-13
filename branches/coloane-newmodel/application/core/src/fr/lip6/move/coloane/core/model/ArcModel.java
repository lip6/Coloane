package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IArcGraphicInfo;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Point;

public class ArcModel extends AbstractElement implements IArc {
	private int id;

	private final IArcFormalism arcFormalism;
	private final IArcGraphicInfo graphicInfo;

	private INode source;
	private INode target;
	private ArrayList<AbsoluteBendpoint> inflexPoints = new ArrayList<AbsoluteBendpoint>();

	ArcModel(IElement parent, IArcFormalism arcFormalism, int id, INode source, INode target) {
		super(parent, arcFormalism.getAttributes());
		this.id = id;
		this.arcFormalism = arcFormalism;
		this.source = source;
		this.target = target;
		this.graphicInfo = new ArcGraphicInfo(this);

		// ((NodeModel) source).addOutcomingArc(this);
		// ((NodeModel) target).addIncomingArc(this);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IElement#getId()
	 */
	public final int getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IArc#getSource()
	 */
	public final INode getSource() {
		return source;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IArc#getTarget()
	 */
	public final INode getTarget() {
		return target;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IArc#getArcFormalism()
	 */
	public final IArcFormalism getArcFormalism() {
		return arcFormalism;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IArc#getGraphicInfo()
	 */
	public final IArcGraphicInfo getGraphicInfo() {
		return graphicInfo;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IArc#addInflexPoint(org.eclipse.draw2d.geometry.Point, int)
	 */
	public final void addInflexPoint(Point p, int index) {
		inflexPoints.add(index, new AbsoluteBendpoint(p));
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IArc#addInflexPoint(org.eclipse.draw2d.geometry.Point)
	 */
	public final void addInflexPoint(Point p) {
		inflexPoints.add(new AbsoluteBendpoint(p));
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IArc#removeInflexPoint(int)
	 */
	public final void removeInflexPoint(int index) {
		inflexPoints.remove(index);
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IArc#modifyInflexPoint(int, org.eclipse.draw2d.geometry.Point)
	 */
	public final void modifyInflexPoint(int index, Point p) {
		inflexPoints.get(index).setLocation(p);
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IArc#modifyInflexPoints(int, int)
	 */
	public final void modifyInflexPoints(int dx, int dy) {
		for (AbsoluteBendpoint inflexPoint : inflexPoints) {
			inflexPoint.translate(dx, dy);
		}
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IArc#getInflexPoint(int)
	 */
	public final AbsoluteBendpoint getInflexPoint(int index) {
		return inflexPoints.get(index);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IArc#getInflexPoints()
	 */
	public final List<AbsoluteBendpoint> getInflexPoints() {
		return Collections.unmodifiableList(inflexPoints);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IArc#reconnect(fr.lip6.move.coloane.core.ui.model.interfaces.INode, fr.lip6.move.coloane.core.ui.model.interfaces.INode)
	 */
	public final void reconnect(INode newSource, INode newTarget) {
		((NodeModel) source).removeOutcomingArc(this);
		((NodeModel) target).removeIncomingArc(this);

		this.source = newSource;
		((NodeModel) source).addOutcomingArc(this);
		this.target = newTarget;
		((NodeModel) target).addIncomingArc(this);

		// Il faut avertir FrameKit
		Coloane.notifyModelChange(this);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#updateAttributesPosition()
	 */
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
}
