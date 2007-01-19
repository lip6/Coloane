package fr.lip6.move.coloane.interfaces;

import fr.lip6.move.coloane.menus.RootMenu;

public interface IUiCom {
	
	/** Affichage d'un message dans la console "Historique" */
	public void printHistoryMessage(String message);
	
	/** Affichage d'un message dans la console "Historique" */
	public void printStateMessage(String message);
	
	/** Affichage des menus */
	public void drawMenu(RootMenu menu);
	
	/** Mise a jour des menus */
	public void updateMenu();
}
