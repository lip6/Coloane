package fr.lip6.move.coloane.projects.its.checks;

import fr.lip6.move.coloane.projects.its.ITypeListProvider;
import fr.lip6.move.coloane.projects.its.TypeDeclaration;
import fr.lip6.move.coloane.projects.its.TypeList;
import fr.lip6.move.coloane.projects.its.obs.ISimpleObserver;
import fr.lip6.move.coloane.projects.its.obs.SimpleObservable;
import fr.lip6.move.coloane.projects.its.order.Orders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CheckList extends SimpleObservable implements Iterable<CheckService>, ISimpleObserver, ITypeListProvider {

	private TypeDeclaration type;
	private List<CheckService> services;
	private ITypeListProvider typeP;
	private Orders orders;
	
	public CheckList(TypeDeclaration td, ITypeListProvider typeP) {
		this.typeP = typeP;
		type = td;
		services = new ArrayList<CheckService>();
		orders = new Orders();
		orders.addObserver(this);
	}

	public TypeDeclaration getType() {
		return type;
	}

	public Iterator<CheckService> iterator() {
		return services.iterator();
	}

	public void addCheck(CheckService checkService) {
		services.add(checkService);
		checkService.addObserver(this);
		notifyObservers();
	}

	public void update() {
		notifyObservers();
	}

	public TypeList getTypes() {
		return typeP.getTypes();
	}
	
	public Orders getOrders() {
		return orders;
	}
}
