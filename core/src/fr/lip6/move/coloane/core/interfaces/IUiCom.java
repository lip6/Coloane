package fr.lip6.move.coloane.core.interfaces;

import fr.lip6.move.coloane.core.menus.RootMenu;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.IUpdateMenuCom;
//import fr.lip6.move.coloane.interfaces.resultats.Results;

import java.util.Vector;

public interface IUiCom {

	/** Affichage d'un message dans la console "Historique" */
	void printHistoryMessage(String message);

	/** Affichage des menus */
	void drawMenu(RootMenu menu);

	/** Mise a jour des menus */
	void updateMenu(Vector<IUpdateMenuCom> updates);

	/** Suppression d'un menu */
	void removeMenu(String menuName);

	/** Desactivation des menus (lors d'un appel de service par exemple) */
	void changeMenuStatus(String rootName, boolean status);

	void setResults(String serviceName, IResultsCom result);

	void printResults();

	/**
	 * Permet la modification des menus de Coloane permettant l'authentification
	 * @param authentication Est-ce que l'authentification est OK ?
	 * @param session Est-ce que une session est ouverte ?
	 */
	void platformState(boolean authentication, int session);
}
