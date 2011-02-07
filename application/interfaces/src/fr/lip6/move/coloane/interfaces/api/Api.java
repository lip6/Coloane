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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public abstract class Api implements IApi {

	/** The logger */
	protected static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** A list of observers to notify */
	private Map<Integer, List<IApiObserver>> observers = new HashMap<Integer, List<IApiObserver>>();

	/** A list of menu items that have to be displayed by Coloane */
	protected List<IItemMenu> menus = new ArrayList<IItemMenu>();

	/** Authentication status */
	private boolean authentication;

	/** Authentication data */
	private Map<String, String> credentials = new HashMap<String, String>();

	/**
	 * Constructor.<br>
	 * By default, the authentication status is set to <code>false</code>.
	 */
	public Api() {
		this.authentication = false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<IItemMenu> getInitialApiMenus() {
		return this.menus;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addObserver(IApiObserver observer, int observerType) {
		List<IApiObserver> typedObservers = this.observers.get(Integer.valueOf(observerType));
		// Create a new list of typedObserver if it does not already exist
		if (typedObservers == null) {
			typedObservers = new ArrayList<IApiObserver>();
			this.observers.put(Integer.valueOf(observerType), typedObservers);
		}
		typedObservers.add(observer);
	}

	/**
	 * Notify observers with corresponding type
	 * @param observerType The type of observers to notify
	 * @param newValue The new value to send to observers
	 */
	protected void notifyObservers(int observerType, Object newValue) {
		List<IApiObserver> typedObservers = this.observers.get(Integer.valueOf(observerType));
		for (IApiObserver observer : typedObservers) {
			observer.update(this, newValue);
		}
	}

	/**
	 * @return <code>true</code> if some credential information have been stored
	 */
	public final boolean isAuthenticated() {
		return authentication;
	}

	/**
	 * Set the authentication status
	 * @param authentication <code>true</code> if the API is authenticated
	 */
	public final void setAuthenticated(boolean authentication) {
		this.authentication = authentication;
	}

	/**
	 * Set authentication properties
	 * @param login The registered login
	 * @param password The registered password
	 */
	public final void setAuthenticationCredential(String login, String password) {
		this.credentials.put(login, password);
	}

	/**
	 * @param key The asked credential
	 * @return The credential value if is exists or <code>null</code>.
	 */
	public final String getAuthenticationCredential(String key) {
		return this.credentials.get(key);
	}
}
