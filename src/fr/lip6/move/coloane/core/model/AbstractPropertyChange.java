package fr.lip6.move.coloane.core.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Manager for listeners
 * 
 * @author Jean-Baptiste Voron
 */
public abstract class AbstractPropertyChange {

	/** Manage properties change listeners. */
	private PropertyChangeSupport pcsDelegate = new PropertyChangeSupport(this);

	/**
	 * Add an object into the list of object to warn about a change in the current object
	 * @param listener the object that has to be warned
	 */
	public final synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
		pcsDelegate.addPropertyChangeListener(listener);
	}

	/**
	 * Remove the listener
	 * @param listener The listener to remove
	 */
	public final synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
		pcsDelegate.removePropertyChangeListener(listener);
	}

	/**
	 * Send a notification to all of its listeners
	 * @param property The updated property
	 * @param oldValue Property old value
	 * @param newValue Property new value
	 */
	protected final void firePropertyChange(String property, Object oldValue, Object newValue) {
		if (pcsDelegate.hasListeners(property)) {
			pcsDelegate.firePropertyChange(property, oldValue, newValue);
		}
	}
}
