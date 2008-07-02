package fr.lip6.move.coloane.interfaces.model;

import java.beans.PropertyChangeListener;

public interface IAbstractPropertyChange {
	/**
	 * Attach a listener to this element.
	 * @param listener
	 */
	void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Remove a listener.
	 * @param listener
	 */
	void removePropertyChangeListener(PropertyChangeListener listener);
}
