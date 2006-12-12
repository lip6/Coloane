package fr.lip6.move.coloane.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import fr.lip6.move.coloane.communications.objects.Menu;
import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.motor.session.Session;

/**
 * Cette classe permet la gestion des mises ˆ jour des menus
 * 
 * @author Christophe Kodrnja, Selvaratnam Senthuran
 * 
 */
public class MenuManager {

	/** Fenetre de travail */
	private static IWorkbenchWindow window;

	/** * Menu courant */
	private MenuManager currentMenu;

	/** Menu Administration */
	private MenuManager menuAdministration;

	/** Menu du formalisme (PetriNet, Accessibility Graph,..) */
	private MenuManager menuFormalisme;

	/**
	 * Constructeur qui prend comme argument la fenetre de travail
	 * @param fenetreTravail fenetre de travail
	 */
	public MenuManager(IWorkbenchWindow fenetreTravail) {
		window = fenetreTravail;
	}

	/**
	 * CrŽe un menu API de test
	 * 
	 * @author Christophe Kodrnja, Selvaratnam Senthuran
	 * @return un menu API
	 */
	

	/**
	 * Met a jour le menu ˆ partir du menu API
	 * 
	 * @author Christophe Kodrnja, Selvaratnam Senthuran
	 */
	public void updateMenu() {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				Session currentSession = Coloane.getDefault().getMotor().getSessionManager().getSession();
				
				if (currentSession == null ) {
					System.err.println("Aucune session en cours...");
					return;
				}
				
				if (currentSession.getSessionMenu() != null) {
					updateFormalismMenu();
				}
				if (currentSession.getAdminMenu() != null) {
					updateAdminMenu();
				}
				
			}
		});

	}

	/**
	 * Met a jour le menu administration ˆ partir du menu API
	 * 
	 * @author Christophe Kodrnja, Selvaratnam Senthuran
	 */
	public void updateFormalismMenu() {
		// Recuperation menu de la session
		Session currentSession = Coloane.getDefault().getMotor().getSessionManager().getSession();
		Menu myMenu = currentSession.getSessionMenu();

		// Creation du nouveau menu
		MenuManager rootMenu = MenuManager.genMenu(myMenu);

		// Trouver l'editeur actif
		Editor editor = (Editor) window.getActivePage().getActiveEditor();

		if (currentSession.getSessionModel() == editor.getModel()) {
			deleteMenuFromBar(myMenu.getName());
			Shell shell = window.getShell();
			org.eclipse.swt.widgets.Menu menu = shell.getMenuBar();
			rootMenu.fill(menu, menu.getItemCount() - 1);

		}
	}

	/**
	 * Met a jour le menu administration à partir du menu API
	 * 
	 * @author Christophe Kodrnja, Selvaratnam Senthuran
	 */
	public void updateAdminMenu() {
//		 Recuperation menu de la session
		Session currentSession = Coloane.getDefault().getMotor().getSessionManager().getSession();
		Menu myMenu = currentSession.getAdminMenu();

		// Creation du nouveau menu
		MenuManager rootMenu = MenuManager.genMenu(myMenu);

		// Trouver l'editeur actif
		Editor editor = (Editor) window.getActivePage().getActiveEditor();

		if (currentSession.getSessionModel() == editor.getModel()) {
			deleteMenuFromBar(myMenu.getName());
			Shell shell = window.getShell();
			org.eclipse.swt.widgets.Menu menu = shell.getMenuBar();
			rootMenu.fill(menu, menu.getItemCount() - 1);

		}
	}
	

	/**
	 * Génère le menu racine et appel genMenu pour la génération des sous menu
	 * et des services
	 * 
	 * @author Christophe Kodrnja, Selvaratnam Senthuran
	 * @param menuAPI
	 *            menu fournit par l'api
	 * @return menu racine
	 */
	public static MenuManager genMenu(Menu menuAPI) {
		// Racine du menu, affichage dans la barre d'eclipse
		if (menuAPI.isRoot()) {
			String nomMenu = menuAPI.getName();
			System.out.println("Creation du menu : "+nomMenu);
			MenuManager rootMenu = new MenuManager(nomMenu);
			MenuManager.genMenu(menuAPI, rootMenu, nomMenu, "");
			return rootMenu;
		}
		return null;
	}

	/**
	 * GŽnŽration des sous menus et des services Fonction recursive
	 * 
	 * @author Christophe Kodrnja, Selvaratnam Senthuran
	 * @param menuAPI
	 *            sous-menu fournit par l'API
	 * @param parent
	 *            menu parent
	 * @param root
	 *            menu racine
	 * @param firstMenu
	 *            nom du premier noeud de l'arbre des menus
	 */
	public static void genMenu(Menu menuAPI, MenuManager parent, String root,String firstMenu) {
		// Nombre de service
		int nbServices = menuAPI.getServiceNumber();
		// On construit les services du menu courant
		String[] temp = {"Use Memory..."};
		if (nbServices != 0) {
			for (int i = 0; i < nbServices; i++) {
				Service serviceCourant = menuAPI.getAService(i);
				// action apres pression du bouton
				parent.add(new ServiceAction(root, serviceCourant.getName(), firstMenu, temp, null, null, serviceCourant.isActive(),serviceCourant.isDefaultValid()));
			}
		}

		// On construit les sous menus avec les services recursivement
		// nombre de sous-menu
		int nbMenu = menuAPI.getSubMenuNumber();
		// creation des sous-menus avec recursion
		if (nbMenu != 0) {
			for (int i = 0; i < nbMenu; i++) {
				Menu menuCourant = menuAPI.getASubMenu(i);
				if (menuCourant.isActive()) {
					// sous menu
					MenuManager subMenu = new MenuManager(menuCourant.getName());

					if (menuCourant.isActive()) {
						if (firstMenu == "") {
							MenuManager.genMenu(menuCourant, subMenu,root, menuCourant.getName());
						} else {
							MenuManager.genMenu(menuCourant, subMenu,root, firstMenu);
						}
					}
					parent.add(subMenu);

				} else {
					// Créer une action grisée
					parent.add(new ServiceAction(root, menuCourant.getName(),firstMenu, temp, null, null, false, false));
				}
			}

		}

	}

	/**
	 * @return Returns the currentMenu.
	 */
	public MenuManager getCurrentMenu() {
		return this.currentMenu;
	}

	/**
	 * @param newMenu
	 *            The currentMenu to set.
	 */
	public void setCurrentMenu(MenuManager newMenu) {
		this.currentMenu = newMenu;
	}

	/**
	 * Obtenir le MenuAdministration
	 * 
	 * @return le MenuAdmnistration
	 */
	public MenuManager getMenuAdministration() {
		return menuAdministration;
	}

	/**
	 * Donner une valeur à MenuAdministration
	 * 
	 * @param menuAdministration
	 *            la valeur
	 */
	public void setMenuAdministration(MenuManager menuAdministration) {
		this.menuAdministration = menuAdministration;
	}

	/**
	 * Obtenir le MenuFormalisme
	 * 
	 * @return le menuMenuFormalisme
	 */
	public MenuManager getMenuFormalisme() {
		return menuFormalisme;
	}

	/**
	 * Donner une valeur à MenuAdmnistration
	 * 
	 * @param menuFormalisme
	 *            la valeur
	 */
	public void setMenuFormalisme(MenuManager menuFormalisme) {
		this.menuFormalisme = menuFormalisme;
	}

	/**
	 * Supprimer un menu dans la barre de menu
	 * 
	 * @author Selvaratnam Senthuran
	 * 
	 * @param strMenu
	 *            nom du menu
	 */
	public void deleteMenuFromBar(String strMenu) {
		MenuItem[] menuArray = window.getShell().getMenuBar().getItems();
		for (int i = 0; i < menuArray.length; i++) {
			
			if (menuArray[i].getText().equalsIgnoreCase(strMenu)) {
				System.out.println("Suppression de "+menuArray[i].getText());
				menuArray[i].dispose();
				break;
			}
		}
	}

	/**
	 * Activer un menu dans la barre de menu
	 * 
	 * @author Selvaratnam Senthuran
	 * 
	 * @param strMenu
	 *            nom du menu
	 */
	public void enableMenuFromBar(String strMenu) {
		MenuItem[] menuArray = window.getShell().getMenuBar().getItems();
		for (int i = 0; i < menuArray.length; i++) {
			if (menuArray[i].getText().equalsIgnoreCase(strMenu)) {
				menuArray[i].setEnabled(true);
				break;
			}
		}
	}

	/**
	 * Desactiver un menu dans la barre de menu
	 * 
	 * @param strMenu Nom du menu
	 */
	public void disableMenuFromBar(String strMenu) {
		MenuItem[] menuArray = window.getShell().getMenuBar().getItems();
		for (int i = 0; i < menuArray.length; i++) {
			if (menuArray[i].getText().equalsIgnoreCase(strMenu)) {
				menuArray[i].setEnabled(false);
				break;
			}
		}
	}

	/**
	 * Grise ou degrise un menu de Platform
	 * 
	 * @param strMenuItem nom du menu
	 * @param isActive si actif->degrise, grise sinon
	 */
	public void setPlatformMenuItemsAccessibility(String strMenuItem,boolean isActive) {
		MenuItem[] menuArray = window.getShell().getMenuBar().getItems();

		
		for (int i = 0; i < menuArray.length; i++) {
			if (menuArray[i].getText().equalsIgnoreCase(Coloane.getString("MNU_PLATFORM"))) {
				MenuItem[] menuItemArray = menuArray[i].getMenu().getItems();
				for (int j = 0; j < menuItemArray.length; j++) {
					if (menuItemArray[j].getText().equalsIgnoreCase(strMenuItem)) {
						menuItemArray[j].setEnabled(isActive);
						break;
					}
				}
				break;
			}
		}
	}

}
