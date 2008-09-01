package fr.lip6.move.coloane.interfaces.model;

import java.beans.PropertyChangeListener;

/**
 * Comportement que doivent définir les objets réactifs sur l'éditeur
 */
public interface IAbstractPropertyChange {
	/**
	 * Attach a listener to this element.
	 * @param listener listener to add
	 */
	void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Remove a listener.
	 * @param listener listener to remove
	 */
	void removePropertyChangeListener(PropertyChangeListener listener);
}
