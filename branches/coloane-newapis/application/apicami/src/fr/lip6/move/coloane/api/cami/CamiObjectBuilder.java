package fr.lip6.move.coloane.api.cami;

import java.util.ArrayList;
import java.util.List;

import fr.lip6.move.coloane.api.camiObject.AttributeModify;
import fr.lip6.move.coloane.api.camiObject.CamiObjectFactory;
import fr.lip6.move.coloane.api.camiObject.Dialog;
import fr.lip6.move.coloane.api.camiObject.menu.IQuestion;
import fr.lip6.move.coloane.api.camiObject.menu.SubMenu;
import fr.lip6.move.coloane.api.interfaces.IAttributeModify;
import fr.lip6.move.coloane.api.interfaces.IBox;
import fr.lip6.move.coloane.api.interfaces.IDomainTable;
import fr.lip6.move.coloane.api.interfaces.IObjectAttribute;
import fr.lip6.move.coloane.api.interfaces.IObjectDomainTable;
import fr.lip6.move.coloane.api.interfaces.IResult;
import fr.lip6.move.coloane.api.interfaces.IUpdateItem;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;

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
	 * Construction des menus de services
	 * @param camiMenu L'ensemble des éléments pour constuire le menu
	 * @return Le menu racine construit
	 * TODO : Transformer les conditionnelles raccourcies en méthode privée
	 */
	public static ISubMenu buildMenu(List<List<String>> camiMenu) {
		// Le menu racine de base...
		ISubMenu root = null;

		// Flag pour indiquer si on parcourt le premier menu (root)
		boolean isRoot = true;

		// Parcours de toutes les commandes AQ
		for (List<String> aq : camiMenu) {

			// Dans le cas du premier menu parcouru, on construit le menu ROOT
			if (isRoot) {
				String name = aq.get(0);
				int questionType = (aq.get(1) != null)?Integer.parseInt(aq.get(1)):-1;
				int questionBehavior  = (aq.get(2) != null)?Integer.parseInt(aq.get(2)):-1;
				root = CamiObjectFactory.getNewRootMenu(name, questionType, questionBehavior, true);
				isRoot = false;

			// Dans tous les autres cas...
			} else {
				String parentName = aq.get(0);
				String name = aq.get(1);
				int questionType = (aq.get(2) != null)?Integer.parseInt(aq.get(2)):-1;
				int questionBehavior  = (aq.get(3) != null)?Integer.parseInt(aq.get(3)):-1;
				boolean valid = (aq.get(4) != null)?computeBoolean(Integer.valueOf(aq.get(4)), 1, true):false;
				boolean dialogAllowed = (aq.get(5) != null)?computeBoolean(Integer.valueOf(aq.get(5)), 1, false):true;
				boolean stopAuthorized = (aq.get(6) != null)?computeBoolean(Integer.valueOf(aq.get(6)), 1, false):true;
				String outputFormalism = aq.get(7);
				boolean activate = (aq.get(8) != null)?computeBoolean(Integer.valueOf(aq.get(8)), 1, true):false;

				// Construction de la question et ajout au menu
				IQuestion question = CamiObjectFactory.getNewQuestion(parentName, name, questionType, questionBehavior, valid, dialogAllowed, stopAuthorized, outputFormalism, activate);
				((SubMenu) root).addQuestion(question);
			}
		}
		return root;
	}

	public static IArc buildArc(ArrayList<String> camiArc) {
		// TODO Auto-generated method stub
		return null;
	}

	public static IAttributeModify buildAttributeModify(
			ArrayList<String> CamiAttributeModify) {
		String attributeType = CamiAttributeModify.get(1);
		String newContent = CamiAttributeModify.get(2);
		int objectID = Integer.parseInt(CamiAttributeModify.get(3));
		IAttributeModify at = new AttributeModify(attributeType, newContent,
				objectID);
		return at;
	}

	public static IBox buildBox(ArrayList<String> camiBox) {
		// TODO Auto-generated method stub
		return null;
	}

	public static IDialog buildDialog(ArrayList<String> camiDialog) {
		
		int id;
		int type;
		int buttonType;
		String title;
		String help;
		String message;
        int inputType;
        int multiLine;
        String defaut;
        
      //  System.out.println(camiDialog.get(0));
        id = Integer.parseInt(camiDialog.get(0));
        //System.out.println("id" + id);
        //System.out.println(camiDialog.get(1));
        type = Integer.parseInt(camiDialog.get(1));
        //System.out.println("type"+type);
        //System.out.println(camiDialog.get(2));
        buttonType = Integer.parseInt(camiDialog.get(2));
        //System.out.println("boutton" + buttonType);
        //System.out.println(camiDialog.get(3));
        title = camiDialog.get(3);
        //System.out.println("titre"+title);
        //System.out.println(camiDialog.get(4));
        help = camiDialog.get(4);
        //System.out.println("help" + help);
        //System.out.println(camiDialog.get(5));
        message = camiDialog.get(5);
        //System.out.println("msg" + message);
        //System.out.println(camiDialog.get(6));
        inputType = Integer.parseInt(camiDialog.get(6));
        //System.out.println("inputtype" + inputType);
        //System.out.println(camiDialog.get(7));
        multiLine = Integer.parseInt(camiDialog.get(7));
       // System.out.println("multiline"+multiLine);
        //System.out.println(camiDialog.get(8));
        defaut= camiDialog.get(8);
        if (defaut == null) {
			defaut="";
		}
        //System.out.println("defaut"+defaut);
        IDialog dialog = new Dialog(id,type,buttonType, title,help,message,inputType,multiLine,defaut);
		return dialog;
	}

	public static IDomainTable buildDomainTable(
			ArrayList<String> camiDomainTable) {
		// TODO Auto-generated method stub
		return null;
	}
	



	public static IGraph buildModel(ArrayList<String> camiModel) {
		// TODO Auto-generated method stub
		return null;
	}

	public static INode buildNode(ArrayList<String> camiNode) {
		// TODO Auto-generated method stub
		return null;
	}

	public static IObjectAttribute buildObjectAttribute(
			ArrayList<String> camiObjectAttribute) {
		// TODO Auto-generated method stub
		return null;
	}

	public static IObjectDomainTable buildObjectDomainTable(
			ArrayList<String> camiObjectDomainTable) {
		// TODO Auto-generated method stub
		return null;
	}

	public static IResult buildResult(ArrayList<String> camiResult) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * nous genere les interfaces des TQ
	 *
	 * @param camiUpdateItem
	 * @return un tableau de TQ 7 & 8
	 */
	public static ArrayList<IUpdateItem> buildUpdateItem(
			ArrayList<ArrayList<String>> camiUpdateItem) {
		ArrayList<IUpdateItem> tab = new ArrayList<IUpdateItem>();
		for (ArrayList<String> tq : camiUpdateItem) {
			String rootName = tq.get(0);
			String serviceName = tq.get(1);
			String tmpState2 = tq.get(2);
			boolean state;
			if (tmpState2 != null) {
				int tmpState = Integer.parseInt(tmpState2);

				if (tmpState == 7) {
					state = true;
				} else {
					state = false;
				}
			} else {
				state = true;
			}
			IUpdateItem update = CamiObjectFactory.getNewUpdateItem(rootName, serviceName, state);
			tab.add(update);
		}
		return tab;
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
