package fr.lip6.move.coloane.core.motor.session;

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
	 * @param session session a suspendre
	 */
	private void suspendSession(ISession session) {
		if (session == null) {
			throw new NullPointerException("Impossible de suspendre une session null"); //$NON-NLS-1$
		}

		LOG.finer("Suspension de la session : " + session); //$NON-NLS-1$

		// Suspension de la session
		session.suspend();

		// Si la session suspendue etait la session courante...
		if (session.equals(currentSession)) {
			setCurrentSession(null);
		}
	}

	/** {@inheritDoc} */
	public boolean suspendSession(String sessionName) {
		ISession toSuspend = getSession(sessionName);

		// Si la session existe
		if (toSuspend != null) {
			suspendSession(toSuspend);
			return true;
		} else {
			LOG.finer("Session " + sessionName + " non enregistree dans le SessionManager"); //$NON-NLS-1$ //$NON-NLS-2$
			return false;
		}
	}

	/** {@inheritDoc} */
	public boolean resumeSession(String sessionName) {
		ISession toResume = getSession(sessionName);

		if (toResume != null) {
			LOG.finer("Reprise de la session : " + sessionName); //$NON-NLS-1$

			if (currentSession != null && !toResume.equals(currentSession)) {
				LOG.warning("La session courante n'est pas suspendue"); //$NON-NLS-1$
				suspendSession(currentSession);
			}

			// Reprise de la session
			toResume.resume();
			setCurrentSession(toResume);

			return true;
		} else {
			LOG.fine("Session " + sessionName + " non enregistree dans le SessionManager"); //$NON-NLS-1$ //$NON-NLS-2$
			return false;
		}
	}

	/** {@inheritDoc} */
	public void destroySession(String sessionName) {
		LOG.fine("Destruction de la session " + sessionName); //$NON-NLS-1$
		ISession toDestroy = sessions.remove(sessionName);
		if (toDestroy != null) {
			// La session courante devient nulle
			if (toDestroy.equals(currentSession)) {
				setCurrentSession(null);
			}
		}
	}

	/** {@inheritDoc} */
	public void destroyAllSessions() {
		LOG.fine("Destruction de toutes les sessions"); //$NON-NLS-1$
		for (ISession session : sessions.values()) {
			session.destroy();
		}
		sessions.clear();
		setCurrentSession(null);
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
