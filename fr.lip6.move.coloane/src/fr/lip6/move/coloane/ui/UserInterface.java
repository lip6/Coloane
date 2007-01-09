package fr.lip6.move.coloane.ui;


import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import fr.lip6.move.coloane.interfaces.*;
import fr.lip6.move.coloane.ui.panels.*;

/**
 * Interface Utilisateur
 */

public class UserInterface implements IUiCom, IUiMotor {
    
    /** Le gestionnaire de menus */
    private static MenuManager menuManager;

    /** La fenêtre de travail */
    private static IWorkbenchWindow fenetreTravail;
    
    /**
     * Constructeur de la classe
     *
     */
    public UserInterface() {
        fenetreTravail = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        menuManager = new MenuManager(fenetreTravail);
    }

	/**
	 * Demande la mise a jour du menu
	 * @param menuAPI arborescence menu
	 * */
	public void updateMenu() {
	    menuManager.updateMenu();
	}

	/**
	 * Ouvrir une fenetre
	 * @param dialog dialogue
	 */
//	public void openWindow(Object dialog) {
//		DialogTool.openWindow(dialog);
//	}

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
	 * Bloquer et debloquer le modele apres une demande de l'API
	 *
	 * @param lock booleen idiquant l'etat du blocage
	 */
	public void changeIU(boolean lock) {

	}

	/**
	 * Afficher une boite de dialogue
	 * @param numDialogBox indique le numero de la boite de dialogue
	 */
//	public void displayDialog(int numDialogBox) {
//		DialogTool.displayDialog(numDialogBox);
//	}

    /**
     * Cacher la boite de dialogue
     * @param dialogNb indique le numero de la boite de dialogue
     */
//	public void hideDialog(int dialogNb) {
//		DialogTool.hideDialog(dialogNb);
//	}

    /**
     * Detruire la boite de dialogue
     * @param dialogNb indique le numero de la boite de dialogue
     */
//	public void destroyDialog(int dialogNb) {
//		DialogTool.destroyDialog(dialogNb);
//	}

	/**
	 * Notification a l'IHM pour mettre a jour l'interface graphique( barre d'outils, menu, etc.)
	 */
	public void updateIhm() {
		
	}

	/**
	 * Bloquer le modele courant (avec une Java synchronized function)
	 *
	 */
	public void lockModel() {
		

	}
	/**
	 * Debloquer le modele courant (with a Java synchronized function)
	 * 
	 */
	public void unlockModel() {
	

	}

    /**
     * Obtenir la valeur de MenuManager
     * @return le menuManager
     */
    public MenuManager getMenuManager() {
        return menuManager;
    }

    /**
     * Donner une valeur a MenuManager
     * @param menuManager la valeur
     */
    public void setMenuManager(MenuManager menuManager) {
        UserInterface.menuManager = menuManager;
    }
}
