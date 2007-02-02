package fr.lip6.move.coloane.api.utils;

import java.util.Iterator;
import java.util.Vector;

import fr.lip6.move.coloane.api.exceptions.UnexpectedCamiCommand;
import fr.lip6.move.coloane.api.objects.DialogCom;
import fr.lip6.move.coloane.api.objects.RootMenuCom;
import fr.lip6.move.coloane.api.objects.ServiceCom;
import fr.lip6.move.coloane.interfaces.IDialog;

/**
 * Constructeur d'objets a partir de commandes CAMI
 */
public class CamiTranslator {

	/**
	 * Constructeur
	 * Rien a faire...
	 */
	public CamiTranslator() {
		super();
	}

	
	/**  
	 * Permet de traduire du CAMI vers l'objet Menu
	 * 
	 * @param camiVec Vector de Vector contenant le CAMI
	 * @return l'objet Menu traduit du CAMI
	 * @throws UnexpectedCAMICommand si jamais camiVec contient une mauvaise commande
	 */
	
	public RootMenuCom getMenu(Vector camiVec) throws UnexpectedCamiCommand {
		Iterator it = camiVec.iterator();
		
		RootMenuCom rootMenu;
		Vector camiCmd;
		
		// On verifie que le vecteur n'est pas vide
		if (camiVec.size() == 0) {
			throw new UnexpectedCamiCommand("Le menu est vide");
		} 
		
		camiCmd = (Vector) it.next();
		
		// Le nom du menu est contenu dans un CQ. Il doit donc apparaitre en premier
		if (!camiCmd.get(0).equals("CQ")) {
			throw new UnexpectedCamiCommand("Le vecteur ne contient pas CQ en premier");
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
				throw new UnexpectedCamiCommand("Le menu est vide (aucun element AQ)");
			} else if (!camiCmd.get(0).equals("AQ")) {
				throw new UnexpectedCamiCommand("Le menu ne contient pas d'element AQ en premiere position");
			}
			
			// Analyse de la commande AQ
			String serviceFather = camiCmd.get(1).toString();
			String serviceName = camiCmd.get(2).toString();
			
			boolean serviceActive = false;
			if (camiCmd.get(9) != null && (Integer.parseInt(camiCmd.get(9).toString()) == 1)) {
				serviceActive = true; 
			}
			
			rootMenu.addMenu(serviceName, serviceFather, false);
			
			boolean serviceSuspensible = false;
			if (camiCmd.get(7) != null && (Integer.parseInt(camiCmd.get(7).toString()) == 2)) {
				serviceSuspensible = true;
			}
			
			boolean dialogueAllwd = false;
			if (camiCmd.get(6) != null && (Integer.parseInt(camiCmd.get(6).toString()) == 2)) {
				dialogueAllwd = true;
			}
			
			boolean defaultValid = false;
			if (camiCmd.get(5) != null && (Integer.parseInt(camiCmd.get(5).toString()) == 1)) {
				defaultValid = true;
			}
			
			String resFormalism = "";
			if (camiCmd.get(8) != null) {
				resFormalism = camiCmd.get(8).toString();
			}
			
			//Creation d'un service
			new ServiceCom(serviceName, serviceActive, serviceSuspensible, dialogueAllwd, defaultValid, resFormalism);
		
		}
		
		return rootMenu;
	}
	
	/**
	 * permet de traduire du CAMI vers l'objet WindowedDialogue
	 * @param camiVec camiVec est un Vector contenant le CAMI
	 * @return l'objet WindowedDialogue traduit du CAMI
	 * @throws UnexpectedCAMICommand si jamais camiVec contient une mauvaise commande
	 */
	public DialogCom getDialog(Vector camiVec)  throws UnexpectedCamiCommand {
		DialogCom dialog = null;
		Vector camiCmd;
		
		Iterator it = camiVec.iterator();
		camiCmd = (Vector) it.next();
		
		// La premiere commande attendue est un DC
		System.out.println("Commande : " + camiCmd.get(0));
		
		if (camiVec.size() == 0) {
			throw new UnexpectedCamiCommand("La boite de dialogue est mal definie : nulle");
		} else if (!camiCmd.get(0).equals("DC")) {
			throw new UnexpectedCamiCommand("La boite de dialogue est mal definie : "+camiCmd.get(0)+" a la place de DC");
		}
		
		// Commande suivante attendue : CE
		camiCmd = (Vector) it.next();
		System.out.println("Commande : "+ camiCmd.get(0));
		
		if (!camiCmd.get(0).equals("CE")) {
			throw new UnexpectedCamiCommand("La boite de dialogue est mal definie : "+camiCmd.get(0)+" a la place de CE");
		} else if (camiCmd.size() != 10) {
			throw new UnexpectedCamiCommand("La boite de dialogue est mal definie : 10 parametres sont attendus...");
		}

		// On commande a parser les resultats
		
		// Identifiant de la boite de dialogue
		int id;
		if (camiCmd.get(1) == null) {
			throw new UnexpectedCamiCommand("Identifiant de la boite de dialogue est nul");
		}
		id = Integer.parseInt(camiCmd.get(1).toString());
		
		// Type de la boite de dialogue (Standart, Warning, Error, Interactif)
		int ttype, type;
		if (camiCmd.get(2) == null) {
			throw new UnexpectedCamiCommand("Type de la boite de dialogue nul");
		}
		ttype = Integer.parseInt(camiCmd.get(2).toString());
		switch (ttype) {
		case 1 : type = IDialog.DLG_STANDARD; break;
		case 2 : type = IDialog.DLG_WARNING; break;
		case 3 : type = IDialog.DLG_ERROR; break;
		case 4 : type = IDialog.DLG_INTERACTIVE; break;
		default : throw new UnexpectedCamiCommand("Type de la boite de dialogue invalide");
		}

		// Nombre de boutons dans la boite de dialogue (1=0bouton,2=1bouton,3=2boutons)
		int tnbButtons,nbButtons;
		if (camiCmd.get(3) == null) {
			throw new UnexpectedCamiCommand("Nombre de boutons de la boite de dialogue incorrect");
		}
		tnbButtons = Integer.parseInt(camiCmd.get(3).toString());
		switch (tnbButtons) {
		case 1 : nbButtons = IDialog.DLG_NO_BUTTON; break;
		case 2 : nbButtons = IDialog.DLG_OK; break;
		case 3 : nbButtons = IDialog.DLG_OK_CANCEL; break;
		default : throw new UnexpectedCamiCommand("Nombre de boutons de la boite de dialogue invalide");
		}
		
		// Titre de la boite de dialogue
		String title;
		if (camiCmd.get(4) == null) {
			throw new UnexpectedCamiCommand("Titre de la boite de dialogue est nul");
		}
		title = camiCmd.get(4).toString();
		
		// Message d'aide
		String helpMsg;
		if (camiCmd.get(5) == null) {
			throw new UnexpectedCamiCommand("Message d'aide nul");
		}
		helpMsg = camiCmd.get(5).toString();
		
		// Message
		String msg;
		if (camiCmd.get(6) == null) {
			throw new UnexpectedCamiCommand("Message principal nul");
		}
		msg = camiCmd.get(6).toString();
		
		
		int allowedEntry;
		
		if (camiCmd.get(7) == null) {
			throw new UnexpectedCamiCommand("Indicateur de saisie nul");
		} else if (camiCmd.get(7).toString().equals("1")) {
			allowedEntry = IDialog.INPUT_AUTHORIZED;
		} else if (camiCmd.get(7).toString().equals("2")) {
			allowedEntry = IDialog.INPUT_FORBIDDEN;
		} else if (camiCmd.get(7).toString().equals("5")) {
			allowedEntry = IDialog.INPUT_AND_ABORT_AUTHORIZED;
			System.err.println("Abort command is not available yet...");
		} else {
			throw new UnexpectedCamiCommand("Indicateur de saisie invalide");
		}
		
		int select;
		if (camiCmd.get(8) == null) {
			throw new UnexpectedCamiCommand("Indicateur de selection nul");
		} else if (camiCmd.get(8).toString().equals("1")) {
			select = IDialog.MULTI_LINE_WITH_SINGLE_SELECTION;
		} else if (camiCmd.get(8).toString().equals("2")) {
			select = IDialog.SINGLE_LINE;
		} else if (camiCmd.get(8).toString().equals("5")) {
			select = IDialog.MULTI_LINE_WITH_MULTI_SELECTION;
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
				if (camiCmd.get(2) == null) {
					throw new UnexpectedCamiCommand("Contenu de la boite de dialogue est nul");
				}
				contents.add(camiCmd.get(2).toString());
			} else {
				throw new UnexpectedCamiCommand("Commande inconnue dans la construction d'une boite de dialogue");
			}
		}
		
		// Creation de l'objet boite de dialogue
		dialog = new DialogCom(id, type, nbButtons, title, helpMsg, msg, allowedEntry, select,"-");
		return dialog;
	}
}
