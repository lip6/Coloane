package fr.lip6.move.coloane.communications.utils;

import java.util.Iterator;
import java.util.Vector;


import fr.lip6.move.coloane.communications.objects.Service;
import fr.lip6.move.coloane.communications.objects.UrgentMessage;
import fr.lip6.move.coloane.communications.objects.WindowedDialogue;
import fr.lip6.move.coloane.exceptions.UnexpectedCamiCommand;
import fr.lip6.move.coloane.menus.MenuNotFoundException;
import fr.lip6.move.coloane.menus.RootMenu;
import fr.lip6.move.coloane.menus.Menu;

/**
 * @author Equipe 2 (Styx)
 */
public class CamiTranslator {

	/**
	 * 
	 */
	public CamiTranslator() {
		super();
	}

	/**
	 * Permet de parcourir l'arbre des menus pour une nouvelle insertion de menu ou de service au bon endroit
	 * @param menuName le nom du menu recherche
	 * @param rootMenu le menu racine
	 * @return le menu ou faire l'insertion
	 */
	private Menu getMatchingMenu(String menuName, Menu rootMenu) {
		Menu tmpMenu;
		
		if (rootMenu.getName().equals(menuName)) {
			return rootMenu;
		}
		
		for (int i = 0; i < rootMenu.getSubMenuNumber(); i++) {
			if (rootMenu.getASubMenu(i).getName().equals(menuName)) {
				return rootMenu.getASubMenu(i);
			} 
			tmpMenu = getMatchingMenu(menuName, rootMenu.getASubMenu(i));
			if (tmpMenu != null) {
				return tmpMenu;
			}
		}
		return null;
	}
	
	/**
	 * permet de parcourir l'arbre des menus pour valider ou invalider un service
	 * @param serviceName le nom du service recherche
	 * @param rootMenu le menu racine
	 * @return le service a modifier
	 */
	private Service getMatchingService(String serviceName, Menu rootMenu) {
		Service tmpService;
		
		for (int i = 0; i < rootMenu.getServiceNumber(); i++) {
			if (rootMenu.getAService(i).getName().equals(serviceName)) {
				return rootMenu.getAService(i);
			}
		}
		
		for (int i = 0; i < rootMenu.getSubMenuNumber(); i++) {
			tmpService = getMatchingService(serviceName, rootMenu.getASubMenu(i));
			if (tmpService != null) {
				return tmpService;
			}
		}
		
		return null;
	}
	
	/**
	 * permet de valider ou d'invalider des parties du menu
	 * @param camiVec Vector de Vector contenant le CAMI
	 * @param rootMenu le menu deja cree
	 * @return l'objet Menu mis a jour
	 * @throws UnexpectedCAMICommand si jamais camiVec contient une mauvaise commande
	 */
	public Menu updateMenu(Vector camiVec, Menu rootMenu) throws UnexpectedCamiCommand {
		Iterator it = camiVec.iterator();
		Vector camiCmd;
		Menu tmpMenu;
		Service tmpService;
		
		while (it.hasNext()) {
			camiCmd = (Vector) it.next();
			
			if (camiCmd.size() == 0) {
				throw new UnexpectedCamiCommand("updateMenu(Vector camiVec, Menu rootMenu) : le vecteur est nul");
			} else if (!camiCmd.get(0).equals("TQ")) {
				throw new UnexpectedCamiCommand("updateMenu(Vector camiVec, Menu rootMenu) : le vecteur ne contient pas TQ en premier");
			}
			
			//System.out.println("@@@@@@@@@@@@@ " + camiCmd.get(2).toString() + " @@@@@@@@@@@@@@");
			//System.out.println("@@@@@@@@@@@@@ " + rootMenu.getName() + " @@@@@@@@@@@@@@");
			
			tmpMenu = getMatchingMenu(camiCmd.get(2).toString(), rootMenu);
			
			//System.out.println("#" + rootMenu.getAService(1).getName() + "#");
			
			if (tmpMenu != null) {
				//System.out.println("non trouve =" + camiCmd.get(2).toString());
				
				//System.out.println("@@@@@@@@@@@@@" + tmpMenu.getName() + "@@@@@@@@@@@@@@");
				
				if (Integer.parseInt(camiCmd.get(3).toString()) == 7) {
					tmpMenu.setActive(true);
				} else if (Integer.parseInt(camiCmd.get(3).toString()) == 8) {
					tmpMenu.setActive(false);
				} else {
					throw new UnexpectedCamiCommand("updateMenu(Vector camiVec, Menu rootMenu) :"
							+ " le vecteur pour un Menu ne contient pas 7 ou 8 en 3eme argument");
				}
			} else {
				tmpService = getMatchingService(camiCmd.get(2).toString(), rootMenu);
				
				if (Integer.parseInt(camiCmd.get(3).toString()) == 7) {
					tmpService.setActive(true);
				} else if (Integer.parseInt(camiCmd.get(3).toString()) == 8) {
					tmpService.setActive(false);
				} else {
					throw new UnexpectedCamiCommand("updateMenu(Vector camiVec, Menu rootMenu) :"
							+ "le vecteur pour le Service \'" + camiCmd.get(2).toString() + "\' ne contient pas 7 ou 8 en 3eme argument");
				}
			}

		}
		
		return rootMenu;
	}
	
	/**  
	 * Permet de traduire du CAMI vers l'objet Menu
	 * 
	 * @param camiVec Vector de Vector contenant le CAMI
	 * @return l'objet Menu traduit du CAMI
	 * @throws UnexpectedCAMICommand si jamais camiVec contient une mauvaise commande
	 */
	
	public Menu getMenu(Vector camiVec) throws UnexpectedCamiCommand {
		Iterator it = camiVec.iterator();
		
		Menu rootMenu;
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
		rootMenu = new RootMenu((String) camiCmd.get(1));
		
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
			
			try {
				rootMenu.addMenu(serviceName, serviceFather, false);
			} catch (MenuNotFoundException e) {
				System.err.println("L'arborescence des menus n'est pas correcte");
			}
			
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
			new Service(serviceName, serviceActive, serviceSuspensible, dialogueAllwd, defaultValid, resFormalism);
		
		}
		
		return rootMenu;
	}
	
	/**
	 * Permet de traduire du CAMI vers l'objet UrgentMessage
	 * @param camiVec est un Vector contenant le CAMI
	 * @return l'objet UrgentMessage traduit du CAMI
	 * @throws UnexpectedCAMICommand si jamais camiVec contient une mauvaise commande
	 */
	public UrgentMessage getUrgentMessage(Vector camiVec) throws UnexpectedCamiCommand {
		UrgentMessage urgentMsg;
		if (camiVec.size() == 0) {
			throw new UnexpectedCamiCommand("getUrgentMessage(Vector camiVec) : le vecteur est nul");
		} else if (!camiVec.get(0).equals("MU")) {
			throw new UnexpectedCamiCommand("getUrgentMessage(Vector camiVec) : le vecteur ne contient pas MU en premier");
		}
		try {
			urgentMsg = new UrgentMessage(Integer.parseInt(camiVec.get(1).toString()), Integer.parseInt(camiVec.get(2).toString()));
			return urgentMsg;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * permet de traduire du CAMI vers l'objet WindowedDialogue
	 * @param camiVec camiVec est un Vector contenant le CAMI
	 * @return l'objet WindowedDialogue traduit du CAMI
	 * @throws UnexpectedCAMICommand si jamais camiVec contient une mauvaise commande
	 */
	public WindowedDialogue getWindowedDialogue(Vector camiVec)  throws UnexpectedCamiCommand {
		WindowedDialogue winDialogue = null;
		Iterator it = camiVec.iterator();
		Vector camiCmd;
		
		System.out.println("############ " + camiVec.get(0) + "############" + camiVec.get(1));
		
		camiCmd = (Vector) it.next();
		System.out.println("############ " + camiCmd.get(0));
		
		if (camiVec.size() == 0) {
			throw new UnexpectedCamiCommand("getWindowedDialogue(Vector camiVec) : le vecteur est nul");
		} else if (!camiCmd.get(0).equals("DC")) {
			throw new UnexpectedCamiCommand("getWindowedDialogue(Vector camiVec) : le vecteur ne contient pas DC en premier mais \'" + camiVec.get(0) + "\'");
		}
		
		camiCmd = (Vector) it.next();
		System.out.println("############ " + camiCmd.get(0));
		
		if (!camiCmd.get(0).equals("CE")) {
			throw new UnexpectedCamiCommand("getWindowedDialogue(Vector camiVec) : le vecteur ne contient pas CE en deuxieme mais \'" + camiCmd.get(0) + "\'");
		} else if (camiCmd.size() != 10) {
			throw new UnexpectedCamiCommand("getWindowedDialogue(Vector camiVec) : le vecteur camiCmd est de taille=" + camiCmd.size() + " et non 10");
		}

		int uId;
		if (camiCmd.get(1) == null) {
			throw new UnexpectedCamiCommand("getWindowedDialogue(Vector camiVec) : CE camiCmd.get(1) est nul");
		}
		uId = Integer.parseInt(camiCmd.get(1).toString());
		
		int typ;
		if (camiCmd.get(2) == null) {
			throw new UnexpectedCamiCommand("getWindowedDialogue(Vector camiVec) : CE camiCmd.get(2) est nul");
		}
		typ = Integer.parseInt(camiCmd.get(2).toString());

		int butNb;
		if (camiCmd.get(3) == null) {
			throw new UnexpectedCamiCommand("getWindowedDialogue(Vector camiVec) : CE camiCmd.get(3) est nul");
		}
		butNb = Integer.parseInt(camiCmd.get(3).toString());
		
		String windowTitl;
		if (camiCmd.get(4) == null) {
			throw new UnexpectedCamiCommand("getWindowedDialogue(Vector camiVec) : CE camiCmd.get(4) est nul");
		}
		windowTitl = camiCmd.get(4).toString();
		
		String helpMsg;
		if (camiCmd.get(5) == null) {
			throw new UnexpectedCamiCommand("getWindowedDialogue(Vector camiVec) : CE camiCmd.get(5) est nul");
		}
		helpMsg = camiCmd.get(5).toString();
		
		String msg;
		if (camiCmd.get(6) == null) {
			throw new UnexpectedCamiCommand("getWindowedDialogue(Vector camiVec) : CE camiCmd.get(6) est nul");
		}
		msg = camiCmd.get(6).toString();
		
		boolean allowedSz;
		boolean allowedAbrt;		
		if (camiCmd.get(7) == null) {
			throw new UnexpectedCamiCommand("getWindowedDialogue(Vector camiVec) : CE camiCmd.get(7) est nul");
		} else if (camiCmd.get(7).toString().equals("1")) {
			allowedAbrt = false;
			allowedSz = true;
		} else if (camiCmd.get(7).toString().equals("2")) {
			allowedAbrt = false;
			allowedSz = false;
		} else if (camiCmd.get(7).toString().equals("5")) {
			allowedAbrt = true;
			allowedSz = true;
		} else {
			throw new UnexpectedCamiCommand("getWindowedDialogue(Vector camiVec) : CE camiCmd.get(7) valeur inconnue");
		}
		
		boolean xLines;
		boolean manySelect;		
		if (camiCmd.get(8) == null) {
			throw new UnexpectedCamiCommand("getWindowedDialogue(Vector camiVec) : CE camiCmd.get(8) est nul");
		} else if (camiCmd.get(8).toString().equals("1")) {
			xLines = false;
			manySelect = false;
		} else if (camiCmd.get(8).toString().equals("2")) {
			xLines = true;
			manySelect = false;
		} else if (camiCmd.get(8).toString().equals("5")) {
			xLines = true;
			manySelect = true;
		} else {
			throw new UnexpectedCamiCommand("getWindowedDialogue(Vector camiVec) : CE camiCmd.get(8) valeur inconnue");
		}
		
		try {
			winDialogue = new WindowedDialogue(uId, msg, typ, 
				butNb, windowTitl, helpMsg, xLines, manySelect, 
				allowedSz, allowedAbrt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		while (it.hasNext()) {
			camiCmd = (Vector) it.next();
			
			if (camiCmd.get(0).equals("FF")) {
				break;
			} else if (camiCmd.get(0).equals("DS")) {
				if (camiCmd.get(2) == null) {
					throw new UnexpectedCamiCommand("getWindowedDialogue(Vector camiVec) : DS camiCmd.get(2) nul");
				}
				winDialogue.addLine(camiCmd.get(2).toString());
			} else {
				throw new UnexpectedCamiCommand("getWindowedDialogue(Vector camiVec) : commande inconnue (ni DS, ni FF)");
			}
		}
		return winDialogue;
	}
}
