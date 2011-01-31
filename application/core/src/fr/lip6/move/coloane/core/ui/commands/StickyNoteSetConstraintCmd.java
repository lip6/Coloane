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

import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

/**
 * Move a stocky note
 */
public class StickyNoteSetConstraintCmd extends Command {

	/** The note */
	private final IStickyNote note;

	/** The new size and position */
	private final Rectangle newBounds;

	/** The old size and positions */
	private Rectangle oldBounds;

	/**
	 * Constructor
	 * @param note The note
	 * @param newBounds New size and location
	 */
	public StickyNoteSetConstraintCmd(IStickyNote note, Rectangle newBounds) {
		if (note == null || newBounds == null) {
			throw new IllegalArgumentException();
		}

		this.note = note;
		this.newBounds = newBounds.getCopy();
		this.newBounds.x = Math.max(this.newBounds.x, 0);
		this.newBounds.y = Math.max(this.newBounds.y, 0);
	}

	/** {@inheritDoc} */
	@Override
	public final boolean canExecute() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		oldBounds = new Rectangle(note.getLocation(), note.getSize());
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		note.setLocation(newBounds.getLocation());
		note.setSize(newBounds.getSize());
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		note.setLocation(oldBounds.getLocation());
		note.setSize(oldBounds.getSize());
	}
}
