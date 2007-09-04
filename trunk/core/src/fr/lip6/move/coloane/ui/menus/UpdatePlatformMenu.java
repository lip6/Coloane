package fr.lip6.move.coloane.ui.menus;

import fr.lip6.move.coloane.main.Coloane;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * Gestion de lactivation des items du menu platform :
 * <ul>
 * 	<li>Connect Model</li>
 * 	<li>Disconnect Model</li>
 * 	<li>Authentication</li>
 * </ul>
 *
 * On utilise une classe a part car il s'agit ici d'ACTION (au sens GEF) du terme
 */

public class UpdatePlatformMenu implements Runnable {

	private String menuName;
	private boolean state;

	/**
	 * Constructeur
	 * @param menuItem Le menu a considerer
	 * @param newState Le nouvel etat a attribuer au menu
	 */
	public UpdatePlatformMenu(String menuItem, boolean newState) {
		this.menuName = menuItem;
		this.state = newState;
	}

	public final void run() {
		Shell shell;

		try {
			shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		} catch (NullPointerException e) {
			Coloane.getLogger().warning("Shell inaccessible");
			return;
		}

		// Parcours des items de la barre de menus
		for (MenuItem item : shell.getMenuBar().getItems()) {
			if (item.getText().equals(Coloane.getParam("MENUBAR_LABEL"))) { //$NON-NLS-1$

				// Parcours des items du sous-menu Platform
				for (MenuItem subItem : item.getMenu().getItems()) {
					if (subItem.getText().equals(Coloane.getParam("PLATFORM_MENU"))) { //$NON-NLS-1$

						// On cherche le bon item
						for (MenuItem subSubItem : subItem.getMenu().getItems()) {
							if (subSubItem.getText().equals(menuName)) {
								SubContributionItem contributionItem = null;
								Object menuItemData = subSubItem.getData();

								if (menuItemData instanceof SubContributionItem) {
									contributionItem = (SubContributionItem) menuItemData;
									IContributionItem cItems = contributionItem.getInnerItem();

									if (cItems instanceof ActionContributionItem) {
										ActionContributionItem items = (ActionContributionItem) cItems;
										items.getAction().setEnabled(state);
										return;
									}
								}
							}
						}
					}
				}

			}
		}
	}
}
