package fr.lip6.move.coloane.interfaces.api.observers;

public interface IBrutalInterruptObserver {
	/**
	 * Met a jour l'observateur d'evenement :  reception d'une erreur
	 * @param e l'objet qui represent l'erreur
	 */
	public void update(String e);
}
