package fr.lip6.move.coloane.interfaces.formalism;

import java.util.List;

/**
 * This class represents all the formalism attribute characteristics.<br>
 * An attribute is like a property of a base element.<br>
 * Each base element has to maintain its own list of attributes.
 * 
 * @author Jean-Baptiste Voron
 */
public interface IAttributeFormalism {

	/** @return Attribute name */
	String getName();

	/** @return Drawable status (should the attribute be displayed ?) */
	boolean isDrawable();

	/** @return Multiline status */
	boolean isMultiLine();

	/**
	 * @return Is the attribute enumerated ?
	 * @see getEnumeration() for fetching authorized values
	 */
	boolean isEnumerated();
	
	/**
	 * @return the list of authorized values for the given attribute
	 */
	List<String> getEnumeration(); 
	
	/**
	 * @return the default value
	 */
	String getDefaultValue();

	/**
	 * @return <code>true</code> if the attribute has to be displayed with a bold font
	 */
	boolean isBold();

	/**
	 * @return <code>true</code> if the attribute has to be displayed with an italic font 
	 */
	boolean isItalic();

	/**
	 * @return the font size
	 */
	Integer getFontSize();
	
	/**
	 * @return <code>true</code> if the attribute has to be displayed even if its valued is the default one
	 */
	boolean isDefaultValueDrawable();
}
