package fr.lip6.move.coloane.projects.its.obs;

/**
 * An observable object, or Subject per the Observer DP.
 * @author Yann
 *
 */
public interface ISimpleObservable {

	/**
	 * Add an observer to this subject.
	 * Double insertion is ignored: the subject uses a SET of observers.
	 * @param o the observer to add
	 */
	void addObserver(ISimpleObserver o);

	/**
	 * Remove an observer from the set.
	 * @param o observer to remove.
	 */
	void deleteObserver(ISimpleObserver o);

}

