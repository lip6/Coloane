package fr.lip6.move.coloane.core.ui.menus;

import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;

import java.util.logging.Logger;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;

/**
 * Define an action that will execute a service
 */
public class ServiceAction extends Action implements IStatedElement {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** All information about the service */
	private IServiceMenu serviceDescription;

	/** Visible state */
	private boolean visible = false;

	/**
	 * Constructor
	 * @param service the service description
	 */
	public ServiceAction(IServiceMenu service) {
		super(service.getName(), IAction.AS_PUSH_BUTTON);
		setId(service.getName());
		this.serviceDescription = service;
		this.visible = service.isVisible();
	}
	
	/** {@inheritDoc} */
	@Override
	public final void run() {
		LOGGER.fine("Service execution : " + serviceDescription.getAssociatedService()); //$NON-NLS-1$
	}

	/** {@inheritDoc} */
	public boolean getState() {
		return visible;
	}

	/** {@inheritDoc} */
	public void setState(boolean state) {
		this.visible = state;
	}
}
