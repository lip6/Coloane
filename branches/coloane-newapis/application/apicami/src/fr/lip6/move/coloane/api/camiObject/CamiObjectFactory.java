package fr.lip6.move.coloane.api.camiObject;

import fr.lip6.move.coloane.api.camiObject.menu.IQuestion;
import fr.lip6.move.coloane.api.camiObject.menu.Question;
import fr.lip6.move.coloane.api.camiObject.menu.SubMenu;
import fr.lip6.move.coloane.api.interfaces.IUpdateItem;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;

/**
 * Factory du package CamiObject
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 */
public final class CamiObjectFactory {

	/**
	 * Constructeur privé
	 */
	private CamiObjectFactory() { }

	/**
	 * Création d'un objet ISessionInfo
	 * @param nameService TODO A documenter
	 * @param aboutService TODO A documenter
	 * @param incremental TODO A documenter
	 * @param resultatCalcule TODO A documenter
	 * @return L'objet contenant les informations sur la session
	 */
	public static ISessionInfo getNewFkInfo(String nameService, String aboutService, String incremental, int resultatCalcule) {
		return new SessionInfo(aboutService, incremental, nameService, resultatCalcule);
	}

	/**
	 * Retourne un nouveau menu racine
	 * @param name Le nom du menu racine
	 * @param type Le type de ce menu
	 * @param behavior Le comportement du menu
	 * @param visibility Le visibilité du menu
	 * @return le menu racine fraîchement créé
	 * @see IQuestion
	 */
	public static ISubMenu getNewRootMenu(String name, int type, int behavior, boolean visibility) {
		return new SubMenu(name, type, behavior, visibility);
	}

	/**
	 * Construction d'une nouvelle question
	 * @param parent Le parent de la question courante
	 * @param name Le nom de la question courante
	 * @param type Le type de la question courante
	 * @param behavior Le comportement de la question courante
	 * @param valid La question est-elle valide ?
	 * @param dialogAllowed Les boites de dialogues sont-elles autorisées ?
	 * @param stopAuthorized Peut-on interrompre le service ?
	 * @param outputFormalism Formalisme de réponse
	 * @param visibility Le menu est-il visible ?
	 * @return Une nouvelle question
	 */
	public static IQuestion getNewQuestion(String parent, String name, int type, int behavior, boolean valid, boolean dialogAllowed, boolean stopAuthorized,String outputFormalism, boolean visibility) {
		return new Question(parent, name, type, behavior, valid, dialogAllowed, stopAuthorized, outputFormalism, visibility);
	}

	/**
	 * nous crée un IUpdateItem.
	 * @param rootName
	 * @param serviceName
	 * @param state
	 * @return IUpdateItem
	 */
	public static IUpdateItem getNewUpdateItem(String rootName, String serviceName, boolean state){
		return new UpdateItem(rootName, serviceName, state);
	}
}
