package fr.lip6.move.coloane.core.ui.menus;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * This class takes a RootMenu and builds a graphic menu for the Eclipse menubar
 * @author Alexandre ORTIZ
 */
public class GraphicalMenu {
	private ISubMenu root;

	/**
	 * Construit un
	 * @param root racine du menu
	 */
	public GraphicalMenu(ISubMenu root) {
		this.root = root;
	}

	/**
	 * Builds recursively a MenuManager from, using a RootMenu given in the constructor.
	 */
	public final MenuManager build() {
		if (root == null) { return null; }
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
				ServiceAction uiAction = new ServiceAction(ui, root.getName(), child.getReference().getName(), child.getName());
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
				ServiceAction exitAction = new ServiceAction(ui, root.getName(), littleChild.getReference().getName(), littleChild.getName());
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
			if (mi.getText().equals(Coloane.getParam("MENUBAR_LABEL"))) { //$NON-NLS-1$
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
