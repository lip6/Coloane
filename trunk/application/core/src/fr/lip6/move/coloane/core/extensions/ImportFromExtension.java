package fr.lip6.move.coloane.core.extensions;

import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

public final class ImportFromExtension {
	private static final Logger LOG = Logger.getLogger("fr.lip6.move.coloane.core");

	/**
	 * Attributs du point d'extension
	 */
	private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.core.imports"; //$NON-NLS-1$
	private static final String WIZREF_EXTENSION = "reference"; //$NON-NLS-1$
	private static final String CLASS_EXTENSION = "class"; //$NON-NLS-1$

	private ImportFromExtension() {	}

	/**
	 * Creer une instance de convertiseur.
	 * @param nomConvertiseur nom du convertiseur a instancier
	 * @return un convertiseur
	 * @throws CoreException Exception lors de la creation de une instance
	 */
	public static IImportFrom createConvertInstance(String ref) throws CoreException {
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		IConfigurationElement convertContribution = null;

		LOG.finest("Parcours des contributions :");
		for (int i = 0; i < contributions.length; i++) {
			LOG.finest(i + " -> " + contributions[i].getAttribute(WIZREF_EXTENSION) + " " + contributions[i].getAttribute(CLASS_EXTENSION));
			if (contributions[i].getAttribute(WIZREF_EXTENSION).equals(ref)) {
				LOG.finest("... Extension decouverte !");
				convertContribution = contributions[i];
				break;
			}
		}

		IImportFrom convertInstance = null;
		if (convertContribution != null) {
			LOG.finest("Instantiation de l'extension (classe : " + convertContribution.getAttribute(CLASS_EXTENSION));
			convertInstance = (IImportFrom) convertContribution.createExecutableExtension(CLASS_EXTENSION);
		}

		LOG.finest("Retour de l'instance de conversion");
		return convertInstance;
	}
}
