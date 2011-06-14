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
package fr.lip6.move.coloane.core.ui.editpart;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Text;

/**
 * Class used to edit attributes directly into the model.<br>
 * It allow to compute the best location for the cell editor area.
 */
public final class AttributeCellEditorLocator implements CellEditorLocator {

	/** The attribute figure (which is a label) */
	private Label attributeFigure;

	/**
	 * Constructor
	 * @param attributeFigure The attribute figure
	 */
	public AttributeCellEditorLocator(Label attributeFigure) {
		this.attributeFigure = attributeFigure;
	}

	/** {@inheritDoc} */
	public void relocate(CellEditor celleditor) {
		Text text = (Text) celleditor.getControl();
		Rectangle rect = attributeFigure.getTextBounds();
		rect.setLocation(attributeFigure.getLocation());
		attributeFigure.translateToAbsolute(rect);
		org.eclipse.swt.graphics.Rectangle trim = text.computeTrim(0, 0, 0, 0);
		rect.translate(trim.x, trim.y);
		rect.width += trim.width;
		rect.width = Math.max(rect.width, 4);
		rect.height += trim.height;
		text.setBounds(rect.x, rect.y, rect.width, rect.height);
	}
}
