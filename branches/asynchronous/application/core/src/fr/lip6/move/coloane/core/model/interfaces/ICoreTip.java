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

import fr.lip6.move.coloane.core.model.CoreTipModel.ArcTipModel;

/**
 * Interface for a graphical tip
 *
 * @author Clément Démoulins
 * @author Jean-Baptiste Voron
 */
public interface ICoreTip extends ILocatedElement {

	/**
	 * @return The link between the tip and the element
	 */
	ArcTipModel getArcModel();

	/**
	 * @return The ID of the object designated by the tip
	 */
	int getIdObject();

	/**
	 * @return The name of the tip
	 */
	String getName();

	/**
	 * @return The value of the tip
	 */
	String getValue();
}
