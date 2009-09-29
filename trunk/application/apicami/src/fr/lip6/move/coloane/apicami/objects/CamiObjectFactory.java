package fr.lip6.move.coloane.apicami.objects;

import fr.lip6.move.coloane.apicami.objects.menu.IQuestion;
import fr.lip6.move.coloane.apicami.objects.menu.Question;
import fr.lip6.move.coloane.apicami.objects.menu.SubMenu;
import fr.lip6.move.coloane.apicami.objects.menu.UpdateMenu;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;

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
		return new SubMenu(name, type, behavior, visibility, "/" + name);
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
	public static IQuestion getNewQuestion(String parent, String name, int type, int behavior, boolean valid, boolean dialogAllowed, boolean stopAuthorized, String outputFormalism, boolean visibility) {
		return new Question(parent, name, type, behavior, valid, dialogAllowed, stopAuthorized, outputFormalism, visibility);
	}

	/**
	 * Construction d'un modificateur de menu
	 * @param rootName Le nom du menu racine
	 * @param serviceName Le nom du service
	 * @param state L'état de l'item de menu
	 * @return L'objet de modification sur le menu
	 */
	public static IUpdateMenu getNewUpdateMenu(String rootName, String serviceName, boolean state) {
		return new UpdateMenu(rootName, serviceName, state);
	}
}
