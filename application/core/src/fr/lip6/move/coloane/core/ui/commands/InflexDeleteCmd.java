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

import fr.lip6.move.coloane.interfaces.model.IArc;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

/**
 * Delete an inflex point from an arc
 * 
 * @author Jean-Baptiste Voron
 */
public class InflexDeleteCmd extends Command {

	/** The arc from which the inflex point will be removed */
	private IArc arc;
	/** The index of the point inside the list of inflex points */
	private int index;
	/** The location of the inflex point that will be removed */
	private Point position;

	/**
	 * Constructor
	 * @param arc The arc from which the inflex point will be removed
	 * @param index the index of the inflex point in the list of arc inflex points
	 */
	public InflexDeleteCmd(IArc arc, int index) {
		super(Messages.InflexDeleteCmd_0);
		this.arc = arc;
		this.index = index;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		this.position = arc.getInflexPoint(index);
		this.redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		this.arc.addInflexPoint(this.position, this.index);
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		this.arc.removeInflexPoint(this.index);
	}
}
