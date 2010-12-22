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
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;

import org.eclipse.gef.commands.Command;

/**
 * Reconnect a link with another source/target
 * 
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class LinkReconnectCmd extends Command {
	/** The link to reconnect to */
	private ILink link;

	/** The note */
	private IStickyNote newNote;
	/** The new element designated by the reconnection */
	private ILinkableElement newElement;

	/** The old note (back up in case of undo) */
	private IStickyNote oldNote;
	/** The old element (backup in case of undo) */
	private ILinkableElement oldElement;

	/**
	 * Constructor
	 * @param link Link to reconnect
	 * @param newNote New source
	 * @param newElement New element
	 */
	public LinkReconnectCmd(ILink link, IStickyNote newNote, ILinkableElement newElement) {
		this.link = link;
		this.newNote = newNote;
		this.newElement = newElement;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		oldNote = link.getNote();
		oldElement = link.getElement();
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		link.reconnect(newNote, newElement);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		link.reconnect(oldNote, oldElement);
	}

}
