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

import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import org.eclipse.gef.commands.Command;

/**
 * Delete a link (between a node {@link ILinkableElement} and a note {@link IStickyNote})
 *
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class LinkDeleteCmd extends Command {
	/** The link to delete */
	private ILink link;

	/**
	 * @param graph The current graph
	 * @param link The link to delete
	 */
	public LinkDeleteCmd(IGraph graph, ILink link) {
		this.link = link;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		link.disconnect();
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		link.connect();
	}
}
