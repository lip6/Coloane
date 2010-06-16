package fr.lip6.move.coloane.projects.its.checks;

import fr.lip6.move.coloane.projects.its.TypeDeclaration;
import fr.lip6.move.coloane.projects.its.obs.ISimpleObserver;
import fr.lip6.move.coloane.projects.its.obs.SimpleObservable;
import fr.lip6.move.coloane.projects.its.order.Orders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CheckList extends SimpleObservable implements Iterable<AbstractCheckService>, ISimpleObserver {

	private TypeDeclaration type;
	private List<AbstractCheckService> services;
	
	public CheckList(TypeDeclaration td) {
		type = td;
		services = new ArrayList<AbstractCheckService>();
		addCheck(new OrderingService(this));
		addCheck(new CheckService(this));
		addCheck(new CTLCheckService(this));
	}

	public TypeDeclaration getType() {
		return type;
	}

	public Iterator<AbstractCheckService> iterator() {
		return services.iterator();
	}

	public void addCheck(AbstractCheckService checkService) {
		services.add(checkService);
		checkService.addObserver(this);
		notifyObservers();
	}

	public void update() {
		notifyObservers();
	}


	public Orders getOrders() {
		for (AbstractCheckService asc : this) {
			if (asc instanceof OrderingService) {
				OrderingService os = (OrderingService) asc;
				return os.getOrders();
			}
		}
		return new Orders();
	}

	public CTLFormulaDescription findCtlFormula(String formName) {
		for (AbstractCheckService asc : this) {
			if (asc instanceof CTLCheckService) {
				CTLCheckService os = (CTLCheckService) asc;
				for (CTLFormulaDescription cfd: os.getFormulae()) {
					if (cfd.getName().equals(formName))
						return cfd;
				}
				return null;
			}
		}
		return null;
	}
	
	public void addCTLFormula (String name, String formula, String comments) { 
		for (AbstractCheckService asc : this) {
			if (asc instanceof CTLCheckService) {
				CTLCheckService os = (CTLCheckService) asc;
				os.addFormula(name, formula, comments);
				return;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CTLFormulaDescription> getCTLFormulas () { 
		for (AbstractCheckService asc : this) {
			if (asc instanceof CTLCheckService) {
				CTLCheckService os = (CTLCheckService) asc;
				return os.getFormulae();
			}
		}
		return Collections.EMPTY_LIST;
	}
	
	
}
