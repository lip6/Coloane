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
 * Commande pour modifier la taille d'un noeud
 */
public class NodeChangeSizeCmd extends Command {

	private INode node;
	private int newScale;
	private int oldScale;

	/**
	 * @param node Noeud à modifier
	 * @param scale Nouvelle taille exprimée en pourcentage de la taille d'origine
	 */
	public NodeChangeSizeCmd(INode node, int scale) {
		this.node = node;
		this.newScale = scale;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		oldScale = node.getGraphicInfo().getScale();
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		node.getGraphicInfo().setScale(newScale);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		node.getGraphicInfo().setScale(oldScale);
	}

}
