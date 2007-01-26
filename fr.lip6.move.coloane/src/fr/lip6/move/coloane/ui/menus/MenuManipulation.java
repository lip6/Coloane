package fr.lip6.move.coloane.ui.menus;

import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class MenuManipulation {
	/*
	 * The first step is to find, on the menubar, the ancestor of
	 * the menu we want to change the enabled property.
	 */
	public static void setEnabled(String fatherName, String menuName,
			boolean enabled) {
		Shell shell =
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		
		/*
		 * When we found it, we just verify recursively every child
		 * until we find the Choosen One.
		 */
		for (MenuItem item : shell.getMenuBar().getItems())
			if(item.getText().equals(fatherName))
				setEnabled(item, menuName, enabled);
	}
	
	/*
	 * If we are on the menu we are looking for, we set
	 * the enable property for it and for all its children
	 */
	private static void setEnabled(MenuItem item,
			String menuName, boolean enabled) {
		
		if(menuName.equals(item.getText())) {
			setEnabled(item, enabled);
			return;
		} else if (item.getMenu() != null) // If this item is not a leaf
			for(MenuItem childItem : item.getMenu().getItems())
				setEnabled(childItem, menuName, enabled);
	}
	
	/*
	 * This method sets the enabled property for a menu and
	 * for all its children.
	 */
	private static void setEnabled(MenuItem item,
			boolean enabled) {
		item.setEnabled(enabled);
		
		for (MenuItem childItem : item.getMenu().getItems()) {
			setEnabled(childItem, enabled);
		}
	}
}
