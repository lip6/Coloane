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
package fr.lip6.move.coloane.core.ui.rulers;

import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * Define a guide
 * 
 * @author Jean-Baptiste Voron
 */
public class EditorGuide {	
	/** Property used to notify listeners when an element is attached/detached to/from the guide */
	public static final String ELEMENT_PROP = "Guide.Element"; //$NON-NLS-1$

	/** Property used to notify listeners when the guide is re-positioned */
	public static final String PROPERTY_POSITION = "Guide.Position"; //$NON-NLS-1$

	private PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	/**
	 *  All elements that are attached to this guide with their alignment.<br>
	 *  <ul>
	 *  	<li>-1: <i>left</i> or <i>top</i></li>
	 *  	<li>0: <i>center</i></li>
	 *  	<li>1: <i>right</i> or <i>bottom</i></li>
	 *  </ul>
	 */
	private Map<ILocatedElement, Integer> attachedElements;

	/** Position */
	private int position;

	/** 
	 * Orientation
	 * @see #HORIZONTAL_ORIENTATION
	 * @see #VERTICAL_ORIENTATION
	 */
	private int orientation;

	/**
	 * Constructor
	 * @param orientation The orientation of the guide
	 * <b>The guide is horizontal if it is associated with a vertical rule</b>
	 */
	public EditorGuide(int orientation) {
		setOrientation(orientation);
	}

	/**
	 * Attach the element to the guide.
	 * Note that the element is also modified.
	 * If the element is already attached, it is detached first.
	 * @param locatedElement The element that is attached to the guide. 
	 * @param alignment The desired alignment
	 * @see #attachedElements
	 */
	public final void attachElement(ILocatedElement locatedElement, int alignment) {
		// Check if the element is already attached to this guide
		if (getAttachedElementsWithAlignment().containsKey(locatedElement) && getAlignment(locatedElement) == alignment) {
			return;
		}

		// If not, the element is added to the list of attached elements (with its alignment)
		getAttachedElementsWithAlignment().put(locatedElement, Integer.valueOf(alignment));

		// Fetch an already existing guide for this object
		EditorGuide guide = locatedElement.getGuide(getOrientation());

		// If a guide already exists, we detach it first...
		if (guide != null && guide != this) {
			guide.detachElement(locatedElement);
		}

		// And attach the new one (in all situations)
		locatedElement.setGuide(this);

		// Tells the element that it is now attached to a guide
		listeners.firePropertyChange(ELEMENT_PROP, null, locatedElement);
	}

	/**
	 * Detach the element form this guide
	 * @param locatedElement The element to detach
	 */
	public final void detachElement(ILocatedElement locatedElement) {
		// Check if the element is actually stick to this guide
		if (getAttachedElementsWithAlignment().containsKey(locatedElement)) {
			getAttachedElementsWithAlignment().remove(locatedElement);
			locatedElement.removeGuide(this.getOrientation());
			listeners.firePropertyChange(ELEMENT_PROP, null, locatedElement);
		}
	}

	/**
	 * Return the side used to attached the element to the guide
	 * @param locatedElement The element
	 * @return the alignment information for this object
	 */
	public final int getAlignment(ILocatedElement locatedElement) {
		if (getAttachedElementsWithAlignment().get(locatedElement) != null) {
			return ((Integer) getAttachedElementsWithAlignment().get(locatedElement)).intValue();
		}
		return -2;
	}

	/**
	 * @return all attached element with their alignment information
	 */
	public final Map<ILocatedElement, Integer> getAttachedElementsWithAlignment() {
		if (attachedElements == null) {
			attachedElements = new Hashtable<ILocatedElement, Integer>();
		}
		return attachedElements;
	}

	/**
	 * @return all objects that are attached to this guide
	 */
	public final Set<ILocatedElement> getAttachedElements() {
		return getAttachedElementsWithAlignment().keySet();
	}

	/**
	 * @return the current position of the guide
	 */
	public final int getPosition() {
		return position;
	}

	/**
	 * @return the orientation of the guide
	 * @see EditorRulerProvider.#HORIZONTAL_ORIENTATION
	 * @see EditorRulerProvider.#VERTICAL_ORIENTATION
	 */
	public final int getOrientation() {
		return orientation;
	}

	/**
	 * Set the guide orientation
	 * @param orientation The guide orientation
	 * @see #HORIZONTAL_ORIENTATION
	 * @see #VERTICAL_ORIENTATION
	 */
	private final void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	/**
	 * Set the position
	 * @param offset The new guide position
	 */
	public final void setPosition(int offset) {
		if (this.position != offset) {
			int oldValue = this.position;
			this.position = offset;
			listeners.firePropertyChange(PROPERTY_POSITION, Integer.valueOf(oldValue), Integer.valueOf(position));
		}
	}

	/**
	 * Remove the listener from the list
	 * @param listener The listener to remove from the list
	 */
	public final void removePropertyChangeListener(PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}

	/**
	 * Add a listener to the list
	 * @param listener The listener to add to the list
	 */
	public final void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}
}
