package fr.lip6.move.coloane.projects.its.variables;

import java.util.Collections;
import java.util.Iterator;

import fr.lip6.move.coloane.projects.its.IModelVariable;

public abstract class LeafModelVariable extends AbstractModelVariable implements IModelVariable {

	public LeafModelVariable(String name) {
		super(name);
	}

	@SuppressWarnings("unchecked")
	public Iterator<IModelVariable> iterator() {
		return Collections.EMPTY_LIST.iterator();
	}
	
}
