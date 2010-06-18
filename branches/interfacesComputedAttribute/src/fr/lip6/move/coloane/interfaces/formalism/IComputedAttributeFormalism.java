package fr.lip6.move.coloane.interfaces.formalism;

import fr.lip6.move.coloane.interfaces.model.IAttributeFormatter;

import org.eclipse.draw2d.geometry.Point;

/**
 * This class represents all the formalism attribute characteristics.<br>
 * An attribute is like a property of a base element.<br>
 * Each base element has to maintain its own list of attributes.
 * 
 * @author Jean-Baptiste Voron
 */
public interface IComputedAttributeFormalism {

	/** @return Attribute name */
	String getName();

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
	
	/** 
	 * <b>Delta Location</b> is used to specify the relative position of the attribute according to the parent element.<br>
	 * @return the delta location
	 */
	Point getDeltaLocation();
	
	/** 
	 * @return The class that is supposed to format the attribute value
	 */
	IAttributeFormatter getAttributeFormatter();
}
