package fr.lip6.move.coloane.interfaces.exceptions;

/**
 * Exception that notifies something went wrong inside a plugin.<br>
 * Two things are detailed:
 * <ul>
 * 	<li>The plugin name</li>
 * 	<li>The reason of the exception</li>
 * </ul>
 *
 * @author Jean-Baptiste Voron
 */
public class PluginException extends ExtensionException {

	/** Serialization stuff */
	private static final long serialVersionUID = 1L;

	/** Plugin name */
	private String pluginName;

	/** Default constructor */
	public PluginException() {
		super();
		this.pluginName = "Unknown Plugin"; //$NON-NLS-1$
	}

	/**
	 * Constructor
	 * @param message The exception reason
	 */
	public PluginException(final String message) {
		super(message);
		this.pluginName = "Unknown Plugin"; //$NON-NLS-1$
	}

	/**
	 * Constructor
	 * @param pluginName The plugin name
	 * @param message The exception reason
	 */
	public PluginException(final String pluginName, final String message) {
		super(message);
		this.pluginName = pluginName; //$NON-NLS-1$
	}

	/**
	 * @return The name of the plugin that has raised the exception
	 */
	public final String getPluginName() {
		return this.pluginName;
	}
}
