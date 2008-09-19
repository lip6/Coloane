package fr.lip6.move.coloane.core.extensions;

import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

/**
 * Extension en charge de gérer les modèles d'exemples proposés lors de la création d'un nouveau modèle
 */
public final class ExampleExtension {

	/**
	 * Attributs du point d'extension 'exports'
	 */
	private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.core.examples"; //$NON-NLS-1$
	private static final String NAME = "name"; //$NON-NLS-1$
	private static final String BUILDER = "build"; //$NON-NLS-1$
	private static final String DESCRIPTION = "description"; //$NON-NLS-1$
	private static final String FORMALISM = "formalism"; //$NON-NLS-1$

	/** Private constructor */
	private ExampleExtension() { }

	/**
	 * Recherche tous les modèles exemples disponibles pour ce formalisme
	 * @param formalism Le formalisme cours d'utilisation
	 * @return Une map qui contient le nom du modèle d'exemple plus une description
	 */
	public static Map<String, String> getModelsName(String formalism) {
		Map<String, String> toReturn = new HashMap<String, String>();

		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		for (int i = 0; i < contributions.length; i++) {
			if (contributions[i].getAttribute(FORMALISM).equalsIgnoreCase(formalism)) {
				toReturn.put(contributions[i].getAttribute(NAME), contributions[i].getAttribute(DESCRIPTION));
			}
		}
		return toReturn;
	}

	/**
	 * Creer une instance de convertiseur
	 * @param name nom du convertiseur a instancier
	 * @return un convertiseur
	 * @throws CoreException Exception lors de la creation de une instance
	 */
	public static IGraph getModel(String name) throws CoreException {
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		IConfigurationElement convertContribution = null;
		for (int i = 0; i < contributions.length; i++) {
			if (contributions[i].getAttribute(NAME).equals(name)) {
				convertContribution = contributions[i];
				break;
			}
		}

		IExample convertInstance = null;
		if (convertContribution != null) {
			convertInstance = (IExample) convertContribution.createExecutableExtension(BUILDER);
		}
		return convertInstance.buildModel();
	}
}
