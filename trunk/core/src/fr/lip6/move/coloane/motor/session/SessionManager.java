package fr.lip6.move.coloane.motor.session;

import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.ui.model.IModelImpl;

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
	public static final int ERROR = -1;
	public static final int CLOSED = 0;
	public static final int CONNECTED = 1;
	public static final int SUSPENDED = 2;


	/**
	 * Constructeur du gestionnaire de sessions
	 */
	public SessionManager() {
		this.currentSession = null;
	}

	/**
	 * Retourne le nom de la session courante
	 * @return Le nom de la session courante
	 */
	public final String getCurrentSessionName() {
		if (currentSession != null) {
			Coloane.getLogger().fine("La session courante est : " + currentSession.getName() + "(Id:" + currentSession.getId() + ")");
			return currentSession.getName();
		}
		return null;
	}

	/**
	 * Retourne le modele attache a la session courante
	 * @return Le modele attache a la session courante
	 */
	public final IModelImpl getCurrentSessionModel() {
		if (currentSession != null) { return currentSession.getModel();	}
		return null;
	}

	/**
	 * Retourne le status de la session courante
	 * @return Le status de la session courante
	 */
	public final int getCurrentSessionStatus() {
		if (currentSession != null) {
			return currentSession.getStatus();
		}
		return ERROR;
	}

	/**
	 * Indique que la session courante est connectee
	 */
	public final void setCurrentSessionConnected() {
		if (currentSession != null) {
			currentSession.setStatus(CONNECTED);
		}
	}

	/**
	 * Indique que la session courante est connectee
	 */
	public final void setCurrentSessionDisconnected() {
		if (currentSession != null) {
			currentSession.setServicesMenu(null);
			currentSession.setAdminMenu(null);
			currentSession.setStatus(CLOSED);
		}
	}

	/**
	 * Retourne la session courante
	 * @return La session courante
	 */
	public final Session getCurrentSession() {
		return currentSession;
	}

	/**
	 * Retourne la session dont le nom est indique
	 * @param sessionName nom de la session
	 * @return Session la session designe ou NULL si on ne trouve pas la session
	 */
	public final Session getSession(String sessionName) {
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
	public final void attachSession(Session s) {
		if (this.currentSession == null) { this.currentSession = s;	}
		this.listOfSessions.add(s);
	}

	/**
	 * Suspension d'une session
	 * @param sessionName Le nom de la session
	 * @return booleen Un indicateur de deroulement
	 */
	public final boolean suspendSession(String sessionName) {
		Coloane.getLogger().finer("Suspension d'une session : " + sessionName);
		Session toSuspend = getSession(sessionName);
		if (toSuspend != null) {
			Coloane.getLogger().finer("Session suspendue : " + toSuspend.getName() + "(" + toSuspend.getId() + ")");
			toSuspend.suspend();
			return true;
		} else {
			Coloane.getLogger().finer("Session suspendue non enregistree");
			return false;
		}
	}

	/**
	 * Reprendre, rendre active une session
	 * @param sessionName nom de la session
	 * @return booleen Un indicateur de deroulement
	 */
	public final boolean resumeSession(String sessionName) {
		Coloane.getLogger().finer("Tentative de reprise d'une session : " + sessionName);
		Session toResume = getSession(sessionName);
		if (toResume != null) {
			Coloane.getLogger().finer("La session est enregistree !");
			toResume.resume();
			if ((currentSession != null) && (!currentSession.getName().equals(sessionName))) {
				Coloane.getLogger().warning("La session courante n'est pas suspendue");
				suspendSession(currentSession.getName());
			}
			this.currentSession = toResume;
			return true;
		} else {
			Coloane.getLogger().warning("Session active non enregistree");
			return false;
		}
	}

	/**
	 * Deconnexion du modele de la session courante
	 * @param sessionName nom de la session
	 */
	public final void destroyCurrentSession() {
		Coloane.getLogger().fine("Destruction de la session courante");
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
		Coloane.getLogger().fine("Destruction de toutes les sessions");
		for (Session session : this.listOfSessions) {
			session.setServicesMenu(null);
			session.setAdminMenu(null);
		}
		this.listOfSessions.clear();
		this.currentSession = null;
	}

	/**
	 * Retourne le status de la session designee
	 * @param sessionName Le nom de la session concernee
	 * @return L'etat de la session
	 */
	public final int getSessionStatus(String sessionName) {
		Session s = getSession(sessionName);
		if (s == null) {
			return ERROR;
		} else {
			return s.getStatus();
		}
	}
}
