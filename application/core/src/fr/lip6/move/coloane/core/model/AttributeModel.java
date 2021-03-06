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
package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.model.interfaces.ISpecialState;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.core.ui.rulers.EditorRulerProvider;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IAttributeGraphicInfo;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;

import java.util.logging.Logger;

/**
 * Describe an object model attribute
 *
 * @author Jean-Baptiste Voron
 */
public class AttributeModel extends AbstractPropertyChange implements IAttribute, ILocatedElement, ISpecialState {
	/** The main logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The reference model element */
	private IElement reference;

	private IAttributeFormalism attributFormalism;

	/** Attribute name */
	private final String name;
	/** Attribute value */
	private String value;

	/** Graphical guides to help the positioning of the attribute */
	private EditorGuide horizontalGuide;
	private EditorGuide verticalGuide;

	/** All the graphical information about this attribute */
	private IAttributeGraphicInfo graphicInfo = new AttributeGraphicInfo(this);

	/**
	 * Constructor
	 *
	 * @param reference The element to which this attribute is associated
	 * @param attributeFormalism The properties of this attribute (given by the formalism)
	 */
	AttributeModel(IElement reference, IAttributeFormalism attributeFormalism) {
		LOGGER.finest("Build an attribute: " + attributeFormalism.getName() + " for #" + reference.getId()); //$NON-NLS-1$ //$NON-NLS-2$
		this.reference = reference;
		this.attributFormalism = attributeFormalism;
		this.name = attributeFormalism.getName();
		this.value = attributeFormalism.getDefaultValue();
	}

	/** {@inheritDoc} */
	@Override
	public final String getName() {
		return name;
	}

	/** {@inheritDoc} */
	@Override
	public final String getValue() {
		return value;
	}

	/** {@inheritDoc} */
	@Override
	public final void setValue(String value) {
		if (value == null) { return; }
		String oldValue = this.value;
		// Warn the controller about the change only if it is necessary
		if (!oldValue.equals(value)) {
			this.value = value;
			firePropertyChange(IAttribute.VALUE_PROP, oldValue, value);
		}
	}

	/** {@inheritDoc} */
	@Override
	public final IAttributeGraphicInfo getGraphicInfo() {
		return graphicInfo;
	}

	/** {@inheritDoc} */
	@Override
	public final ILocationInfo getLocationInfo() {
		return this.graphicInfo;
	}

	/** {@inheritDoc} */
	@Override
	public final IElement getReference() {
		return reference;
	}

	/** {@inheritDoc} */
	@Override
	public final IAttributeFormalism getAttributeFormalism() {
		return attributFormalism;
	}

	/** {@inheritDoc} */
	@Override
	public final EditorGuide getGuide(int orientation) {
		if (orientation == EditorRulerProvider.HORIZONTAL_ORIENTATION) {
			return this.horizontalGuide;
		} else {
			return this.verticalGuide;
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void setGuide(EditorGuide guide) {
		if (guide.getOrientation() == EditorRulerProvider.HORIZONTAL_ORIENTATION) {
			this.horizontalGuide = guide;
		} else {
			this.verticalGuide = guide;
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void removeGuide(int orientation) {
		if (orientation == EditorRulerProvider.HORIZONTAL_ORIENTATION) {
			this.horizontalGuide = null;
		} else {
			this.verticalGuide = null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public final String toString() {
		return "Attribute: " + name + "= " + value + " [" + reference + "])"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

	/** {@inheritDoc} */
	@Override
	public final void setSpecialState(boolean state) {
		firePropertyChange(SPECIAL_STATE_CHANGE, null, state);
	}
}
