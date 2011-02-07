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

import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Color;

/**
 * Interface that describes a node figure and available operations.
 */
public interface INodeFigure extends IFigure {

	/** Default background color for figures */
	Color FILLED_BACKGROUND_COLOR = ColorConstants.black;

	/**
	 * @see org.eclipse.draw2d.Shape#setLineWidth(int)
	 * {@inheritDoc}
	 */
	void setLineWidth(int w);

	/**
	 * Fetch all available anchors to perform connection to the figure
	 * @return ConnectionAnchor
	 */
	ConnectionAnchor getConnectionAnchor();

	/**
	 * @return the model element that corresponds to the figure
	 */
	INode getModel();

	/**
	 * Set the model element associated to this figure
	 * @param modelElement the model node element
	 * @see INode
	 */
	void setModelElement(INode modelElement);
}
