/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.lip6.move.coloane.core.ui.properties.editor;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.texteditor.IUpdate;

/**
 * Class for creating actions.
 */
class TextViewerAction extends Action implements IUpdate {

	private int fOperationCode = -1;
	private ITextOperationTarget fOperationTarget;

	/**
	 * Creates a new action.
	 *
	 * @param viewer the viewer
	 * @param operationCode the opcode
	 */
	public TextViewerAction(ITextViewer viewer, int operationCode) {
		fOperationCode = operationCode;
		fOperationTarget = viewer.getTextOperationTarget();
		update();
	}

	/**
	 * Updates the enabled state of the action.
	 * Fires a property change if the enabled state changes.
	 *
	 * @see Action#firePropertyChange(String, Object, Object)
	 */
	public void update() {
		boolean wasEnabled = isEnabled();
		boolean isEnabled = (fOperationTarget != null && fOperationTarget.canDoOperation(fOperationCode));
		setEnabled(isEnabled);

		if (wasEnabled != isEnabled) {
			//all this stuff is necessary because instantiating boolean is not allowed....
			Boolean wasE;
			if (wasEnabled) {
				wasE = Boolean.TRUE;
			} else {
				wasE = Boolean.FALSE;
			}
			Boolean isE;
			if (isEnabled) {
				isE = Boolean.TRUE;
			} else {
				isE = Boolean.FALSE;
			}
			firePropertyChange(ENABLED, wasE, isE);
		}
	}

	/**
	 * @see Action#run()
	 */
	@Override
	public void run() {
		if (fOperationCode != -1 && fOperationTarget != null) {
			fOperationTarget.doOperation(fOperationCode);
		}
	}
}
