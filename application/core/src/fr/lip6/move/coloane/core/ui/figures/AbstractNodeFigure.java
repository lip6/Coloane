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

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Shape;
import org.eclipse.swt.SWT;

/**
 * Basic abstract class used by all figure node classes.<br>
 * You can use the current state of the associated model element while drawing your figure.<br>
 * Please remember that the drawing of a figure must be quick and uses very low resources.
 * Don't make any strong computations to decide whether your figure should be red or blue...
 *
 * @author Jean-Baptiste Voron
 */
public abstract class AbstractNodeFigure extends Shape implements INodeFigure {

	/**
	 * This element can be used to adapt the aspect of the figure according to
	 * the current state of the associated model node element. Please be careful
	 * when fetching some attributes from the model... Test the value of the property
	 * before doing any changes to the figure.
	 */
	private INode modelElement;

	/** {@inheritDoc} */
	@Override
	public final void paintFigure(Graphics graphics) {
		graphics.setAntialias(SWT.ON);
		super.paintFigure(graphics);
	}

	/**
	 * {@inheritDoc}
	 */
	public final INode getModel() {
		return this.modelElement;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setModelElement(INode modelElement) {
		INode oldModelElement = this.modelElement;
		this.modelElement = modelElement;
		if (oldModelElement != modelElement) {
			modelElementChanged(oldModelElement, modelElement);
		}
	}
	
	/**
	 * Called when model element changed. Implementers should remove any listeners from old model element.
	 * @param oldModelElement old model element, null if first call of method setModelElement.
	 * @param newModelElement new model element
	 */
	protected void modelElementChanged(INode oldModelElement, INode newModelElement) {
	}
}
