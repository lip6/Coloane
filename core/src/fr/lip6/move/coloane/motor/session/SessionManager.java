package fr.lip6.move.coloane.motor.session;

import java.util.ArrayList;

/**
 * Gestionnaire de Sessions
 */
public class SessionManager {

	/** La session courante */
	private Session currentSession;

	/** Liste des sessions */
	private ArrayList<Session> listOfSessions = new ArrayList<Session>();

	/** Les indicateurs de statuts */
	public static final int OPEN = 1;
	public static final int SUSPEND = 2;


	/**
	 * Constructeur du gestionnaire de sessions
	 */
	public SessionManager() {
		this.currentSession = null;
	}

	/**
	 * Retourne la session courante
	 * @return la session courante
	 */
	public final Session getCurrentSession() {
		return currentSession;
	}

	/**
	 * Retourne la session dont le nom est donne
	 * @param sessionName nom de la session
	 * @return Session la session courante
	 */
	public final Session getSession(String sessionName) {
		for (Session session : listOfSessions) {
			if (sessionName.equals(session.getName())) { return session; }
		}
		return null;
	}


	/**
	 * Positionne la session courante
	 * Si aucune session est courante... Celle la devient la session courante
	 * @param s la session a positionner comme courante
	 */
	public final void setSession(Session s) {
		if (this.currentSession == null) {
			System.out.println("Session par defaut : " + s.getName());
			this.currentSession = s;
		}
		this.listOfSessions.add(s);
	}


	/**
	 * Suspension d'une session
	 * @param sessionName nom de la session
	 */
	public final void suspendSession(String sessionName) {
		this.getSession(sessionName).setStatus(SUSPEND);
	}


	/**
	 * Reprendre, rendre active une session
	 * @param sessionName nom de la session
	 */
	public final void resumeSession(String sessionName) {
		this.getSession(sessionName).setStatus(OPEN);
	}


	/**
	 * Deconnexion du modele de la session courante
	 * @param sessionName nom de la session
	 */
	public final void destroyCurrentSession() {
		if (currentSession != null) {
			// Suppression de la liste des sessions active
			this.listOfSessions.remove(this.currentSession);

			// Supression des menus
			this.currentSession.setServicesMenu(null);
			this.currentSession.setAdminMenu(null);

			// La session courante devient nulle
			this.currentSession = null;
		}
	}


	/**
	 * Deconnexion brutale de tous les modeles
	 */
	public final void destroyAllSessions() {
		for (Session session : this.listOfSessions) {
			session.setServicesMenu(null);
			session.setAdminMenu(null);
		}
		this.listOfSessions.clear();
		this.currentSession = null;
	}
}
