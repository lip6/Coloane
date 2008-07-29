package fr.lip6.move.coloane.core.ui.menus;

import fr.lip6.move.coloane.core.motor.Motor;
import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.service.IService;

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

	private ISession session;

	/**
	 * @param service Objet décrivant un service
	 * @param session session attaché à ce service
	 */
	public ServiceAction(IServiceMenu service, ISession session) {
		super(service.getName(), IAction.AS_PUSH_BUTTON);
		setId(service.getName());
//		setToolTipText(service.getHelps());
		this.serviceId = service.getAssociatedService();
		this.session = session;
	}

	/** {@inheritDoc} */
	@Override
	public final void run() {
		LOGGER.fine("Execution du service : " + serviceId); //$NON-NLS-1$
		IService service = session.getService(serviceId);
		if (service == null) {
			LOGGER.warning("Service non disponible : " + serviceId); //$NON-NLS-1$
		}
		Motor.getInstance().askForService(service);
	}
}
