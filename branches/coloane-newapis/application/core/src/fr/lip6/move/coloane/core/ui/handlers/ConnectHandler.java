package fr.lip6.move.coloane.core.ui.handlers;

import fr.lip6.move.coloane.core.motor.Motor;
import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.core.motor.session.ISessionManager;
import fr.lip6.move.coloane.core.motor.session.SessionManager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Logger;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.HandlerEvent;

/**
 * Handler pour la commande d'ouverture d'une session
 */
public class ConnectHandler extends AbstractHandler implements PropertyChangeListener {
	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/**
	 * Constructeur, ajout d'un listener sur le SessionManager
	 */
	public ConnectHandler() {
		SessionManager.getInstance().addPropertyChangeListener(this);
	}

	/** {@inheritDoc} */
	public final Object execute(ExecutionEvent event) throws ExecutionException {
		Motor.getInstance().openSession();
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isEnabled() {
		ISession currentSession = SessionManager.getInstance().getCurrentSession();
		return SessionManager.getInstance().isAuthenticated() && currentSession != null && currentSession.getStatus() == ISession.CLOSED;
	}

	/** {@inheritDoc} */
	@Override
	public final void dispose() {
		SessionManager.getInstance().removePropertyChangeListener(this);
	}

	/** {@inheritDoc} */
	public final void propertyChange(PropertyChangeEvent evt) {
		LOGGER.finer("ConnectHandler : " + isEnabled());
		if (evt.getPropertyName().equals(ISessionManager.PROP_CURRENT_SESSION)) {
			if (evt.getOldValue() != null) {
				((ISession) evt.getOldValue()).removePropertyChangeListener(this);
			}
			if (evt.getNewValue() != null) {
				((ISession) evt.getNewValue()).addPropertyChangeListener(this);
			}
			fireHandlerChanged(new HandlerEvent(this, true, false));
		} else if (evt.getPropertyName().equals(ISessionManager.PROP_AUTHENTICATION)) {
			fireHandlerChanged(new HandlerEvent(this, true, false));
		} else if (evt.getPropertyName().equals(ISession.PROP_CONNECTION)) {
			fireHandlerChanged(new HandlerEvent(this, true, false));
		}
	}
}
