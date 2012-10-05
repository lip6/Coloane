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

import org.eclipse.gef.EditPartListener;

/**
 * Classes that implement this interface want to be able to change their aspect according to the selection state.<br>
 * In that case, they should provide a SelectionEditPArtListener.
 */
public interface ISelectionEditPartListener {

	/**
	 * Some constants
	 */
	int HIGHLIGHT = 10;
	int HIGHLIGHT_NONE = 11;

	/**
	 * @return The listener used to deal with the selection state
	 */
	EditPartListener getSelectionEditPartListener();
}
