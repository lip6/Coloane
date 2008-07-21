package fr.lip6.move.coloane.core.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class AbstractPropertyChange {
	/**
	 * Manage properties change listeners.
	 */
	private PropertyChangeSupport pcsDelegate = new PropertyChangeSupport(this);

	/** {@inheritDoc} */
	public final synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
		pcsDelegate.addPropertyChangeListener(listener);
	}

	/** {@inheritDoc} */
	public final synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
		pcsDelegate.removePropertyChangeListener(listener);
	}

	protected final void firePropertyChange(String property, Object oldValue, Object newValue) {
		if (pcsDelegate.hasListeners(property)) {
			pcsDelegate.firePropertyChange(property, oldValue, newValue);
		}
	}
}
