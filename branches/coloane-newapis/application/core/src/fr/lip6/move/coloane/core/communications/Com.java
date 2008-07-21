package fr.lip6.move.coloane.core.communications;

import java.util.logging.Logger;

/**
 * Objet en charge de toutes les communications avec une API de communication.
 * Ces API sont connectées aux serveurs de services (type FrameKit)<br>
 */
public final class Com implements ICom {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** L'instance de Com */
	private static ICom instance = null;

	/**
	 * Construteur de l'objet en charge des communication avec une API
	 */
	private Com() {	}

	/**
	 * Renvoie toujours le même objet Com
	 * @return l'interface sur l'objet en charge des communication de Coloane avec une API
	 */
	public static ICom getInstance() {
		if (instance == null) {
			LOGGER.config("Creation de l'objet de communications"); //$NON-NLS-1$
			instance = new Com();
		}
		return instance;
	}
}
