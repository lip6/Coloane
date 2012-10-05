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

import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.ui.prefs.ColorsPrefs;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.jface.resource.JFaceResources;

/**
 * A rounded rectangle with text inside.
 */
public class TipFigure extends RoundedRectangle {
	private static final int MARGIN = 5;

	/**
	 * Create a tip with a unmodifiable text.
	 * @param tip text of the tip
	 */
	public TipFigure(ICoreTip tip) {
		setBorder(new MarginBorder(MARGIN));
		setCornerDimensions(new Dimension(10, 10));

		Label label = new Label(tip.getName() + " : " + tip.getValue()); //$NON-NLS-1$
		// TODO : sortir la police dans les préférences
		label.setFont(JFaceResources.getDefaultFont());

		setSize(label.getTextBounds().getSize().expand(MARGIN * 2, MARGIN * 2));
		setBackgroundColor(ColorsPrefs.getColor("COLORTIP_BACKGROUND")); //$NON-NLS-1$
		setForegroundColor(ColorsPrefs.getColor("COLORTIP_FOREGROUND")); //$NON-NLS-1$
		setLineWidth(2);
		setLayoutManager(new StackLayout());
		add(label);
	}
}
