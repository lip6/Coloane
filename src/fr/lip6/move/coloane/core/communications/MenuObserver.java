package fr.lip6.move.coloane.core.communications;

import fr.lip6.move.coloane.core.ui.menus.ColoaneAPIRootMenu;
import fr.lip6.move.coloane.core.ui.menus.MenuManipulation;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

/**
 * Handle notifications coming from an API and dedicated to menu updates.
 * 
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class MenuObserver implements Observer {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	
	/** Session handled by this observer */
	private ColoaneAPIRootMenu rootMenu;
	
	public MenuObserver(ColoaneAPIRootMenu rootMenu) {
		this.rootMenu = rootMenu;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void update(Observable o, Object newMenus) {
		LOGGER.warning("Menu should be updated"); //$NON-NLS-1$
		LOGGER.finer("Cleaning..."); //$NON-NLS-1$
		this.rootMenu.removeAll();

		// Build sub-menus
		LOGGER.finer("Fetching new sub-menus"); //$NON-NLS-1$
		List<ISubMenu> submenus = (List<ISubMenu>) newMenus; 
		for (ISubMenu submenu : submenus) {
			this.rootMenu.add(MenuManipulation.buildSubMenu(submenu));
		}
	}
}
