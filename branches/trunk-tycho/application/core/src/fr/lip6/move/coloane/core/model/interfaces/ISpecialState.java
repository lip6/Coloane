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
package fr.lip6.move.coloane.core.model.interfaces;

/**
 * The elements that implement this interface can be highlighted in a special state.<br>
 * This can be done if and only if the associated edit is able to catch and handle the event {@link #SPECIAL_STATE_CHANGE}
 * 
 * @author Jean-Baptiste Voron
 */
public interface ISpecialState {
	/** Event raised when the element has to be highlighted in a special state */
	String SPECIAL_STATE_CHANGE = "Element.SpecialStateChange"; //$NON-NLS-1$

	/**
	 * Set the special state
	 * @param state the state
	 */
	void setSpecialState(boolean state);
}
