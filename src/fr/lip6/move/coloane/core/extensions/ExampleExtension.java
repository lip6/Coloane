package fr.lip6.move.coloane.core.extensions;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.interfaces.exceptions.PluginException;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

/**
 * Allow plug-ins to define some model examples.<br>
 * Those examples will be used and displayed during the creation wizard.
 * 
 * @author Jean-Baptiste Voron
 */
public final class ExampleExtension {

	/**
	 * Extension attributes
	 */
	private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.core.examples"; //$NON-NLS-1$
	private static final String NAME = "name"; //$NON-NLS-1$
	private static final String BUILDER = "build"; //$NON-NLS-1$
	private static final String DESCRIPTION = "description"; //$NON-NLS-1$
	private static final String FORMALISM = "formalism"; //$NON-NLS-1$

	/** Private constructor */
	private ExampleExtension() { }

	/**
	 * Looks for all models available for this formalism
	 * @param formalismName The formalism
	 * @return a map with name and description of each example
	 */
	public static Map<String, String> getModelsName(IFormalism formalism) {
		Map<String, String> toReturn = new HashMap<String, String>();

		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		for (int i = 0; i < contributions.length; i++) {
			if (contributions[i].getAttribute(FORMALISM).equalsIgnoreCase(formalism.getName())) {
				toReturn.put(contributions[i].getAttribute(NAME), contributions[i].getAttribute(DESCRIPTION));
			}
		}
		return toReturn;
	}

	/**
	 * Ask for a example model
	 * @param modelName name of the wanted model
	 * @return The associated graph object or <code>null</code> if no graph has been found
	 * @throws CoreException If something went wrong with the extension mechanism
	 * @throws ColoaneException If something went wrong during the graph building
	 */
	public static IGraph getModel(String modelName) throws CoreException, ColoaneException {
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		IConfigurationElement convertContribution = null;
		for (int i = 0; i < contributions.length; i++) {
			if (contributions[i].getAttribute(NAME).equals(modelName)) {
				convertContribution = contributions[i];
				break;
			}
		}
		
		// Create the extension instance 
		IExample convertInstance = null;
		if (convertContribution != null) {
			convertInstance = (IExample) convertContribution.createExecutableExtension(BUILDER);
		}
		
		// Execute the instance
		try {
			return convertInstance.buildModel();
		} catch (PluginException e) {
			throw new ColoaneException("Plugin error " + e.getMessage() + "[" + e.getPluginName() + "]");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		}
	}
}
