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

/**
 * Cette classe définie une modification sur un menu.
 */
public interface IUpdateMenu {

	/**
	 * Recupere le nom du menu principal a modifier
	 * @return le nom du menu principal a modifier
	 */
	String getRootName();

	/**
	 * Recupere le nom de l'item a modifier
	 * @return le nom de l'item a modifier
	 */
	String getServiceName();

	/**
	 * Recupere l'etat de l'item
	 * @return true, si l'item est visible, false sinon
	 */
	boolean getState();
}
