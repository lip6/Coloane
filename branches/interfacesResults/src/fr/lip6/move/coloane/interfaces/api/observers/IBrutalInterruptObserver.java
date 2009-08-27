package fr.lip6.move.coloane.interfaces.api.observers;

/**
 * Cette interface définie un observeur pour l'événement: récéption d'une erreur.
 */
public interface IBrutalInterruptObserver {

	/**
	 * Met a jour l'observateur d'evenement :  reception d'une erreur
	 * @param e l'objet qui represent l'erreur
	 */
	void update(String e);
}
