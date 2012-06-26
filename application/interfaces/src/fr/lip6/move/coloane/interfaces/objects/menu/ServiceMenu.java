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
package fr.lip6.move.coloane.interfaces.objects.menu;

import fr.lip6.move.coloane.interfaces.api.services.IApiService;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Define a menu that provides access to a service.
 *
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class ServiceMenu extends ItemMenu implements IServiceMenu {

	/** Associated service */
	private IApiService associatedService;

	/** true if the service is not attached to a session */
	private boolean global;

	/**
	 * Constructor
	 * @param name The name of the option
	 * @param visibility The visibility of the option
	 * @param helpMessage An help message associated with the option
	 * @param associatedService The associated service
	 */
	public ServiceMenu(String name, boolean visibility, String helpMessage, IApiService associatedService) {
		super(name, visibility, helpMessage);
		this.associatedService = associatedService;
	}

	/**
	 * Constructor
	 * @param name The name of the option
	 * @param visibility The visibility of the option
	 * @param helpMessage An help message associated with the option
	 * @param associatedService The associated service
	 * @param menuIcon An icon associated with the service
	 */
	public ServiceMenu(String name, boolean visibility, String helpMessage, IApiService associatedService, ImageDescriptor menuIcon) {
		super(name, visibility, helpMessage, menuIcon);
		this.associatedService = associatedService;
	}

	/**
	 * Constructor
	 * @param name The name of the option
	 * @param visibility The visibility of the option
	 * @param helpMessage An help message associated with the option
	 * @param associatedService The associated service
	 * @param global true if the service is not attached to a session
	 */
	public ServiceMenu(String name, boolean visibility, String helpMessage, IApiService associatedService, boolean global) {
		super(name, visibility, helpMessage);
		this.associatedService = associatedService;
		this.global = global;
	}

	/**
	 * Constructor
	 * @param name The name of the option
	 * @param visibility The visibility of the option
	 * @param helpMessage An help message associated with the option
	 * @param associatedService The associated service
	 * @param menuIcon An icon associated with the service
	 * @param global true if the service is not attached to a session
	 */
	public ServiceMenu(String name, boolean visibility, String helpMessage, IApiService associatedService, ImageDescriptor menuIcon, boolean global) {
		super(name, visibility, helpMessage, menuIcon);
		this.associatedService = associatedService;
		this.global = global;
	}

	/**
	 * {@inheritDoc}
	 */
	public final IApiService getAssociatedService() {
		return this.associatedService;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isGlobal() {
		return global;
	}

}
