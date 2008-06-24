package fr.lip6.move.coloane.core.ui.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class AbstractPropertyChange {
	/**
	 * Manage properties change listeners.
	 */
	private PropertyChangeSupport pcsDelegate = new PropertyChangeSupport(this);
	
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IElement#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public final synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
		pcsDelegate.addPropertyChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.IElement#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public final synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
		pcsDelegate.removePropertyChangeListener(listener);
	}

	protected final void firePropertyChange(String property, Object oldValue, Object newValue) {
		if (pcsDelegate.hasListeners(property)) {
			pcsDelegate.firePropertyChange(property, oldValue, newValue);
		}
	}
}
