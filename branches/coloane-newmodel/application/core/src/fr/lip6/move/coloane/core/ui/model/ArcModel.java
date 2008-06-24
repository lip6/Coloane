package fr.lip6.move.coloane.core.ui.model;

import fr.lip6.move.coloane.core.motor.formalisms.elements.Arc;
import fr.lip6.move.coloane.core.ui.model.interfaces.IArc;
import fr.lip6.move.coloane.core.ui.model.interfaces.IArcGraphicInfo;
import fr.lip6.move.coloane.core.ui.model.interfaces.INode;

public class ArcModel extends AbstractElement implements IArc {
	private int id;
	
	private Arc arcFormalism;
	private IArcGraphicInfo graphicInfo = new ArcGraphicInfo(this);
	
	private INode source;
	private INode target;

	ArcModel(Arc arcFormalism, int id, INode source, INode target) {
		super(arcFormalism.getAttributes());
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

	public Arc getArcFormalism() {
		return arcFormalism;
	}

	public IArcGraphicInfo getGraphicInfo() {
		return graphicInfo;
	}
}
