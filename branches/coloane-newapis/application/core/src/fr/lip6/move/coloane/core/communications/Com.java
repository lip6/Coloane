package fr.lip6.move.coloane.core.communications;

import fr.lip6.move.coloane.interfaces.api.connection.IApi;

import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

/**
 * Objet en charge de toutes les communications avec une API de communication.
 * Ces API sont connectées aux serveurs de services (type FrameKit)<br>
 */
public final class Com implements ICom {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** L'identifiant du point d'extension définissant une API */
	private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.core.apis"; //$NON-NLS-1$

	/** L'instance de Com */
	private static ICom instance = null;

	/** L'objet IApi courrant */
	private IApi api = null;

	/**
	 * Construteur de l'objet en charge des communication avec une API
	 */
	private Com() {	}

	/**
	 * Creer une instance d'une API de communication
	 * @param name Le nom de l'API qu'on souhaite instancier
	 * @return une API fraîchement créée
	 * @throws CoreException Exception lors de la creation d'une instance
	 */
	public static IApi getModel(String name) throws CoreException {
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		IConfigurationElement convertContribution = null;
		for (int i = 0; i < contributions.length; i++) {
			if (contributions[i].getAttribute("name").equals(name)) { //$NON-NLS-1$
				convertContribution = contributions[i];
				break;
			}
		}

		IApi api = null;
		if (convertContribution != null) {
			api = (IApi) convertContribution.createExecutableExtension("class"); //$NON-NLS-1$
		}
		return api;
	}

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

	/**
	 * @return instance de l'api de communication
	 */
	public IApi getApi() {
		return api;
	}

	/**
	 * Permet de modifier l'api de communication
	 * @param api nouvelle api à utiliser
	 */
	public void setApi(IApi api) {
		this.api = api;
	}
}
