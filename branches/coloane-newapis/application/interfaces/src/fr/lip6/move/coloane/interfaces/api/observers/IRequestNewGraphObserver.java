package fr.lip6.move.coloane.interfaces.api.observers;

/**
 * Cette interface définie un observeur pour l'événement: demande d'un nouveau modèle vide.
 */
public interface IRequestNewGraphObserver {

	/**
	 * @param formalism Le formalisme que doit instancier le graphe
	 */
	void update(String formalism);
}
