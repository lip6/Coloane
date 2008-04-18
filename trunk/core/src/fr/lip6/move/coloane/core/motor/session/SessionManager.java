package fr.lip6.move.coloane.core.motor.session;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

import java.util.ArrayList;

/**
 * Gestionnaire de Sessions
 */
public final class SessionManager {

	/** Est-on authentifie sur la plate-forme ? */
	private boolean authenticated;

	/** La session courante */
	private Session currentSession;

	/** Liste des sessions */
	private ArrayList<Session> listOfSessions = new ArrayList<Session>();

	/** L'instance singleton du Session Manager */
	private static SessionManager instance = null;

	/** Les indicateurs de statuts */
	public static final int ERROR = -1;
	public static final int CLOSED = 0;
	public static final int CONNECTED = 1;
	public static final int SUSPENDED = 2;

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
	public static synchronized SessionManager getInstance() {
		if (instance == null) { instance = new SessionManager(); }
		return instance;
	}

	/**
	 * Retourne la session courante
	 * @return La session courante
	 */
	public Session getCurrentSession() {
		return currentSession;
	}

	/**
	 * Retourne le nom de la session courante
	 * @return Le nom de la session courante
	 */
	public String getCurrentSessionName() {
		if (currentSession != null) {
			Coloane.getLogger().fine("La session courante est : " + currentSession.getName() + "(Id:" + currentSession.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			return currentSession.getName();
		}
		return null;
	}

	/**
	 * Retourne le modele attache a la session courante
	 * @return Le modele attache a la session courante
	 */
	public IModelImpl getCurrentSessionModel() {
		if (currentSession != null) { return currentSession.getModel();	}
		return null;
	}

	/**
	 * Retourne le status de la session courante
	 * @return Le status de la session courante (ou ERROR en cas de session inexistante)
	 */
	public int getCurrentSessionStatus() {
		if (currentSession != null) {
			return currentSession.getStatus();
		}
		return ERROR;
	}

	/**
	 * Indique que la session courante est connectee<br>
	 * Attention connectee =! authentifie (cf {@link #authenticated}
	 */
	public void setCurrentSessionConnected() {
		if (currentSession != null) {
			currentSession.setStatus(CONNECTED);
		}
	}

	/**
	 * Indique que la session courante est deconnectee
	 * Attention connectee =! authentifie (cf {@link #authenticated}
	 */
	public void setCurrentSessionDisconnected() {
		if (currentSession != null) {
			currentSession.setServicesMenu(null);
			currentSession.setAdminMenu(null);
			currentSession.setStatus(CLOSED);
		}
	}

	/**
	 * Retourne la session dont le nom est indique
	 * @param sessionName nom de la session
	 * @return Session la session designe ou NULL si on ne trouve pas la session
	 */
	public Session getSession(String sessionName) {
		for (Session session : listOfSessions) {
			if (sessionName.equals(session.getName())) { return session; }
		}
		return null;
	}

	/**
	 * Ajoute une session au manager<br>
	 * Si aucune session est courante... Celle la devient la session courante
	 * @param s la session a positionner comme courante
	 */
	public void attachSession(Session s) {
		if (this.currentSession == null) { this.currentSession = s;	}
		this.listOfSessions.add(s);
	}

	/**
	 * Suspension d'une session
	 * @param sessionName Le nom de la session
	 * @return booleen Un indicateur de deroulement
	 */
	public boolean suspendSession(String sessionName) {
		Coloane.getLogger().finer("Suspension d'une session : " + sessionName); //$NON-NLS-1$
		Session toSuspend = getSession(sessionName);
		if (toSuspend != null) {
			Coloane.getLogger().finer("Session suspendue : " + toSuspend.getName() + "(" + toSuspend.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			toSuspend.suspend();
			return true;
		} else {
			Coloane.getLogger().finer("Session suspendue non enregistree"); //$NON-NLS-1$
			return false;
		}
	}

	/**
	 * Reprendre, rendre active une session
	 * @param sessionName nom de la session
	 * @return booleen Un indicateur de deroulement
	 */
	public boolean resumeSession(String sessionName) {
		Coloane.getLogger().finer("Tentative de reprise d'une session : " + sessionName); //$NON-NLS-1$
		Session toResume = getSession(sessionName);
		if (toResume != null) {
			Coloane.getLogger().finer("La session est enregistree !"); //$NON-NLS-1$
			toResume.resume();
			if ((currentSession != null) && (!currentSession.getName().equals(sessionName))) {
				Coloane.getLogger().warning("La session courante n'est pas suspendue"); //$NON-NLS-1$
				suspendSession(currentSession.getName());
			}
			this.currentSession = toResume;
			return true;
		} else {
			Coloane.getLogger().warning("Session active non enregistree"); //$NON-NLS-1$
			return false;
		}
	}

	/**
	 * Destruction de la session courante
	 * @param sessionName nom de la session
	 */
	public boolean destroySession(String sessionName) {
		Coloane.getLogger().fine("Destruction de la session " + sessionName); //$NON-NLS-1$
		Session toDestroy = getSession(sessionName);
		if (toDestroy != null) {
			// Suppression de la liste des sessions active
			listOfSessions.remove(toDestroy);

			// Supression des menus
			toDestroy.setServicesMenu(null);
			toDestroy.setAdminMenu(null);

			// La session courante devient nulle
			if (sessionName.equals(currentSession.getName())) {
				currentSession = null;
			}
			return true;
		}
		return false;
	}

	/**
	 * Deconnexion brutale de tous les modeles
	 */
	public void destroyAllSessions() {
		Coloane.getLogger().fine("Destruction de toutes les sessions"); //$NON-NLS-1$
		for (Session session : this.listOfSessions) {
			session.setServicesMenu(null);
			session.setAdminMenu(null);
			session.setStatus(CLOSED);
		}
	}

	/**
	 * Retourne le status de la session designee
	 * @param sessionName Le nom de la session concernee
	 * @return L'etat de la session
	 */
	public int getSessionStatus(String sessionName) {
		Session s = getSession(sessionName);
		if (s == null) {
			return ERROR;
		} else {
			return s.getStatus();
		}
	}

	/**
	 * Retourne le status d'authentification du client<br>
	 * L'authentification est valable pour tous les modeles
	 * @return Un booleen
	 */
	public boolean isAuthenticated() {
		return authenticated;
	}

	/**
	 * Positionne le status d'authentification du client
	 * @param authenticated Le nouveau status du client
	 */
	public void setAuthenticated(boolean authStatus) {
		this.authenticated = authStatus;
	}
}
