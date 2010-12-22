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

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;

/**
 * EditPart that manages links between tips and its element
 * 
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class TipArcEditPart extends AbstractConnectionEditPart {

	/** {@inheritDoc} */
	@Override
	protected final void createEditPolicies() {
	}

	/** {@inheritDoc} */
	@Override
	protected final IFigure createFigure() {
		PolylineConnection arc = new PolylineConnection();
		arc.setLineStyle(Graphics.LINE_DOT);
		return arc;
	}
}
