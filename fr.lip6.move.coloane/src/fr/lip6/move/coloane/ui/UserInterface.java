package fr.lip6.move.coloane.ui;


import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import fr.lip6.move.coloane.interfaces.*;
import fr.lip6.move.coloane.menus.RootMenu;
import fr.lip6.move.coloane.motor.session.Session;
import fr.lip6.move.coloane.ui.menus.GraphicalMenu;
import fr.lip6.move.coloane.ui.panels.*;

/**
 * Interface Utilisateur
 */

public class UserInterface implements IUiCom, IUiMotor {
    
    /** La fenêtre de travail */
    private static IWorkbenchWindow fenetreTravail;
    
    /** Le module de communication */
    private IComUi com = null;
    
    /** Le module de moteur */
    private IMotorUi motor = null; 
    
    
    
    /**
     * Constructeur de la classe
     */
    public UserInterface() {
        fenetreTravail = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
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
    public void sendStateMessage(String message) {
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
		currentSession.setSessionMenu(menu);
		GraphicalMenu gmenu = new GraphicalMenu(menu,fenetreTravail,this);
		gmenu.build();		
	}
	
	/**
	 * Demande la mise a jour du menu
	 * @param menuAPI arborescence menu
	 * */
	public void updateMenu() {
		Session currentSession = motor.getSessionManager().getCurrentSession();
		GraphicalMenu gmenu = new GraphicalMenu(currentSession.getSessionMenu(),fenetreTravail,this);
		gmenu.update();
	}
	
	/**
	 * Demande d'un service
	 * @param rootMenuName Le nom du menu racine
	 * @param parentName Le nom du pere de la feuille cliquee
	 * @param serviceName LE nom du service demande
	 */
	public void askForService(String rootMenuName, String parentName, String serviceName) {
		this.com.askForService(rootMenuName, parentName, serviceName);
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
