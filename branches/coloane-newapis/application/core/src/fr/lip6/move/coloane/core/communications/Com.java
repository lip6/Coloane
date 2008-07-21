package fr.lip6.move.coloane.core.communications;

import fr.lip6.move.coloane.core.ui.dialogs.AuthenticationInformation;
import fr.lip6.move.coloane.interfaces.api.connection.IApi;
import fr.lip6.move.coloane.interfaces.api.connection.IApiConnection;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
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
	private static Com instance = null;

	/** L'objet IApi courrant */
	private IApi api = null;

	private IApiConnection connection;

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
	private static IApi getApi(String name) throws CoreException {
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		for (IConfigurationElement element : contributions) {
			if (element.getAttribute("name").equals(name)) { //$NON-NLS-1$
				return (IApi) element.createExecutableExtension("class"); //$NON-NLS-1$
			}
		}
		throw new IllegalArgumentException("l'api " + name + " n'est pas connu");  //$NON-NLS-1$//$NON-NLS-2$
	}

	/**
	 * @return liste des noms des api disponible
	 */
	public static List<String> getApisName() {
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		List<String> apis = new ArrayList<String>();
		for (IConfigurationElement element : contributions) {
			apis.add((String) element.getAttribute("name")); //$NON-NLS-1$
		}
		return apis;
	}

	/**
	 * Renvoie toujours le même objet Com
	 * @return l'interface sur l'objet en charge des communication de Coloane avec une API
	 */
	public static Com getInstance() {
		if (instance == null) {
			LOGGER.config("Creation de l'objet de communications"); //$NON-NLS-1$
			instance = new Com();
		}
		return instance;
	}

	public IConnectionInfo authentication(AuthenticationInformation infos, IProgressMonitor monitor) throws ApiException {
		try {
			api = getApi(infos.getApiType());
		} catch (CoreException e) {
			e.printStackTrace();
		}
		connection = api.getApiConnection();
		connection.setIpServer(infos.getIp());
		connection.setPortServer(infos.getPort());
		connection.setLogin(infos.getLogin());
		connection.setPassword(infos.getPass());
		IConnectionInfo connectionInfo = connection.openConnection();
		return connectionInfo;
	}

	public Object openSession(IGraph graph, IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object closeSession(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		return null;
	}

}
