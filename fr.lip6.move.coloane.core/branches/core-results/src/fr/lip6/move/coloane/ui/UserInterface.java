package fr.lip6.move.coloane.ui;


import java.util.Vector;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

//import fr.lip6.move.coloane.communications.objects.Results;
import fr.lip6.move.coloane.factory.SyntaxCheckerReport;
import fr.lip6.move.coloane.interfaces.*;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.IUpdateMenuCom;
import fr.lip6.move.coloane.menus.MenuNotFoundException;
import fr.lip6.move.coloane.menus.RootMenu;
import fr.lip6.move.coloane.motor.session.Session;
import fr.lip6.move.coloane.results.ActionsList;
import fr.lip6.move.coloane.results.Result;
import fr.lip6.move.coloane.results.ResultsList;
import fr.lip6.move.coloane.ui.dialogs.Dialog;
import fr.lip6.move.coloane.ui.dialogs.DialogFactory;
import fr.lip6.move.coloane.ui.dialogs.IDialog;
import fr.lip6.move.coloane.ui.dialogs.UnknowDialogException;
import fr.lip6.move.coloane.ui.menus.GraphicalMenu;
import fr.lip6.move.coloane.ui.menus.MenuManipulation;
import fr.lip6.move.coloane.ui.panels.*;

/**
 * Interface Utilisateur
 */

public class UserInterface implements IUiCom, IUiMotor {

	/** La fenetre de travail */
	private static IWorkbenchWindow fenetreTravail;

	/** Le module de communication */
	private IComUi com = null;

	/** Le module de moteur */
	private IMotorUi motor = null; 

	/** La gestion des resultats */
	private ActionsList serviceResultList = null;


	/**
	 * Constructeur de la classe
	 */
	public UserInterface() {
		fenetreTravail = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

		// Le gestionnaire de resultats de services
		serviceResultList = new ActionsList();
	}


	/**
	 * Affichage d'un message sur la vue historique
	 * @param message a afficher
	 */
	public void printHistoryMessage(String message) {
		final String msg=message;

		Display.getDefault().asyncExec(new Runnable(){
			public void run(){
				if (HistoryView.instance != null) {
					HistoryView.instance.addLine(msg);
				}
			}
		});   
	}

	/** 
	 * Afficher un menu
	 * @param menu La racine du menu a afficher 
	 */
	public void drawMenu (RootMenu menu) {
		Session currentSession = motor.getSessionManager().getCurrentSession();
		currentSession.setServicesMenu(menu);
		GraphicalMenu gmenu = new GraphicalMenu(menu,fenetreTravail,this);
		gmenu.build();		
	}


	/**
	 * Demande la mise a jour du menu
	 * @param updates La liste des mises a jour a faire sur les menus
	 */
	public void updateMenu(Vector<IUpdateMenuCom> updates) {
		Session currentSession = motor.getSessionManager().getCurrentSession();
		if (currentSession != null) {

			// Recuperation du menu de service de la session
			RootMenu service = currentSession.getServicesMenu();

			for (IUpdateMenuCom up : updates) {
				if (up.getRoot().equals(service.getName())) {
					try {
						service.setEnabled(up.getService(), up.getState());
					} catch (MenuNotFoundException e) {
						System.err.println("Le menu "+up.getService()+" n'a pu etre mis a jour");
					}
				}
			}

			GraphicalMenu gmenu = new GraphicalMenu(currentSession.getServicesMenu(),fenetreTravail,this);
			gmenu.update();
		} 
	}


	/**
	 * Demande la suppression d'un menu designee par son nom
	 * @param menuName Le nom du menu a supprimer
	 */
	public void removeMenu(String menuName) {
		MenuManipulation.remove(menuName);
	}


	/**
	 * Desactivation du rootMenu
	 * @param rootMenu menu Root a griser (ainsi que tous ses fils)
	 */
	public void changeMenuStatus(String rootName, boolean status) {
		MenuManipulation.setEnabled(rootName,rootName,status);
	}


	/**
	 * Affichage des resultats d'un appel de service
	 * @param serviceName Le nom du service qui produit ses resultats
	 * @param result L'objet contenant les resultats pour ce service
	 */
	public void setResults(String serviceName, IResultsCom result) {
		if (serviceResultList != null) {
			String labelService;
			ResultsList r = null;


			if ((serviceName == "") || (result == null)) {
				labelService = "No result";
				r = new ResultsList(labelService);
				String  description = "";
				String object = "";
				r.add(new Result(object,description));				
			}
			
			SyntaxCheckerReport scr = new SyntaxCheckerReport(result);
			r = scr.getResultList();
			
			
			

			/*
			 * SYNTAX CHECKER
			 */
		/*	if (serviceName.equals("Petri net syntax checker")) {
				labelService = "Syntax-Checker Results";
				r = new ResultsList(labelService);

				String description = null;

				if (result.getHeadDescription().equals("List of unnamed places.")) {
					description = "This place is unnamed";
				} else if (result.getHeadDescription().equals("List of unnamed transitions.")) {
					description = "This transition is unnamed";
				} else {
					description = result.getHeadDescription();
				}

				// Parcours de mes resultats
				for (String object : result.getListOfElement()) {
					r.add(new Result(object,description));
				}
*/
				/*
				 * STRUCTURAL BOUNDS
				 */
		/*	} else if (serviceName.equals("Compute structural bounds")) {

				labelService = "Structural Bounds";
				r = new ResultsList(labelService);

				// Parcours de mes resultats
				for (String object : result.getListOfElement()) {
					r.add(new Result(object,result.getHeadDescription()));
				}
*/
				/*
				 * STRUCTURAL SAFETY
				 */
			/*} else if (serviceName.equals("Is the net structuraly safe?")) {
				labelService = "Structural safety";
				r = new ResultsList(labelService);

				if (result.getHeadDescription().equals("Here are unsafe places")) {					

					// Parcours de mes resultats
					for (String object : result.getSublistOfDescription(1)) {
						r.add(new fr.lip6.move.coloane.results.Result(object,""));
					}

				} else if (result.getHeadDescription().equals("Your net is not safe")) {
					String description = "Your net is not safe";
					r.add(new fr.lip6.move.coloane.results.Result(description,"Reasons are given above"));
				}
*/
				/*
				 * STRUCTURAL BOUNDS
				 */
			/*} else if (serviceName.equals("Is the net structurally bounded?")) {
				labelService = "Structural bounds";
				r = new ResultsList(labelService);

				r.add(new fr.lip6.move.coloane.results.Result(result.getHeadDescription(),""));
*/
				/*
				 * P INVARIANT
				 */
			/*} else if (serviceName.equals("P-invariants")) {
				labelService = "P-invariants";
				r = new ResultsList(labelService);

				String liste = "";

				// Parcours de mes resultats
				for (String object : result.getListOfElement()) {
					liste = liste+object+",";
				}

				// Suppression de la derniere virgule
				if (liste.length() > 1) {
					liste = liste.substring(0, liste.length()-1);
				}

				r.add(new fr.lip6.move.coloane.results.Result(liste,result.getHeadDescription()));				
*/

				/*
				 * T INVARIANT
				 */
		/*	} else if (serviceName.equals("T-invariants")) {
				labelService = "T-invariants";
				r = new ResultsList(labelService);

				String liste = "";

				// Parcours de mes resultats
				for (String object : result.getListOfElement()) {
					liste = liste+object+",";
				}

				// Suppression de la derniere virgule
				if (liste.length() > 1) {
					liste = liste.substring(0, liste.length()-1);
				}

				r.add(new fr.lip6.move.coloane.results.Result(liste,result.getHeadDescription()));				
			}*/

			serviceResultList.setResultsList(r);			
		}
	}


	/**
	 * Affichage des resultats dans la vue resultats
	 */
	public void printResults() {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				try {
					serviceResultList.addResultsList();
					fenetreTravail.getActivePage().showView(MainPerspectiveFactory.RESULTS_VIEW);
					serviceResultList.display(MainPerspectiveFactory.RESULTS_VIEW, fenetreTravail);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Demande d'un service
	 * @param rootMenuName Le nom du menu racine
	 * @param parentName Le nom du pere de la feuille cliquee
	 * @param serviceName LE nom du service demande
	 */
	public void askForService(String rootMenuName, String parentName, String serviceName) {
		serviceResultList.removeAll();
		this.com.askForService(rootMenuName, parentName, serviceName);
	}


	/** Affichage d'une boite de dialogue
	 * @param Dialog L'objet contenant toutes les informations sur la boite de dialogue a afficher
	 */
	public void drawDialog(Dialog d) {
		IDialog dialog = null;
		try {
			dialog = DialogFactory.create(
					d.id, d.type, d.buttonType, d.title, 
					d.help, d.message, d.inputType, d.multiLine,d.def);

			// Ouverture de la boite de dialogue
			dialog.open();

			if (dialog.getDialogResult().getAnswerType() == IDialog.TERMINATED_CANCEL) {
				return;	
			}

			// Capture des resultats
			this.com.getDialogAnswers(dialog.getDialogResult());

		} catch (UnknowDialogException e) {
			System.err.println("Echec de la construction de la boite de dialogue");
			e.printStackTrace();
		}


	}

	/**
	 * On attache le module de communication a l' l'interface utilisateur
	 * @param IComUi L'interface
	 */
	public void setCom(IComUi com) {
		this.com = com;
	}


	/**
	 * On attache le module du moteur a l' l'interface utilisateur
	 * @param IMotorUi L'interface
	 */
	public void setMotor(IMotorUi mot) {
		this.motor = mot;
	}
}
