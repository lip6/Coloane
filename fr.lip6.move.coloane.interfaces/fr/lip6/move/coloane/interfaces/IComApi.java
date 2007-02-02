package fr.lip6.move.coloane.interfaces;

import java.util.Vector;

/**
 * Services proposes par le module de communication de Coloane a l'API
 */
public interface IComApi {
	
	/** Demande de service a la plateforme FrameKit */
	public void askForService(String rootMenuName, String parentName, String serviceName);
	
	/** Affichage d'un message dans la console d'historique */
	public void printHistoryMessage(String message);
	
	/** Affichage d'un message dans la console d'etats */
	public void printStateMessage(String message);
	
	/** Affichage des menus */
	public void drawMenu(Vector<IRootMenuCom> menu);
	
	/** Mise a jour des menus */
	public void updateMenu();
	
	/** Affichage de boites de dialogue */
	public void drawDialog(IDialogCom dialog);
	
	/** Transmet le modele */
	public IModel getModel();
	
	/** Met a jour l'etat de fraicheur du modele */
	public void setModelDirty(boolean state);
	
	/** Indique l'etat de fraicheur du modele */
	public boolean getDirtyState();
	
	/** Indique la date de mise a jour du modele */
	public int getDateModel();
	
	/** Construit un nouveau modele dans un nouvel editeur */
	public void setNewModel(IModel model); 
	
	/** Destruction de toutes les sessions */
	public void closeAllSessions();	
	
	/** Affichage des messages de service de FK */
	public void setUiMessage(int type, String text, int specialType);
	
	/** Affichage des resultats suite a un appel de service */
	public void printResults();
	
	public void setResults(String serviceName, IResultsCom resultsCom);
}
