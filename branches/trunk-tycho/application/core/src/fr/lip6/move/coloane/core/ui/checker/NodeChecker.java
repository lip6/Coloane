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
package fr.lip6.move.coloane.core.ui.checker;

import fr.lip6.move.coloane.interfaces.formalism.INodeChecker;
import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * Wrapper defining a complete node checker.
 * It contains :<ul>
 * <li>an {@link INodeChecker} : condition to create a marker</li>
 * <li>the marker message</li>
 * <li>the marker severity</li>
 * </ul>
 * The {@link NodeChecker#check(INode)} method check the given {@link INode}.
 * If the method return true, a marker has to be created and the marker message is obtained by {@link NodeChecker#getMessage()} and it severity by {@link NodeChecker#getSeverity()}.
 * @author Florian David
 */
public class NodeChecker {
	/** @see INodeChecker */
	private INodeChecker checker;
	/** The marker severity */
	private Integer severity;
	/** The marker severity */
	private String message;
	
	/**
	 * Construct a node checker.
	 * @param checker the {@link INodeChecker} condition to create the marker.
	 * @param message the marker message.
	 * @param severity the marker severity.
	 */
	NodeChecker(INodeChecker checker, String message, Integer severity) {
		this.severity = severity;
		this.checker = checker;
		this.message = message;
	}
	
	/**
	 * Getter on marker severity.
	 * @return the marker severity.
	 */
	public final Integer getSeverity() {
		return severity;
	}

	/**
	 * Getter on marker message.
	 * @return the marker message.
	 */
	public final String getMessage() {
		return message;
	}

	/**
	 * Call the {@link INodeChecker#performCheck(INode)} method of the checker on an {@link INode}.
	 * @param node the {@link INode} to check.
	 * @return <code>true</code> if a marker must be created, <code>false</code> otherwise.
	 */
	public final boolean check(INode node) {
		return checker.performCheck(node);
	}
}
