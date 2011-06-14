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
package fr.lip6.move.coloane.core.ui.commands.properties;

import fr.lip6.move.coloane.interfaces.model.IArc;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;

/**
 * Commande pour changer la couleur d'un arc.
 */
public class ArcChangeColorCmd extends Command {
	private Color oldColor;
	private Color newColor;
	private IArc arc;

	/**
	 * @param arc Arc à modifier
	 * @param color Nouvelle couleur
	 */
	public ArcChangeColorCmd(IArc arc, Color color) {
		this.arc = arc;
		this.newColor = color;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		oldColor = arc.getGraphicInfo().getColor();
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		arc.getGraphicInfo().setColor(newColor);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		arc.getGraphicInfo().setColor(oldColor);
	}

}
