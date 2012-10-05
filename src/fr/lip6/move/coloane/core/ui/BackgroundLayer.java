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
package fr.lip6.move.coloane.core.ui;

import fr.lip6.move.coloane.core.main.Coloane;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Image;

/**
 * Define a background layer in order to display a palm picture
 *
 * @author Jean-Baptiste Voron
 */
public class BackgroundLayer extends FreeformLayer {
	public static final String BACKGROUND_LAYER = "Background Palm Layer"; //$NON-NLS-1$
	private Image palm = new Image(Coloane.getParent().getDisplay(), Coloane.class.getResourceAsStream("/resources/icons/coloane_transparent.png")); //$NON-NLS-1$

	/**
	 * Constructor
	 */
	public BackgroundLayer() {
		setOpaque(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void paintFigure(Graphics graphics) {
		if (isOpaque()) {
			graphics.drawImage(palm, new Point(5, 5));
		}
	}
}
