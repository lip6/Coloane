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
package fr.lip6.move.coloane.interfaces.formalism;

import org.eclipse.draw2d.IFigure;

/**
 * Cette classe regroupe toutes les informations graphiques relative à un élément de formalisme
 */
public interface IGraphicalDescription {

	/**
	 * @return Le nom qui doit être affiché dans la palette pour cet élément de formalisme
	 */
	String getPaletteName();

	/**
	 * @return La description de l'élément de formalisme
	 */
	String getDescription();

	/**
	 * @return Est-ce que l'élément de formalisme doit être affiché dans la palette ?
	 */
	boolean isPalettable();

	/**
	 * @return Est-ce que l'élément de formalisme doit être visible sur le modèle
	 */
	boolean isDrawable();

	/**
	 * @return La figure associée à l'élément de formalisme
	 */
	IFigure getAssociatedFigure();

	/**
	 * @return L'icône (16 pixels) associée à l'élément de formalisme
	 */
	String getIcon16px();

	/**
	 * @return L'icône (24 pixels) associée à l'élément de formalisme
	 */
	String getIcon24px();

	/**
	 * @return L'indicateur de remplissage de ma figure
	 */
	boolean isFilled();

	/**
	 * @return La hauteur par défaut de la figure
	 */
	Integer getHeight();

	/**
	 * @return La largeur par défaut de la figure
	 */
	Integer getWidth();
	
}
