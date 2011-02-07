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
package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.core.ui.commands.StickyNoteEditCmd;
import fr.lip6.move.coloane.core.ui.figures.sticky.StickyNoteFigure;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;

/**
 * Sticky Notes editing policies
 *
 * @author Clément Démoulins
 */
public class LabelDirectEditPolicy extends DirectEditPolicy {

	/** {@inheritDoc} */
	@Override
	protected final Command getDirectEditCommand(DirectEditRequest edit) {
		String labelText = (String) edit.getCellEditor().getValue();
		StickyEditPart label = (StickyEditPart) getHost();
		StickyNoteEditCmd command = new StickyNoteEditCmd((IStickyNote) label.getModel(), labelText);
		return command;
	}

	/** {@inheritDoc} */
	@Override
	protected final void showCurrentEditValue(DirectEditRequest request) {
		String value = (String) request.getCellEditor().getValue();
		((StickyNoteFigure) getHostFigure()).setText(value);
		//hack to prevent async layout from placing the cell editor twice.
		getHostFigure().getUpdateManager().performUpdate();
	}
}
