package fr.lip6.move.coloane.projects.its.checks;

import fr.lip6.move.coloane.projects.its.obs.ISimpleObserver;
import fr.lip6.move.coloane.projects.its.obs.SimpleObservable;
import fr.lip6.move.coloane.projects.its.order.Ordering;
import fr.lip6.move.coloane.projects.its.order.Orders;

public class OrderParameter extends SimpleObservable implements ISimpleObserver  {
	private Ordering selection;
	private Orders orders;
	
	public OrderParameter(Orders orders) {
		this.orders = orders;
		orders.addObserver(this);
	}

	public void update() {
		notifyObservers();
	}

	public Ordering getSelection() {
		return selection;
	}
	
	public void setSelection(Ordering selection) {
		this.selection = selection;
	}
	
	public Orders getOrders() {
		return orders;
	}
}
