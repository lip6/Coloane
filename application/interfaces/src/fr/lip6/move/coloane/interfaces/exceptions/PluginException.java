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
public class PluginException extends Exception {

	/** Serialization stuff */
	private static final long serialVersionUID = 1L;

	/** Message associated to the exception */
	private String message;

	/** Plugin name */
	private String name;

	/** Default constructor */
	public PluginException() {
		this.name = "Unknown Plugin"; //$NON-NLS-1$
		this.message = "Unknown Model Error"; //$NON-NLS-1$
	}

	/**
	 * Constructor
	 * @param message The exception reason
	 */
	public PluginException(final String message) {
		this.message = message; //$NON-NLS-1$
	}

	/**
	 * Constructor
	 * @param name The plugin name
	 * @param message The exception reason
	 */
	public PluginException(final String name, final String message) {
		this.message = message; //$NON-NLS-1$
		this.name = name; //$NON-NLS-1$
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMessage() {
		return this.message;
	}

	/**
	 * @return The name of the plugin that has raised the exception
	 */
	public final String getPluginName() {
		return this.name;
	}
}
