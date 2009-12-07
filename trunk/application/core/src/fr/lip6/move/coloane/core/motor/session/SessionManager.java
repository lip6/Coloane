package fr.lip6.move.coloane.core.motor.session;

import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.swt.widgets.Display;

/**
 * Gestionnaire de Sessions
 */
public final class SessionManager implements ISessionManager {
	/** Le logger pour la classe */
	private static final Logger LOG = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** L'instance singleton du Session Manager */
	private static SessionManager instance = null;

	/** Est-on authentifie sur la plate-forme ? */
	private boolean authenticated;

	/** La session courante */
	private ISession currentSession;

	/** Liste des sessions */
	private Map<String, ISession> sessions = new HashMap<String, ISession>();

	/** Gestion des listeners */
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * Constructeur du gestionnaire de sessions
	 */
	private SessionManager() {
		this.currentSession = null;
	}

	/**
	 * Retourne le gestionnaire de sessions
	 * @return SessionManager Une instance du gestionnaire de sessions
	 */
	public static synchronized ISessionManager getInstance() {
		if (instance == null) { instance = new SessionManager(); }
		return instance;
	}

	/** {@inheritDoc} */
	public ISession getCurrentSession() {
		return currentSession;
	}

	/** {@inheritDoc} */
	public ISession getSession(String sessionName) {
		return sessions.get(sessionName);
	}

	/** {@inheritDoc} */
	public ISession getSession(IGraph graph) {
		for (ISession session : sessions.values()) {
			if (session.getGraph().equals(graph)) {
				return session;
			}
		}
		return null;
	}

	/** {@inheritDoc} */
	public Collection<ISession> getSessions() {
		return sessions.values();
	}

	/** {@inheritDoc} */
	public ISession newSession(String sessionId) {
		// Le nom de la nouvelle session ne doit pas être null
		if (sessionId == null) {
			throw new NullPointerException("Nom de session null"); //$NON-NLS-1$
		}

		// Le nom de la nouvelle session ne doit pas être vide
		if (sessionId.length() == 0) {
			throw new IllegalArgumentException("Le nom de la session ne doit pas être vide"); //$NON-NLS-1$
		}

		// Si une session homonyme existe déjà... On lève une IllegalArgumentException
		if (sessions.containsKey(sessionId)) {
			LOG.fine("Une session homonyme (" + sessionId + ") existe deja..."); //$NON-NLS-1$ //$NON-NLS-2$
			throw new IllegalArgumentException("Cette session existe déjà :" + sessionId); //$NON-NLS-1$
		}

		// Sinon on cree la session
		ISession newSession = new Session(sessionId);
		sessions.put(sessionId, newSession);
		if (this.currentSession == null) {
			setCurrentSession(newSession);
		}
		return newSession;
	}

	/**
	 * Reprendre, rendre active une session
	 * @param sessionId nom de la session
	 * @return booleen Un indicateur de deroulement
	 */
	public boolean resumeSession(String sessionId) {
		ISession toResume = getSession(sessionId);

		if (toResume != null) {
			LOG.finer("Reprise de la session : " + sessionId); //$NON-NLS-1$

			// Reprise de la session
			((Session) toResume).resume();
			setCurrentSession(toResume);

			return true;
		} else {
			LOG.fine("Session " + sessionId + " non enregistree dans le SessionManager"); //$NON-NLS-1$ //$NON-NLS-2$
			return false;
		}
	}

	/**
	 * Destruction de la session courante
	 * @param sessionName nom de la session
	 * @throws ApiException En cas d'erreur lors de la fermeture de la session
	 */
	public void deleteSession(String sessionName) throws ApiException {
		LOG.fine("Destruction de la session " + sessionName); //$NON-NLS-1$
		ISession toDestroy = sessions.remove(sessionName);
		if (toDestroy != null) {
			((Session) toDestroy).destroy();
			// La session courante devient nulle
			if (toDestroy.equals(currentSession)) {
				setCurrentSession(null);
			}
		}
	}

	/**
	 * Deconnexion brutale de tous les modeles
	 * @throws ApiException En cas d'erreur lors de la fermeture de la session
	 */
	public void disconnectAllSessions() throws ApiException {
		LOG.fine("Déconnexion de toutes les sessions"); //$NON-NLS-1$
		for (ISession session : sessions.values()) {
			((Session) session).disconnect();
		}
	}

	/**
	 * Change la session courrante
	 * @param currentSession nouvelle session courrante
	 */
	private void setCurrentSession(ISession currentSession) {
		ISession previousSession = this.currentSession;
		this.currentSession = currentSession;

		LOG.finer("La session " + currentSession + " est maintenant la session courante"); //$NON-NLS-1$ //$NON-NLS-2$

		// Rafraichisement des vues annexes
		firePropertyChange(PROP_CURRENT_SESSION, previousSession, currentSession);
	}

	/** {@inheritDoc} */
	public boolean isAuthenticated() {
		return authenticated;
	}

	/** {@inheritDoc} */
	public void setAuthenticated(boolean authStatus) {
		if (authenticated == authStatus) {
			return;
		}
		this.authenticated = authStatus;
		firePropertyChange(PROP_AUTHENTICATION, !authStatus, authStatus);
	}

	/** {@inheritDoc} */
	public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/** {@inheritDoc} */
	public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	/**
	 * Envoie une notification de modification de propriété
	 * @param property La propriété
	 * @param oldValue L'ancienne valeur de la propriété
	 * @param newValue La nouvelle valeur
	 */
	protected void firePropertyChange(final String property, final Object oldValue, final Object newValue) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				if (pcs.hasListeners(property)) {
					pcs.firePropertyChange(property, oldValue, newValue);
				}
			}
		});
	}

	/**
	 * Demande à toutes les sessions d'afficher ce message.
	 * @param message message à afficher dans les consoles
	 * @param type type du message (des constantes sont définies dans la classe MessageType)
	 */
	public void printConsoleMessage(String message, MessageType type) {
		for (ISession session : sessions.values()) {
			((Session) session).printConsoleMessage(message, type);
		}
	}
}
