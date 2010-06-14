package fr.lip6.move.coloane.projects.its.variables;

import fr.lip6.move.coloane.interfaces.model.INode;

public class ScalarInstanceVariable extends CompositeModelVariable {

	private String type;

	public ScalarInstanceVariable(INode inst, String type, int index) {
		super(""+index);
		setId(getName());
		this.type = inst.getAttribute("type").getValue() +  " (" + type + ")";
	}
	
	public String getDescription() {
		return "Scalar instance "+ getInstanceName();
	}

	public String getInstanceName() {
		return "tab[ " + getName()+ " ]:" + type;
	}

}
