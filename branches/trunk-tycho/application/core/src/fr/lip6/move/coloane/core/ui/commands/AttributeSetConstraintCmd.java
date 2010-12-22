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

import fr.lip6.move.coloane.interfaces.model.IAttribute;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

/**
 * Set a new position for an attribute
 * 
 * @author Jean-Baptiste Voron
 */
public class AttributeSetConstraintCmd extends Command {

	/** The new location for the attribute */
	private Point newPosition;

	/** The old location (backed-up to be able to undo the operation) */
	private Point oldPosition;

	/** The attribute */
	private final IAttribute attribute;

	/**
	 * Constructor
	 * @param attribute The attribute to move
	 * @param newPosition New position for the attribute
	 */
	public AttributeSetConstraintCmd(IAttribute attribute, Point newPosition) {
		super(Messages.AttributeSetConstraintCmd_0);
		// Attribute and NewPosition should not be null
		assert(attribute != null);
		assert(newPosition != null);
		
		this.attribute = attribute;
		this.newPosition = newPosition.getCopy();
	}

	/**
	 * A node is always movable.
	 * Its size is given by the formalism and blocked by the <i>editpolicies</i>
	 * @return true
	 */
	@Override
	public final boolean canExecute() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		this.oldPosition = attribute.getGraphicInfo().getLocation();
		this.redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		attribute.getGraphicInfo().setLocation(newPosition);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		attribute.getGraphicInfo().setLocation(oldPosition);
	}

}
