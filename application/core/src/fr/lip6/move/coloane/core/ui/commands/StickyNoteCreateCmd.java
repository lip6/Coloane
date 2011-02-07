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
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

/**
 * Add a sticky note
 */
public class StickyNoteCreateCmd extends Command {

	/** The note */
	private IStickyNote note;

	/** The graph where the note will be put into */
	private final ICoreGraph graph;

	/** The location of the note */
	private Point location;

	/** Dimensions of the note */
	private Dimension dimension;

	/**
	 * Constructor
	 * @param graph The graph that will hold the note
	 * @param dimensions Note dimensions
	 */
	public StickyNoteCreateCmd(IGraph graph, Rectangle dimensions) {
		super(Messages.StickyNoteCreateCommand_0);
		this.graph = (ICoreGraph) graph;
		this.location = dimensions.getLocation();
		this.dimension = dimensions.getSize();
	}

	/** {@inheritDoc} */
	@Override
	public final boolean canExecute() {
		return graph != null;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		note = graph.createStickyNote();
		note.setLocation(location);
		note.setSize(this.dimension);
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		graph.addSticky(note);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		graph.deleteSticky(note);
	}
}
