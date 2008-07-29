package fr.lip6.move.coloane.apiws.interfaces.observer;

/**
 * Cette interface définie un observateur d'événement: récéption d'une 'FATAL ERROR' de la part du wrapper
 */
public interface IMyReceptErrorObserver {

	/**
	 * Met a jour l'observateur d'evenement : récéption d'une 'FATAL ERROR' de la part du wrapper
	 * @param e Le message d'erreur reçu
	 */
	void update(String e);
}
