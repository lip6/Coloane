package fr.lip6.move.coloane.interfaces.model.core;

import fr.lip6.move.coloane.interfaces.model.IAbstractPropertyChange;

import java.beans.PropertyChangeListener;

public interface ICoreAbstractPropertyChange extends IAbstractPropertyChange {
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
