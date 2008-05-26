package fr.lip6.move.coloane.api.interfaces;

/**
 * cette interface represente l'automate de la session.
 * @author kahoo && uu.
 *
 */
public interface ISessionStateMachine {

	/** l'état initial de l'automate.
	 * qui correspond a une session créée,cf automates d'etat de la session.
	 */
     static final int INITIAL_STATE = 0;

	/**
	 * ca correspond a l'attente des menus et updates,cf automates d'etat de la session.
	 */
	static final int WAITING_FOR_MENUS_AND_UPDATES_STATE = 1;


	/**
	 * ca correspond a session ouverte .
	 */
	static final int IDLE_STATE = 2;

	/**
	 * ca correspond a lattente de la suspension de la session .
	 */
	static final int WAITING_FOR_SUSPEND_SESSION_STATE = 3;


	/**
	 * ca correspond a letat de la suspension de la session .
	 */
	static final int SUSPEND_SESSION_STATE = 4;


	/**
	 * ca correspond a lattente de la reprise de la session .
	 */
	static final int WAITING_FOR_RESUME_SESSION_STATE = 5;

	/**
	 * ca correspond a lattente de la reprise de la fin de la session .
	 */
	static final int WAITING_FOR_CLOSE_SESSION_STATE = 6;


	/**
	 * ca correspond a la fin de la session .
	 */
	static final int CLOSE_SESSION_STATE = 7;
	/**
	 * nous retourne l'etat de notre session.
	 * @return int
	 */
    public int getState();


    /**
     * nous positionne l'etat WAITING_FOR_MENUS_AND_UPDATES_STATE, si possible.
     * @return boolean
     */
    public boolean setWaitingForUpdatesAndMenusState();

    /**
     * nous positionne l'etat IdleState, si possible.
     * @return boolean
     */
    public boolean setIdleState();


    /**
     * nous positionne l'etat WAITING_FOR_SUSPEND_SESSION_STATE, si possible.
     * @return boolean
     */
    public boolean setWaitingForSuspendSessionState();

    /**
     * nous positionne SUSPEND_SESSION_STATE, si possible.
     * @return boolean
     */
    public boolean setSuspendSessionState();


    /**
     * nous positionne l'etat WAITING_FOR_RESUME_SESSION_STATE, si possible.
     * @return boolean
     */
    public boolean setWaitingForResumeSessionState();


	public boolean setWaitingForCloseSessionState();

	public boolean CloseSessionState();

}
