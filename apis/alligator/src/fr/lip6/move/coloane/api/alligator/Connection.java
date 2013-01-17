package fr.lip6.move.coloane.api.alligator;

import fr.lip6.move.alligator.interfaces.ServiceDescription;
import fr.lip6.move.alligator.interfaces.ServiceManager;
import fr.lip6.move.coloane.api.alligator.preferences.AlligatorPreferencePage.Data;
import fr.lip6.move.coloane.interfaces.api.services.IApiService;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.SubMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.cosyverif.alligator.ExportedServices;
import org.cosyverif.alligator.service.Description;
import org.cosyverif.alligator.service.Identifier;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * Connection to an Alligator platform. This connection is used as a Job that repeatedly asks ist Alligator for its services.
 */
public final class Connection
    extends Job {

    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

    private static final String PING_VALUE = "ping";

    public Map<Identifier, Description> runningDescriptions = new ConcurrentHashMap<Identifier, Description>();

    private ISubMenu menu;

    private Data data;

    @Deprecated
    private volatile ServiceManager oldServices = null;

    private volatile ExportedServices newServices = null;

    /**
     * Create a connection from configuration data:
     * 
     * @param data
     *        The configuration data
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
    @Deprecated
    public
        ServiceManager getOldServices() {
        return oldServices;
    }

    /**
     * @return The service manager
     */
    public
        ExportedServices getServices() {
        return newServices;
    }

    /**
     * @return the Alligator menu
     */
    public
        ISubMenu getMenu() {
        synchronized (menu) {
            return menu;
        }
    }

    /**
     * Set the alligator mane
     * 
     * @param menu
     *        The new menu
     */
    private
        void setMenu(ISubMenu menu) {
        synchronized (this.menu) {
            this.menu = menu;
        }
    }

    /**
     * Obtain the Alligator menu
     * 
     * @param monitor
     *        A monitor
     */
    @Deprecated
    private
        void computeOldMenu(IProgressMonitor monitor) {
        try {
            LOGGER.info("Connecting to " + data.getOldAddress() + "...");
            // Establish connection:
            JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
            factory.setAddress(data.getOldAddress()
                                   .toString());
            ServiceManager services = factory.create(ServiceManager.class);
            // Configure the HTTPConduit used to communicate with Alligator
            HTTPConduit conduit = (HTTPConduit) ClientProxy.getClient(services)
                                                           .getConduit();
            conduit.getClient()
                   .setAllowChunking(false);
            conduit.getClient()
                   .setReceiveTimeout(600000);
            // Compute menu:
            LOGGER.info("Computing menu...");
            if (services == null || !services.ping(PING_VALUE)
                                             .equals(PING_VALUE)) {
                throw new IllegalStateException();
            } else {
                oldServices = services;
            }
            ISubMenu menu = new SubMenu(data.getName() + " (old server)", true, Utility.getImage("alligator-logo.png"));
            IApiService refreshService = new RefreshService(this);
            menu.addServiceMenu(new ServiceMenu("(Refresh)", true, "Refresh thes menu.", refreshService, true));
            List<ServiceDescription> descriptions = oldServices.getServices();
            Comparator<ServiceDescription> comparator = new Comparator<ServiceDescription>() {
                @Override
                public
                    int compare(ServiceDescription lhs, ServiceDescription rhs) {
                    return lhs.getName()
                              .compareToIgnoreCase(rhs.getName());
                }
            };
            Collections.sort(descriptions, comparator);
            for (final ServiceDescription service : descriptions) {
                IApiService apiService = new RunService(service, this);
                LOGGER.finer("Adding to '" + data.getName() + "' (oldstyle) service '" + service.getName() + "' (" +
                             service.getId() + ")");
                // Handle sub-menus using name separators:
                String serviceName = service.getName();
                StringTokenizer tokenizer = new StringTokenizer(serviceName, ":");
                ISubMenu current = menu;
                do {
                    String name = tokenizer.nextToken()
                                           .trim();
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
            throw new IllegalStateException();
        }
    }

    /**
     * Obtain the Alligator menu
     * 
     * @param monitor
     *        A monitor
     */
    private
        void computeMenu(IProgressMonitor monitor) {
        try {
            // Establish connection:
            LOGGER.info("Connecting to " + data.getAddress() + "...");
            JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
            factory.setAddress(data.getAddress()
                                   .toString());
            ExportedServices services = factory.create(ExportedServices.class);
            // Configure the HTTPConduit used to communicate with Alligator
            HTTPConduit conduit = (HTTPConduit) ClientProxy.getClient(services)
                                                           .getConduit();
            conduit.getClient()
                   .setAllowChunking(false);
            conduit.getClient()
                   .setReceiveTimeout(60000);
            // Compute menu:
            LOGGER.info("Computing menu...");
            if (services == null || !services.ping(PING_VALUE)
                                             .equals(PING_VALUE)) {
                LOGGER.warning("Cannot use new alligator services.");
                throw new IllegalStateException();
            } else {
                newServices = services;
            }
            // Add "Refresh" submenu:
            ISubMenu menu = new SubMenu(data.getName(), true, Utility.getImage("alligator-logo.png"));
            IApiService refreshService = new RefreshService(this);
            menu.addServiceMenu(new ServiceMenu("(Refresh)", true, "Refresh thes menu.", refreshService, true));
            // Sort services by name:
            List<Description> descriptions = new ArrayList<Description>(newServices.getAvailableServices());
            Comparator<Description> comparator = new Comparator<Description>() {
                @Override
                public
                    int compare(Description lhs, Description rhs) {
                    return (lhs.getName() + " v" + lhs.getVersion()).compareToIgnoreCase(rhs.getName() + " v" +
                                                                                         lhs.getVersion());
                }
            };
            Collections.sort(descriptions, comparator);
            // Create services menu:
            for (final Description service : descriptions) {
                IApiService apiService = new RunService(service, this);
                LOGGER.finer("Adding to '" + data.getName() + "' service '" + service.getName() + "'...");
                // Handle sub-menus using name separators:
                String serviceName = service.getName() + " v" + service.getVersion();
                StringTokenizer tokenizer = new StringTokenizer(serviceName, ":");
                ISubMenu current = menu;
                do {
                    String name = tokenizer.nextToken()
                                           .trim();
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
                        current.addServiceMenu(new ServiceMenu(name, true, service.getHelp(), apiService, true));
                    }
                } while (tokenizer.hasMoreTokens());
            }
            // Create menus for running services:
            for (Identifier identifier : Identifiers.getIdentifiers()) {
                try {
                    // Icons taken from http://findicons.com/pack/109/play_stop_pause
                    if (data.getAddress()
                            .toString()
                            .startsWith(identifier.getServer()
                                                  .toString())) {
                        ISubMenu submenu = null;
                        IApiService getResultsService = new ResultService(identifier, this);
                        if (newServices.isFinished(identifier)) {
                            submenu = new SubMenu(identifier.getKey(), true, Utility.getImage("stopped-small.png"));
                            submenu.addServiceMenu(new ServiceMenu("Get results", true,
                                                                   "Obtains the final results of the service.",
                                                                   getResultsService, true));
                        } else {
                            submenu = new SubMenu(identifier.getKey(), true, Utility.getImage("running-small.png"));
                            submenu.addServiceMenu(new ServiceMenu("Get results", true,
                                                                   "Obtains the temporary results of the service.",
                                                                   getResultsService, true));
                        }
                        IApiService cloneService = new RunService(identifier, this);
                        submenu.addServiceMenu(new ServiceMenu("Clone", true, "Clones the service.", cloneService, true));
                        IApiService killService = new KillService(identifier, this);
                        submenu.addServiceMenu(new ServiceMenu("Kill", true, "Kills the service.", killService, true));
                        menu.addSubMenu(submenu);

                    }
                } catch (Exception e) {
                    LOGGER.warning("Cannot load task identifier '" + identifier + "'.");
                    Identifiers.remove(identifier);
                }
            }
            setMenu(menu);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Connection to " + data.getName() + " caught exception:" + e.toString());
            throw new IllegalStateException();
        }
    }

    @Override
    protected
        IStatus run(IProgressMonitor monitor) {
        LOGGER.info("Computing menu of server: " + data.getName());
        if (monitor.isCanceled()) {
            return Status.CANCEL_STATUS;
        }
        try {
            try {
                LOGGER.info("Trying to compute menu for new-style alligator.");
                computeMenu(monitor);
            } catch (Exception e) {
                LOGGER.info("Trying to compute menu for old-style alligator.");
                computeOldMenu(monitor);
            }
        } catch (Exception e) {
        }
        LOGGER.info("Waiting " + data.getRefresh() + " minutes before update of server: " + data.getName());
        this.schedule(data.getRefresh() * 1000 * 60);
        return Status.OK_STATUS;
    }

}
