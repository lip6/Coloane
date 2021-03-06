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

import fr.lip6.move.coloane.interfaces.formalism.IGlobalAttributeFormalism;

/**
 * Describe an attribute object (always attached to a model object).<br>
 * An attribute can be attached to numerous kind of model objects:
 * <ul>
 * 	<li>arc</li>
 * 	<li>node</li>
 * 	<li>graph</li>
 * </ul>
 */
public interface IAttribute extends IAbstractPropertyChange {

	/** Value update property */
	String VALUE_PROP = "Attribute.ValueUpdate"; //$NON-NLS-1$

	/** The attribute must be selected */
	String SELECT_LIGHT_PROP = "Attribute.SelectLightUpdate"; //$NON-NLS-1$

	/** The attribute must be selected */
	String SELECT_HEAVY_PROP = "Attribute.SelectHeavyUpdate"; //$NON-NLS-1$

	/** The attribute must be unselected */
	String UNSELECT_LIGHT_PROP = "Attribute.UnSelecLighttUpdate"; //$NON-NLS-1$

	/** The attribute must be unselected */
	String UNSELECT_HEAVY_PROP = "Attribute.UnSelectHeavyUpdate"; //$NON-NLS-1$

	/**
	 * @return the attribute value
	 */
	String getValue();

	/**
	 * @return the attribute name
	 */
	String getName();

	/**
	 * @param value set a new value for this attribute
	 */
	void setValue(String value);

	/**
	 * Fetch the object that describe all graphical properties for this attribute.<br>
	 * @return IAttributeGraphicInfo
	 * @see IAttributeGraphicInfo
	 */
	IAttributeGraphicInfo getGraphicInfo();

	/**
	 * @return The element to which the attribute is related to
	 */
	IElement getReference();

	/**
	 * @return The attribute description given by the formalism
	 */
	IGlobalAttributeFormalism getAttributeFormalism();
}
