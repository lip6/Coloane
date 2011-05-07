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

import fr.lip6.move.coloane.interfaces.objects.menu.IItemMenu;

import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * To use extension point 'fr.lip6.move.coloane.apis', this interface must be implemented to provide the Api menu.
 * To update the menu or send messages, you can use fire a property change event on the listeners with appropriate data.
 *
 * For update the menu, you need to send as new value an updated <code>List&lt;IItemMenu&gt;</code>.
 * For send a message, set the new value of the PropertyChangeEvent as a ConsoleMessage.
 *
 * @author Clément Démoulins
 * @author Jean-Baptiste Voron
 */
public interface IApi {

	String API_MENU = "property.api.menu";
	String API_MESSAGE = "property.api.message";

	/**
	 * @return The initial list of menus associated with the API
	 */
	List<IItemMenu> getInitialApiMenus();

	/**
	 * Add a listener.
	 * @param listener the listener to add
	 */
	void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Add a listener for a specified property.
	 * @param propertyName property listened
	 * @param listener the listener to add
	 */
	void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);

	/**
	 * Remove a listener.
	 * @param listener the listener to remove
	 */
	void removePropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Remove a listener for a specified property.
	 * @param propertyName property concerned
	 * @param listener the listener to remove
	 */
	void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);
}
