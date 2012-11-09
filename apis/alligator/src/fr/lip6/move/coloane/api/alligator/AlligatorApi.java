/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator;

import fr.lip6.move.alligator.interfaces.ServiceDescription;
import fr.lip6.move.alligator.interfaces.ServiceManager;
import fr.lip6.move.coloane.api.alligator.preferences.PreferenceConstants;
import fr.lip6.move.coloane.interfaces.api.AbstractApi;
import fr.lip6.move.coloane.interfaces.api.IApi;
import fr.lip6.move.coloane.interfaces.api.services.IApiService;
import fr.lip6.move.coloane.interfaces.exceptions.ServiceException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.menu.IItemMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.SubMenu;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.services.ConsoleMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.Display;

/**
 * Api extension to manage invocation of service from Alligator platform.
 * 
 * @author Clément Démoulins
 */
public class AlligatorApi extends AbstractApi implements IApi,
		IPropertyChangeListener {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

	private static final String PING_VALUE = "ping";

	/**
	 * Default menu returned if the connection with an Alligator server is not
	 * established.
	 */
	private static final List<IItemMenu> DISCONNECTED_MENU = Arrays.asList((IItemMenu) new ServiceMenu("Disconnected", true, "",
					new IApiService() {
						public List<IResult> run(IGraph model, IProgressMonitor monitor) throws ServiceException {
							return Collections.emptyList();
						}

						public String getName() {
							return "Disconnected";
						}

						public String getDescription() {
							return "";
						}
					}));

	/**
	 * Default menu returned if the connection with an Alligator server is not
	 * established.
	 */
	private static final List<IItemMenu> EMPTY_MENU = Arrays.asList((IItemMenu) new ServiceMenu("Connecting", true, "",
					new IApiService() {
						public List<IResult> run(IGraph model, IProgressMonitor monitor) throws ServiceException {
							throw new ServiceException("The platform is not connected");
						}

						public String getName() {
							return "Connecting";
						}

						public String getDescription() {
							return "";
						}
					}));

	/**
	 * Alligator service manager, may be null
	 */
	private volatile ServiceManager serverManager;

	/**
	 */
	public AlligatorApi() {
		IPreferenceStore prefStore = Activator.getDefault().getPreferenceStore();
		prefStore.addPropertyChangeListener(this);

		connect(prefStore.getString(PreferenceConstants.P_ALLIGATOR_URL));
	}

	/**
	 * Connect this instance to the Alligator server.
	 * 
	 * @param address
	 *            URL of the alligator server, example:
	 *            http://localhost:9000/servicemanager
	 */
	private void connect(final String address) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress(address);
		serverManager = factory.create(ServiceManager.class);
		System.err.println("# " + serverManager.getClass());
		HTTPConduit conduit = (HTTPConduit) ClientProxy.getClient(serverManager).getConduit();
		System.err.println(ClientProxy.getClient(serverManager).getConduit());
		conduit.getClient().setAllowChunking(false);
		conduit.getClient().setReceiveTimeout(600000); // 10 minutes
	}

	/**
	 * @return return an updated menu using the current service manager. If the
	 *         connection is not established, the menu
	 *         <code>DISCONNECTED_MENU</code> will be returned.
	 */
	private List<IItemMenu> updatedMenu() {
		if (serverManager == null || !serverManager.ping(PING_VALUE).equals(PING_VALUE)) {
			LOGGER.finer("Disconnected mode");
			return DISCONNECTED_MENU;
		}

		LOGGER.finer("Creating new menu");
		List<IItemMenu> menu = new ArrayList<IItemMenu>();
		IServiceMenu serviceItem;
		IApiService apiService;

		Map<String, ISubMenu> parentMenus = new HashMap<String, ISubMenu>();
		for (final ServiceDescription service : serverManager.getServices()) {
			apiService = new AlligatorService(service, this);
			LOGGER.finer("Add service id: " + service.getId());

			String serviceName = service.getName();
			String parentService = null;
			String[] array = serviceName.split(":");
			if (array.length == 2) {
				serviceName = array[1];
				parentService = array[0];
			}
			serviceItem = new ServiceMenu(service.getName(), true,
					service.getShortDescription(), apiService, true);

			if (parentService != null) {
				ISubMenu parent = parentMenus.get(parentService);
				if (parent == null) {
					// create it
					parent = new SubMenu(parentService, true);
					parentMenus.put(parentService, parent);
					menu.add(parent);
				}
				parent.addServiceMenu(serviceItem);
			} else {
				menu.add(serviceItem);
			}
		}
		Collections.sort(menu, new Comparator<IItemMenu>() {
			public int compare(IItemMenu o1, IItemMenu o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		return menu;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see fr.lip6.move.coloane.interfaces.api.IApi#getInitialApiMenus()
	 */
	public final List<IItemMenu> getInitialApiMenus() {
		LOGGER.fine("Load initial alligator menu");
		Job job = new Job("Connection to Alligator") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				final List<IItemMenu> menu = updatedMenu();
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						firePropertyChange(IApi.API_MENU, null, menu);
					}
				});
				return Status.OK_STATUS;
			}
		};
		job.setUser(false);
		job.schedule();
		return EMPTY_MENU;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
	 */
	public final void propertyChange(PropertyChangeEvent event) {
		LOGGER.fine("Receive property change: " + event.getProperty());
		if (event.getProperty().equals(PreferenceConstants.P_ALLIGATOR_URL)) {
			connect((String) event.getNewValue());
			firePropertyChange(IApi.API_MENU, null, updatedMenu());
		}
	}

	/**
	 * @return the current Server manager or null if the connection is not
	 *         established.
	 */
	public final ServiceManager getServerManager() {
		return serverManager;
	}

	/**
	 * @param message
	 *            Message to print into the console
	 * @param messageType
	 *            Type for this message
	 * @see {@link ConsoleMessage}
	 */
	public final void sendConsoleMessage(String message, int messageType) {
		firePropertyChange(API_MESSAGE, null, new ConsoleMessage(message,
				messageType));
	}
}
