package fr.lip6.move.coloane.projects.its.variables;

import fr.lip6.move.coloane.interfaces.model.INode;

public class TransitionClockVariable extends LeafModelVariable {

	private String id;
	private String clock;

	public TransitionClockVariable(INode transition) {
		super(transition.getAttribute("label").getValue());
		id = "__clock_T_"+ transition.getId() + getName(); 
		clock = "[ " + transition.getAttribute("earliestFiringTime").getValue() 
			+ ", " 
			+ transition.getAttribute("latestFiringTime").getValue() + " ]"; 
	}
	
	public String getId() {
		return id;
	}

	public String getDescription() {
		return "An integer representing a transition clock value "+ clock +".";
	}
}
