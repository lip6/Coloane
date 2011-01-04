package fr.lip6.move.coloane.projects.its.variables;

import fr.lip6.move.coloane.projects.its.IModelVariable;

public abstract class AbstractModelVariable implements IModelVariable {

	private String name;
	private IModelVariable parent;
	private String id;

	
	public AbstractModelVariable(String name) {
		this.name = name;
	}

	protected void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public IModelVariable getParent() {
		if (parent != null)
			return parent;
		return this;
	}

	public String getQualifiedName() {
		String s="";
		if (getParent() != this) {
			s = getParent().getQualifiedName() + ".";
		}
		return s + name;
	}

	public void setParent(IModelVariable parent) {
		this.parent = parent;
	}

	public String getId() {
		String s="";
		if (getParent() != this) {
			s = getParent().getId() + ".";
		}
		return s + id;
	}

}
