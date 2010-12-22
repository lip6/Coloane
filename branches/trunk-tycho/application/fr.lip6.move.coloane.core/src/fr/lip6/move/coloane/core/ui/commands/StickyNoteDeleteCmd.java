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

import fr.lip6.move.coloane.core.model.interfaces.ICoreGraph;
import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

/**
 * Delete a sticky note
 */
public class StickyNoteDeleteCmd extends Command {

	/** Note to delete */
	private final IStickyNote note;

	/** The graph that holds the note */
	private final ICoreGraph graph;

	/** Links used by this note */
	private List<ILink> links;

	/**
	 * Constructor
	 * @param graph The graph that holds the note
	 * @param stickyNote The note
	 */
	public StickyNoteDeleteCmd(IGraph graph, IStickyNote stickyNote) {
		super(Messages.StickyNoteDeleteCmd_0);
		this.graph = (ICoreGraph) graph;
		this.note = stickyNote;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		links = new ArrayList<ILink>(note.getLinks());
		this.redo(); // Execute
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		graph.deleteSticky(note);
		for (ILink link : links) {
			link.disconnect();
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		graph.addSticky(note);
		for (ILink link : links) {
			link.connect();
		}
	}
}
