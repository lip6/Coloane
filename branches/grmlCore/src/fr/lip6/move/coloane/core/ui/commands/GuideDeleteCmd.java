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
import fr.lip6.move.coloane.core.ui.rulers.EditorRuler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.gef.commands.Command;

/**
 * Delete a guide associate to a ruler
 */
public class GuideDeleteCmd extends Command {

	/** The ruler that holds the guide */
	private EditorRuler ruler;

	/** The guide which will be deleted */
	private EditorGuide guide;

	/** Backup objects in case of backup */
	private Map<ILocatedElement, Integer> oldElements;

	/**
	 * Constructor
	 * @param guide The guide to delete
	 * @param ruler The ruler that holds the guide
	 */
	public GuideDeleteCmd(EditorGuide guide, EditorRuler ruler) {
		super(Messages.DeleteGuideCommand_0);
		this.guide = guide;
		this.ruler = ruler;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		oldElements = new HashMap<ILocatedElement, Integer>(guide.getAttachedElementsWithAlignment());
		Iterator<ILocatedElement> iter = oldElements.keySet().iterator();
		while (iter.hasNext()) {
			guide.detachElement(iter.next());
		}
		ruler.removeGuide(guide);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		ruler.addGuide(guide);
		Iterator<ILocatedElement> iter = oldElements.keySet().iterator();
		while (iter.hasNext()) {
			ILocatedElement locatedElement = iter.next();
			guide.attachElement(locatedElement, ((Integer) oldElements.get(locatedElement)).intValue());
		}
	}
}
