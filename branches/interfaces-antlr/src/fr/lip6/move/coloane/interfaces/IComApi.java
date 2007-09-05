package fr.lip6.move.coloane.interfaces;

import fr.lip6.move.coloane.interfaces.model.IModel;

import fr.lip6.move.coloane.interfaces.objects.IDialogCom;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.IRootMenuCom;
import fr.lip6.move.coloane.interfaces.objects.IUpdateMenuCom;

import java.util.Vector;

/**
 * Services proposes par le module de communication de Coloane a une API de communication
 */
public interface IComApi {

	/**
	 * Affichage d'un message dans la console d'historique de Coloane
	 * @param message Le texte qui doit etre affiche
	 */
	void printHistoryMessage(String message);

	/**
	 * Affichage des menus envoyes par la plate-forme
	 * @param menu Le menu a afficher
	 * @see IRootMenuCom
	 */
	void drawMenu(IRootMenuCom menu);

	/**
	 * Mise a jour du contenu des menus
	 * Chaque appel de service, ou plus simplement chaque action sur la plate-forme
	 * peut provoquer la mise a jour des menus affiches par Coloane. Ces modifications
	 * doivent etre prise en compte et repercutees sur Coloane
	 * @param updates Les mises a jour a effectuer sur les menus
	 */
	void updateMenu(Vector<IUpdateMenuCom> updates);

	/**
	 * Affichage des boites de dialogues envoyees par la plate-forme
	 * @param dialog Un objet contenant toutes les informations pour construire une boite de dialogue
	 * @see IDialogCom
	 */
	void drawDialog(IDialogCom dialog);

	/**
	 * Fourni le modele en cours d'edition
	 * @return L'interface sur le modele en cours d'edition
	 * @see IModel
	 */
	IModel sendModel();

	/**
	 * Indique l'etat de fraicheur du modele
	 * @return TRUE si le modele est sale, FALSE si le modele est PROPRE
	 */
	boolean getDirtyState();

	/**
	 * Indique la date de derniere mise a jour du modele
	 * @return La date de derniere mise a jour du modele
	 */
	int getDateModel();

	/**
	 * Certains services implique la creation d'un nouveau modele dans Coloane
	 * Cette methode fourni le nouveau modele a afficher par Coloane
	 * @param model Le nouveau modele a construire
	 * @see IModel
	 */
	void setNewModel(IModel model);

	/**
	 * Affichage des messages de service de FK.
	 * La plate-forme peut envoye un certain nombre de messages purement indicatif :
	 * (erreur, warnings etc...) a afficher sur le client.
	 * @param type Le type du message
	 * @param text Le message en lui-meme
	 * @param specialType Un indicateur de messages speciaux fournis par la plate-forme
	 */
	void setUiMessage(int type, String text, int specialType);

	/**
	 * Affichage des resultats suite a un appel de service
	 * Les resultats sont envoyes auparavant gracce a la methode setResults
	 */
	void printResults();

	/**
	 * Envoi des resultats fournis par la plate-forme
	 * @param serviceName Le nom du service qui envoie les resultats
	 * @param resultsCom Les resultats
	 * @see IResultsCom
	 */
	void setResults(String serviceName, IResultsCom resultsCom);

	/**
	 * Destruction de toutes les sessions du cote Coloane
	 */
	void closeAllSessions();
}
