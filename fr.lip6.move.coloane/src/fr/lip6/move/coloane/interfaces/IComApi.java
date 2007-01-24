package fr.lip6.move.coloane.interfaces;

import fr.lip6.move.coloane.communications.models.Model;
import fr.lip6.move.coloane.menus.RootMenu;

public interface IComApi {
	
	/** Demande de service a la plateforme FrameKit */
	public void askForService(String rootMenuName, String parentName, String serviceName);
	
	/** Affichage d'un message dans la console d'historique */
	public void printHistoryMessage(String message);
	
	/** Affichage d'un message dans la console d'etats */
	public void printStateMessage(String message);
	
	/** Affichage des menus */
	public void drawMenu(RootMenu menu);
	
	/** Mise a jour des menus */
	public void updateMenu();
	
	/** Transmet le modele */
	public Model getModel();
	
	/** Met a jour l'etat de fraicheur du modele */
	public void setModelDirty(boolean state);
	
	/** Indique l'etat de fraicheur du modele */
	public boolean getDirtyState();
	
	/** Indique la date de mise a jour du modele */
	public int getDateModel();
}
