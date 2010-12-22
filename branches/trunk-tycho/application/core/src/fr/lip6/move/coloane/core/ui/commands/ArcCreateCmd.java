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

import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.gef.commands.Command;

/**
 * First step of the creation of an arc between two nodes.<br>
 * This command is created when the user click on the source node.<br>
 * 
 * @see ArcCompleteCmd
 * @author Jean-Baptiste Voron
 */
public class ArcCreateCmd extends Command {
	/** source node */
	private final INode source;

	/** arc formalism */
	private final IArcFormalism arcFormalism;

	/**
	 * Begin the creation of an arc
	 * @param source The arc source
	 * @param formalism The formalism that describes the arc
	 */
	public ArcCreateCmd(INode source, IArcFormalism formalism) {
		super(Messages.ArcCreateCmd_0);
		this.source = source;
		this.arcFormalism = formalism;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean canExecute() {
		return true; // always possible
	}

	/**
	 * @return The arc source
	 */
	public final INode getSource() {
		return source;
	}

	/**
	 * @return The formalism that describes the arc
	 */
	public final IArcFormalism getArcFormalism() {
		return arcFormalism;
	}
}
