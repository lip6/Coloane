package fr.lip6.move.coloane.core.motor.session;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.menus.RootMenu;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

/**
 * Definition d'une session
 */
public class Session {

	/** Compteur de sessions */
	private static int cntSession = 1;

	/** Le modele associe */
	private IModelImpl sessionModel;

	/** Nom de la session */
	private String sessionName;

	/** Identifiant de la session */
	private int sessionId;

	/** Arborescence du menu administration */
	private RootMenu adminMenu;

	/** Arborescence du menu et des services de la session */
	private RootMenu sessionMenu;

	/** Status de la session */
	private int sessionStatus;


	/**
	 * Constructeur de la classe Session
	 * @param name Nom de la session
	 */
	public Session(String name) {
		this.sessionName = name;
		this.sessionId = cntSession++;
		this.sessionModel = null;
		this.sessionStatus = SessionManager.CLOSED;
	}

	/**
	 * Suspend la session
	 */
	protected final void suspend() {
		Coloane.getLogger().finer("Suspension de la session " + this.sessionName);
		if (sessionStatus == SessionManager.CONNECTED) {
			sessionStatus = SessionManager.SUSPENDED;
		}
	}

	/**
	 * Resume la session
	 */
	protected final void resume() {
		Coloane.getLogger().finer("Reprise de la session " + this.sessionName);
		if (sessionStatus == SessionManager.SUSPENDED) {
			sessionStatus = SessionManager.CONNECTED;
		}
	}

	/**
	 * Retoune le nom de la session
	 * @return name
	 */
	public final String getName() {
		return sessionName;
	}

	/**
	 * Retourne le numero de la session
	 * @return int Le numero de la session
	 */
	protected final int getId() {
		return this.sessionId;
	}

	/**
	 * Retoune le modele
	 * @return IModelImpl Le modele de la session
	 */
	public final IModelImpl getModel() {
		return this.sessionModel;
	}

	/**
	 * Positionne le modele
	 * @param model nouveau modele
	 */
	public final void setModel(IModelImpl model) {
		this.sessionModel = model;
	}

	/**
	 * Retourne le menu d'administration
	 * @return la racine du menu d'administration
	 */
	public final RootMenu getAdminMenu() {
		return adminMenu;
	}

	/**
	 * Indique le menu d'administration attache ˆ la session
	 * @param adminMenu La racinde du menu d'administration
	 */
	public final void setAdminMenu(RootMenu admin) {
		this.adminMenu = admin;
	}

	/**
	 * Retourne le menu de service de la session
	 * @return la racine du menu de services
	 */
	public final RootMenu getServicesMenu() {
		return sessionMenu;
	}

	/**
	 * Indique le menu de services attache a la session
	 * @param sessionMenu la racine du menu de services
	 */
	public final void setServicesMenu(RootMenu root) {
		this.sessionMenu = root;
	}

	/**
	 * Retourne le status courant de la session
	 * @return le status courant de la session
	 */
	protected final int getStatus() {
		return sessionStatus;
	}

	/**
	 * Modifie le status courant de la session
	 * @param sessionStatus Le status courant de la session
	 */
	protected final void setStatus(int status) {
		this.sessionStatus = status;
	}
}
