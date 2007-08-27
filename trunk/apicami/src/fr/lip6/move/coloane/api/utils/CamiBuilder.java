package fr.lip6.move.coloane.api.utils;


import fr.lip6.move.coloane.api.exceptions.UnexpectedCamiCommand;
import fr.lip6.move.coloane.api.main.Api;
import fr.lip6.move.coloane.interfaces.objects.DialogCom;
import fr.lip6.move.coloane.interfaces.objects.IDialogCom;
import fr.lip6.move.coloane.interfaces.objects.IMenuCom;
import fr.lip6.move.coloane.interfaces.objects.IRootMenuCom;
import fr.lip6.move.coloane.interfaces.objects.MenuCom;
import fr.lip6.move.coloane.interfaces.objects.RootMenuCom;

import java.util.Iterator;
import java.util.Vector;

/**
 * Constructeur d'objets a partir de commandes CAMI
 */
public final class CamiBuilder {

	private CamiBuilder() {	}

	/**
	 * Traduction CAMI -> MENU
	 *
	 * @param Vector Les commandes CAMI
	 * @return L'objet IRootMenuCom contenant tous les sous-menus
	 * @throws UnexpectedCAMICommand si le vecteur de commandes contient une mauvaise commande
	 * @see RootMenuCom
	 */

	public static IRootMenuCom buildMenu(Vector<Vector<String>> camiVec) throws UnexpectedCamiCommand {
		Iterator it = camiVec.iterator();
		Vector camiCmd = (Vector) it.next();

		// On verifie que le vecteur n'est pas vide
		if (camiVec.size() == 0) {
			Api.getLogger().warning("Le menu recu (CAMI) est vide");
			throw new UnexpectedCamiCommand("Le menu est vide");
		}

		// Le nom du menu est contenu dans un CQ. Il doit donc apparaitre en premier
		if (!camiCmd.get(0).equals("CQ")) {
			Api.getLogger().warning("Le vecteur ne contient pas CQ en premier");
			throw new UnexpectedCamiCommand("Le vecteur ne contient pas CQ en premier");
		}

		// Definition du menu racine
		IRootMenuCom rootMenu = new RootMenuCom((String) camiCmd.get(1));

		// Construction du menu (Parcours des elements du vecteur)
		while (it.hasNext()) {
			camiCmd = (Vector) it.next();

			// Si c'est une fin de service
			if (camiCmd.get(0).equals("FQ")) {
				break;
			}

			if (camiCmd.size() == 0) {
				Api.getLogger().warning("Le menu est vide (aucun element AQ)");
				throw new UnexpectedCamiCommand("Le menu est vide (aucun element AQ)");
			} else if (!camiCmd.get(0).equals("AQ")) {
				Api.getLogger().warning("Le menu ne contient pas d'element AQ en premiere position");
				throw new UnexpectedCamiCommand("Le menu ne contient pas d'element AQ en premiere position (" + camiCmd.get(0) + " lu)");
			}

			// Analyse de la commande AQ
			String serviceFather = camiCmd.get(1).toString();
			String serviceName = camiCmd.get(2).toString();

			IMenuCom smenu = new MenuCom(serviceName, serviceFather, false);
			rootMenu.addMenu(smenu);
		}
		return rootMenu;
	}

	/**
	 * Traduction CAMI -> Boite de Dialogue
	 *
	 * @param camiVec Vector contenant le CAMI
	 * @return l'objet DialogCom traduit du CAMI definissant une boite de dialogue
	 * @throws UnexpectedCAMICommand si le vecteur de commandes contient une mauvaise commande
	 * @see DialogCom
	 */
	public static IDialogCom buildDialog(Vector camiVec) throws UnexpectedCamiCommand {
		Iterator it = camiVec.iterator();
		Vector camiCmd = (Vector) it.next();

		// On veirife que le vecteur n'est pas vide
		if (camiVec.size() == 0) { throw new UnexpectedCamiCommand("La boite de dialogue est mal definie");	}
		// La premiere commande attendue est un DC
		if (!camiCmd.get(0).equals("DC")) {	throw new UnexpectedCamiCommand("La boite de dialogue est mal definie" + camiCmd.get(0) + "a la place de DC");	}

		camiCmd = (Vector) it.next();

		// Commande suivante attendue : CE
		if (!camiCmd.get(0).equals("CE")) {
			throw new UnexpectedCamiCommand("La boite de dialogue est mal definie" + camiCmd.get(0) + " a la place de CE");
		} else if (camiCmd.size() != 10) {
			throw new UnexpectedCamiCommand("La boite de dialogue est mal definie: 10 parametres sont attendus.........");
		}

		// On commande a parser les resultats
		// Identifiant de la boite de dialogue
		int id;
		if (camiCmd.get(1) == null) { throw new UnexpectedCamiCommand("Identifiant de la boite de dialogue est nul"); }
		id = Integer.parseInt(camiCmd.get(1).toString());

		// Type de la boite de dialogue (Standart, Warning, Error, Interactif)
		if (camiCmd.get(2) == null) { throw new UnexpectedCamiCommand("Type de la boite de dialogue est nul"); }

		int type;
		int paramType = Integer.parseInt(camiCmd.get(2).toString());
		switch (paramType) {
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
				throw new UnexpectedCamiCommand("Type de la boite de dialogue invalide");
		}

		// Nombre de boutons dans la boite de dialogue
		// (1 = 0 bouton, 2 = 1 bouton, 3 = 2 boutons)
		if (camiCmd.get(3) == null) { throw new UnexpectedCamiCommand("Nombre de boutons de dialogue incorrect"); }

		int nbButtons;
		int paramButton = Integer.parseInt(camiCmd.get(3).toString());
		switch (paramButton) {
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
				throw new UnexpectedCamiCommand("Nombre de boutons de la boite de dialogue invalide");
		}

		// Titre de la boite de dialogue
		if (camiCmd.get(4) == null) { throw new UnexpectedCamiCommand("Titre de la boite de dialogue nul"); }
		String title = camiCmd.get(4).toString();

		// Message d'aide
		if (camiCmd.get(5) == null) { throw new UnexpectedCamiCommand("Message d'aide nul"); }
		String helpMsg = camiCmd.get(5).toString();

		// Message
		if (camiCmd.get(6) == null) { throw new UnexpectedCamiCommand("Message principal nul");	}
		String msg = camiCmd.get(6).toString();


		int allowedEntry;
		if (camiCmd.get(7) == null) {
			throw new UnexpectedCamiCommand("Indicateur de saisie nul");
		} else if (camiCmd.get(7).toString().equals("1")) {
			allowedEntry = IDialogCom.INPUT_AUTHORIZED;
		} else if (camiCmd.get(7).toString().equals("2")) {
			allowedEntry = IDialogCom.INPUT_FORBIDDEN;
		} else if (camiCmd.get(7).toString().equals("5")) {
			allowedEntry = IDialogCom.INPUT_AND_ABORT_AUTHORIZED;
		} else {
			throw new UnexpectedCamiCommand("Indicateur de saisie invalide");
		}

		int select;
		if (camiCmd.get(8) == null) {
			throw new UnexpectedCamiCommand("Indicateur de selection nul");
		} else if (camiCmd.get(8).toString().equals("1")) {
			select = IDialogCom.MULTI_LINE_WITH_SINGLE_SELECTION;
		} else if (camiCmd.get(8).toString().equals("2")) {
			select = IDialogCom.SINGLE_LINE;
		} else if (camiCmd.get(8).toString().equals("5")) {
			select = IDialogCom.MULTI_LINE_WITH_MULTI_SELECTION;
		} else {
			throw new UnexpectedCamiCommand("Indicateur de selection nul");
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
				if (camiCmd.get(2) == null) { throw new UnexpectedCamiCommand("Contenu de la boite de dialogue nul"); }
				contents.add(camiCmd.get(2).toString());
			// Dans tous les autres cas
			} else {
				throw new UnexpectedCamiCommand("Commande inconnue dans la construction d'une boite de dialogue");
			}
		}

		// Creation de l'objet boite de dialogue
		IDialogCom dialog = new DialogCom(id);
		dialog.setButtonType(type);
		dialog.setDefault(contents.get(0));
		dialog.setButtonType(nbButtons);
		dialog.setHelp(helpMsg);
		dialog.setTitle(title);
		dialog.setMessage(msg);
		dialog.setMultiLine(select);
		dialog.setInputType(allowedEntry);
		return dialog;
	}
}
