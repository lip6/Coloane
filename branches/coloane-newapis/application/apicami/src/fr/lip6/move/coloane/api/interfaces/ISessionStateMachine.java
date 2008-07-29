package fr.lip6.move.coloane.api.interfaces;

/**
 * Cette interface décrit l'automate d'états associé à chaque session
 */
public interface ISessionStateMachine {

	/** L'état initial de l'automate qui correspond a une session créée */
     int INITIAL_STATE = 0;

	/** Etat correspondant à l'attente des menus et updates */
	int WAITING_FOR_MENUS_AND_UPDATES_STATE = 1;

	/** Etat correspondant à une session ovuerte */
	int IDLE_STATE = 2;

	/** Etat correspondant à l'attente de la suspension de session */
	int WAITING_FOR_SUSPEND_SESSION_STATE = 3;

	/** Etat correspondant à une session suspendue */
	int SUSPEND_SESSION_STATE = 4;

	/** Etat correspondant à l'attente de reprise de session */
	int WAITING_FOR_RESUME_SESSION_STATE = 5;

	/** Etat correspondant à l'attente de fin de session */
	int WAITING_FOR_CLOSE_SESSION_STATE = 6;

	/** Etat correspondant à une session fermée */
	int CLOSE_SESSION_STATE = 0; // Le même que INITIAL_STATE


	/** TODO : A documenter */
	int WAITING_FOR_MODEL_STATE = 9;

	/** TODO : A documenter */
	int WAITING_FOR_RESULT_STATE = 10;

	/** aprés une invalidation de modele , on se met en attente de updates */
	int WAITING_FOR_UPDATES_STATE = 11;

	/** aprés une invalidation de modele , ce dernier est sale */
	int MODELE_SALE_STATE = 12;

	/**
	 * @return l'état courant de la session
	 */
	int getState();

    /**
     * Positionne la session dans l'etat WAITING_FOR_MENUS_AND_UPDATES_STATE, si possible.
     * @return <code>true</code> si l'opération est réussie
     */
    boolean setWaitingForUpdatesAndMenusState();

    /**
     * Positionne la session dans l'etat IDLE_STATE, si possible.
     * @return <code>true</code> si l'opération est réussie
     */
    boolean setIdleState();

    /**
     * Positionne la session dans l'etat WAITING_FOR_SUSPEND_SESSION_STATE, si possible.
     * @return <code>true</code> si l'opération est réussie
     */
    boolean setWaitingForSuspendSessionState();

    /**
     * Positionne la session dans l'etat SUSPEND_SESSION_STATE, si possible.
     * @return <code>true</code> si l'opération est réussie
     */
    boolean setSuspendSessionState();

    /**
     * Positionne la session dans l'etat WAITING_FOR_RESUME_SESSION_STATE, si possible.
     * @return <code>true</code> si l'opération est réussie
     */
    boolean setWaitingForResumeSessionState();

    /**
     * Positionne la session dans l'etat WAITING_FOR_CLOSE_SESSION_STATE, si possible.
     * @return <code>true</code> si l'opération est réussie
     */
	boolean setWaitingForCloseSessionState();

	/**
	 * Positionne la session dans l'etat CLOSE_SESSION_STATE, si possible.
     * @return <code>true</code> si l'opération est réussie
	 */
	boolean closeSessionState();

	/**
	 * Positionne la session dans l'etat WAITING_FOR_MODEL_STATE, si possible.
     * @return <code>true</code> si l'opération est réussie
	 */
	boolean setWaitingForModelState();

	/**
	 * Positionne la session dans l'etat WAITING_FOR_RESULT_STATE, si possible.
     * @return <code>true</code> si l'opération est réussie
	 */
	boolean setWaitingForResultState();

	/**
	 * Positionne la session dans l'etat MODELE_SALE_STATE, si possible.
     * @return <code>true</code> si l'opération est réussie
	 */
	boolean setModeleSaleState();

	/**
	 * Positionne la session dans l'etat WAITING_FOR_UPDATES_STATE, si possible.
     * @return <code>true</code> si l'opération est réussie
	 */
	boolean setWaitingForUpdatesState();

}
