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

import fr.lip6.move.coloane.interfaces.model.IArc;

import org.eclipse.gef.commands.Command;

/**
 * Command that allow to curve a straight arc and to straight a curved arc.
 *
 * @author Jean-Baptiste Voron
 */
public class ArcChangeCurveCmd extends Command {

	/** Arc on which the action is done */
	private final IArc arc;

	/**
	 * Constructor
	 * @param arc Arc on which the action is done
	 */
	public ArcChangeCurveCmd(IArc arc) {
		super(Messages.ArcChangeCurve_0);
		this.arc = (IArc) arc;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		this.redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		arc.getGraphicInfo().setCurve(!arc.getGraphicInfo().getCurve());
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		redo();
	}
}
