package fr.lip6.move.coloane.core.extensions;

import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

/**
 * Classe responsable des extensions d'import de modèles
 */
public final class ImportFromExtension {
	/**
	 * Attributs du point d'extension
	 */
	private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.core.imports"; //$NON-NLS-1$
	private static final String WIZREF_EXTENSION = "reference"; //$NON-NLS-1$
	private static final String CLASS_EXTENSION = "class"; //$NON-NLS-1$
	private static final String FORMALISMS_EXTENSION = "id"; //$NON-NLS-1$

	/**
	 * Constructeur
	 */
	private ImportFromExtension() {	}

	/**
	 * Creer une instance de convertiseur.
	 * @param ref référence du convertiseur a instancier
	 * @return un convertiseur
	 * @throws CoreException Exception lors de la creation de une instance
	 */
	public static IImportFrom createConvertInstance(String ref) throws CoreException {
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		IConfigurationElement convertContribution = null;

		for (int i = 0; i < contributions.length; i++) {
			if (contributions[i].getAttribute(WIZREF_EXTENSION).equals(ref)) {
				convertContribution = contributions[i];
				break;
			}
		}

		IImportFrom convertInstance = null;
		if (convertContribution != null) {
			convertInstance = (IImportFrom) convertContribution.createExecutableExtension(CLASS_EXTENSION);
		}

		return convertInstance;
	}

	/**
	 * @param idWizard Id d'une extension d'import
	 * @return Liste des formalismes supportés par cette extension
	 */
	public static List<IFormalism> getFormalisms(String idWizard) {
		List<IFormalism> formalisms = new ArrayList<IFormalism>();
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);

		for (IConfigurationElement contribution : contributions) {
			if (contribution.getAttribute(WIZREF_EXTENSION).equals(idWizard)) {
				for (IConfigurationElement child : contribution.getChildren()) {
					formalisms.add(FormalismManager.getInstance().getFormalismById(child.getAttribute(FORMALISMS_EXTENSION)));
				}
			}
		}

		return formalisms;
	}
}
