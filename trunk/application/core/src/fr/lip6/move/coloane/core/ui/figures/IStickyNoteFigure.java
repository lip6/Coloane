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

/**
 * Interface représentant une note
 */
public interface IStickyNoteFigure {

	/** The default amount of pixels subtracted from the figure's height and width to determine the size of the corner. */
	int DEFAULT_CORNER_SIZE = 10;

	/**
	 * Permet de modifier la taille du coin en haut à droite
	 * @param cornerSize taille
	 */
	void setCornerSize(int cornerSize);
}
