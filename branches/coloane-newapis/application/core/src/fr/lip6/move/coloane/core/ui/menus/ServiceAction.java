package fr.lip6.move.coloane.core.ui.menus;

import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;

import java.util.logging.Logger;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;


/**
 * Action permettant d'ajouter un service au menu.<br>
 * Utilise le ServiceManager pour executer le service attaché.
 */
public class ServiceAction extends Action {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private String serviceId;

	/**
	 * @param service Objet décrivant un service
	 */
	public ServiceAction(IServiceMenu service) {
		super(service.getName(), IAction.AS_PUSH_BUTTON);
		setId(service.getName());
//		setToolTipText(service.getHelps());
		this.serviceId = service.getAssociatedService();
	}

	/** {@inheritDoc} */
	@Override
	public final void run() {
		LOGGER.fine("Execution du service : " + serviceId); //$NON-NLS-1$
	}
}
