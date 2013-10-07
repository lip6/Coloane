package fr.lip6.move.coloane.api.alligator;

import fr.lip6.move.coloane.api.alligator.preferences.AlligatorPreferencePage.Data;
import fr.lip6.move.coloane.interfaces.api.services.IApiService;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.SubMenu;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.cosyverif.Client;
import org.cosyverif.alligator.ExecutionWS;
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

    public Map<Identifier, Description> runningDescriptions = new ConcurrentHashMap<Identifier, Description>();

    private ISubMenu menu;

    private Data data;

    private volatile ExecutionWS newServices = null;

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
    public
        ExecutionWS
        getServices() {
        return newServices;
    }

    /**
     * @return the Alligator menu
     */
    public
        ISubMenu
        getMenu() {
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
        void
        setMenu(ISubMenu menu) {
        synchronized (this.menu) {
            this.menu = menu;
        }
    }

    private
        String
        nameOf(Description description) {
        if ("".equals(description.tool()) && "".equals(description.version())) {
            return description.name();
        } else if (!"".equals(description.tool()) && !"".equals(description.version())) {
            return description.name() + " (" + description.tool() + " v" + description.version() + ")";
        } else if ("".equals(description.tool())) {
            return description.name() + " (" + "v" + description.version() + ")";
        } else if ("".equals(description.version())) {
            return description.name() + " (" + description.tool() + ")";
        } else {
            return description.name();
        }
    }

    /**
     * Obtain the Alligator menu
     * 
     * @param monitor
     *        A monitor
     */
    private
        void
        computeMenu(IProgressMonitor monitor) {
        try {
            // Establish connection:
            LOGGER.info("Connecting to " + data.getAddress() + "...");
            Client client = Client.remote(data.getAddress());
            ExecutionWS services = client.execution();
            // Compute menu:
            LOGGER.info("Computing menu...");
            if (services == null || "".equals(services.getName())) {
                LOGGER.warning("Cannot use new alligator services.");
                throw new IllegalStateException();
            } else {
                newServices = services;
            }
            // Add "Refresh" submenu:
            ISubMenu menu = new SubMenu(data.getName(), true, Utility.getImage("alligator-logo.png"));
            IApiService refreshService = new RefreshService(this);
            // Sort services by name:
            Description[] descriptions = newServices.getAvailableServices();
            Comparator<Description> comparator = new Comparator<Description>() {
                @Override
                public
                    int
                    compare(Description lhs,
                            Description rhs) {
                    return (nameOf(lhs)).compareToIgnoreCase(nameOf(rhs));
                }
            };
            Arrays.sort(descriptions, comparator);
            // Create services menu:
            for (final Description service : descriptions) {
                IApiService apiService = new RunService(service, this);
                LOGGER.finer("Adding to '" + data.getName() + "' service '" + service.name() + "'...");
                // Handle sub-menus using name separators:
                String serviceName = nameOf(service);
                StringTokenizer tokenizer = new StringTokenizer(serviceName, ":");
                ISubMenu current = menu;
                do {
                    String name = tokenizer.nextToken()
                                           .trim();
                    // Remove leading number.
                    Matcher m = Pattern.compile("(\\d+\\.)(.*)")
                                       .matcher(name);
                    if (m.matches()) {
                        name = m.group(2)
                                .trim();
                    }
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
                        current.addServiceMenu(new ServiceMenu(name, true, service.help(), apiService, true));
                    }
                } while (tokenizer.hasMoreTokens());
            }
            // Create menus for running services:
            for (Identifier identifier : Identifiers.getIdentifiers()) {
                try {
                    // Icons taken from http://findicons.com/pack/109/play_stop_pause
                    ISubMenu submenu = null;
                    IApiService getResultsService = new ResultService(identifier, this);
                    if (newServices.isFinished(identifier)) {
                        submenu = new SubMenu(identifier.key(), true, Utility.getImage("stopped-small.png"));
                        submenu.addServiceMenu(new ServiceMenu("Get results", true,
                                                               "Obtains the final results of the service.", getResultsService,
                                                               true));
                    } else {
                        submenu = new SubMenu(identifier.key(), true, Utility.getImage("running-small.png"));
                        submenu.addServiceMenu(new ServiceMenu("Get results", true,
                                                               "Obtains the temporary results of the service.",
                                                               getResultsService, true));
                    }
                    IApiService cloneService = new RunService(identifier, this);
                    submenu.addServiceMenu(new ServiceMenu("Clone", true, "Clones the service.", cloneService, true));
                    IApiService killService = new KillService(identifier, this);
                    submenu.addServiceMenu(new ServiceMenu("Kill", true, "Kills the service.", killService, true));
                    menu.addSubMenu(submenu);
                } catch (Exception e) {
                    LOGGER.fine("Cannot load task identifier '" + identifier + "' on '" + data.getAddress() + "'.");
                    Identifiers.remove(identifier);
                }
            }
            menu.addServiceMenu(new ServiceMenu("(Refresh)", true, "Refresh thes menu.", refreshService, true));
            setMenu(menu);
        } catch (Throwable e) {
            e.printStackTrace();
            LOGGER.warning("Connection to " + data.getName() + " caught exception:" + e.toString());
            throw new IllegalStateException();
        }
    }

    @Override
    protected
        IStatus
        run(IProgressMonitor monitor) {
        LOGGER.info("Computing menu of server: " + data.getName());
        if (monitor.isCanceled()) {
            return Status.CANCEL_STATUS;
        }
        try {
            LOGGER.info("Trying to compute menu for new-style alligator.");
            computeMenu(monitor);
        } catch (Exception e) {
        }
        LOGGER.info("Waiting " + data.getRefresh() + " minutes before update of server: " + data.getName());
        this.schedule(data.getRefresh() * 1000 * 60);
        return Status.OK_STATUS;
    }

}
