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

import org.eclipse.gef.commands.Command;

/**
 * Edit a sticky note (change its value)
 */
public class StickyNoteEditCmd extends Command {

	/** The note */
	private IStickyNote note;

	/** The new value for the sticky note */
	private String newValue;

	/** The old note */
	private String oldValue;

	/**
	 * Constructor
	 * @param note The sticky note
	 * @param newValue The new value for the sticky note
	 */
	public StickyNoteEditCmd(IStickyNote note, String newValue) {
		super(Messages.StickyNoteEditCommand_0);
		this.note = note;
		this.newValue = ""; //$NON-NLS-1$
		if (newValue != null) {
			this.newValue = newValue;
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		oldValue = note.getLabelContents();
		note.setLabelContents(newValue);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		note.setLabelContents(oldValue);
	}
}
