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
package fr.lip6.move.coloane.interfaces.api;

/**
 * Define methods that have to be implemented by API Observers.<br>
 * Cannot rely on {@link Observer} interface since it requires {@link Api} to extend {@link Observable}
 *
 * @author Jean-Baptiste Voron
 */
public interface IApiObserver {

	int MENU_OBSERVER = 0;
	int MESSAGE_OBSERVER = 1;

	
	/**
	 * Something has been updated on the API
	 * @param api The API that has been changed
	 * @param newValue The new value to take into account
	 */
	void update(IApi api, Object newValue);
}
