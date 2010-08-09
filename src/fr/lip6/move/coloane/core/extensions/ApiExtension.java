package fr.lip6.move.coloane.core.extensions;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.interfaces.api.IApi;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

public class ApiExtension {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	
	/**
	 * Extension attributes
	 */
	private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.core.apis"; //$NON-NLS-1$
	private static final String NAME = "name"; //$NON-NLS-1$
	private static final String DESCRIPTION = "description"; //$NON-NLS-1$
	private static final String ICON = "icon"; //$NON-NLS-1$
	private static final String FORMALISM = "formalism"; //$NON-NLS-1$
	private static final String CLASS = "class"; //$NON-NLS-1$

	/** Private constructor */
	private ApiExtension() { }

	/**
	 * Looks for all available APIs (for a given formalism)
	 * @param formalismName The formalism
	 * @return a list of available APIs
	 * @see ApiDescription
	 */
	public static List<ApiDescription> getApis(IFormalism formalism) {
		List<ApiDescription> apis = new ArrayList<ApiDescription>();

		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		for (int i = 0; i < contributions.length; i++) {
			String acceptedFormalism = contributions[i].getAttribute(FORMALISM);
			if ((acceptedFormalism.equals("*")) || (acceptedFormalism.equalsIgnoreCase(formalism.getName()))) { //$NON-NLS-1$
				try {
					IApi apiClass = (IApi) contributions[i].createExecutableExtension(CLASS);
					apis.add(new ApiDescription(apiClass, contributions[i].getAttribute(NAME), contributions[i].getAttribute(DESCRIPTION), contributions[i].getAttribute(ICON)));
				} catch (ColoaneException e) {
					LOGGER.warning("Something went wrong during the association with the API : " + contributions[i].getAttribute(NAME)); //$NON-NLS-1$
					LOGGER.warning(e.getLogMessage());
				} catch (CoreException e) {
					LOGGER.warning("The API main cannot be loaded."); //$NON-NLS-1$
				}
			}
		}
		return apis;
	}
}
