package its;

import java.util.ArrayList;

public class SimpleObservable {
	private ArrayList<ISimpleObserver> obs = new ArrayList<ISimpleObserver>(); 

	public void addObserver (ISimpleObserver o) {
		obs.add(o);
	}
	
	public void deleteObserver(ISimpleObserver o) {
		obs.remove(o);
	}
	
	protected void notifyObservers() {
		for (ISimpleObserver o : obs) {
			o.update();
		}
	}

}
