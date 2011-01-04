package fr.lip6.move.coloane.projects.its.variables;

import fr.lip6.move.coloane.interfaces.model.INode;

public class InstanceVariable extends CompositeModelVariable {

	private String type;

	public InstanceVariable(INode inst, String type) {
		super(inst.getAttribute("name").getValue());
		setId("i_" + inst.getId() +"_"+ getName());
		this.type = inst.getAttribute("type").getValue() +  " (" + type + ")";
	}
	
	public String getDescription() {
		return "Instance "+ getInstanceName();
	}

	public String getInstanceName() {
		return getName()+ ":" + type;
	}

}
