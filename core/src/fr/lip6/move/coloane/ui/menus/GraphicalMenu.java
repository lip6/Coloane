package fr.lip6.move.coloane.ui.menus;

import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.menus.ChildMenu;
import fr.lip6.move.coloane.menus.RootMenu;
import fr.lip6.move.coloane.ui.UserInterface;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * This class takes a RootMenu and builds a graphic menu for the Eclipse menubar
 * @author Alexandre ORTIZ
 */
public class GraphicalMenu {
	private RootMenu root;
	private Shell shell;
	private UserInterface ui;

	/**
	 * Constructeur
	 * @param root
	 * @param window The window repersenting the workbench.<br/>
	 * Can be obtained with PlatformUI.getWorkbench().getActiveWorkbenchWindow()
	 */
	public GraphicalMenu(RootMenu r, IWorkbenchWindow window, UserInterface userInterface) {
		this.shell  = window.getShell();
		this.root = r;
		this.ui = userInterface;
	}

	/**
	 * Builds recursively a MenuManager from, using a RootMenu given in the constructor.
	 */
	public final MenuManager build() {
		MenuManager rootMenuManager = new MenuManager(root.getName());

		/*
		 * Before building anything, we have to verify that the menu does not already exits
		 */
		if (this.alreadyExist()) {
			this.remove();
		}

		/*
		 * We recursively build the menu.
		 */
		for (ChildMenu child : root.getChildren()) {
			if (child.isLeaf()) {
				UIAction uiAction = new UIAction(ui, root.getName(), child.getParent(), child.getName());
				uiAction.setEnabled(child.isEnabled());
				rootMenuManager.add(uiAction);
			} else {
				buildChildMenu(child, rootMenuManager);
			}
		}

		MenuItem coloane = locateColoaneMenu();
		rootMenuManager.fill(coloane.getMenu(), coloane.getMenu().getItems().length);

		return rootMenuManager;
	}

	/**
	 * Builds recursively a MenuManager from a Childmenu.
	 * @param child The child we want to add to the graphical menu
	 * @param parentMenuManager The MenuManager on which we will add the child
	 */
	private void buildChildMenu(ChildMenu child, MenuManager parentMenuManager) {
		MenuManager childMenuManager = new MenuManager(child.getName());
		parentMenuManager.add(childMenuManager);

		for (ChildMenu littleChild : child.getChildren()) {
			/*
			 * If we are on a leaf, we don't add a MenuManager but a Action.
			 */
			if (littleChild.isLeaf()) {
				UIAction exitAction = new UIAction(ui, root.getName(), littleChild.getParent(), littleChild.getName());
				exitAction.setEnabled(littleChild.isEnabled());
				childMenuManager.add(exitAction);
			} else {
				buildChildMenu(littleChild, childMenuManager);
			}
		}
	}

	/**
	 * Find the Coloane service menu in the menubar
	 * @return an handle on the menu
	 */
	private MenuItem locateColoaneMenu() {
		for (MenuItem mi : shell.getMenuBar().getItems()) {
			if (mi.getText().equals(Coloane.getParam("MENUBAR_LABEL"))) {
				return mi;
			}
		}
		return null;
	}

	/**
	 * Removes a menu from the menubar.
	 */
	public final void remove() {
		MenuItem coloane = locateColoaneMenu();
		if (coloane == null) { return; }

		for (MenuItem mi : coloane.getMenu().getItems()) {
			if (mi.getText().equals(root.getName())) {
				mi.dispose();
				return;
			}
		}
	}

	/**
	 * Check wether a menu is already in the menu bar
	 */
	private boolean alreadyExist() {
		MenuItem coloane = locateColoaneMenu();
		if (coloane == null) { return false; }

		for (MenuItem mi : coloane.getMenu().getItems()) {
			if (mi.getText().equals(root.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Updates a menu (i.e. modifies it in the menubar).
	 */
	public final MenuManager update() {
		remove();
		return build();
	}
}
