package its.obs;

import java.util.ArrayList;
import java.util.List;

/**
 * A basic implementation of an observer.
 * @author Yann
 *
 */
public class SimpleObservable implements ISimpleObservable {
	private List<ISimpleObserver> obs = new ArrayList<ISimpleObserver>();

	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see its.ISimpleObservable#addObserver(its.ISimpleObserver)
	 */
	public final void addObserver(ISimpleObserver o) {
		if (!obs.contains(o)) {
				obs.add(o);
		}
	}

	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see its.ISimpleObservable#deleteObserver(its.ISimpleObserver)
	 */
	public final void deleteObserver(ISimpleObserver o) {
		obs.remove(o);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyObservers() {
		for (ISimpleObserver o : obs) {
			o.update();
		}
	}

}
