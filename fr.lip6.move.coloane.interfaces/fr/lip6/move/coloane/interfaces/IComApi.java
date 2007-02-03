package fr.lip6.move.coloane.interfaces;

import java.util.Vector;

/**
 * Services proposes par le module de communication de Coloane a une API de communication
 */
public interface IComApi {
	
	/**
	 * Affichage d'un message dans la console d'historique de Coloane
	 * @param message Le texte qui doit etre affiche
	 */
	public void printHistoryMessage(String message);
	
	/**
	 * Affichage des menus envoyes par la plate-forme
	 * @param menuList Ensemble des menus a afficher
	 * @see IRootMenuCom
	 */
	public void drawMenu(Vector<IRootMenuCom> menuList);
	
	/**
	 * Mise a jour du contenu des fichiers.
	 * Chaque appel de service, ou plus simplement chaque action sur la plate-forme
	 * peut provoquer la mise a jour des menus affiches par Coloane. Ces modifications
	 * doivent etre prise en compte et repercutees sur Coloane
	 */
	public void updateMenu();
	
	/**
	 * Affichage des boites de dialogues envoyees par la plate-forme
	 * @param dialog Un objet contenant toutes les informations pour construire une boite de dialogue
	 * @see IDialogCom
	 */
	public void drawDialog(IDialogCom dialog);
	
	/**
	 * Fourni le modele en cours d'edition
	 * @return L'interface sur le modele en cours d'edition
	 * @see IModelCom
	 */
	public IModelCom getModel();
	
	/**
	 * Met a jour l'etat de fraicheur du modele.
	 * Apres un service, le modele est frais...
	 * @param state Le nouvel etat du modele
	 */
	public void setModelDirty(boolean state);
	
	/**
	 * Indique l'etat de fraicheur du modele
	 * @return TRUE si le modele est sale, FALSE si le modele est PROPRE
	 */
	public boolean getDirtyState();
	
	/**
	 * Indique la date de derniere mise a jour du modele
	 * @return La date de derniere mise a jour du modele 
	 */
	public int getDateModel();
	
	/**
	 * Certains services implique la creation d'un nouveau modele dans Coloane
	 * Cette methode fourni le nouveau modele a afficher par Coloane
	 * @param model Le nouveau modele a construire
	 * @see IModelCom
	 */
	public void setNewModel(IModelCom model); 
	
	/** 
	 * Affichage des messages de service de FK.
	 * La plate-forme peut envoye un certain nombre de messages purement indicatif :
	 * (erreur, warnings etc...) a afficher sur le client.
	 * @param type Le type du message
	 * @param text Le message en lui-même
	 * @param specialType Un indicateur de messages speciaux fournis par la plate-forme
	 */
	public void setUiMessage(int type, String text, int specialType);
	
	/** 
	 * Affichage des resultats suite a un appel de service
	 * Les resultats sont envoyes auparavant gracce a la methode setResults
	 */
	public void printResults();
	
	/**
	 * Envoi des resultats fournis par la plate-forme
	 * @param serviceName Le nom du service qui envoie les resultats
	 * @param resultsCom Les resultats
	 * @see IResultsCom
	 */
	public void setResults(String serviceName, IResultsCom resultsCom);
	
	/**
	 * Destruction de toutes les sessions du cote Coloane
	 */
	public void closeAllSessions();	
}
