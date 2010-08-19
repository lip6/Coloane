package fr.lip6.move.coloane.api.warehouse;

import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "fr.lip6.move.coloane.api.warehouse";

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() { }

	/**
	 * {@inheritDoc}
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/**
	 * {@inheritDoc}
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
	
	/**
	 * Fetch the value associated to a plug-in property<br>
	 * All the properties are stored in a single file: plugin.properties<br>
	 * @param key parameter identifier
	 * @return parameter value or <code>null</code> if the parameter does not exist
	 */
	public static String getParam(String key) {
		try {
			return Platform.getResourceBundle(plugin.getBundle()).getString(key);
		} catch (NullPointerException e) {
			return null;
		}
	}
}
