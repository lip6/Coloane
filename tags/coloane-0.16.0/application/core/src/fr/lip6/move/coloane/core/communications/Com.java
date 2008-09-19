package fr.lip6.move.coloane.core.communications;

import fr.lip6.move.coloane.core.ui.dialogs.AuthenticationInformation;
import fr.lip6.move.coloane.interfaces.api.IApi;
import fr.lip6.move.coloane.interfaces.api.IApiConnection;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptServiceStateObserver;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;

/**
 * Objet en charge de toutes les communications avec une API de communication.<br>
 * Ces API sont connectées aux serveurs de services (type FrameKit)<br>
 * <br>
 * Cette est faite pour être manipulé uniquement par le package motor,
 * pour accéder accéder au fonctionnalité de l'api de communication, il faut
 * utilisé les méthodes de la classe Motor
 */
public final class Com implements ICom {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** L'identifiant du point d'extension définissant une API */
	private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.core.apis"; //$NON-NLS-1$

	/** L'instance de Com */
	private static Com instance = null;

	private IApiConnection connection;

	/**
	 * Construteur de l'objet en charge des communication avec une API
	 */
	private Com() {	}

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

	/**
	 * Créer une instance d'une API de communication
	 * @param name Le nom de l'API qu'on souhaite instancier
	 * @return une API fraîchement créée
	 * @throws CoreException Exception lors de la creation d'une instance
	 */
	private IApi getApi(String name) throws CoreException {
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		for (IConfigurationElement element : contributions) {
			LOGGER.finest("Detection de l'API : " + element.getAttribute("name"));  //$NON-NLS-1$//$NON-NLS-2$
			if (element.getAttribute("name").equals(name)) { //$NON-NLS-1$
				return (IApi) element.createExecutableExtension("class"); //$NON-NLS-1$
			}
		}
		throw new IllegalArgumentException("l'API '" + name + "' n'est pas connue");  //$NON-NLS-1$//$NON-NLS-2$
	}

	/**
	 * @return liste des noms des APIs disponibles
	 */
	public List<String> getApisName() {
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		List<String> apis = new ArrayList<String>();
		for (IConfigurationElement element : contributions) {
			apis.add((String) element.getAttribute("name")); //$NON-NLS-1$
		}
		return apis;
	}

	/**
	 * Authentification
	 * @param infos informations de connexion (contient login, pass etc...)
	 * @param monitor le moniteur d'avancement
	 * @return Les informations de connexions (résultat)
	 * @throws ApiException En cas de problème
	 */
	public IConnectionInfo authentication(AuthenticationInformation infos, IProgressMonitor monitor) throws ApiException {
		IApi api;
		monitor.beginTask(Messages.Com_0, 4);
		try {
			api = getApi(infos.getApiType());
		} catch (CoreException e) {
			LOGGER.warning("Impossible d'instancier l'API désirée : " + infos.getApiType()); //$NON-NLS-1$
			e.printStackTrace();
			return null;
		}
		monitor.worked(1);

		// Création d'un objet de connection
		monitor.subTask(Messages.Com_1);
		connection = api.createApiConnection();
		monitor.worked(1);

		// Observers pour tous les messages asynchrones
		// TODO : dans un Thread ou pas ?
		monitor.subTask(Messages.Com_2);
		connection.setBrutalInterruptObserver(new BrutalInterruptObserver(), false);
		connection.setReceptMessageObserver(new ReceptMessageObserver(), false);
		connection.setReceptDialogObserver(new ReceptDialogObserver(), false);
		connection.setReceptMenuObserver(new ReceptMenuObserver(), false);
		connection.setReceptResultObserver(new ReceptResultObserver(), false);
		monitor.worked(1);

		monitor.subTask(Messages.Com_3);
		IConnectionInfo connectionInfo = connection.openConnection(infos.getLogin(), infos.getPass(), infos.getIp(), infos.getPort());
		monitor.worked(1);
		return connectionInfo;
	}

	/**
	 * Déconnexion
	 * @param softMode Le softMode détermine si les sessions connectées doivent être déconnectées proprement par l'API
	 */
	public void breakConnection(boolean softMode) {
		// On activele soft mode : Les sessions doivent être déconnectées d'abord
		connection.closeConnection(softMode);
	}

	/**
	 * @return une nouvelle IApiSession non connecté
	 * @throws ApiException {@link IApiSession}
	 */
	public IApiSession createApiSession() throws ApiException {
		return connection.createApiSession();
	}

	/**
	 * Ajouter un observateur
	 * @param o observateur de service
	 */
	public void setReceptServiceStateObserver(IReceptServiceStateObserver o) {
		connection.setReceptServiceStateObserver(o, false);
	}
}
