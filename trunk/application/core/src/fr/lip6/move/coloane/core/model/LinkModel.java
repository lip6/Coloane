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
package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.interfaces.model.IElement;

import java.util.logging.Logger;

/**
 * Link between two elements {@link IElement}
 */
public class LinkModel implements ILink {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The sticky note */
	private IStickyNote note;
	/** The element that is linked to the sticky note */
	private ILinkableElement element;

	/**
	 * Constructor.<br>
	 * Create a link between two elements.
	 * This link is not oriented.
	 * <br><br>
	 * @param note The sticky note
	 * @param element The element
	 */
	public LinkModel(IStickyNote note, ILinkableElement element) {
		LOGGER.fine("Build a link: " + note + "--" + element); //$NON-NLS-1$ //$NON-NLS-2$
		if (note == null || element == null) {
			throw new NullPointerException("Argument must be not null"); //$NON-NLS-1$
		}
		this.note = note;
		this.element = element;
	}
	
	/** {@inheritDoc} */
	public final void connect() {
		LOGGER.finer("Connect the link: " + note + "--" + element); //$NON-NLS-1$ //$NON-NLS-2$
		this.note.addLink(this);
		this.element.addLink(this);
	}

	/** {@inheritDoc} */
	public final void disconnect() {
		LOGGER.finer("Disconnect the link: " + note + "--" + element); //$NON-NLS-1$ //$NON-NLS-2$
		this.note.removeLink(this);
		this.element.removeLink(this);
	}

	/** {@inheritDoc} */
	public final ILinkableElement getElement() {
		return element;
	}

	/** {@inheritDoc} */
	public final IStickyNote getNote() {
		return note;
	}

	/** {@inheritDoc} */
	public final void reconnect(IStickyNote newNote, ILinkableElement newElement) {
		note.removeLink(this);
		element.removeLink(this);
		note = newNote;
		element = newElement;
		note.addLink(this);
		element.addLink(this);
	}
}
