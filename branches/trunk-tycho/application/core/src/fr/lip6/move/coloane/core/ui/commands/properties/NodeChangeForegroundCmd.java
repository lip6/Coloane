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
import org.eclipse.swt.graphics.Color;

/**
 * Commande pour modifier la couleur d'avant plan d'un noeud.
 */
public class NodeChangeForegroundCmd extends Command {
	private Color oldColor;
	private Color newColor;
	private INode node;

	/**
	 * @param node Noeud à modifier
	 * @param color Nouvelle couleur
	 */
	public NodeChangeForegroundCmd(INode node, Color color) {
		this.node = node;
		this.newColor = color;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		oldColor = node.getGraphicInfo().getForeground();
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		node.getGraphicInfo().setForeground(newColor);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		node.getGraphicInfo().setForeground(oldColor);
	}

}
