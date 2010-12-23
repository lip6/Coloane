/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.interfaces.formalism.constraints;

import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
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
 * Define a constraint that limits the number of connections on a formalism element.<br>
 * This constraint <b>forbids</b> a formalism element to have:
 * <ul>
 * 	<li>more than <code>maxIn</code> incoming arcs</li>
 * 	<li>more than <code>maxOut</code> outgoing arcs</li>
 * </ul>
 * 
 * @author Jean-Baptiste Voron
 */
public class CardinalityConstraint implements IConstraint, IConstraintLink, IExecutableExtension {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The element on which the constraint is applied */
	private String element;

	/** Maximum number of incoming arcs */
	private int maxIn;

	/** Maximum number of outgoing arcs */
	private int maxOut;

	/**
	 * Constructor used by the FormalismManager.<br>
	 * The class is initialize by the method {@link #setInitializationData(IConfigurationElement, String, Object)}
	 * @see {@link FormalismManager}
	 */
	public CardinalityConstraint() { }

	/** {@inheritDoc} */
	public final boolean isSatisfied(INode source, INode target, IArcFormalism arcFormalism) {
		// We have to check that cardinality constraints are OK for both source and target nodes

		// For the source node
		if (source.getNodeFormalism().getName().equals(element)) {
			// The number of outgoing arcs must be lower than maxOut
			if ((this.maxOut >= 0) && (source.getOutgoingArcs().size() >= this.maxOut)) {
				return false;
			}
		}

		// For the target node
		if (target.getNodeFormalism().getName().equals(element)) {
			// The number of incoming arcs must be lower than maxIn
			if ((this.maxIn >= 0) && (target.getIncomingArcs().size() >= this.maxIn)) {
				return false;
			}
		}

		// If everything is OK...
		return true;
	}

	/** {@inheritDoc} */
	public final void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		// Parameters
		Map<String, String> myParams = new HashMap<String, String>();
		IConfigurationElement[] parameters = config.getChildren(PARAMETER_ID);

		// Fetch all parameters
		for (IConfigurationElement param : parameters) {
			if ((param.getAttribute(PARAMETER_NAME) != null) && (param.getAttribute(PARAMETER_VALUE) != null)) {
				myParams.put(param.getAttribute(PARAMETER_NAME), param.getAttribute(PARAMETER_VALUE));
			}
		}

		// Check whether mandatory parameters exist
		if (!myParams.containsKey("element")) {  //$NON-NLS-1$
			LOGGER.warning("Missing \"element\" parameter in constraint definition..."); //$NON-NLS-1$
			IStatus errorStatus = new Status(IStatus.ERROR, "fr.lip6.move.coloane.extensions.tools", "Missing \"element\" parameter in constraint definition...");  //$NON-NLS-1$ //$NON-NLS-2$
			throw new CoreException(errorStatus);
		}

		if (!myParams.containsKey("maxIn") || !myParams.containsKey("maxOut")) {  //$NON-NLS-1$//$NON-NLS-2$
			LOGGER.warning("Missing parameter (\"maxIn\" or \"maxOut\") in constraint definition..."); //$NON-NLS-1$
			IStatus errorStatus = new Status(IStatus.ERROR, "fr.lip6.move.coloane.extensions.tools", "Missing parameter (\"maxIn\" or \"maxOut\") in constraint definition...");  //$NON-NLS-1$ //$NON-NLS-2$
			throw new CoreException(errorStatus);
		}

		this.element = myParams.get("element"); //$NON-NLS-1$
		this.maxIn = Integer.valueOf(myParams.get("maxIn")); //$NON-NLS-1$
		this.maxOut = Integer.valueOf(myParams.get("maxOut")); //$NON-NLS-1$
	}
}
