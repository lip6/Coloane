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
package fr.lip6.move.coloane.core.formalisms.elements;

import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

/**
 * Description d'un arc dans un formalisme.<br>
 * Plusieurs types d'arcs peuvent exister dans un formalisme.<br>
 * Par exemple, dans le formalisme des réseaux de Petri on retrouve :
 * <ul>
 * 	<li>Les arcs simples</li>
 * 	<li>Les arcs inhibiteurs</li>
 * </ul>
 */
public class ArcFormalism extends ElementFormalism implements IArcFormalism {

	/**
	 * Constructeur
	 * @param name Nom de l'arc dans le formalisme
	 * @param formalism Le formalisme qui définit cet arc
	 */
	public ArcFormalism(String name, IFormalism formalism) {
		super(name, formalism);
	}
}
