package fr.lip6.move.coloane.projects.its.variables;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.lip6.move.coloane.projects.its.IModelVariable;

public abstract class CompositeModelVariable extends AbstractModelVariable implements IModelVariable {

	public CompositeModelVariable(String name) {
		super(name);
	}

	private List<IModelVariable> children = new ArrayList<IModelVariable>();

	public Iterator<IModelVariable> iterator() {
		return children.iterator();
	}

	
	public void addChild (IModelVariable var) {
		children.add(var);
		var.setParent(this);
	}
}
