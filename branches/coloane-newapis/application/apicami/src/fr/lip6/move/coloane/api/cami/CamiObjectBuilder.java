package fr.lip6.move.coloane.api.cami;

import fr.lip6.move.coloane.api.camiObject.CamiObjectFactory;
import fr.lip6.move.coloane.api.camiObject.Dialog;
import fr.lip6.move.coloane.api.camiObject.menu.IQuestion;
import fr.lip6.move.coloane.api.camiObject.menu.Service;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.service.IService;

import java.util.List;

/**
 * Cette classe est chargée de la construction d'objets compatibles avec Coloane
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 * @author Jean-Baptiste Voron
 */
public final class CamiObjectBuilder {

	/**
	 * Constructeur
	 */
	private CamiObjectBuilder() { }

	/**
	 * Construit l'objet ISessionInfo avec les informations en provenance du parser
	 * @param camiFKInfo Les éléments qui permettent la construction
	 * @return Les informations sur la session
	 */
	public static ISessionInfo buildSessionInfo(List<String> camiFKInfo) {
		String aboutService = camiFKInfo.get(0);
		String incremental = camiFKInfo.get(1);
		String nameService = camiFKInfo.get(2);
		int resultatCalcule = Integer.valueOf(camiFKInfo.get(3));
		ISessionInfo sessionInfo = CamiObjectFactory.getNewFkInfo(aboutService, incremental, nameService, resultatCalcule);
		return sessionInfo;
	}

	/**
	 * Construction de la racine d'un menu
	 * @param name Le nom du menu racine
	 * @param questionType Le type du menu racine
	 * @param questionBehavior Le comportement du menu racine
	 * @return La description du menu
	 */
	public static ISubMenu buildRootMenu(String name, String questionType, String questionBehavior) {
		int type = handleNullValue(questionType, Integer.parseInt(questionType), -1);
		int behavior  = handleNullValue(questionBehavior, Integer.parseInt(questionBehavior), -1);
		return (CamiObjectFactory.getNewRootMenu(name, type, behavior, true));
	}

	/**
	 * Extraction des informations fournies par la plate-forme concernant la description d'une question.<br>
	 * Les questions comportent les informations pour construire les menus, et les services.
	 * @param description La liste des informations de construction de la question fournie par la plate-forme
	 * @return La question décrite
	 * TODO : Transformer les conditionnelles raccourcies en méthode privée
	 */
	public static IQuestion buildQuestion(List<String> description) {
		String parentName = description.get(0);
		String name = description.get(1);
		int questionType = handleNullValue(description.get(2), Integer.parseInt(description.get(2)), -1);
		int questionBehavior  = handleNullValue(description.get(3), Integer.parseInt(description.get(3)), -1);
		boolean valid = handleNullValue(description.get(4), computeBoolean(Integer.valueOf(description.get(4)), 1, true), false);
		boolean dialogAllowed = handleNullValue(description.get(5), computeBoolean(Integer.valueOf(description.get(5)), 1, false), true);
		boolean stopAuthorized = handleNullValue(description.get(6), computeBoolean(Integer.valueOf(description.get(6)), 1, false), true);
		String outputFormalism = description.get(7);
		boolean activate = handleNullValue(description.get(8), computeBoolean(Integer.valueOf(description.get(8)), 1, true), false);

		// Construction de la question
		IQuestion question = CamiObjectFactory.getNewQuestion(parentName, name, questionType, questionBehavior, valid, dialogAllowed, stopAuthorized, outputFormalism, activate);
		return question;
	}

	/**
	 * Constructeur d'un modificateur de menu
	 * @param description La liste des informations de construction du modificateur fournie par la plate-forme
	 * @return La descriptino de la modification à entreprendre sur le menu
	 * TODO : Transformer les conditionnelles raccourcies en méthode privée
	 */
	public static IUpdateMenu buildUpdate(List<String> description) {
		String rootName = description.get(0);
		String serviceName = description.get(1);
		boolean state = handleNullValue(description.get(2), computeBoolean(Integer.valueOf(description.get(2)), 7, true), true);

		// Construction de l'objet modificateur de menu
		IUpdateMenu update = CamiObjectFactory.getNewUpdateMenu(rootName, serviceName, state);
		return update;
	}

	/**
	 * Construction de la liste des services installés et accessibles sur la plate-forme
	 * @param root Le menu racine auquel est attaché la liste de services
	 * @param question Les questions transmises par la plate-forme
	 * @return la liste des services
	 */
	public static IService buildService(ISubMenu root, IQuestion question) {
		IService service = new Service(question, root.getName());
		return service;
	}

	/**
	 * Construction d'une boite de dialogue à l'aide des informations transmises par la plate-forme
	 * @param description La description de la boite de dialogue
	 * @return La boite de dialogue construite
	 */
	public static IDialog buildDialog(List<String> description) {
		int id = Integer.parseInt(description.get(0));
		int type = Integer.parseInt(description.get(1));
		int buttonType = Integer.parseInt(description.get(2));
		String title = description.get(3);
		String help = description.get(4);
		String message = description.get(5);
		int inputType = Integer.parseInt(description.get(6));
		int multiLine = Integer.parseInt(description.get(7));
		String defaultValue = description.get(8);
		if (defaultValue == null) {
			defaultValue = "";
		}

		IDialog dialog = new Dialog(id, type, buttonType, title, help, message, inputType, multiLine, defaultValue);
		return dialog;
	}

	/**
	 * Construction d'un résultat à partir des informations transmises par la plate-forme
	 * @param description La description du resultat
	 * @return Le résultat
	 */
	public static IResult buildResult(List<String> description) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Détermine la valeur résultat si l'objet est <code>null</code>
	 * @param <T> Le type de l'objet résultat attendu (il dépend des paramètres)
	 * @param obj L'objet qui doit être testé
	 * @param ifTrue La valeur si l'objet n'est pas <code>null</code>
	 * @param ifFalse La valeur si l'objet est <code>null</code>
	 * @return L'objet
	 */
	private static <T> T handleNullValue(final Object obj, final T ifTrue, final T ifFalse) {
		if (obj != null) {
			return ifTrue;
		} else {
			return ifFalse;
		}
	}

	/**
	 * Calcule une valeur booléenne en fonction d'un paramètre et de deux choix
	 * @param value La valeur effective
	 * @param compare La valeur qui sert d'étalon
	 * @param success résultat si la valeur effective est égale à la valeur étalon
	 * @return <code>true</code> si valeur effective == valeur étalon; <code>false</code> sinon
	 */
	private static boolean computeBoolean(int value, int compare, boolean success) {
		if (value == compare) {
			return success;
		} else {
			return !success;
		}
	}
}
