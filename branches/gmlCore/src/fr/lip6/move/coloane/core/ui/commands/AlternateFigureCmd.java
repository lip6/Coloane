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

import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.gef.commands.Command;

/**
 * Command that switches a node figure between the standard one and one of the alternate one.
 *
 * @author Jean-Baptiste Voron
 */
public class AlternateFigureCmd extends Command {

	/** Node on which the action has to be performed */
	private final INode node;

	/** Index of the previous selected figure (to be able to perform an UNDO) */
	private int oldIndex;

	/**
	 * Constructor
	 * @param node Node on which the action has to be performed
	 */
	public AlternateFigureCmd(INode node) {
		super(Messages.AlternateFigureCmd_0);
		this.node = (INode) node;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		this.redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		oldIndex = node.getGraphicInfo().switchGraphicalDescription();
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		node.getGraphicInfo().switchGraphicalDescription(oldIndex);
	}
}
