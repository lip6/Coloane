package fr.lip6.move.coloane.apiws.interfaces.evenements;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.objects.IMenu;

public interface IAnswerChangeSession {
	
	/**
	 * Recupere le formalism de la prochaine session
	 * @return le formalism de la prochaine session
	 */
	public String getFormalism();
	
	/**
	 * Recupere l'identifiant de la prochaine session
	 * @return l'identifiant de la prochaine session
	 */
	public String getIdSession();
	
	/**
	 * Recupere les menus des services disponible pour la prochaine session
	 * @return les menus des services disponible pour la prochaine session
	 */
	public ArrayList<IMenu> getMenus();

}
