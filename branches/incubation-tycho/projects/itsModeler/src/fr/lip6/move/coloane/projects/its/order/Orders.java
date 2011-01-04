package fr.lip6.move.coloane.projects.its.order;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.lip6.move.coloane.projects.its.obs.SimpleObservable;

public class Orders extends SimpleObservable implements Iterable<Ordering> {

	private List<Ordering> orders = new ArrayList<Ordering>();
	
	public Iterator<Ordering> iterator() {
		return orders.iterator();
	}

	public void addOrder(String name, Ordering order) {
		Group parent = new Group(name);
		parent.addChild(order);
		orders.add(parent);
		notifyObservers();
	}

}
