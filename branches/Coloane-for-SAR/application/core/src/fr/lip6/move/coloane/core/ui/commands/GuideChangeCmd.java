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
package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;

import org.eclipse.gef.commands.Command;

/**
 * Change the guide associated to an element
 */
public class GuideChangeCmd extends Command {
	/** The moveable element */
	private ILocatedElement locatedElement;

	/** Old and new guides */
	private EditorGuide oldGuide, newGuide;

	/** Old and new align */
	private int oldAlign, newAlign;

	/**
	 * Constructor
	 * @param locatedElement The element
	 * @param orientation The orientation of the guide
	 * @see EditorRulerProvider
	 */
	public GuideChangeCmd(ILocatedElement locatedElement, int orientation) {
		super();
		this.locatedElement = locatedElement;
		this.oldGuide = locatedElement.getGuide(orientation);
	}

	/**
	 * Switch guide
	 * @param oldGuide The old guide
	 * @param newGuide The new guide
	 * @param newAlignment The new align indicator
	 */
	protected final void changeGuide(EditorGuide oldGuide, EditorGuide newGuide, int newAlignment) {
		if (oldGuide != null && oldGuide != newGuide) {
			oldGuide.detachElement(locatedElement);
		}

		// You need to re-attach the part even if the oldGuide and the newGuide
		// are the same because the alignment could have changed
		if (newGuide != null) {
			newGuide.attachElement(locatedElement, newAlignment);
		}
	}

	/**
	 * Set the new guide
	 * @param guide The new guide
	 * @param alignment The new align indicator
	 */
	public final void setNewGuide(EditorGuide guide, int alignment) {
		this.newGuide = guide;
		this.newAlign = alignment;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		if ((this.newGuide == null) && (this.oldGuide == null)) { return; }
		// Cache the old values
		if (this.oldGuide != null) {
			this.oldAlign = this.oldGuide.getAlignment(this.locatedElement);
		}
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		changeGuide(this.oldGuide, this.newGuide, this.newAlign);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		changeGuide(this.newGuide, this.oldGuide, this.oldAlign);
	}
}
