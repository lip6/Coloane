package fr.lip6.move.coloane.core.motor.formalisms.elements;

import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;

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

	/** Is the attribute multiline? */
	private boolean isMultiline;

	/** Is the attribute drawable? */
	private boolean isDrawable;

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
	private Point delta = new Point(0,0);

	/** Defines if this attribute is enumerated, and then enumeration is non null. */
	private boolean enumerated;

	/** Defines the set of legal values for this attribute */
	private List<String> enumeration;

	/**
	 * Build an attribute
	 * @param name Name
	 * @param isDrawable Drawable status
	 * @param isMultiline Multiline status
	 * @param isEnumerated Enumerated status
	 * @param enumValue Authorized values for the enumeration (or <code>null</code> if isEnumerated is <code>false</code>)
	 */
	public AttributeFormalism(String name, boolean isDrawable, boolean isMultiline, boolean isEnumerated, List<String> enumValue) {
		this.name = name;
		this.isDrawable = isDrawable;
		this.isMultiline = isMultiline;
		this.enumerated = isEnumerated;
		this.enumeration = enumValue;
	}

	/**
	 * Build an attribute
	 * @param name Name
	 * @param isDrawable Drawable status
	 * @param isMultiline Multiline status
	 * @param isEnumerated Enumerated status
	 * @param enumValue Authorized values for the enumeration (or <code>null</code> if isEnumerated is <code>false</code>)
	 * @param defaultValue Default value
	 * @param isDefaultValueDrawable Drawable status for the default value
	 */
	public AttributeFormalism(String name, boolean isDrawable, boolean isMultiline, boolean isEnumerated, List<String> enumValues, String defaultValue, boolean isDefaultValueDrawable) {
		this(name, isDrawable, isMultiline, isEnumerated, enumValues);
		this.defaultValue = defaultValue;
		this.isDefaultValueDrawable = isDefaultValueDrawable;
	}

	/** {@inheritDoc} */
	public final String getName() { return this.name; }

	/** {@inheritDoc} */
	public final boolean isDrawable() { return isDrawable; }

	/** {@inheritDoc} */
	public final boolean isMultiLine() { return isMultiline; }

	/** {@inheritDoc} */
	public final String getDefaultValue() {
		if (defaultValue != null) { return defaultValue; }
		return ""; //$NON-NLS-1$
	}

	/**
	 * Set the default value.<br>
	 * @param defaultValue the new default value
	 */
	public final void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/** {@inheritDoc} */
	public final boolean isBold() {
		return bold;
	}

	/**
	 * Set the bold status of the attribute
	 * @param bold <code>true</code> if the attribute has to be displayed in bold
	 */
	public final void setBold(boolean bold) {
		this.bold = bold;
	}

	/** {@inheritDoc} */
	public final boolean isItalic() {
		return italic;
	}

	/**
	 * Set the italic status of the attribute
	 * @param italic <code>true</code> if the attribute has to be displayed in italic
	 */
	public final void setItalic(boolean italic) {
		this.italic = italic;
	}

	/** {@inheritDoc} */
	public final Integer getFontSize() {
		return this.size;
	}

	/**
	 * Set the default font size of the attribute
	 * @param size font-size
	 */
	public final void setSize(String size) {
		this.size = Integer.valueOf(size);
	}
	
	/** {@inheritDoc} */
	public final List<String> getEnumeration() {
		return this.enumeration;
	}
	
	/** {@inheritDoc} */
	public final boolean isEnumerated() {
		return this.enumerated;
	}
	
	/** {@inheritDoc} */
	public final boolean isDefaultValueDrawable() {
		return this.isDefaultValueDrawable;
	}
	
	/**
	 * Should the attribute be displayed even if its value matches the default one?
	 * @param isDefaultValueDrawable
	 */
	public final void setDefaultValueDrawable(boolean isDefaultValueDrawable) {
		this.isDefaultValueDrawable = isDefaultValueDrawable;
	}

	/**
	 * <b>Delta Location</b> is used to specify the relative position of the attribute according to the parent element.<br>
	 * @param x X coordinate of the Delta Location.
	 */
	public final void setXDelta(int x) {
		this.delta.x = x;
	}
	
	/**
	 * <b>Delta Location</b> is used to specify the relative position of the attribute according to the parent element.<br>
	 * @param y Y coordinate of the Delta Location.
	 */
	public final void setYDelta(int y) {
		this.delta.y = y;
	}
	
	/** {@inheritDoc} */
	public final Point getDeltaLocation() {
		return this.delta;
	}
}
