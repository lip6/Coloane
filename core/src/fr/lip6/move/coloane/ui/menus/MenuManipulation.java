package fr.lip6.move.coloane.ui.menus;

import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class MenuManipulation {

	protected MenuManipulation() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * The first step is to find, on the menubar, the ancestor of
	 * the menu we want to change the enabled property.
	 */
	public static void setEnabled(String fatherName, String menuName, boolean enabled) {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

		/*
		 * When we found it, we just verify recursively every child
		 * until we find the Choosen One.
		 */
		for (MenuItem item : shell.getMenuBar().getItems()) {
			if (item.getText().equals("Coloane Services")) {
				setEnabled(item, menuName, enabled);
			}
		}
	}

	/*
	 * If we are on the menu we are looking for, we set
	 * the enable property for it and for all its children
	 */
	private static void setEnabled(MenuItem item, String menuName, boolean enabled) {

		if (menuName.equals(item.getText())) {
			setEnabled(item, enabled);
			return;
		} else if (item.getMenu() != null) { // If this item is not a leaf
			for (MenuItem childItem : item.getMenu().getItems()) {
				setEnabled(childItem, menuName, enabled);
			}
		}
	}

	/*
	 * This method sets the enabled property for a menu and
	 * for all its children.
	 */
	private static void setEnabled(MenuItem item, boolean enabled) {
		item.setEnabled(enabled);

		if (item.getMenu() == null) {
			return;
		}

		for (MenuItem childItem : item.getMenu().getItems()) {
			setEnabled(childItem, enabled);
		}
	}

	/**
	 * Removes a menu from the menubar
	 * @param menuName The name of menu we want to delete
	 */
	public static void remove(String menuName) {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

		for (MenuItem item : shell.getMenuBar().getItems()) {
			if (item.getText().equals("Coloane Services")) {
				remove(item, menuName);
			}
		}
	}

	public static void remove(MenuItem father, String menuName) {
		for (MenuItem mi : father.getMenu().getItems()) {
			if (mi.getText().equals(menuName)) {
				mi.dispose();
				return;
			}
		}
	}
}
