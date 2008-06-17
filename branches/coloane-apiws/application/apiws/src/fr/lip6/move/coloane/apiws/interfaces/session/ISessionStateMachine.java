package fr.lip6.move.coloane.apiws.interfaces.session;

public interface ISessionStateMachine {

	/**
	 * Etat initiale de l'automate
	 * Cela correspond a une session creeer
	 */
	static final int INITIAL_STATE = 0;

	/**
	 * Etat attent menu
	 * Cela correspond a l'attente des menu
	 */
	static final int WAITING_FOR_MENUS_AND_UPDATES_STATE = 1;

	/**
	 * Etat repos
	 * Cela correspond a l'etat a session ouverte
	 */
	static final int IDLE_STATE = 2;


	/**
	 * Etat session suspendu
	 * Cela correspond a l etat de la suspension de la session .
	 */
	static final int SUSPEND_SESSION_STATE = 3;


	/**
	 * Etat attend resultat-reponse
	 * Cela correspond a l'attente des resultat
	 */
	static final int WAITING_FOR_RESULT_SESSION_STATE = 4;
	
	
	/**
	 * Etat session fermer
	 * Cela correspond a la fin de la session
	 */
	static final int CLOSE_SESSION_STATE = 99;
	
	
	/**
	 * Recupere l'etat courrant de la session dans l'automate
	 * @return l'etat courrant de la session dans l'automate
	 */
    public int getState();
    
    
    /**
     * Aller a l'etat WAITING_FOR_MENUS_AND_UPDATES_STATE, si possible
     * @return true, si possible d'aller vers WAITING_FOR_MENUS_AND_UPDATES_STATE, false sinon
     */
    public boolean goToWaitingForUpdatesAndMenusState();
    
    
    /**
     * Aller a l'etat IDLE_STATE, si possible
     * @return true, si possible d'aller vers IDLE_STATE, false sinon
     */
    public boolean goToIdleState();
    
    
    /**
     * Aller a l'etat SUSPEND_SESSION_STATE, si possible
     * @return true, si possible d'aller vers SUSPEND_SESSION_STATE, false sinon
     */
    public boolean goToSuspendSessionState();
    
    
    /**
     * Aller a l'etat WAITING_FOR_RESULT_SESSION_STATE, si possible
     * @return true, si possible d'aller vers WAITING_FOR_RESULT_SESSION_STATE, false sinon
     */
    public boolean goToWaitingForResumeSessionState();
    
    
    /**
     * Aller a l'etat CLOSE_SESSION_STATE, si possible
     * @return true, si possible d'aller vers CLOSE_SESSION_STATE, false sinon
     */
    public boolean goToCloseSessionState();
    
}
