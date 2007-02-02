package fr.lip6.move.coloane.interfaces;

import fr.lip6.move.coloane.communications.objects.Result;
import fr.lip6.move.coloane.menus.RootMenu;
import fr.lip6.move.coloane.ui.dialogs.Dialog;

public interface IUiCom {
	
	/** Affichage d'un message dans la console "Historique" */
	public void printHistoryMessage(String message);
	
	/** Affichage d'un message dans la console "Historique" */
	public void printStateMessage(String message);
	
	/** Affichage des menus */
	public void drawMenu(RootMenu menu);
	
	/** Affichage des boites de dialogue */
	public void drawDialog(Dialog dialog);
	
	/** Mise a jour des menus */
	public void updateMenu();
	
	/** Suppression d'un menu */
	public void removeMenu(String menuName);
	
	/** Desactivation des menus (lors d'un appel de service par exemple) */
	public void changeMenuStatus(String rootName, boolean status);
	
	public void setResults(String serviceName, Result result);
	
	public void printResults();
}
