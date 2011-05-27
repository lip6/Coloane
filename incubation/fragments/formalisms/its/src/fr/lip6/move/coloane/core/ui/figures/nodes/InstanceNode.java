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
 *   Yann THIERRY-MIEG (LIP6)
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.core.ui.figures.nodes;

import fr.lip6.move.coloane.core.ui.figures.AbstractNodeFigure;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Dimension;

/**
 * Description of a Scalar Set figure : a box with some suggestion of nested boxes inside.
 *
 * @author Y. Thierry-Mieg, based on C. Demoulins RectangleNode class
 */
public class InstanceNode extends AbstractNodeFigure implements PropertyChangeListener {
	private String toshow = ":";

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void fillShape(Graphics graphics) {
		graphics.fillRectangle(getBounds().getResized(-1, -1));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void modelElementChanged(INode oldModelElement,
			INode newModelElement) {
		if (oldModelElement != null) {
			oldModelElement.removePropertyChangeListener(this);
		}
		newModelElement.addPropertyChangeListener(this);
		// trigger an update.
		propertyChange(null);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void outlineShape(Graphics graphics) {
		graphics.drawString(toshow, getBounds().getTopLeft().translate(5, 3));
		setBorder(new LineBorder(2));
	}

	/**
	 * {@inheritDoc}
	 */
	public final ConnectionAnchor getConnectionAnchor() {
		return new ChopboxAnchor(this);
	}

	/**
	 * We update the String toshow and the size here if necessary to reflect model changes.
	 * {@inheritDoc}
	 */
	public void propertyChange(PropertyChangeEvent event) {
		
		String name = getModel().getAttribute("name").getValue();
		String type = getModel().getAttribute("type").getValue();
		String toshow2 = name + " : " + type;
		if (! toshow2.equals(toshow) ) {
			toshow = toshow2;
			Dimension textsize = FigureUtilities.getStringExtents(toshow, getFont());
			getModel().getGraphicInfo().setSize(textsize.expand(10,15));
		}
	}
}
