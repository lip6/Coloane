package fr.lip6.move.coloane.interfaces;

import fr.lip6.move.coloane.menus.RootMenu;

public interface IComApi {
	
	/** Demande de service a la plateforme FrameKit */
	public void askForService(String rootMenuName, String parentName, String serviceName);
	
	/** Affichage d'un message dans la console d'historique */
	public void printHistoryMessage(String message);
	
	/** Affichage des menus */
	public void drawMenu(RootMenu menu);
}
