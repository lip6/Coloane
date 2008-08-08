package fr.lip6.move.coloane.apiws.interfaces.session;

/**
 * Cette interface repéresente les états de l'automate représentant le cycle de vie d'une session.
 * Cette interface contient également les méthodes pour interagir sur cette automate.
 *
 * @author Monir CHAOUKI
 */
public interface ISessionStateMachine {

	/**
	 * Etat initiale de l'automate
	 * Cela correspond a une session creeer
	 */
	int INITIAL_STATE = 0;

	/**
	 * Etat attent menu
	 * Cela correspond a l'attente des menu
	 */
	int WAITING_FOR_MENUS_AND_UPDATES_STATE = 1;

	/**
	 * Etat repos
	 * Cela correspond a l'etat a session ouverte
	 */
	int IDLE_STATE = 2;


	/**
	 * Etat session suspendu
	 * Cela correspond a l etat de la suspension de la session .
	 */
	int SUSPEND_SESSION_STATE = 3;

	/**
	 * Etat attend resultat-reponse
	 * Cela correspond a l'attente des resultat
	 */
	int WAITING_FOR_RESULT_STATE = 4;

	/**
	 * Etat attend resultat-fermeture
	 */
	int WAITING_FOR_CLOSE_SESSION_STATE = 5;

	/**
	 * Etat session fermer
	 * Cela correspond a la fin de la session
	 */
	int CLOSE_SESSION_STATE = 99;

	/**
	 * Recupere l'etat courrant de la session dans l'automate
	 * @return l'etat courrant de la session dans l'automate
	 */
    int getState();

    /**
     * Initialise l'etat courrant de la session dans l'automate
     * @param state le nouvelle état de la session dans l'automate
     */
    void setState(int state);

    /**
     * Aller a l'etat WAITING_FOR_MENUS_AND_UPDATES_STATE, si possible
     * @return true, si possible d'aller vers WAITING_FOR_MENUS_AND_UPDATES_STATE, false sinon
     */
    boolean goToWaitingForUpdatesAndMenusState();

    /**
     * Aller a l'etat IDLE_STATE, si possible
     * @return true, si possible d'aller vers IDLE_STATE, false sinon
     */
    boolean goToIdleState();

    /**
     * Aller a l'etat SUSPEND_SESSION_STATE, si possible
     * @return true, si possible d'aller vers SUSPEND_SESSION_STATE, false sinon
     */
    boolean goToSuspendSessionState();

    /**
     * Aller a l'etat WAITING_FOR_RESULT_SESSION_STATE, si possible
     * @return true, si possible d'aller vers WAITING_FOR_RESULT_SESSION_STATE, false sinon
     */
    boolean goToWaitingForResultState();

    /**
     * Aller a l'etat WAITING_FOR_CLOSE_SESSION_STATE, si possible
     * @return true, si possible d'aller vers WAITING_FOR_CLOSE_SESSION_STATE, false sinon
     */
    boolean goToWaitingForCloseSessionState();

    /**
     * Aller a l'etat CLOSE_SESSION_STATE, si possible
     * @return true, si possible d'aller vers CLOSE_SESSION_STATE, false sinon
     */
    boolean goToCloseSessionState();

}
