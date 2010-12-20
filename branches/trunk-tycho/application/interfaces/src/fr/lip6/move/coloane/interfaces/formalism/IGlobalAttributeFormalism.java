package fr.lip6.move.coloane.interfaces.formalism;

import org.eclipse.draw2d.geometry.Point;

public interface IGlobalAttributeFormalism {

	/** @return Attribute name */
	String getName();

	/**
	 * @return the default value
	 */
	String getDefaultValue();
	
	/**
	 * @return Drawable status (should the attribute be displayed ?)
	 * */
	boolean isDrawable();

	/**
	 * @return <code>true</code> if the attribute has to be displayed even if its valued is the default one
	 */
	boolean isDefaultValueDrawable();

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
	 * <b>Delta Location</b> is used to specify the relative position of the attribute according to the parent element.<br>
	 * @return the delta location
	 */
	Point getDeltaLocation();
}
