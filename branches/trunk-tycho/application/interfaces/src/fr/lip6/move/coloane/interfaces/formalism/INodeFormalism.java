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

/**
 * Description d'un noeud dans un formalisme.<br>
 * Plusieurs types de noeuds peuvent exister dans un formalisme.<br>
 * Par exemple, dans le formalisme des réseaux de Petri on retrouve :
 * <ul>
 * 	<li>Les places</li>
 * 	<li>Les transitions</li>
 * </ul>
 *
 * Dans certains cas, les noeuds peuvent contenir un ensemble de noeuds, arcs, attributs etc...<br>
 * Cette capacité permet de définir des formalismes où la hiérarchie est possible.</br>
 * Cette définition définit un attribut {@link #container} qui permet de préciser cette compétence.
 */
public interface INodeFormalism extends IElementFormalism {

	/**
	 * @return Un indicateur qui permet de savoir si le noeud peut contenir un modèle
	 */
	boolean isContainer();
}
