package fr.lip6.move.coloane.core.ui.model;

import fr.lip6.move.coloane.core.motor.formalisms.elements.Arc;

public class ArcModel extends AbstractElement implements IArc {
	private int id;
	private INode source;
	private INode target;

	ArcModel(Arc arcFormalism, int id, INode source, INode target) {
		super(arcFormalism.getAttributes());
		this.id = id;
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
}
