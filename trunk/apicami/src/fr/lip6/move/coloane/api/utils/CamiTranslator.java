package fr.lip6.move.coloane.api.utils;


import fr.lip6.move.coloane.api.exceptions.UnexpectedCamiCommand;
import fr.lip6.move.coloane.api.log.LogsUtils;
import fr.lip6.move.coloane.api.main.Api;
import fr.lip6.move.coloane.api.objects.MenuCom;
import fr.lip6.move.coloane.api.objects.RootMenuCom;
import fr.lip6.move.coloane.interfaces.objects.DialogCom;
import fr.lip6.move.coloane.interfaces.objects.IDialogCom;
import fr.lip6.move.coloane.interfaces.objects.IMenuCom;
import fr.lip6.move.coloane.interfaces.objects.IRootMenuCom;

import java.util.Iterator;
import java.util.Vector;

/**
 * Constructeur d'objets a partir de commandes CAMI
 */
public class CamiTranslator {

	private LogsUtils logsUtils;
	/**
	 * Constructeur
	 */
	public CamiTranslator() {
		super();
		logsUtils = new LogsUtils();
	}

	/**
	 * Permet de traduire du CAMI vers l'objet MenuCom
	 *
	 * @param camiVectype filter text
	 *            Vector de Vector contenant le CAMI
	 * @return L'objet RootMenuCom contenant tous les sous-menus
	 * @throws UnexpectedCAMICommand
	 *             si camiVec contient une mauvaise commande
	 * @see RootMenuCom
	 */

	public final IRootMenuCom getMenu(Vector camiVec) throws UnexpectedCamiCommand {
		Api.apiLogger.entering("Camitranslator", "getMenu", camiVec);
		IRootMenuCom rootMenu;
		Vector camiCmd;

		Iterator it = camiVec.iterator();

		// On verifie que le vecteur n'est pas vide
		if (camiVec.size() == 0) {
			UnexpectedCamiCommand ucc = new UnexpectedCamiCommand("Le menu est vide");
			Api.apiLogger.throwing("CamiTranslator", "getMenu",
					ucc);
			Api.apiLogger.warning(ucc.getMessage() + logsUtils.stackToString(ucc));
			throw ucc;
		}

		camiCmd = (Vector) it.next();

		// Le nom du menu est contenu dans un CQ. Il doit donc apparaitre en
		// premier
		if (!camiCmd.get(0).equals("CQ")) {
			UnexpectedCamiCommand cmd = new UnexpectedCamiCommand("Le vecteur ne contient pas CQ en premier");
			Api.apiLogger.throwing("CamiTranslator", "getMenu", cmd);
			Api.apiLogger.warning(cmd.getMessage() + logsUtils.stackToString(cmd));
			throw cmd;
		}

		// Definition du menu racine
		rootMenu = new RootMenuCom((String) camiCmd.get(1));

		// Construction du menu (Parcours des elements du vecteur)
		while (it.hasNext()) {
			camiCmd = (Vector) it.next();

			// Si c'est une fin de service
			if (camiCmd.get(0).equals("FQ")) {
				break;
			}

			if (camiCmd.size() == 0) {
				UnexpectedCamiCommand cmd = new UnexpectedCamiCommand("Le menu est vide (aucun element AQ)");
				Api.apiLogger.throwing("CamiTranslator", "getMenu", cmd);
				Api.apiLogger.warning(cmd.getMessage() + logsUtils.stackToString(cmd));
				throw cmd;
			} else if (!camiCmd.get(0).equals("AQ")) {
				UnexpectedCamiCommand cmd = new UnexpectedCamiCommand("Le menu ne contient pas d'element AQ en premiere position");
				Api.apiLogger.throwing("CamiTranslator", "getMenu", cmd);
				Api.apiLogger.warning(cmd.getMessage() + logsUtils.stackToString(cmd));
				throw cmd;
			}

			// Analyse de la commande AQ
			String serviceFather = camiCmd.get(1).toString();
			String serviceName = camiCmd.get(2).toString();

			// boolean serviceActive = false;
			// if (camiCmd.get(9) != null &&
			// (Integer.parseInt(camiCmd.get(9).toString()) == 1)) {
			// serviceActive = true;
			// }

			IMenuCom smenu = new MenuCom(serviceName, serviceFather, false);
			rootMenu.addMenu(smenu);

			// boolean serviceSuspensible = false;
			// if (camiCmd.get(7) != null &&
			// (Integer.parseInt(camiCmd.get(7).toString()) == 2)) {
			// serviceSuspensible = true;
			// }
			//
			// boolean dialogueAllwd = false;
			// if (camiCmd.get(6) != null &&
			// (Integer.parseInt(camiCmd.get(6).toString()) == 2)) {
			// dialogueAllwd = true;
			// }
			//
			// boolean defaultValid = false;
			// if (camiCmd.get(5) != null &&
			// (Integer.parseInt(camiCmd.get(5).toString()) == 1)) {
			// defaultValid = true;
			// }
			//
			// String resFormalism = "";
			// if (camiCmd.get(8) != null) {
			// resFormalism = camiCmd.get(8).toString();
			// }
		}
		Api.apiLogger.exiting("CamiTranslator", "getMenu", rootMenu);
		return rootMenu;
	}

	/**
	 * Permet de traduire du CAMI vers l'objet DialogCom
	 *
	 * @param camiVec
	 *            camiVec est un Vector contenant le CAMI
	 * @return l'objet DialogCom traduit du CAMI definissant une boite de
	 *         dialogue
	 * @throws UnexpectedCAMICommand
	 *             si camiVec contient une mauvaise commande
	 * @see DialogCom
	 */
	public final IDialogCom getDialog(Vector camiVec) throws UnexpectedCamiCommand {
		Api.apiLogger.entering("CamiTranslator", "getDialog", camiVec);
		IDialogCom dialog = null;
		Vector camiCmd;

		Iterator it = camiVec.iterator();
		camiCmd = (Vector) it.next();

		// La premiere commande attendue est un DC
		if (camiVec.size() == 0) {
			UnexpectedCamiCommand cmd = new UnexpectedCamiCommand("La boite de dialogue est mal definie");
			Api.apiLogger.throwing("CamiTranslator", "getDialog", cmd);
			Api.apiLogger.warning(cmd.getMessage() + logsUtils.stackToString(cmd));
			throw cmd;
		} else if (!camiCmd.get(0).equals("DC")) {
			UnexpectedCamiCommand cmd = new UnexpectedCamiCommand("La boite de dialogue est mal definie" + camiCmd.get(0) + "a la place de DC");
			Api.apiLogger.throwing("CamiTranslator", "getDialog", cmd);
			Api.apiLogger.warning(cmd.getMessage() + logsUtils.stackToString(cmd));
			throw cmd;
		}

		camiCmd = (Vector) it.next();

		// Commande suivante attendue : CE
		if (!camiCmd.get(0).equals("CE")) {
			UnexpectedCamiCommand e = new UnexpectedCamiCommand("La boite de dialogue est mal definie" + camiCmd.get(0) + " a la place de CE");
			Api.apiLogger.throwing("CamiTranslator", "getDialog",  e);
			Api.apiLogger.warning(e.getMessage() + logsUtils.stackToString(e));
			throw e;
		} else if (camiCmd.size() != 10) {
			UnexpectedCamiCommand e = new UnexpectedCamiCommand("La boite de dialogue est mal definie: 10 parametres sont attendus.........");
			Api.apiLogger.throwing("CamiTranslator", "getDialog", e);
			Api.apiLogger.warning(e.getMessage() + logsUtils.stackToString(e));
			throw e;
		}

		// On commande a parser les resultats

		// Identifiant de la boite de dialogue
		int id;
		if (camiCmd.get(1) == null) {
			UnexpectedCamiCommand e = new UnexpectedCamiCommand("Identifiant de la boite de dialogue est nul");
			Api.apiLogger.throwing("CamiTranslator", "getDialog", e);
			Api.apiLogger.warning(e.getMessage() + logsUtils.stackToString(e));
			throw e;
		}
		id = Integer.parseInt(camiCmd.get(1).toString());

		// Type de la boite de dialogue (Standart, Warning, Error, Interactif)
		int ttype, type;
		if (camiCmd.get(2) == null) {
			UnexpectedCamiCommand e = new UnexpectedCamiCommand("Type de la boite de dialogue est nul");
			Api.apiLogger.throwing("CamiTranslator", "getDialog", e);
			Api.apiLogger.warning(e.getMessage() + logsUtils.stackToString(e));
			throw e;
		}
		ttype = Integer.parseInt(camiCmd.get(2).toString());
		switch (ttype) {
			case 1:
				type = IDialogCom.DLG_STANDARD;
				break;
			case 2:
				type = IDialogCom.DLG_WARNING;
				break;
			case 3:
				type = IDialogCom.DLG_ERROR;
				break;
			case 4:
				type = IDialogCom.DLG_INTERACTIVE;
				break;
			default:
				UnexpectedCamiCommand e = new UnexpectedCamiCommand("Type de la boite de dialogue invalide");
				Api.apiLogger.throwing("CamiTranslator", "getDialog", e);
				Api.apiLogger.warning(e.getMessage() + logsUtils.stackToString(e));
				throw e;
		}

		// Nombre de boutons dans la boite de dialogue
		// (1=0bouton,2=1bouton,3=2boutons)
		int tnbButtons, nbButtons;
		if (camiCmd.get(3) == null) {
			UnexpectedCamiCommand e = new UnexpectedCamiCommand("Nombre de boutons de dialogue incorrect");
			Api.apiLogger.throwing("CamiTranslator", "getDialog", e);
			Api.apiLogger.warning(e.getMessage() + logsUtils.stackToString(e));
			throw e;
		}
		tnbButtons = Integer.parseInt(camiCmd.get(3).toString());
		switch (tnbButtons) {
			case 1:
				nbButtons = IDialogCom.DLG_NO_BUTTON;
				break;
			case 2:
				nbButtons = IDialogCom.DLG_OK;
				break;
			case 3:
				nbButtons = IDialogCom.DLG_OK_CANCEL;
				break;
			default:
				UnexpectedCamiCommand e = new UnexpectedCamiCommand("Nombre de boutons de la boite de dialogue invalide");
				Api.apiLogger.throwing("CamiTranslator", "getDialog", e);
				Api.apiLogger.warning(e.getMessage() + logsUtils.stackToString(e));
				throw e;
		}

		// Titre de la boite de dialogue
		String title;
		if (camiCmd.get(4) == null) {
			UnexpectedCamiCommand e = new UnexpectedCamiCommand("Titre de la boite de dialogue nul");
			Api.apiLogger.throwing("CamiTranslator", "getDialog", e);
			Api.apiLogger.warning(e.getMessage() + logsUtils.stackToString(e));

			throw e;
		}
		title = camiCmd.get(4).toString();
		Api.apiLogger.finer("TITRE :" + title);
		//System.out.println("TITLE :" + title);

		// Message d'aide
		String helpMsg;
		if (camiCmd.get(5) == null) {
			UnexpectedCamiCommand e = new UnexpectedCamiCommand("Message d'aide nul");
			Api.apiLogger.throwing("CamiTranslator", "getDialog", e);
			Api.apiLogger.warning(e.getMessage() + logsUtils.stackToString(e));
			throw e;
		}
		helpMsg = camiCmd.get(5).toString();

		// Message
		String msg;
		if (camiCmd.get(6) == null) {
			UnexpectedCamiCommand e = new UnexpectedCamiCommand("Message principal nul");
			Api.apiLogger.throwing("CamiTranslator", "getDialog", e);
			Api.apiLogger.warning(e.getMessage() + logsUtils.stackToString(e));
			throw e;
		}
		msg = camiCmd.get(6).toString();

		int allowedEntry;

		if (camiCmd.get(7) == null) {
			UnexpectedCamiCommand e = new UnexpectedCamiCommand("Indicateur de saisie nul");
			Api.apiLogger.throwing("CamiTranslator", "getDialog", e);
			Api.apiLogger.warning(e.getMessage() + logsUtils.stackToString(e));
			throw e;
		} else if (camiCmd.get(7).toString().equals("1")) {
			allowedEntry = IDialogCom.INPUT_AUTHORIZED;
		} else if (camiCmd.get(7).toString().equals("2")) {
			allowedEntry = IDialogCom.INPUT_FORBIDDEN;
		} else if (camiCmd.get(7).toString().equals("5")) {
			allowedEntry = IDialogCom.INPUT_AND_ABORT_AUTHORIZED;
			Api.apiLogger.warning("Abort command is not available yet...");
			//System.err.println("Abort command is not available yet...");
		} else {
			UnexpectedCamiCommand e = new UnexpectedCamiCommand("Indicateur de saisie invalide");
			Api.apiLogger.throwing("CamiTranslator", "getDialog", e);
			Api.apiLogger.warning(e.getMessage() + logsUtils.stackToString(e));
			throw e;
		}

		int select;
		if (camiCmd.get(8) == null) {
			UnexpectedCamiCommand e = new UnexpectedCamiCommand("Indicateur de selection nul");
			Api.apiLogger.throwing("CamiTranslator", "getDialog", e);
			Api.apiLogger.warning(e.getMessage() + logsUtils.stackToString(e));
			throw e;
		} else if (camiCmd.get(8).toString().equals("1")) {
			select = IDialogCom.MULTI_LINE_WITH_SINGLE_SELECTION;
		} else if (camiCmd.get(8).toString().equals("2")) {
			select = IDialogCom.SINGLE_LINE;
		} else if (camiCmd.get(8).toString().equals("5")) {
			select = IDialogCom.MULTI_LINE_WITH_MULTI_SELECTION;
		} else {
			UnexpectedCamiCommand e = new UnexpectedCamiCommand("Indicateur de selection nul");
			Api.apiLogger.throwing("CamiTranslator", "getDialog", e);
			Api.apiLogger.warning(e.getMessage() + logsUtils.stackToString(e));
			throw e;
		}

		// Certains contenus peuvent etre transmis par FK a la boite de dialogue
		Vector<String> contents = new Vector<String>();
		while (it.hasNext()) {
			camiCmd = (Vector) it.next();

			// Dans le cas d'une fin de boite de dialogue
			if (camiCmd.get(0).equals("FF")) {
				break;

				// Dans le cas d'une insertion de contenu
			} else if (camiCmd.get(0).equals("DS")) {
				if (camiCmd.get(2) == null) {
					UnexpectedCamiCommand e = new UnexpectedCamiCommand("Contenu de la boite de dialogue nul");
					Api.apiLogger.throwing("CamiTranslator", "getDialog", e);
					Api.apiLogger.warning(e.getMessage() + logsUtils.stackToString(e));
					throw e;
				}
				contents.add(camiCmd.get(2).toString());
			} else {
				UnexpectedCamiCommand e = new UnexpectedCamiCommand("Commande inconnue dans la construction d'une boite de dialogue");
				Api.apiLogger.throwing("CamiTranslator", "getDialog", e);
				Api.apiLogger.warning(e.getMessage() + logsUtils.stackToString(e));
				throw e;
			}
		}

		// Creation de l'objet boite de dialogue
		dialog = new DialogCom(id);
		dialog.setButtonType(type);
		dialog.setDefault(contents.get(0));
		dialog.setHelp(helpMsg);
		dialog.setTitle(title);
		dialog.setMessage(msg);
		dialog.setMultiLine(select);
		dialog.setInputType(allowedEntry);
		Api.apiLogger.exiting("CamiTranslator", "getDialog", dialog);
		return dialog;
	}
}
