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

import fr.lip6.move.coloane.interfaces.formalism.IArcChecker;
import fr.lip6.move.coloane.interfaces.model.IArc;

/**
 * Wrapper defining a complete arc checker.
 * It contains :<ul>
 * <li>an {@link IArcChecker} : condition to create a marker</li>
 * <li>the marker message</li>
 * <li>the marker severity</li>
 * </ul>
 * 
 * The {@link ArcChecker#check(IArc)} method check the given {@link IArc}.
 * If the method return true, a marker has to be created and the marker message is obtained by {@link ArcChecker#getMessage()} and it severity by {@link ArcChecker#getSeverity()}.
 * 
 * @author Florian David
 */
public class ArcChecker {
	/** @see IArcChecker */
	private IArcChecker checker;
	/** The marker severity */
	private Integer severity;
	/** The marker severity */
	private String message;
	
	/**
	 * Construct a arc checker.
	 * @param checker the {@link IArcChecker} condition to create the marker.
	 * @param message the marker message.
	 * @param severity the marker severity.
	 */
	ArcChecker(IArcChecker checker, String message, Integer severity) {
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
	 * Call the {@link IArcChecker#performCheck(IArc)} method of the checker on an {@link IArc}.
	 * @param arc the {@link IArc} to check.
	 * @return <code>true</code> if a marker must be created, <code>false</code> otherwise.
	 */
	public final boolean check(IArc arc) {
		return checker.performCheck(arc);
	}
}
