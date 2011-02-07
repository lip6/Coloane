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
 * Command to change nodeLink of a node.
 *
 * @author Clément Démoulins
 */
public class NodeLinkCmd extends Command {

	private INode node;
	private String newLink;
	private String oldLink;

	/**
	 * Change the link of a node
	 * @param node node
	 * @param link new link
	 */
	public NodeLinkCmd(INode node, String link) {
		this.node = node;
		this.newLink = link;
	}
	
	/** {@inheritDoc} */
	@Override
	public final void execute() {
		this.oldLink = node.getNodeLink();
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		node.setNodeLink(newLink);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		node.setNodeLink(oldLink);
	}

}
