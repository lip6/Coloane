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

import com.google.inject.Injector;

import java.util.List;

/**
 * This class represents dedicated formalism (standard) attribute characteristics.<br>
 * An attribute is like a property of a base element.<br>
 * Each base element has to maintain its own list of attributes.<br>
 * <br>
 * Standard Attributes share some properties with Computed Attributes.<br>
 * Those common properties are detailed in Global Attributes definition.
 *
 * @see IComputedAttributeFormalism
 * @see IGlobalAttributeFormalism
 *
 * @author Jean-Baptiste Voron
 */
public interface IAttributeFormalism extends IGlobalAttributeFormalism {

	/** @return Multiline status */
	boolean isMultiLine();
	

	/**  @param isMultiline Whether the attribute is displayed on multiple lines */
	void setMultiline(boolean isMultiline);

	/**
	 * @return The parser for this attribute's value
	 */
	IAttributeParser getParser();
	
	/**
	 * @param parser The parser to use to parse this attribute's value
	 */
	void setParser(IAttributeParser parser);
	
	/**
	 * @return the xtext setup class for this attribute
	 */
	Injector getInjector();
	
	/**
	 * @param injector  The guice injector for this attribute
	 */
	void setInjector(Injector injector);
	
	/**
	 * @return The list of {@link AttributeFormalism} attached to this element.
	 */
	List<IAttributeFormalism> getAttributes();

	/**
	 * Set the default value.<br>
	 * @param defaultValue the new default value
	 */
	void setDefaultValue(String defaultValue);

	/**
	 * Set the bold status of the attribute
	 * @param bold <code>true</code> if the attribute has to be displayed in bold
	 */
	void setBold(boolean bold);

	/**
	 * Set the italic status of the attribute
	 * @param italic <code>true</code> if the attribute has to be displayed in italic
	 */
	void setItalic(boolean italic);

	/**
	 * Set the default font size of the attribute
	 * @param size font-size
	 */
	void setSize(String size);

	/**
	 * @param isDefaultValueDrawable Should the attribute be displayed even if its value matches the default one?
	 */
	void setDefaultValueDrawable(boolean isDefaultValueDrawable);

	/**
	 * <b>Delta Location</b> is used to specify the relative position of the attribute according to the parent element.<br>
	 * @param x X coordinate of the Delta Location.
	 */
	void setXDelta(int x);

	/**
	 * <b>Delta Location</b> is used to specify the relative position of the attribute according to the parent element.<br>
	 * @param y Y coordinate of the Delta Location.
	 */
	void setYDelta(int y);
	
	/**
	 * Add a new attribute to this attribute
	 * @param name The reference name of the child attribute
	 * @param minOccurs The minimum number of occurences of the attribute
	 * @param maxOccurs The maximum number of occurences of the attribute
	 */
	void addAttribute(String name, int minOccurs, int maxOccurs);
}
