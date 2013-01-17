/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator;

import fr.lip6.move.coloane.api.alligator.preferences.AlligatorPreferencePage;
import fr.lip6.move.coloane.api.alligator.preferences.AlligatorPreferencePage.Data;
import fr.lip6.move.coloane.api.alligator.preferences.PreferenceConstants;
import fr.lip6.move.coloane.interfaces.objects.menu.IItemMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator
    extends AbstractUIPlugin
    implements IPropertyChangeListener {

    // The plug-in ID
    public static final String PLUGIN_ID = "fr.lip6.move.coloane.api.alligator"; //$NON-NLS-1$

    private static Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator");

    // The shared instance
    private static Activator plugin;

    private List<Connection> connections;

    /**
     * The constructor
     */
    public Activator() {
        connections = new ArrayList<Connection>();
    }

    /**
     * @return The menus defined by Alligators.
     */
    public final
        List<IItemMenu> getMenus() {
        List<IItemMenu> result = new ArrayList<IItemMenu>();
        for (Connection connection : connections) {
            result.add(connection.getMenu());
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    public final
        void start(BundleContext context)
            throws Exception {
        super.start(context);
        plugin = this;
        // Create connections to Alligators and fetch their menus:
        for (AlligatorPreferencePage.Data data : AlligatorPreferencePage.fromPreferences()) {
            connections.add(new Connection(data));
        }
        getPreferenceStore().addPropertyChangeListener(plugin);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    public final
        void stop(BundleContext context)
            throws Exception {
        // Cancel all connections:
        for (Connection connection : connections) {
            connection.cancel();
        }
        connections.clear();
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static
        Activator getDefault() {
        return plugin;
    }

    @Override
    public final
        void propertyChange(PropertyChangeEvent event) {
        LOGGER.fine("Receive property change: " + event.getProperty());
        if (event.getProperty()
                 .equals(PreferenceConstants.P_ALLIGATOR_LIST)) {
            // Cancel all connections:
            for (Connection connection : connections) {
                connection.cancel();
            }
            connections.clear();
            // Connect:
            for (Data data : AlligatorPreferencePage.fromPreferences()) {
                connections.add(new Connection(data));
            }
        }
    }

}
