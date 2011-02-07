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
package fr.lip6.move.coloane.core.ui.actions;

import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.ui.commands.LocatedElementSetConstraintCmd;
import fr.lip6.move.coloane.core.ui.editpart.NodeEditPart;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Action that moves an element (nodes, note, ...) from one pixel in a direction.<br>
 * The available directions are set by following constants.
 *
 * @author Clément Démoulins
 *
 * @see ILocatedElement
 */
public class NodeMoveAction extends SelectionAction {
	/** Move UP */
	public static final String UP = "move.up"; //$NON-NLS-1$
	/** Move DOWN */
	public static final String DOWN = "move.down"; //$NON-NLS-1$
	/** Move LEFT */
	public static final String LEFT = "move.left"; //$NON-NLS-1$
	/** Move RIGHT */
	public static final String RIGHT = "move.right"; //$NON-NLS-1$

	private int dx = 0;
	private int dy = 0;

	/**
	 * Constructor
	 * @param workbench The associated workbench
	 * @param action The direction of the move (see constants)
	 */
	public NodeMoveAction(IWorkbenchPart workbench, String action) {
		super(workbench);
		setId(action);
		if (UP.equals(action)) {
			dy = -1;
		} else if (DOWN.equals(action)) {
			dy = 1;
		} else if (LEFT.equals(action)) {
			dx = -1;
		} else if (RIGHT.equals(action)) {
			dx = 1;
		}
	}

	/** {@inheritDoc} */
	@Override
	protected final void init() {
		super.init();
		setEnabled(false);
	}

	/** {@inheritDoc} */
	@Override
	protected final boolean calculateEnabled() {
		for (Object obj : getSelectedObjects()) {
			if (obj instanceof NodeEditPart) {
				return true;
			}
		}
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final void run() {
		CompoundCommand cc = new CompoundCommand();
		for (Object obj : getSelectedObjects()) {
			if (obj instanceof EditPart) {
				ILocatedElement node = (ILocatedElement) ((EditPart) obj).getModel();
				Point location = node.getLocationInfo().getLocation();
				cc.add(new LocatedElementSetConstraintCmd((ILocatedElement) node, location.getCopy().getTranslated(this.dx, this.dy)));
			}
		}
		execute(cc);
	}
}
