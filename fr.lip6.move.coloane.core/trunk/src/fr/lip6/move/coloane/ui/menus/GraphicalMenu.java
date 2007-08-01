package fr.lip6.move.coloane.ui.menus;


import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;

import fr.lip6.move.coloane.menus.ChildMenu;
import fr.lip6.move.coloane.menus.RootMenu;
import fr.lip6.move.coloane.ui.UserInterface;

/**
 * This class takes a RootMenu and builds a graphic
 * menu for the Eclipse menubar, inserting it
 * just after the "Platform" menu.
 * 
 * @author Alexandre ORTIZ
 *
 */
public class GraphicalMenu {
	private RootMenu root;
	private Shell shell;
	private UserInterface ui;

	/**
	 * @param root
	 * @param window The window repersenting the workbench.<br/>
	 *  Can be obtained with
	 *  	PlatformUI.getWorkbench().getActiveWorkbenchWindow() 
	 */
	public GraphicalMenu(RootMenu root, IWorkbenchWindow window, UserInterface ui) {
		this.shell  = window.getShell();
		this.root = root;
		this.ui = ui;
	}

	/**
	 * Builds recursively a MenuManager from, using a RootMenu
	 * given in the constructor.
	 */
	public  MenuManager build() {
		MenuManager rootMenuManager = new MenuManager(root.getName());

		/*
		 * Before building anything, we have to verify that the menu does not already exits
		 */
		if (this.check()) {
			this.remove();
		}		

		/*
		 * We recursively build the menu.
		 */
		for(ChildMenu aChild : root.getChildren()) {
			if (aChild.isLeaf()) {
				UIAction uiAction = new UIAction(ui, root.getName(),aChild.getReference(), aChild.getName());
				uiAction.setEnabled(aChild.getEnabled());

				rootMenuManager.add(uiAction);
			} else {
				buildChildMenu(aChild, rootMenuManager);
			}
		}

		Menu menuBar = shell.getMenuBar();
		MenuItem[] mi = menuBar.getItems();

		/*
		 * We search the Platform menu's position to add this
		 * menu just after it.
		 */
		// TODO remove the hardcoded menu name
		for (int i = 0; i < mi.length; i++) {
			if (mi[i].getText().contentEquals("Coloane Services")) {
				rootMenuManager.fill(mi[i].getMenu(),
						mi[i].getMenu().getItems().length);
				break;
			}
		}

		return rootMenuManager;
	}

	/**
	 * Builds recursively a MenuManager from a Childmenu.
	 * @param child The child we want to add to the graphical menu
	 * @param parentMenuManager The MenuManager on which we will add the child
	 */
	public void buildChildMenu(ChildMenu child, MenuManager parentMenuManager) {
		MenuManager childMenuManager = new MenuManager(child.getName());
		parentMenuManager.add(childMenuManager);

		for(ChildMenu littleChild : child.getChildren()) {
			/*
			 * If we are on a leaf, we don't add a MenuManager but a Action.
			 */
			if (littleChild.isLeaf()) {
				UIAction exitAction = new UIAction(ui, root.getName(),littleChild.getReference(), littleChild.getName());
				exitAction.setEnabled(littleChild.getEnabled());
				childMenuManager.add(exitAction);
			} else {
				buildChildMenu(littleChild, childMenuManager);
			}
		}
	}

	/**
	 * Removes a menu from the menubar.
	 *
	 */
	// TODO remove the hardcoded menu name
	public void remove() {
		for (MenuItem mi : shell.getMenuBar().getItems()) {
			if (mi.getText().equals("Coloane Services")) {
				for (MenuItem mi1 : mi.getMenu().getItems()) {
					if (mi1.getText().equals(root.getName())) {
						mi1.dispose();
						return;
					}
				}
			}
		}
	}

	/**
	 * Check wether a menu is already in the menu bar
	 */
	private boolean check() {
		for (MenuItem mi : shell.getMenuBar().getItems()) {
			if (mi.getText().equals("Coloane Services")) {
				for (MenuItem mi1 : mi.getMenu().getItems()) {
					if (mi1.getText().equals(root.getName())) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Updates a menu (i.e. modifies it in the menubar).
	 */
	public MenuManager update() {
		remove();
		return build();
	}
}