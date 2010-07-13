package fr.lip6.move.coloane.core.extensions;

import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

/**
 * Describe an importe extension.<br>
 */
public final class ImportFromExtension {
	/**
	 * Extension attributes
	 */
	private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.core.imports"; //$NON-NLS-1$
	
	private static final String WIZREF_EXTENSION = "wizard_id"; //$NON-NLS-1$
	private static final String CLASS_EXTENSION = "class"; //$NON-NLS-1$
	private static final String FORMALISMS_EXTENSION = "id"; //$NON-NLS-1$

	/**
	 * Constructor
	 */
	private ImportFromExtension() {	}

	/**
	 * Build a import builder
	 * @param importType référence du convertiseur a instancier
	 * @return un convertiseur
	 * @throws CoreException Exception lors de la creation de une instance
	 */
	public static IImportFrom createConvertInstance(String importType) throws CoreException {
		// Fetch all available builders, and try to match with the importType
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		IConfigurationElement convertContribution = null;
		for (int i = 0; i < contributions.length; i++) {
			if (contributions[i].getAttribute(WIZREF_EXTENSION).equals(importType)) {
				convertContribution = contributions[i];
				break;
			}
		}

		// Set up the builder
		IImportFrom convertInstance = null;
		if (convertContribution != null) {
			convertInstance = (IImportFrom) convertContribution.createExecutableExtension(CLASS_EXTENSION);
		}

		return convertInstance;
	}

	/**
	 * Fetch all available formalism for this format
	 * @param importType The type of import... This information is used in order to find mathing builders
	 * @return The list of supported formalism for this import wizard
	 */
	public static List<IFormalism> getFormalisms(String importType) {
		List<IFormalism> formalisms = new ArrayList<IFormalism>();
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);

		// Browse all extensions, and build the list of formalisms
		for (IConfigurationElement contribution : contributions) {
			if (contribution.getAttribute(WIZREF_EXTENSION).equals(importType)) {
				for (IConfigurationElement child : contribution.getChildren()) {
					formalisms.add(FormalismManager.getInstance().getFormalismById(child.getAttribute(FORMALISMS_EXTENSION)));
				}
			}
		}

		return formalisms;
	}
}
