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

import com.google.inject.Injector;

import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeParser;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;

/**
 * This class represents all the formalism attribute characteristics.<br>
 * An attribute is like a property of a base element.<br>
 * Each base element has to maintain its own list of attributes.
 *
 * @author Jean-Baptiste Voron
 */
public class AttributeFormalism implements IAttributeFormalism {
	/** Name */
	private String name;
	
	/** Reference */
	private String reference;

	/** Is the attribute multiline? */
	private boolean isMultiline = false;

	/** Is the attribute drawable? */
	private boolean isDrawable = true;

	/** The default value */
	private String defaultValue = null;

	/** Should the attribute be displayed even if its value matches the default one? */
	private boolean isDefaultValueDrawable = false;

	/** Bold? */
	private boolean bold = false;

	/** Italic? */
	private boolean italic = false;

	/** Font size? */
	private int size = 10;

	/** Delta Location */
	private Point delta = new Point(0, 0);
	
	/** Defines the parser for the text entered */
	private IAttributeParser parser;
	
	/** Defines the xtext setup class for the text entered */
	private Injector injector;
	
	/** Containing formalism */
	private IFormalism formalism;

	/** Attributes list (as reference/name pairs) */
	private List<ChildAttribute> attributes = new ArrayList<ChildAttribute>();

	/**
	 * Build an attribute
	 * @param name Name
	 * @param reference The name of the attribute's reference (can be empty)
	 * @param formalism The formalism containing this attribute
	 */
	public AttributeFormalism(String name, String reference, IFormalism formalism) {
		this.name = name;
		this.reference = reference;
		this.formalism = formalism;
	}

	/**
	 * Build an attribute
	 * @param name Name
	 * @param reference Th name of the attribute's reference (can be empty)
	 * @param defaultValue Default value
	 * @param formalism The formalism containing this attribute
	 */
	public AttributeFormalism(String name, String reference, String defaultValue, IFormalism formalism) {
		this(name, reference, formalism);
		this.defaultValue = defaultValue;
	}
	
	/** {@inheritDoc} */
	@Override
	public final String getName() { return this.name; }
	
	/** {@inheritDoc} */
	@Override
	public final String getReference() { return reference; }

	/** {@inheritDoc} */
	@Override
	public final boolean isDrawable() { return isDrawable; }
	
	/** {@inheritDoc} */
	@Override
	public final void setDrawable(boolean isDrawable) { this.isDrawable = isDrawable; }

	/** {@inheritDoc} */
	@Override
	public final boolean isMultiLine() { return isMultiline; }
	
	/** {@inheritDoc} */
	@Override
	public final void setMultiline(boolean isMultiline) { this.isMultiline = isMultiline; }

	/** {@inheritDoc} */
	@Override
	public final String getDefaultValue() {
		if (defaultValue != null) { return defaultValue; }
		return ""; //$NON-NLS-1$
	}

	/** {@inheritDoc} */
	@Override
	public final void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isBold() {
		return bold;
	}

	/** {@inheritDoc} */
	@Override
	public final void setBold(boolean bold) {
		this.bold = bold;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isItalic() {
		return italic;
	}

	/** {@inheritDoc} */
	@Override
	public final void setItalic(boolean italic) {
		this.italic = italic;
	}

	/** {@inheritDoc} */
	@Override
	public final Integer getFontSize() {
		return this.size;
	}

	/** {@inheritDoc} */
	@Override
	public final void setSize(String size) {
		this.size = Integer.valueOf(size);
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isDefaultValueDrawable() {
		return this.isDefaultValueDrawable;
	}

	/** {@inheritDoc} */
	@Override
	public final void setDefaultValueDrawable(boolean isDefaultValueDrawable) {
		this.isDefaultValueDrawable = isDefaultValueDrawable;
	}

	/** {@inheritDoc} */
	@Override
	public final void setXDelta(int x) {
		this.delta.x = x;
	}

	/** {@inheritDoc} */
	@Override
	public final void setYDelta(int y) {
		this.delta.y = y;
	}

	/** {@inheritDoc} */
	@Override
	public final Point getDeltaLocation() {
		return this.delta;
	}
	
	/** {@inheritDoc} */
	@Override
	public final void addAttribute(String name, int minOccurs, int maxOccurs) {
		this.attributes.add(new ChildAttribute(name, minOccurs, maxOccurs));
	}

	/** {@inheritDoc} */
	@Override
	public final List<IAttributeFormalism> getAttributes() {
		List<IAttributeFormalism> list = new ArrayList<IAttributeFormalism>();
		for (ChildAttribute c : attributes) {
			list.add(formalism.getAttributeFormalism(c.getRefName(), "")); //child attributes are always reference-less //$NON-NLS-1$
		}
		return list;
	}

	/** {@inheritDoc} */
	@Override
	public final IAttributeParser getParser() {
		return parser;
	}

	/** {@inheritDoc} */
	@Override
	public final void setParser(IAttributeParser parser) {
		this.parser = parser;
	}

	/** {@inheritDoc} */
	@Override
	public final Injector getInjector() {
		return injector;
	}

	/** {@inheritDoc} */
	@Override
	public final void setInjector(Injector injector) {
		this.injector = injector;
	}
	
}
