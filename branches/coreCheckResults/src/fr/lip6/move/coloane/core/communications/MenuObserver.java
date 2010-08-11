package fr.lip6.move.coloane.core.communications;

import fr.lip6.move.coloane.core.session.ISession;
import fr.lip6.move.coloane.core.session.SessionManager;
import fr.lip6.move.coloane.core.ui.UserInterfaceManager;
import fr.lip6.move.coloane.core.ui.menus.MenuManipulation;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;
import fr.lip6.move.coloane.interfaces.objects.menu.IItemMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * Handle notifications coming from an API and dedicated to menu updates.
 * 
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class MenuObserver implements IReceptMenuObserver {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** {@inheritDoc} */
	public final void update(List<ISubMenu> menus) {
		for (ISubMenu subMenu : menus) {
			MenuManipulation.build(rootApiMenu, session)
		}
	}

	/**
	 * @param item item concerné
	 * @param map map contenant les mises à jours a effectuer
	 * @return [+] si sa item est actif [-] sinon
	 */
	private String visibility(IItemMenu item, Map<String, IUpdateMenu> map) {
		if (map.containsKey(item.getName())) {
			if (map.get(item.getName()).getState()) {
				return "[+]"; //$NON-NLS-1$
			} else {
				return "[-]"; //$NON-NLS-1$
			}
		} else {
			if (item.isVisible()) {
				return "[+]"; //$NON-NLS-1$
			} else {
				return "[-]"; //$NON-NLS-1$
			}
		}
	}

	/**
	 * Affichage du menu dans la console
	 * @param menu menu qui doit être affiché
	 * @param mapUpdateMenu mise à jours à appliquer
	 * @param dec décalage correspondant à la profondeur actuelle
	 * @return String représentant le menu
	 */
	private String menuToString(ISubMenu menu, Map<String, IUpdateMenu> mapUpdateMenu, String dec) {
		StringBuilder sb = new StringBuilder();

		sb.append(dec).append(visibility(menu, mapUpdateMenu)).append(" [menu] ").append(menu.getName()).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$

		for (IServiceMenu service : menu.getServiceMenus()) {
			sb.append("   ").append(dec).append(visibility(service, mapUpdateMenu)).append(" [service] ").append(service.getName()).append("\n");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		}

		for (IOptionMenu option : menu.getOptions()) {
			sb.append("   ").append(dec).append(visibility(option, mapUpdateMenu)); //$NON-NLS-1$
			if (option.isValidated()) {
				sb.append(" [X] "); //$NON-NLS-1$
			} else {
				sb.append(" [ ] "); //$NON-NLS-1$
			}
			sb.append(" [option] ").append(option.getName()).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		for (ISubMenu subMenu : menu.getSubMenus()) {
			sb.append(menuToString(subMenu, mapUpdateMenu, "   " + dec)); //$NON-NLS-1$
		}

		return sb.toString();
	}
}
