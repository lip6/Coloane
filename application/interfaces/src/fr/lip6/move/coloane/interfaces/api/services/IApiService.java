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
package fr.lip6.move.coloane.interfaces.api.services;

import fr.lip6.move.coloane.interfaces.objects.services.IService;

/**
 * Define what a API service should be.<br>
 * Such a service is based on a {@link IService}.<br>
 * But, an API service should provide some information about itself.
 *
 * @author Jean-Baptiste Voron
 */
public interface IApiService extends IService {

	/**
	 * @return The service name
	 */
	String getName();
	
	/**
	 * @return A simple description of the service
	 */
	String getDescription();
}
