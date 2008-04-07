package fr.lip6.move.coloane.core.extensions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import fr.lip6.move.coloane.core.interfaces.IImportFrom;

public class ImportFromExtension {
	/**
	 * Attributs du point d'extension 
	 */
	private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.core.imports"; //$NON-NLS-1$
	private static final String WIZREF_EXTENSION = "reference"; //$NON-NLS-1$
	private static final String CLASS_EXTENSION = "class"; //$NON-NLS-1$
	

	/**
	 * Creer une instance de convertiseur.
	 * @param nomConvertiseur nom du convertiseur a instancier
	 * @return un convertiseur
	 * @throws CoreException Exception lors de la creation de une instance
	 */
	public static IImportFrom createConvertInstance(String ref) throws CoreException{
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		IConfigurationElement convertContribution = null;
		for (int i = 0; i < contributions.length; i++) {
			if(contributions[i].getAttribute(WIZREF_EXTENSION).equals(ref)) {
				convertContribution = contributions[i];
				break;
			}
		}
		
		IImportFrom convertInstance = null;
		if(convertContribution != null) {
			convertInstance = (IImportFrom)convertContribution.createExecutableExtension(CLASS_EXTENSION);
		}
		return convertInstance;
	}
}
