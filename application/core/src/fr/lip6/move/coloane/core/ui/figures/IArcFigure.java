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
package fr.lip6.move.coloane.core.ui.figures;

import fr.lip6.move.coloane.interfaces.model.IArc;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;

/**
 * Interface that describes an arc figure and available operations on it.
 */
public interface IArcFigure extends IFigure, Connection {

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.draw2d.Shape#setLineWidth(int)
	 */
	void setLineWidth(int w);

	/**
	 * @return the model arc element that corresponds to the figure
	 */
	IArc getModel();

	/**
	 * Set the model arc element associated to this figure
	 * @param modelElement the model arc element
	 * @see IArc
	 */
	void setModelElement(IArc modelElement);

}
