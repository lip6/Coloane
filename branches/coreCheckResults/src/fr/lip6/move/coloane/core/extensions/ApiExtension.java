package fr.lip6.move.coloane.core.extensions;

import fr.lip6.move.coloane.core.communications.MenuObserver;
import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.session.ISession;
import fr.lip6.move.coloane.core.ui.menus.ColoaneAPIRootMenu;
import fr.lip6.move.coloane.core.ui.menus.ColoaneMenuManager;
import fr.lip6.move.coloane.core.ui.menus.MenuManipulation;
import fr.lip6.move.coloane.interfaces.api.IApi;
import fr.lip6.move.coloane.interfaces.objects.menu.IItemMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

public class ApiExtension {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	
	/**
	 * Extension attributes
	 */
	private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.core.apis"; //$NON-NLS-1$
	private static final String NAME = "name"; //$NON-NLS-1$
	private static final String DESCRIPTION = "description"; //$NON-NLS-1$
	private static final String ICON = "icon"; //$NON-NLS-1$
	private static final String FORMALISM = "formalism"; //$NON-NLS-1$
	private static final String CLASS = "class"; //$NON-NLS-1$

	/**
	 * Looks for all available APIs (for a given formalism)
	 * @param formalism The formalism
	 * @return a list of available APIs
	 * @see ApiDescription
	 */
	public static List<ApiDescription> getAvailableApis(ISession session) {
		List<ApiDescription> availableApis = new ArrayList<ApiDescription>();

		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		for (int i = 0; i < contributions.length; i++) {
			String acceptedFormalism = contributions[i].getAttribute(FORMALISM);
			
			if (acceptedFormalism == null) {
				LOGGER.warning("API not recognized. It does not declare a valid list of accepted formalisms"); //$NON-NLS-1$
				continue;
			}
			if ((acceptedFormalism.equals("*")) || (acceptedFormalism.equalsIgnoreCase(session.getGraph().getFormalism().getName()))) { //$NON-NLS-1$
				try {
					IApi apiClass = (IApi) contributions[i].createExecutableExtension(CLASS);
					ApiDescription api = new ApiDescription(apiClass, contributions[i].getAttribute(NAME), contributions[i].getAttribute(DESCRIPTION), contributions[i].getAttribute(ICON));
						
					// Build the root menu
					LOGGER.finer("Building the " + api.getName() + " root-menu associated with the session"); //$NON-NLS-1$ //$NON-NLS-2$
					ColoaneAPIRootMenu apiMenu = MenuManipulation.buildRootMenu(api.getName(), api.getDescription(), api.getIcon());
					apiClass.addObserver(new MenuObserver(apiMenu));
												
					try {
					// Build sub-menus
					LOGGER.finer("Fetching sub-menus"); //$NON-NLS-1$
					List<IItemMenu> submenus = api.getApiClass().getApiMenus();
					for (IItemMenu submenu : submenus) {
						ColoaneMenuManager newMenu = MenuManipulation.buildSubMenu(apiMenu, submenu);
						if (newMenu != null) {
							apiMenu.add(newMenu);
						}
					}
					api.setRootMenu(apiMenu);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					// Add the API description to the list of available APi for this formalism
					availableApis.add(api);
				} catch (ColoaneException e) {
					LOGGER.warning("Something went wrong during the association with the API : " + contributions[i].getAttribute(NAME)); //$NON-NLS-1$
					LOGGER.warning(e.getLogMessage());
					e.printStackTrace();
				} catch (CoreException e) {
					LOGGER.warning("The API main cannot be loaded." + e.getMessage()); //$NON-NLS-1$
					e.printStackTrace();
				}
			}
		}
		return availableApis;
	}
}
