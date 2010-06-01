package fr.lip6.move.coloane.projects.its.order;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Group implements Ordering {

	private String name;
	
	private List<Ordering> children = new ArrayList<Ordering> ();

	public Group(String name) {
		this.name = name;
	}
	
	public Domain getDomain() {
		return Domain.SDD;
	}

	public String getName() {
		return name;
	}
	
	public void addChild (Ordering o)  {
		children.add(o);
	}

	public Iterator<Ordering> iterator() {
		return children.iterator();
	}

}
