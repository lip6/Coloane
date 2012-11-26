package fr.lip6.move.coloane.api.alligator;

import fr.lip6.move.alligator.interfaces.ServiceDescription;
import fr.lip6.move.alligator.interfaces.ServiceManager;
import fr.lip6.move.coloane.api.alligator.preferences.AlligatorPreferencePage.Data;
import fr.lip6.move.coloane.interfaces.api.services.IApiService;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.SubMenu;

import java.util.StringTokenizer;
import java.util.logging.Logger;

import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * Connection to an Alligator platform.
 * This connection is used as a Job that repeatedly asks ist Alligator for its services.
 */
public final class Connection extends Job {
	
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

	private static final String PING_VALUE = "ping";

	private ISubMenu menu;
	
	private Data data;
	
	private volatile ServiceManager serviceManager;

	/**
	 * Create a connection from configuration data:
	 * @param data The configuration data
	 */
	public Connection(Data data) {
		super("Connection to " + data.getName());
		this.menu = new SubMenu(data.getName() + " (connecting)", true, null);
		this.data = data;
		this.setPriority(Job.LONG);
		this.setUser(false);
		this.schedule();
	}
	
	/**
	 * @return The service manager
	 */
	public ServiceManager getServiceManager() {
		synchronized (serviceManager) {
			return serviceManager;
		}
	}

	/**
	 * @return the Alligator menu
	 */
	public ISubMenu getMenu() {
		synchronized (menu) {
			return menu;
		}
	}

	/**
	 * Set the alligator mane
	 * @param menu The new menu
	 */
	private void setMenu(ISubMenu menu) {
		synchronized (menu) {
			this.menu = menu;
		}
	}
	
	/**
	 * Obtain the Alligator menu
	 * @param monitor A monitor
	 */
	private void computeMenu(IProgressMonitor monitor) {
		try {
		// Establish connection:
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress(data.getAddress().toString());
		serviceManager = factory.create(ServiceManager.class);
		// Configure the HTTPConduit used to communicate with Alligator
		HTTPConduit conduit = (HTTPConduit) ClientProxy.getClient(serviceManager).getConduit();
		conduit.getClient().setAllowChunking(false);
		conduit.getClient().setReceiveTimeout(600000);
		// Compute menu:
		if (serviceManager == null || !serviceManager.ping(PING_VALUE).equals(PING_VALUE)) {
			setMenu(new SubMenu(data.getName() + " (disconnected)", true, null));
			return;
		}
		ISubMenu menu = new SubMenu(data.getName(), true, null);
		// TODO: Add sub-menus for "Connect", "Disconnect" and "Refresh".
		for (final ServiceDescription service: serviceManager.getServices()) {
			IApiService apiService = new AlligatorService(service, this);
			LOGGER.finer("Adding to '" + data.getName() + "' service '" + service.getName() + "' (" + service.getId() + ")");
			// Handle sub-menus using name separators:
			String serviceName = service.getName();
			StringTokenizer tokenizer = new StringTokenizer(serviceName, ":");
			ISubMenu current = menu;
			do {
				String name = tokenizer.nextToken().trim();
				if (tokenizer.hasMoreTokens()) {
					// Token is a sub-menu that should be created (or not):
					ISubMenu child = current.getSubMenu(name);
					if (child == null) {
						ISubMenu created = new SubMenu(name, true);
						current.addSubMenu(created);
						current = created;
					} else {
						current = child;
					}
				} else {
					// Add service menu:
					current.addServiceMenu(new ServiceMenu(name, true, service.getShortDescription(), apiService, true));
				}
			} while (tokenizer.hasMoreTokens());
		}
		setMenu(menu);
		} catch (Exception e) {
			LOGGER.warning("Connection to " + data.getName() + " caught exception:" + e.toString());
			setMenu(new SubMenu(data.getName() + " (disconnected)", true, null));
		}
	}
	
	@Override
	protected IStatus run(IProgressMonitor monitor) {
		if (monitor.isCanceled()) {
			return Status.CANCEL_STATUS;
		}
		computeMenu(monitor);
		LOGGER.finer("Waiting " + data.getRefresh() + " minutes before update of server: " + data.getName());
		this.schedule(data.getRefresh() * 1000 * 60);
		return Status.OK_STATUS;
	}

}
