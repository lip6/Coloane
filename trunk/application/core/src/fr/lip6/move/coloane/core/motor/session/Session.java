package fr.lip6.move.coloane.core.motor.session;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.menus.RootMenu;
import fr.lip6.move.coloane.core.results.ResultTreeList;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

/**
 * Definition d'une session
 */
public class Session implements ISession {

	/** Le modele associe */
	private IModelImpl sessionModel;

	/** Le ResultTreeList associé */
	private ResultTreeList serviceResults;

	/** Nom de la session */
	private String sessionName;

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
		this.sessionModel = null;
		this.sessionStatus = ISession.CLOSED;
		this.serviceResults = new ResultTreeList();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISession#suspend()
	 */
	public final void suspend() {
		Coloane.getLogger().finer("Suspension de la session " + sessionName); //$NON-NLS-1$
		if (sessionStatus == ISession.CONNECTED) {
			sessionStatus = ISession.SUSPENDED;
		}
		Coloane.getLogger().finer("Etat de la session " + sessionName + " = " + sessionStatus); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISession#resume()
	 */
	public final void resume() {
		Coloane.getLogger().finer("Reprise de la session " + sessionName + " (Etat actuel : " + sessionStatus + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		if (sessionStatus == ISession.SUSPENDED) {
			sessionStatus = ISession.CONNECTED;
		}
		Coloane.getLogger().finer("Etat de la session " + sessionName + " = " + sessionStatus); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISession#getName()
	 */
	public final String getName() {
		return sessionName;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISession#getModel()
	 */
	public final IModelImpl getModel() {
		return this.sessionModel;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISession#setModel(fr.lip6.move.coloane.core.ui.model.IModelImpl)
	 */
	public final void setModel(IModelImpl model) {
		this.sessionModel = model;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISession#getAdminMenu()
	 */
	public final RootMenu getAdminMenu() {
		return adminMenu;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISession#setAdminMenu(fr.lip6.move.coloane.core.menus.RootMenu)
	 */
	public final void setAdminMenu(RootMenu admin) {
		this.adminMenu = admin;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISession#getServicesMenu()
	 */
	public final RootMenu getServicesMenu() {
		return sessionMenu;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISession#setServicesMenu(fr.lip6.move.coloane.core.menus.RootMenu)
	 */
	public final void setServicesMenu(RootMenu root) {
		this.sessionMenu = root;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISession#getStatus()
	 */
	public final int getStatus() {
		return sessionStatus;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISession#setStatus(int)
	 */
	public final void setStatus(int status) {
		this.sessionStatus = status;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.session.ISession#getServiceResults()
	 */
	public final ResultTreeList getServiceResults() {
		return serviceResults;
	}
}
