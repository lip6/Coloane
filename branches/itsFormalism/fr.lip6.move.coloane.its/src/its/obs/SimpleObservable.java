package its.obs;

import java.util.ArrayList;

public class SimpleObservable implements ISimpleObservable {
	private ArrayList<ISimpleObserver> obs = new ArrayList<ISimpleObserver>(); 

	/* (non-Javadoc)
	 * @see its.ISimpleObservable#addObserver(its.ISimpleObserver)
	 */
	public void addObserver (ISimpleObserver o) {
		obs.add(o);
	}
	
	/* (non-Javadoc)
	 * @see its.ISimpleObservable#deleteObserver(its.ISimpleObserver)
	 */
	public void deleteObserver(ISimpleObserver o) {
		obs.remove(o);
	}
	
	public void notifyObservers() {
		for (ISimpleObserver o : obs) {
			o.update();
		}
	}

}
