package fr.lip6.move.coloane.core.extensions;

import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

/**
 * Classe responsable des extensions d'export de modèles
 */
public final class ExportToExtension {

	/**
	 * Attributs du point d'extension 'exports'
	 */
	private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.core.exports"; //$NON-NLS-1$
	private static final String WIZREF_EXTENSION = "reference"; //$NON-NLS-1$
	private static final String CLASS_EXTENSION = "class"; //$NON-NLS-1$
	private static final String EXT_EXTENSION = "extension"; //$NON-NLS-1$
	private static final String FORMALISMS_EXTENSION = "id"; //$NON-NLS-1$

	/**
	 * Constructeur
	 */
	private ExportToExtension() { }

	/**
	 * @param idWizard nom de l'extension d'export
	 * @param formalism formalisme du modèle à exporter
	 * @return <code>true</code> si export accepte ce formalisme
	 */
	public static boolean canPerform(String idWizard, IFormalism formalism) {
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		for (IConfigurationElement contribution : contributions) {
			if (contribution.getAttribute(WIZREF_EXTENSION).equals(idWizard)) {
				for (IConfigurationElement child : contribution.getChildren()) {
					if (child.getAttribute(FORMALISMS_EXTENSION).equals(formalism.getId())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Consulte le registre des extensions pour trouver l'extension de fichier associee au wizard invoque
	 * @param ref La reference du wizard
	 * @return L'extension de fichier sous forme de chaine de caracteres (ou null)
	 */
	public static String findExtension(String ref) {
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		for (int i = 0; i < contributions.length; i++) {
			if (contributions[i].getAttribute(WIZREF_EXTENSION).equals(ref)) {
				return contributions[i].getAttribute(EXT_EXTENSION);
			}
		}
		return null;
	}

	/**
	 * Creer une instance de convertiseur
	 * @param ref référence du convertiseur a instancier
	 * @return un convertiseur
	 * @throws CoreException Exception lors de la creation de une instance
	 */
	public static IExportTo createConvertInstance(String ref) throws CoreException {
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		IConfigurationElement convertContribution = null;
		for (int i = 0; i < contributions.length; i++) {
			if (contributions[i].getAttribute(WIZREF_EXTENSION).equals(ref)) {
				convertContribution = contributions[i];
				break;
			}
		}

		IExportTo convertInstance = null;
		if (convertContribution != null) {
			convertInstance = (IExportTo) convertContribution.createExecutableExtension(CLASS_EXTENSION);
		}
		return convertInstance;
	}
}
