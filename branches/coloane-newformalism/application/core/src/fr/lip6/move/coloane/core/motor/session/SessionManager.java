package fr.lip6.move.coloane.core.motor.session;

import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Logger;

/**
 * Gestionnaire de Sessions
 */
public final class SessionManager extends Observable implements ISessionManager {
	/** Le logger pour la classe */
	private static final Logger LOG = Logger.getLogger("fr.lip6.move.coloane.core");

	/** Est-on authentifie sur la plate-forme ? */
	private boolean authenticated;

	/** La session courante */
	private ISession currentSession;

	/** Liste des sessions */
	private ArrayList<ISession> listOfSessions = new ArrayList<ISession>();

	/** L'instance singleton du Session Manager */
	private static SessionManager instance = null;

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

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISessionManager#getCurrentSession()
	 */
	public ISession getCurrentSession() {
		return currentSession;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISessionManager#getSession(java.lang.String)
	 */
	public ISession getSession(String sessionName) {
		for (ISession session : listOfSessions) {
			if (session.getName().equals(sessionName)) { return session; }
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISessionManager#newSession(java.lang.String)
	 */
	public ISession newSession(String name) {
		// Si une session homonyme existe deja... On retourne null
		if (this.getSession(name) != null) {
			LOG.fine("Une session homonyme (" + name + ") existe deja..."); //$NON-NLS-1$ //$NON-NLS-2$
			return this.getSession(name);
		}

		// Le nom de la nouvelle session ne doi pas etre vide ou null
		if ((name == null) || ("".equals(name))) {
			return null;
		}

		// Sinon on cree la session
		ISession newSession = new Session(name);
		if (this.currentSession == null) {
			this.currentSession = newSession;
			LOG.finer("La nouvelle session " + name + " est maintenant la session courante"); //$NON-NLS-1$ //$NON-NLS-2$

			// Rafraichisement des vues annexes
			setChanged();
			notifyObservers(currentSession);
		}
		this.listOfSessions.add(newSession);
		return newSession;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISessionManager#suspendSession(java.lang.String)
	 */
	public boolean suspendSession(String sessionName) {
		LOG.finer("Suspension d'une session : " + sessionName); //$NON-NLS-1$
		ISession toSuspend = getSession(sessionName);

		// Si la session existe vraiment
		if (toSuspend != null) {
			LOG.finer("Session suspendue : " + toSuspend.getName()); //$NON-NLS-1$

			// si la session suspendue etait la session courante...
			if (toSuspend.equals(this.currentSession)) {
				LOG.finer("La session courante est maintenant nulle"); //$NON-NLS-1$
				this.currentSession = null;
			}

			// Suspension de la session
			toSuspend.suspend();

			return true;
		} else {
			LOG.finer("Session suspendue non enregistree dans le SessionManager"); //$NON-NLS-1$
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISessionManager#resumeSession(java.lang.String)
	 */
	public boolean resumeSession(String sessionName) {
		LOG.finer("Reprise d'une session : " + sessionName); //$NON-NLS-1$
		ISession toResume = getSession(sessionName);

		if (toResume != null) {
			LOG.finer("La session est enregistree dans le SessionManager !"); //$NON-NLS-1$

			if ((currentSession != null) && (!currentSession.getName().equals(sessionName))) {
				LOG.warning("La session courante n'est pas suspendue"); //$NON-NLS-1$
				suspendSession(currentSession.getName());
			}

			this.currentSession = toResume;
			toResume.resume();

			setChanged();
			notifyObservers(currentSession);
			return true;
		} else {
			LOG.fine("Session active non enregistree dans le SessionManager"); //$NON-NLS-1$
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISessionManager#destroySession(java.lang.String)
	 */
	public boolean destroySession(String sessionName) {
		LOG.fine("Destruction de la session " + sessionName); //$NON-NLS-1$
		ISession toDestroy = getSession(sessionName);
		if (toDestroy != null) {
			// Suppression de la liste des sessions active
			listOfSessions.remove(toDestroy);

			// Supression des menus
			toDestroy.setServicesMenu(null);
			toDestroy.setAdminMenu(null);

			// La session courante devient nulle
			if (sessionName.equals(currentSession.getName())) {
				currentSession = null;
				setChanged();
				notifyObservers(null);
			}
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISessionManager#destroyAllSessions()
	 */
	public void destroyAllSessions() {
		LOG.fine("Destruction de toutes les sessions"); //$NON-NLS-1$
		for (ISession session : this.listOfSessions) {
			session.setServicesMenu(null);
			session.setAdminMenu(null);
			session.setStatus(ISession.CLOSED);
		}
		this.listOfSessions.clear();
		this.currentSession = null;

		setChanged();
		notifyObservers(null);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISessionManager#isAuthenticated()
	 */
	public boolean isAuthenticated() {
		return authenticated;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISessionManager#setAuthenticated(boolean)
	 */
	public void setAuthenticated(boolean authStatus) {
		this.authenticated = authStatus;
	}
}
