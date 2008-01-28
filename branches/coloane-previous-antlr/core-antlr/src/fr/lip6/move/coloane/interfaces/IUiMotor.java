package fr.lip6.move.coloane.interfaces;

public interface IUiMotor {

	/**
	 * Permet la modification des menus de Coloane permettant l'authentification
	 * @param authentication Est-ce que l'authentification est OK ?
	 * @param session Est-ce que une session est ouverte ?
	 */
	void platformState(boolean authentication, int session);

	void redrawMenus();

}
