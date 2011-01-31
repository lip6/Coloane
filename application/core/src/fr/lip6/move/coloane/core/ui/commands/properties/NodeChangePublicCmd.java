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
package fr.lip6.move.coloane.core.ui.commands.properties;

import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.gef.commands.Command;

/**
 * Command to change public state of a node.
 *
 * @author Clément Démoulins
 */
public class NodeChangePublicCmd extends Command {

	private INode node;
	private boolean newState;
	private boolean oldState;

	/**
	 * @param node node
	 * @param state new state
	 */
	public NodeChangePublicCmd(INode node, boolean state) {
		this.node = node;
		this.newState = state;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		this.oldState = node.isInterface();
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		node.setInterface(newState);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		node.setInterface(oldState);
	}

}
