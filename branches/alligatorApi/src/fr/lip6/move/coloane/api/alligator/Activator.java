package fr.lip6.move.coloane.api.alligator;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "fr.lip6.move.coloane.api.alligator"; //$NON-NLS-1$
	
	private static Logger LOGGER;

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/** {@inheritDoc}
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public final void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator");
		LOGGER.setLevel(Level.ALL);
	}

	/** {@inheritDoc}
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public final void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
