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
     static final int INITIALE_STATE = 0;

	/**
	 * ca correspond a l'attente des menus et updates,cf automates d'etat de la session.
	 */
	static final int WAITING_FOR_MENUS_AND_UPDATES_STATE = 1;


	/**
	 * ca correspond a session ouverte .
	 */
	static final int IDLE_STATE = 2;

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
}
