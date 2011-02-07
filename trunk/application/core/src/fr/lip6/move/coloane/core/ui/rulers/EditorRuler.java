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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.rulers.RulerProvider;

/**
 * Define a ruler
 *
 * @author Jean-Baptiste Voron
 */
public class EditorRuler {

	/** Event raised when some a new guide is connected to this ruler */
	public static final String NEW_GUIDE_PROP = "Rule.AddGuide"; //$NON-NLS-1$
	/** Event raised when some a guide is removed from this ruler */
	public static final String REMOVE_GUIDE_PROP = "Rule.RemoveGuide"; //$NON-NLS-1$
	/** Event raised when the unit of the ruler has changed */
	public static final String UNIT_PROP = "Rule.Units"; //$NON-NLS-1$

	/** Set of listeners */
	private PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	/** The unit */
	private int unit;

	/**
	 * The rule orientation
	 * @see EditorRulerProvider.#HORIZONTAL_ORIENTATION
	 * @see EditorRulerProvider.#VERTICAL_ORIENTATION
	 */
	private int orientation;

	/** List of guides that are associated with this ruler */
	private List<EditorGuide> guides = new ArrayList<EditorGuide>();

	/**
	 * Constructor
	 * @param isHorizontal <code>true</code> if the ruler is horizontal
	 */
	public EditorRuler(int orientation) {
		this(orientation, RulerProvider.UNIT_CENTIMETERS);
	}

	/**
	 * Constructor
	 * @param isHorizontal <code>true</code> if the ruler is horizontal
	 * @param unit The unit used by this ruler
	 */
	public EditorRuler(int orientation, int unit) {
		this.orientation = orientation;
		setUnit(unit);
	}

	/**
	 * Add a guide to the ruler
	 * @param guide The guide to add to the ruler
	 * @see EditorGuide
	 */
	public final void addGuide(EditorGuide guide) {
		if (!guides.contains(guide)) {
			guides.add(guide);
			listeners.firePropertyChange(NEW_GUIDE_PROP, null, guide);
		}
	}

	/**
	 * @return the list of attached guides
	 */
	public final List<EditorGuide> getGuides() {
		return guides;
	}

	/**
	 * @return the unit used by the ruler
	 */
	public final int getUnit() {
		return unit;
	}

	/**
	 * @return <code>true</code> if the ruler is hidden
	 */
	public final boolean isHidden() {
		return false;
	}

	/**
	 * @return the ruler orientation
	 * @see EditorRulerProvider.#HORIZONTAL_ORIENTATION
	 * @see EditorRulerProvider.#VERTICAL_ORIENTATION
	 */
	public final int getOrientation() {
		return this.orientation;
	}
	
	/**
	 * @return the orientation of guides that are attached to this ruler
	 * Basically, their orientation is the opposite of the ruler's one.
	 */
	public final int getGuidesOrientation() {
		if (getOrientation() == EditorRulerProvider.HORIZONTAL_ORIENTATION) {
			return EditorRulerProvider.VERTICAL_ORIENTATION;
		} else {
			return EditorRulerProvider.HORIZONTAL_ORIENTATION;
		}
	}

	/**
	 * Remove the designated guide
	 * @param guide The guide to remove from the ruler
	 */
	public final void removeGuide(EditorGuide guide) {
		if (guides.remove(guide)) {
			listeners.firePropertyChange(REMOVE_GUIDE_PROP, guide, null);
		}
	}

	/**
	 * Add a listener
	 * @param listener The listener to add to the list
	 */
	public final void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}

	/**
	 * Remove a listener
	 * @param listener The listener to remove from the list
	 */
	public final void removePropertyChangeListener(PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}

	/**
	 * Set the visibility of the ruler
	 * @param isHidden <code>true</code> if the ruler has to be hidden
	 */
	public void setHidden(boolean isHidden) { }

	/**
	 * Change the unit of the ruler
	 * @param newUnit The new unit to use
	 */
	public final void setUnit(int newUnit) {
		if (unit != newUnit) {
			int oldUnit = unit;
			unit = newUnit;
			listeners.firePropertyChange(UNIT_PROP, oldUnit, newUnit);
		}
	}
}
