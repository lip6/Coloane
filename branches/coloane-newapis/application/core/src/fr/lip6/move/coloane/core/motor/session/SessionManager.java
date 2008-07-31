package fr.lip6.move.coloane.core.motor.session;

import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.logging.Logger;

/**
 * Gestionnaire de Sessions
 */
public final class SessionManager extends Observable implements ISessionManager {
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
	public ISession newSession(String name) {
		// Le nom de la nouvelle session ne doit pas être null
		if (name == null) {
			throw new NullPointerException("Nom de session null"); //$NON-NLS-1$
		}

		// Le nom de la nouvelle session ne doit pas être vide
		if (name.length() == 0) {
			throw new IllegalArgumentException("Le nom de la session ne doit pas être vide"); //$NON-NLS-1$
		}

		// Si une session homonyme existe déjà... On lève une IllegalArgumentException
		if (sessions.containsKey(name)) {
			LOG.fine("Une session homonyme (" + name + ") existe deja..."); //$NON-NLS-1$ //$NON-NLS-2$
			throw new IllegalArgumentException("Cette session existe déjà :" + name); //$NON-NLS-1$
		}

		// Sinon on cree la session
		ISession newSession = new Session(name);
		if (this.currentSession == null) {
			setCurrentSession(newSession);
		}
		sessions.put(name, newSession);
		return newSession;
	}

	/**
	 * Reprendre, rendre active une session
	 * @param sessionName nom de la session
	 * @return booleen Un indicateur de deroulement
	 */
	public boolean resumeSession(String sessionName) {
		ISession toResume = getSession(sessionName);

		if (toResume != null) {
			LOG.finer("Reprise de la session : " + sessionName); //$NON-NLS-1$

			// Reprise de la session
			((Session) toResume).resume();
			setCurrentSession(toResume);

			return true;
		} else {
			LOG.fine("Session " + sessionName + " non enregistree dans le SessionManager"); //$NON-NLS-1$ //$NON-NLS-2$
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
		this.currentSession = currentSession;

		LOG.finer("La session " + currentSession + " est maintenant la session courante"); //$NON-NLS-1$ //$NON-NLS-2$

		// Rafraichisement des vues annexes
		setChanged();
		notifyObservers(currentSession);
	}

	/** {@inheritDoc} */
	public boolean isAuthenticated() {
		return authenticated;
	}

	/** {@inheritDoc} */
	public void setAuthenticated(boolean authStatus) {
		this.authenticated = authStatus;
	}
}
