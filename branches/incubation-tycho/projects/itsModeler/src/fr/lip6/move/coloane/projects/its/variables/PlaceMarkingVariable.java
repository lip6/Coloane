package fr.lip6.move.coloane.projects.its.variables;

import fr.lip6.move.coloane.interfaces.model.INode;

public class PlaceMarkingVariable extends LeafModelVariable {


	
	public PlaceMarkingVariable(INode node) {
		super(node.getAttribute("name").getValue());
		setId("P_"+ node.getId() + getName()); 		
	}

	public String getDescription() {
		return "An integer representing the marking of a Time Petri net place";
	}

}
