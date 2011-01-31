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

import java.util.List;

/**
 * The element that implement this interface can be linked to a sticky note.
 * 
 * @author Clément Démoulins
 * @author Jean-Baptiste Voron
 */
public interface ILinkableElement {
	/**
	 * Add a sticky link to the element.
	 * @param link The link to add
	 */
	void addLink(ILink link);

	/**
	 * Remove a sticky link from the element
	 * @param link The link to remove 
	 * @return <code>true</code> if the link has been correctly removed
	 */
	boolean removeLink(ILink link);

	/**
	 * @return A <b>unmodifiable</b> list of sticky links 
	 */
	List<ILink> getLinks();
}
