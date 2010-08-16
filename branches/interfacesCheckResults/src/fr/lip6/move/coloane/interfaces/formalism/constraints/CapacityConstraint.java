package fr.lip6.move.coloane.interfaces.formalism.constraints;

import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * Define a constraint that limits the number that can be contained by a node.<br>
 * This constraint forbids the formalism element {@link GraphFormalism} to contain more than {@link #max} objects
 * 
 * @author Jean-Baptiste Voron
 */
public class CapacityConstraint implements IConstraint, IConstraintNode, IExecutableExtension {

	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The element that is constraint */
	private String element;

	/** Maximum number of objects */
	//private int max;

	/**
	 * Constructor<br>
	 * The class is initialize by the method {@link #setInitializationData(IConfigurationElement, String, Object)}
	 * @see {@link FormalismManager}
	 */
	public CapacityConstraint() { }

	/** 
	 * {@inheritDoc}
	 */
	public final boolean isSatisfied(INode node) {
		return (node.getNodeFormalism().getName().equals(element));
	}

	/** 
	 * {@inheritDoc}
	 */
	public final void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		// Fetch parameters
		Map<String, String> myParams = new HashMap<String, String>();
		IConfigurationElement[] parameters = config.getChildren(PARAMETER_ID);

		for (IConfigurationElement param : parameters) {
			if ((param.getAttribute(PARAMETER_NAME) != null) && (param.getAttribute(PARAMETER_VALUE) != null)) {
				myParams.put(param.getAttribute(PARAMETER_NAME), param.getAttribute(PARAMETER_VALUE));
			}
		}

		// Check whether mandatory parameters have been provided
		if (!myParams.containsKey("element")) {  //$NON-NLS-1$
			LOGGER.warning("Missing \"element\" parameter in constraint definition..."); //$NON-NLS-1$
			IStatus errorStatus = new Status(IStatus.ERROR, "fr.lip6.move.coloane.extensions.tools", "Missing \"element\" parameter in constraint definition...");  //$NON-NLS-1$ //$NON-NLS-2$
			throw new CoreException(errorStatus);
		}

		if (!myParams.containsKey("max")) {  //$NON-NLS-1$
			LOGGER.warning("Missing parameter (\"max\") in constraint definition..."); //$NON-NLS-1$
			IStatus errorStatus = new Status(IStatus.ERROR, "fr.lip6.move.coloane.extensions.tools", "Missing parameter (\"max\") in constraint definition...");  //$NON-NLS-1$ //$NON-NLS-2$
			throw new CoreException(errorStatus);
		}

		this.element = myParams.get("element"); //$NON-NLS-1$
		//this.max = Integer.valueOf(myParams.get("maxIn")); //$NON-NLS-1$
	}
}
