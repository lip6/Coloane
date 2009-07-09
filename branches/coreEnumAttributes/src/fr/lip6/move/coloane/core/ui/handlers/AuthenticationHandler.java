package fr.lip6.move.coloane.core.ui.handlers;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.Motor;
import fr.lip6.move.coloane.core.motor.session.ISessionManager;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.ui.dialogs.AuthenticationDialog;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Logger;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.HandlerEvent;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;

/**
 * Handler pour la commande d'authentification
 */
public class AuthenticationHandler extends AbstractHandler implements PropertyChangeListener {
	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/**
	 * Constructeur, ajout d'un listener sur le SessionManager
	 */
	public AuthenticationHandler() {
		SessionManager.getInstance().addPropertyChangeListener(this);
		IHandlerService service = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);
		service.activateHandler(Coloane.getParam("BREAK"), new BreakConnectionHandler(), null, true); //$NON-NLS-1$
		service.activateHandler(Coloane.getParam("CONNECT"), new ConnectHandler()); //$NON-NLS-1$
		service.activateHandler(Coloane.getParam("DISCONNECT"), new DisconnectHandler()); //$NON-NLS-1$
	}

	/** {@inheritDoc} */
	public final Object execute(ExecutionEvent event) throws ExecutionException {
		// Affichage de la boite de dialogue d'authentification
		AuthenticationDialog authDialog = new AuthenticationDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		if (authDialog.open() == Dialog.OK) {
			Motor.getInstance().authentication(authDialog.getResults());
		}
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isEnabled() {
		return !SessionManager.getInstance().isAuthenticated();
	}

	/** {@inheritDoc} */
	@Override
	public final void dispose() {
		SessionManager.getInstance().removePropertyChangeListener(this);
	}

	/** {@inheritDoc} */
	public final void propertyChange(PropertyChangeEvent evt) {
		LOGGER.finer("AuthenticationHandler : " + isEnabled()); //$NON-NLS-1$
		if (evt.getPropertyName().equals(ISessionManager.PROP_AUTHENTICATION)) {
			fireHandlerChanged(new HandlerEvent(this, true, false));
		}
	}

}
