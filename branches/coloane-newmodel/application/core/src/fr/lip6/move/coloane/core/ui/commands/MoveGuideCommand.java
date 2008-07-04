/*******************************************************************************
 * Copyright (c) 2006-2007 INCOME2010 Project.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Institute AIFB, University of Karlsruhe - initial API and implementation
 *******************************************************************************/
package fr.lip6.move.coloane.core.ui.commands;

import java.util.Iterator;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import fr.lip6.move.coloane.core.model.ILocatedElement;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;

/**
 * A command to move a guide.
 * 
 * @author Pratik Shah, Yu Li
 */
public class MoveGuideCommand extends Command {
	
	/** Déplacement appliqué au guide */
	private int delta;
	
	/** Guide concerné par la déplacement */
	private EditorGuide guide;

	/**
	 * Constructeur
	 * @param guide le guide concerné par le déplacement
	 * @param delta le déplacement appliqué au guide
	 */
	public MoveGuideCommand(EditorGuide guide, int delta) {
		super("Move a guide");
		this.guide = guide;
		this.delta = delta;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		guide.setPosition(guide.getPosition() + delta);
		Iterator<ILocatedElement> iter = guide.getModelObjects().iterator();
		while (iter.hasNext()) {
			ILocatedElement locatedElement = iter.next();
			Point location = locatedElement.getLocationInfo().getLocation().getCopy();
			if (guide.isHorizontal()) {
				location.y += delta;
			} else { 
				location.x += delta;
			}			
			locatedElement.getLocationInfo().setLocation(location);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		guide.setPosition(guide.getPosition() - delta);
		Iterator<ILocatedElement> iter = guide.getModelObjects().iterator();
		while (iter.hasNext()) {
			ILocatedElement locatedElement = iter.next();
			Point location = locatedElement.getLocationInfo().getLocation().getCopy();
			if (guide.isHorizontal()) {
				location.y -= delta;
			} else {
				location.x -= delta;
			}			
			locatedElement.getLocationInfo().setLocation(location);
		}
	}
}
