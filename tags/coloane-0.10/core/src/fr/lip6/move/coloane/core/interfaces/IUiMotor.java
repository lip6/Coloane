package fr.lip6.move.coloane.core.interfaces;

public interface IUiMotor {

	/**
	 * Permet la modification des menus de Coloane permettant l'authentification
	 * @param authentication Est-ce que l'authentification est OK ?
	 * @param session Est-ce que une session est ouverte ?
	 */
	void platformState(boolean authentication, int session);

	/**
	 * Demande de reaffichage des menus<br>
	 * Rien ne garantie que les menus ont ete effectivement modifie...
	 */
	void redrawMenus();

	/**
	 * Desactivation du menu principal (lors d'un appel de service par exemple)
	 * @param rootName Le nom du menu root
	 * @param Le nouveau status a appliquer a ce menu
	 */
	void changeRootMenuStatus(String rootName, boolean status);

}
