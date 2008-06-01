package fr.lip6.move.coloane.core.motor.session;

import fr.lip6.move.coloane.core.main.Coloane;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Gestionnaire de Sessions
 */
public final class SessionManager extends Observable implements ISessionManager {

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
			if (sessionName.equals(session.getName())) { return session; }
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISessionManager#newSession(java.lang.String)
	 */
	public void newSession(String name) {
		ISession newSession = new Session(name);
		if (this.currentSession == null) {
			this.currentSession = newSession;

			// Rafraichisement des vues annexes
			setChanged();
			notifyObservers(currentSession);
		}
		this.listOfSessions.add(newSession);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISessionManager#suspendSession(java.lang.String)
	 */
	public boolean suspendSession(String sessionName) {
		Coloane.getLogger().finer("Suspension d'une session : " + sessionName); //$NON-NLS-1$
		ISession toSuspend = getSession(sessionName);
		if (toSuspend != null) {
			Coloane.getLogger().finer("Session suspendue : " + toSuspend.getName()); //$NON-NLS-1$
			toSuspend.suspend();
			return true;
		} else {
			Coloane.getLogger().finer("Session suspendue non enregistree dans le SessionManager"); //$NON-NLS-1$
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISessionManager#resumeSession(java.lang.String)
	 */
	public boolean resumeSession(String sessionName) {
		Coloane.getLogger().finer("Tentative de reprise d'une session : " + sessionName); //$NON-NLS-1$
		ISession toResume = getSession(sessionName);
		if (toResume != null) {
			Coloane.getLogger().finer("La session est enregistree dans le SessionManager !"); //$NON-NLS-1$
			toResume.resume();
			if ((currentSession != null) && (!currentSession.getName().equals(sessionName))) {
				Coloane.getLogger().warning("La session courante n'est pas suspendue"); //$NON-NLS-1$
				suspendSession(currentSession.getName());
			}
			this.currentSession = toResume;
			setChanged();
			notifyObservers(currentSession);
			return true;
		} else {
			Coloane.getLogger().fine("Session active non enregistree dans le SessionManager"); //$NON-NLS-1$
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISessionManager#destroySession(java.lang.String)
	 */
	public boolean destroySession(String sessionName) {
		Coloane.getLogger().fine("Destruction de la session " + sessionName); //$NON-NLS-1$
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
		Coloane.getLogger().fine("Destruction de toutes les sessions"); //$NON-NLS-1$
		for (ISession session : this.listOfSessions) {
			session.setServicesMenu(null);
			session.setAdminMenu(null);
			session.setStatus(ISession.CLOSED);
		}
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
