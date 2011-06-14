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
import fr.lip6.move.coloane.interfaces.objects.menu.ServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.services.ConsoleMessage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;

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
 * @author Clément Démoulins
 */
public class AlligatorApi extends AbstractApi implements IApi, IPropertyChangeListener {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

	private static final QName SERVICE_NAME = new QName("http://interfaces.alligator.move.lip6.fr/", "ServiceManagerService");
	private static final String PING_VALUE = "ping";
	
	/**
	 * Default menu returned if the connection with an Alligator server is not established.
	 */
	private static final List<IItemMenu> DISCONNECTED_MENU = Arrays.asList((IItemMenu) new ServiceMenu("Disconnected", true, "", new IApiService() {
		@Override
		public List<IResult> run(IGraph model, IProgressMonitor monitor) throws ServiceException {
			throw new ServiceException("The platform is not connected");
		}
		@Override
		public String getName() { return "Disconnected"; }
		@Override
		public String getDescription() { return ""; }
	}));

	/**
	 * Default menu returned if the connection with an Alligator server is not established.
	 */
	private static final List<IItemMenu> EMPTY_MENU = Arrays.asList((IItemMenu) new ServiceMenu("Connecting", true, "", new IApiService() {
		@Override
		public List<IResult> run(IGraph model, IProgressMonitor monitor) throws ServiceException {
			throw new ServiceException("The platform is not connected");
		}
		@Override
		public String getName() { return "Connecting"; }
		@Override
		public String getDescription() { return ""; }
	}));
	
	/**
	 * Alligator service manager, may be null
	 */
	private ServiceManager serverManager;

	/**
	 */
	public AlligatorApi() {
		IPreferenceStore prefStore = Activator.getDefault().getPreferenceStore();
		prefStore.addPropertyChangeListener(this);

		try {
			connect(new URL(prefStore.getString(PreferenceConstants.P_ALLIGATOR_URL) + "?wsdl"));
		} catch (MalformedURLException e) {
			LOGGER.warning(e.getMessage());
		}
	}
	
	/**
	 * Connect this instance to the Alligator server.
	 * @param wsdlUrl URL of the wsdl of the alligator server, example: http://localhost:9000/servicemanager?wsdl
	 */
	private void connect(URL wsdlUrl) {
		try {
			Service service = Service.create(wsdlUrl, SERVICE_NAME);
			serverManager = service.getPort(ServiceManager.class);
		} catch (WebServiceException e) {
			LOGGER.warning("Connection to " + wsdlUrl + " failed.");
			LOGGER.throwing(this.getClass().getName(), "connect", e);
		}
	}
	
	/**
	 * @return return an updated menu using the current service manager.
	 * If the connection is not established, the menu <code>DISCONNECTED_MENU</code> will be returned.
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

		for (final ServiceDescription service : serverManager.getServices()) {
			apiService = new AlligatorService(service, this);
			LOGGER.finer("Add service id: " + service.getId());
			serviceItem = new ServiceMenu(service.getName(), true, service.getShortDescription(), apiService);
			menu.add(serviceItem);
		}
		return menu;
	}

	/** {@inheritDoc}
	 * @see fr.lip6.move.coloane.interfaces.api.IApi#getInitialApiMenus()
	 */
	@Override
	public final List<IItemMenu> getInitialApiMenus() {
		LOGGER.fine("Load initial alligator menu");
		Job job = new Job("Connection to Alligator") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				final List<IItemMenu> menu = updatedMenu();
				Display.getDefault().asyncExec(new Runnable() {
					@Override
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

	/** {@inheritDoc}
	 * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
	 */
	@Override
	public final void propertyChange(PropertyChangeEvent event) {
		LOGGER.fine("Receive property change: " + event.getProperty());
		if (event.getProperty().equals(PreferenceConstants.P_ALLIGATOR_URL)) {
			try {
				connect(new URL((String) event.getNewValue()));
				firePropertyChange(IApi.API_MENU, null, updatedMenu());
			} catch (MalformedURLException e) {
				LOGGER.warning(e.getMessage());
			}
		}
	}

	/**
	 * @return the current Server manager or null if the connection is not established.
	 */
	public final ServiceManager getServerManager() {
		return serverManager;
	}

	/**
	 * @param message Message to print into the console
	 * @param messageType Type for this message
	 * @see {@link ConsoleMessage}
	 */
	public final void sendConsoleMessage(String message, int messageType) {
		firePropertyChange(API_MESSAGE, null, new ConsoleMessage(message, messageType));
	}
}
