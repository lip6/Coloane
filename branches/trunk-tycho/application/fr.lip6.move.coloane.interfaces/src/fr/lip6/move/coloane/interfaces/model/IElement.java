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
package fr.lip6.move.coloane.interfaces.model;

import fr.lip6.move.coloane.interfaces.formalism.IComputedAttributeFormalism;

import java.util.Collection;

/**
 * Définition d'un objet de modèle (graphe, noeud, attribut etc...).<br>
 * Elle définit les propriétés et comportements de base des éléments d'un modèle.
 */
public interface IElement extends IAbstractPropertyChange {
	/**
	 * Id de la propriété quand l'attribut devient DefaultValue ou était DefaultValue.
	 */
	String ATTRIBUTE_CHANGE = "AttributChange"; //$NON-NLS-1$

	/**
	 * @return unique id.
	 */
	int getId();

	/**
	 * @return parent of this IElement.
	 */
	IElement getParent();

	/**
	 * @param name name of the attribute.
	 * @return the IAttribute named attribute or null.
	 */
	IAttribute getAttribute(String name);

	/**
	 * @return Collection of drawable attributes. This list also contains computedAttributes
	 * @see IComputedAttributeFormalism
	 */
	Collection<IAttribute> getDrawableAttributes();

	/**
	 * @return Collection of all IAttribute.
	 */
	Collection<IAttribute> getAttributes();

	/**
	 * Put this attribute to this element.
	 * @param name Le nom de l'attribut
	 * @param attribute L'objet décrivant l'attribut
	 */
	void putAttribute(String name, IAttribute attribute);
}
