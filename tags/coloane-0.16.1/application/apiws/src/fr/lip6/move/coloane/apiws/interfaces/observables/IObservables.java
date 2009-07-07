package fr.lip6.move.coloane.apiws.interfaces.observables;

/**
 * Cette interface permet d'identifier les diffférents événements observables.
 *
 * @author Monir CHAOUKI
 */
public interface IObservables {

	/** Réception d'une boîte de dialogue */
	Integer RECEPT_DIALOG = 0;

	/** Réception d'un menu */
	Integer RECEPT_MENU = 1;

	/** Réception d'un message */
	Integer RECEPT_MESSAGE = 2;

	/** Réception d'un résultat */
	Integer RECEPT_RESULT = 3;

	/** Réception d'une erreur */
	Integer RECEPT_ERROR = 4;

	/** Déconnexion ordonnée */
	Integer DISCONNECT = 5;

	/** Réception d'une erreur */
	Integer BRUTAL_INTERRUPT = 6;

	/** Récéption d'une information sur un service */
	Integer RECEPT_SERVICE_STATE = 7;

	/** Demande un nouveau model */
	Integer REQUEST_NEW_GRAPH = 8;

	/**
	 * Récupére le type de l'observable
	 * @return le type de de l'observable
	 */
	Integer getTypeObservable();
}
