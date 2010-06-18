package fr.lip6.move.coloane.core.communications;

import fr.lip6.move.coloane.core.motor.Motor;
import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.ui.UserInterface;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMenu;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;
import fr.lip6.move.coloane.interfaces.objects.menu.IItemMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;

import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * Permet d'être notifié des changements de menu
 */
public class ReceptMenuObserver implements IReceptMenuObserver {
	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** {@inheritDoc} */
	public final void update(IReceptMenu menu) {
		if (menu.getMenus() != null) {
			LOGGER.finer("Réception d'une nouvelle liste de menu : " + menu.getMenus()); //$NON-NLS-1$
			// Affichage du menu dans la console pour le debug
			for (ISubMenu subMenu : menu.getMenus()) {
				LOGGER.finest(subMenu.getName() + "\n" + menuToString(subMenu, menu.getUpdateMenus(), "")); //$NON-NLS-1$ //$NON-NLS-2$
			}

			UserInterface.getInstance().drawMenus(menu.getMenus());
		}
		if (menu.getUpdateMenus() != null) {
			LOGGER.finer("Réception d'une mise à jour de menu : " + menu.getUpdateMenus()); //$NON-NLS-1$
			UserInterface.getInstance().updateMenu(menu.getUpdateMenus());

			// Affichage d'une mise à jour
			if (menu.getMenus() == null) {
				for (String s : menu.getUpdateMenus().keySet()) {
					LOGGER.finest("Menu update - " + s + " = " + menu.getUpdateMenus().get(s).getState()); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		}
		if (menu.getServices() != null && SessionManager.getInstance().getCurrentSession() != null) {
			ISession session = SessionManager.getInstance().getCurrentSession();
			session.addAllServices(menu.getServices());
		}

		for (Job job : Job.getJobManager().find(null)) {
			if (job.getName().equals(Motor.OPEN_SESSION_JOB)) {
				job.done(Status.OK_STATUS);
			}
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
