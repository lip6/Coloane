package fr.lip6.move.coloane.core.ui.handlers;

import fr.lip6.move.coloane.core.motor.Motor;
import fr.lip6.move.coloane.core.motor.session.ISessionManager;
import fr.lip6.move.coloane.core.motor.session.SessionManager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.HandlerEvent;

/**
 * Handler pour la commande de fermeture de la connection
 */
public class BreakConnectionHandler extends AbstractHandler implements PropertyChangeListener {

	/**
	 * Constructeur, ajout d'un listener sur le SessionManager
	 */
	public BreakConnectionHandler() {
		SessionManager.getInstance().addPropertyChangeListener(this);
	}

	/** {@inheritDoc} */
	public final Object execute(ExecutionEvent event) throws ExecutionException {
		Motor.getInstance().breakConnection();
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isEnabled() {
		return SessionManager.getInstance().isAuthenticated();
	}

	/** {@inheritDoc} */
	@Override
	public final void dispose() {
		SessionManager.getInstance().removePropertyChangeListener(this);
	}

	/** {@inheritDoc} */
	public final void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(ISessionManager.PROP_AUTHENTICATION)) {
			fireHandlerChanged(new HandlerEvent(this, true, false));
		}
	}

}
