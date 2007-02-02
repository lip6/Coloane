package fr.lip6.move.coloane.ui;


import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import fr.lip6.move.coloane.communications.objects.Result;
import fr.lip6.move.coloane.interfaces.*;
import fr.lip6.move.coloane.menus.RootMenu;
import fr.lip6.move.coloane.motor.session.Session;
import fr.lip6.move.coloane.results.ActionsList;
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
    
    /** La fentre de travail */
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
     * Affichage d'un message sur la vue State
     * @param message message a afficher
     */
    public void printStateMessage(String message) {
   		final String msg=message;
        
        Display.getDefault().asyncExec(new Runnable(){
           public void run(){
             if (StateView.instance != null) {
        	   		StateView.instance.addLine(msg);
             } else {
            	 	HistoryView.instance.addLine("state : "+msg);
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
	 * @param menuAPI arborescence menu
	 */
	public void updateMenu() {
		Session currentSession = motor.getSessionManager().getCurrentSession();
		if (currentSession != null) {
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
	public void setResults(String serviceName, Result result) {
		if (serviceResultList != null) {
			String labelService;
			ResultsList r = null;
			
			if (serviceName.equals("Petri net syntax checker")) {
				
				labelService = "Syntax-Checker Results";
				r = new ResultsList(labelService);
				
				String description = null;
				
				System.out.println(" ! > "+result.getDescription());
				if (result.getDescription().equals("List of unnamed places.")) {
					description = "This place is unnamed";
				} else if (result.getDescription().equals("List of unnamed transitions.")) {
					System.out.println("Ajout de la description "+description);
					description = "This transition is unnamed";
				}
				
				// Parcours de mes resultats
				for (String object : result.getList()) {
					System.out.println("Ajout d'un objet :"+object);
					r.add(new fr.lip6.move.coloane.results.Result(object,description));
				}
			}
			
			System.out.println("Transmission des resultats");
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
