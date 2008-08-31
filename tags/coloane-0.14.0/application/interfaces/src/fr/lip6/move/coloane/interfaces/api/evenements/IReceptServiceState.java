package fr.lip6.move.coloane.interfaces.api.evenements;

/**
 * Cette interface définie l'objet représentant une information sur le service en cours d'exécution
 * à envoyer aux observateurs d'événements: récéption d'informations le service en cours d'exécution.
 */
public interface IReceptServiceState {

	/** Etat du service: inactif */
	int IDLE_STATE = 1;

	/** Etat du service: actif */
	int ACTIVE_STATE = 2;

	/** Etat du service: terminé */
	int FINISHED_STATE = 3;

	/** Etat du service: stoppé */
	int STOPPED_STATE = 4;

	/** Etat du service: en arriére plan */
	int BACKGROUND_STATE = 5;

	/** Etat du service: erreur d'excution */
	int ERROR_STATE = 6;

	/** Etat du service: élément acitvé */
	int ELEMENT_ACTIVE_STATE = 7;

	/** Etat du service: élément désactivé */
	int ELEMENT_INACTIVE_STATE = 8;

	/**
	 * Récupére le message sur l'état du service en exécution.
	 * @return Le message sur l'état du service en exécution.
	 */
	String getMessage();

	/**
	 * Récupére le nom du service en exécution.
	 * @return Le nom du service en exécution.
	 */
	String getServiceName();

	/**
	 * Récupére l'état du service en exécution.
	 * @return L'état du service en exécution.
	 */
	int getState();

}
