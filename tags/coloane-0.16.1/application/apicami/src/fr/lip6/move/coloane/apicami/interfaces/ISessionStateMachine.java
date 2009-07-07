package fr.lip6.move.coloane.apicami.interfaces;

/**
 * Cette interface décrit l'automate d'états associé à chaque session
 */
public interface ISessionStateMachine {

	/** L'état initial de l'automate qui correspond a une session créée mais pas encore ouverte... */
	int INITIAL_STATE = 0;

	/** Etat correspondant à une session ouverte */
	int IDLE_STATE = 1;

	/** Etat correspondant à l'attente de la suspension de session */
	int BUSY_STATE = 2;

	/** Etat correspondant à une session suspendue */
	int SUSPEND_STATE = 3;

	/**
	 * @return l'état courant de la session
	 */
	int getCurrentState();

	/**
	 * Indique que la session est en cours d'utilisation
	 */
	void setBusy();

	/**
	 * Indique que la session est disponible
	 */
	void setIdle();

	/**
	 * Indique que la session est suspendue
	 */
	void setSuspend();

	/**
	 * Indique que la session est deconnectée mais prête à être connectée
	 */
	void setInitial();
}
