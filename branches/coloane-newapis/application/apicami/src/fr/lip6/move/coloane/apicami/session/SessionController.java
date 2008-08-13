package fr.lip6.move.coloane.apicami.session;

import fr.lip6.move.coloane.apicami.interfaces.ISessionController;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Cette classe est chargée de la gestion des sessions
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 * @author Jean-Baptiste Voron
 */
public final class SessionController implements ISessionController {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

	/** L'instance du session contrôleur */
	private static ISessionController instance = null;

	/** L'ensemble de nos session */
	private List<ApiSession> sessionsList;

	/** La session active */
	private ApiSession activeSession;

	/**
	 * Constructeur masqué pour éviter les doublons
	 */
	private SessionController() {
		this.activeSession = null;
		this.sessionsList = new ArrayList<ApiSession>();
	}

	/**
	 * @return l'instance unique du session contrôleur
	 */
	public static ISessionController getInstance() {
		if (instance == null) {
			instance = new SessionController();
		}
		return instance;
	}

	/**
	 * {@inheritDoc}
	 */
	public ApiSession getActiveSession() {
		if (this.activeSession == null) {
			LOGGER.warning("La session active est nulle");
		}
		return this.activeSession;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setActiveSession(ApiSession session) {
		// On controle pour savoir si la session qu'on souhaite activer est deja enregistree
		if (this.sessionsList.contains(session)) {
			LOGGER.finest("La nouvelle session active est : " + session.getName());
			this.activeSession = session;
			return true;
		} else {
			LOGGER.warning("La session " + session.getName() + " n'est pas enregistree (donc pas ouverte)");
			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void addSession(ApiSession session) {
		LOGGER.finest("Ajout de la session " + session.getName() + " a la liste des sessions controlees");
		this.sessionsList.add(session);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeSession(ApiSession session) {
		// Si la session qu'on souhaite supprimer est active...
		if (session.equals(this.activeSession)) {
			this.activeSession = null;
		}
		this.sessionsList.remove(session);
	}

	/**
	 * {@inheritDoc}
	 */
	public void closeAllSessions() throws ApiException {
		// Copie de la liste des sessions
		List<ApiSession> tmpList = new ArrayList<ApiSession>(this.sessionsList);
		for (IApiSession session : tmpList) {
			LOGGER.finer("Fermeture de la session " + session.getName());
			session.close();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyEndOpenSession() {
		this.activeSession.notifyEndOpenSession();
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyEndSuspendSession() {
		this.activeSession.notifyEndSuspendSession();
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyEndCloseSession() {
		this.activeSession.notifyEndCloseSession();
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyEndResumeSession(String nameSession) {
		// Je recherche la session qui vient de reprendre et je la notifie
		for (ApiSession session : this.sessionsList) {
			if (session.getName().equals(nameSession)) {
				session.notifyEndResumeSession();
				break;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyReceptSessionInfo(ISessionInfo o) {
		this.activeSession.notifyReceptSessionInfo(o);
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyWaitingForModel() {
		this.activeSession.notifyWaitingForModel();
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyWaitingForResult() {
		this.activeSession.notifyWaitingForResult();
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyEndResult() {
		this.activeSession.notifyEndResult();
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyEndInvalidModel() {
		this.activeSession.notifyEndInvalidatedSession();
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyReceptDialog(IDialog dialog) {
		this.activeSession.storeDialog(dialog);
	}
}
