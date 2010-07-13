package fr.lip6.move.coloane.core.model.interfaces;

import fr.lip6.move.coloane.interfaces.model.ILocationInfo;

import org.eclipse.draw2d.geometry.Dimension;

/**
 * Sticky note interface 
 * 
 * @author Jean-Baptiste Voron
 */
public interface IStickyNote extends ILocationInfo, ILinkableElement {

	/** Event raised when the sticky note value is updated */
	String VALUE_PROP = "Sticky.ValueUpdate"; //$NON-NLS-1$

	/** Event raised when the sticky note is resized */
	String RESIZE_PROP = "Sticky.Resize"; //$NON-NLS-1$

	/**
	 * @return The sticky note value
	 */
	String getLabelContents();

	/**
	 * Set a new value for the sticky note
	 * @param newText The new value
	 */
	void setLabelContents(String newText);

	/**
	 * @return The sticky note size
	 */
	Dimension getSize();

	/**
	 * Set a new size for the sticky note
	 * @param size The new size
	 */
	void setSize(Dimension size);
}
