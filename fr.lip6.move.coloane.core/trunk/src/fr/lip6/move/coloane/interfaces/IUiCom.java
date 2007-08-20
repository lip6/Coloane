package fr.lip6.move.coloane.interfaces;

import fr.lip6.move.coloane.communications.objects.Results;
import fr.lip6.move.coloane.interfaces.objects.IUpdateMenuCom;
import fr.lip6.move.coloane.menus.RootMenu;

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

	void setResults(String serviceName, Results result);

	void printResults();
}
