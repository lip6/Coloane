package fr.lip6.move.coloane.api.cami;

import fr.lip6.move.coloane.api.camiObject.CamiObjectFactory;
import fr.lip6.move.coloane.api.camiObject.Dialog;
import fr.lip6.move.coloane.api.camiObject.menu.IQuestion;
import fr.lip6.move.coloane.api.camiObject.menu.Service;
import fr.lip6.move.coloane.api.camiObject.menu.SubMenu;
import fr.lip6.move.coloane.api.interfaces.IResult;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;
import fr.lip6.move.coloane.interfaces.objects.service.IService;

import java.util.ArrayList;
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
		ISessionInfo kfi = CamiObjectFactory.getNewFkInfo(aboutService, incremental, nameService, resultatCalcule);

		return kfi;
	}

	/**
	 * Construction de la racine d'un menu
	 * @param root Une liste de valeur de commandes définissant le menu racine
	 * @return La description du menu (qui devra être passée aux autres méthodes)
	 */
	public static ISubMenu buildRootMenu(List<String> root) {
		String name = root.get(0);
		int type = (root.get(1) != null)?Integer.parseInt(root.get(1)):-1;
		int behavior  = (root.get(2) != null)?Integer.parseInt(root.get(2)):-1;
		return (CamiObjectFactory.getNewRootMenu(name, type, behavior, true));
	}

	/**
	 * Construction des menus de services
	 * @param root Le menu racine contenant toutes les questions transmises
	 * @param questions La liste des questions transmises par la plate-forme
	 * @return Le menu racine entièrement construit
	 * TODO : Transformer les conditionnelles raccourcies en (une seule ?) méthode privée
	 */
	public static ISubMenu buildMenus(ISubMenu root, List<IQuestion> questions) {
		// Parcours de toutes les questions
		for (IQuestion question : questions) {
			((SubMenu) root).addQuestion(root, question);
		}
		return root;
	}

	/**
	 * Construction de la liste des services installés et accessibles sur la plate-forme
	 * @param root Le menu racine auquel est attaché la liste de services
	 * @param questions Les questions transmises par la plate-forme
	 * @return la liste des services
	 */
	public static List<IService> buildServices(ISubMenu root, List<IQuestion> questions) {
		// La liste des services installés surla plate-forme
		List<IService> services = new ArrayList<IService>();

		for (IQuestion question : questions) {
			IService newService = new Service(question, root.getName());
			services.add(newService);
		}
		return services;
	}

	/**
	 * Constructeur des modificateurs de menu
	 * @param camiUpdateItems L'ensemble des éléments pour modifier le menu
	 * @return Une liste de modification à entreprendre sur le menu
	 * TODO : Transformer les conditionnelles raccourcies en méthode privée
	 */
	public static List<IUpdateMenu> buildUpdateItem(List<List<String>> camiUpdateItems) {
		List<IUpdateMenu> list = new ArrayList<IUpdateMenu>();

		// Parcours de tous les éléments envoyés par la plate-forme
		for (List<String> tq : camiUpdateItems) {
			String rootName = tq.get(0);
			String serviceName = tq.get(1);
			boolean state = (tq.get(2) != null)?computeBoolean(Integer.valueOf(tq.get(2)), 7, true):true;

			// Construction de l'objet modificateur de menu
			IUpdateMenu update = CamiObjectFactory.getNewUpdateMenu(rootName, serviceName, state);
			list.add(update);
		}
		return list;
	}

	/*
	 * on construit la boite de dialogue
	 * @param camiDialog les elements necessaires pour cette construction
	 */
	public static IDialog buildDialog(List<String> camiDialog) {
		int id;
		int type;
		int buttonType;
		String title;
		String help;
		String message;
		int inputType;
		int multiLine;
		String defaut;
		IDialog dialog;
		List<String> list = new ArrayList<String>();
		id = Integer.parseInt(camiDialog.get(0));
		type = Integer.parseInt(camiDialog.get(1));
		buttonType = Integer.parseInt(camiDialog.get(2));
		title = camiDialog.get(3);
		help = camiDialog.get(4);
		message = camiDialog.get(5);
		inputType = Integer.parseInt(camiDialog.get(6));
		multiLine = Integer.parseInt(camiDialog.get(7));
		defaut = camiDialog.get(8);
		if (defaut == null) {
			defaut = "";
		}
		if (camiDialog.size() != 9) {
			for (int i = 9; i < camiDialog.size(); i++) {
				list.add(camiDialog.get(i));
			}
		}
		dialog = new Dialog(id, type, buttonType, title, help, message, inputType, multiLine, defaut, list);
		return dialog;
	}


	public static IResult buildResult(ArrayList<String> camiResult) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Extraction des informations fournies par la plate-forme en une liste de questions.<br>
	 * Les questions comportent les informations pour construire les menus, et les services.
	 * @param camiMenu La liste des commandes de construction des questions fournie par la plate-forme
	 * @return Une liste de questions
	 */
	public static List<IQuestion> buildQuestions(List<List<String>> camiMenu) {
		// La liste des questions en construction
		List<IQuestion> questions = new ArrayList<IQuestion>();

		// Parcours de toutes les commandes AQ
		for (List<String> aq : camiMenu) {
			String parentName = aq.get(0);
			String name = aq.get(1);
			int questionType = (aq.get(2) != null)?Integer.parseInt(aq.get(2)):-1;
			int questionBehavior  = (aq.get(3) != null)?Integer.parseInt(aq.get(3)):-1;
			boolean valid = (aq.get(4) != null)?computeBoolean(Integer.valueOf(aq.get(4)), 1, true):false;
			boolean dialogAllowed = (aq.get(5) != null)?computeBoolean(Integer.valueOf(aq.get(5)), 1, false):true;
			boolean stopAuthorized = (aq.get(6) != null)?computeBoolean(Integer.valueOf(aq.get(6)), 1, false):true;
			String outputFormalism = aq.get(7);
			boolean activate = (aq.get(8) != null)?computeBoolean(Integer.valueOf(aq.get(8)), 1, true):false;

			// Construction de la question
			IQuestion question = CamiObjectFactory.getNewQuestion(parentName, name, questionType, questionBehavior, valid, dialogAllowed, stopAuthorized, outputFormalism, activate);
			questions.add(question);
		}
		return questions;
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
