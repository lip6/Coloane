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
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.core.ui.rulers.EditorRulerProvider;
import fr.lip6.move.coloane.interfaces.formalism.IComputedAttributeFormalism;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IAttributeFormatter;
import fr.lip6.move.coloane.interfaces.model.IAttributeGraphicInfo;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Describe an object model <b>computed</b> attribute
 *
 * @author Jean-Baptiste Voron
 */
public class ComputedAttributeModel extends AbstractPropertyChange implements IAttribute, ILocatedElement, PropertyChangeListener {
	/** The main logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The reference model element */
	private IElement reference;
	
	/** The parent */
	private IAttribute parent;
	
	private boolean isLeaf;

	private IComputedAttributeFormalism computedAttributFormalism;

	/** Attribute Formatter */
	private IAttributeFormatter formatter = null;

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
	 * @param computedAttributFormalism The properties of this attribute (given by the formalism)
	 */
	ComputedAttributeModel(IElement reference, IComputedAttributeFormalism computedAttributFormalism) {
		LOGGER.finest("Build a computed attribute: " + computedAttributFormalism.getName() + " for #" + reference.getId()); //$NON-NLS-1$ //$NON-NLS-2$
		this.reference = reference;
		this.computedAttributFormalism = computedAttributFormalism;
		this.name = computedAttributFormalism.getName();
		// For this attribute, the default value is not automatically assigned
		// this.value = computedAttributFormalism.getDefaultValue();
		this.value = "";  //$NON-NLS-1$
	}

	/** {@inheritDoc} */
	public final String getName() {
		return name;
	}

	/** {@inheritDoc} */
	public final String getValue() {
		// Check whether the formatter has been instantiated or not
		if (this.formatter == null) {
			this.formatter = computedAttributFormalism.getAttributeFormatter();
		}
		// Apply the desired formatter
		return this.formatter.applyFormat(this.value, this.reference);
	}

	/**
	 * {@inheritDoc}
	 *
	 * Should not be used here...<br>
	 * <b>A computed attribute is read-only</b>
	 * */
	public final void setValue(String value) {
		return;
	}

	/** {@inheritDoc} */
	public final IAttributeGraphicInfo getGraphicInfo() {
		return graphicInfo;
	}

	/** {@inheritDoc} */
	public final IElement getReference() {
		return reference;
	}

	/** {@inheritDoc} */
	public final IComputedAttributeFormalism getAttributeFormalism() {
		return this.computedAttributFormalism;
	}

	/** {@inheritDoc} */
	public final EditorGuide getGuide(int orientation) {
		if (orientation == EditorRulerProvider.HORIZONTAL_ORIENTATION) {
			return this.horizontalGuide;
		} else {
			return this.verticalGuide;
		}
	}

	/** {@inheritDoc} */
	public final void setGuide(EditorGuide guide) {
		if (guide.getOrientation() == EditorRulerProvider.HORIZONTAL_ORIENTATION) {
			this.horizontalGuide = guide;
		} else {
			this.verticalGuide = guide;
		}
	}

	/** {@inheritDoc} */
	public final void removeGuide(int orientation) {
		if (orientation == EditorRulerProvider.HORIZONTAL_ORIENTATION) {
			this.horizontalGuide = null;
		} else {
			this.verticalGuide = null;
		}
	}

	/** {@inheritDoc} */
	public final ILocationInfo getLocationInfo() {
		return this.graphicInfo;
	}

	/** {@inheritDoc} */
	@Override
	public final String toString() {
		return "ComputedAttribute: " + name + "= " + value + " [" + reference + "])"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

	/**
	 * {@inheritDoc}
	 *
	 * Typically, the node has been updated (or one of its standard attributes).
	 * Thus, computed attributes should be updated too !
	 */
	public final void propertyChange(PropertyChangeEvent evt) {
		// We want to update the AttributeEditPart (the view associated to this attribute)
		firePropertyChange(IAttribute.VALUE_PROP, evt.getOldValue(), evt.getNewValue());
	}

	//These functions apply to imbricated attributes; supposing there are no imbricated attributes in
	//computed attributes, these don't do much. Do not call them as is!
	
	public boolean isLeaf() {
		return isLeaf;
	}

	public Collection<IAttribute> getAttributes() {
		return null;
	}

	public IAttribute getAttribute(String attName) {
		return null;
	}

	public void setAttributes(Collection<IAttribute> values) {
		
	}

	public void addAttribute(IAttribute value) {
		
	}

	public IAttribute getParent() {
		return parent;
	}

	public void setLeaf() {
	}

	public void initialiseValue() {
	}
}
