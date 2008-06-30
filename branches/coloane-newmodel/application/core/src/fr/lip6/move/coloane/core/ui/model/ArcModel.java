package fr.lip6.move.coloane.core.ui.model;

import fr.lip6.move.coloane.core.motor.formalisms.elements.Arc;
import fr.lip6.move.coloane.core.ui.model.interfaces.IArc;
import fr.lip6.move.coloane.core.ui.model.interfaces.IArcGraphicInfo;
import fr.lip6.move.coloane.core.ui.model.interfaces.IElement;
import fr.lip6.move.coloane.core.ui.model.interfaces.INode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Point;

public class ArcModel extends AbstractElement implements IArc {
	private int id;

	private Arc arcFormalism;
	private IArcGraphicInfo graphicInfo = new ArcGraphicInfo(this);

	private INode source;
	private INode target;
	private ArrayList<AbsoluteBendpoint> inflexPoints = new ArrayList<AbsoluteBendpoint>();

	ArcModel(IElement parent, Arc arcFormalism, int id, INode source, INode target) {
		super(parent, arcFormalism.getAttributes());
		this.id = id;
		this.arcFormalism = arcFormalism;
		this.source = source;
		((NodeModel) source).addSourceArc(this);
		this.target = target;
		((NodeModel) target).addTargetArc(this);
	}

	public final int getId() {
		return id;
	}

	public final INode getSource() {
		return source;
	}

	public final INode getTarget() {
		return target;
	}

	public final Arc getArcFormalism() {
		return arcFormalism;
	}

	public final IArcGraphicInfo getGraphicInfo() {
		return graphicInfo;
	}

	public final void addInflexPoint(Point p, int index) {
		inflexPoints.add(index, new AbsoluteBendpoint(p));
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	public final void addInflexPoint(Point p) {
		inflexPoints.add(new AbsoluteBendpoint(p));
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	public final void removeInflexPoint(int index) {
		inflexPoints.remove(index);
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	public final void modifyInflexPoint(int index, Point p) {
		inflexPoints.get(index).setLocation(p);
		firePropertyChange(IArc.INFLEXPOINT_PROP, null, this);
	}

	public final void modifyInflexPoints(int dx, int dy) {
		for (AbsoluteBendpoint inflexPoint : inflexPoints) {
			inflexPoint.translate(dx, dy);
		}
	}

	public final AbsoluteBendpoint getInflexPoint(int index) {
		return inflexPoints.get(index);
	}

	public final List<AbsoluteBendpoint> getInflexPoints() {
		return Collections.unmodifiableList(inflexPoints);
	}
}
