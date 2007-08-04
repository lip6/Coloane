package fr.lip6.move.coloane.motor.session;

import java.util.ArrayList;
import java.util.Iterator;

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
	public Session getCurrentSession() {
		return currentSession;
	}

	/**
	 * Retourne la session dont le nom est donne
	 * @param sessionName nom de la session
	 * @return Session la session courante
	 */
	public Session getSession(String sessionName) {
		Iterator it;
		Session session = null;
		for (it = listOfSessions.iterator(); it.hasNext();) {
			session = (Session) it.next();
			if (sessionName.equals(session.getName())) {
				return session;
			}
		}
		return session;
	}


	/**
	 * Positionne la session courante
	 * Si aucune session est courante... Celle la devient la session courante
	 * @param s la session a positionner comme courante
	 */
	public void setSession(Session s) {
		if(this.currentSession == null) {
			System.out.println("Session par defaut : "+s.getName());
			this.currentSession = s;
		}
		this.listOfSessions.add(s);
	}


	/**
	 * Suspension d'une session
	 * @param sessionName nom de la session
	 */
	public void suspendSession(String sessionName) {
		this.getSession(sessionName).setStatus(SUSPEND);
	}


	/**
	 * Reprendre, rendre active une session
	 * @param sessionName nom de la session
	 */
	public void resumeSession(String sessionName) {
		this.getSession(sessionName).setStatus(OPEN);
	}


	/**
	 * Deconnexion du modele de la session courante
	 * @param sessionName nom de la session
	 */
	public void destroyCurrentSession() {
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
	public void destroyAllSessions() {
		Iterator i;
		Session session = null;
		for (i = listOfSessions.iterator(); i.hasNext();) {
			session = (Session) i.next();
			session.setServicesMenu(null);
			session.setAdminMenu(null);
			this.listOfSessions.remove(session);
		}

		this.currentSession = null;
	}   
}
