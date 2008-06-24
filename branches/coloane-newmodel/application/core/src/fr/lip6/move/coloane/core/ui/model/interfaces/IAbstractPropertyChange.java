package fr.lip6.move.coloane.core.ui.model.interfaces;

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
