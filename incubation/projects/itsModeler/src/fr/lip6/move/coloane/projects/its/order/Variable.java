package fr.lip6.move.coloane.projects.its.order;

import java.util.Collections;
import java.util.Iterator;

public class Variable implements Ordering {

	private String name;

	public Variable(String name) {
		this.name = name;
	}
	
	public Domain getDomain() {
		return Domain.Integer;
	}

	public String getName() {
		return name;
	}

	@SuppressWarnings("unchecked")
	public Iterator<Ordering> iterator() {
		return Collections.EMPTY_LIST.iterator();
	}

}
