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
package fr.lip6.move.coloane.core.copypast;

import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

/**
 * Copy action
 *
 * @author Clément Démoulins
 */
public class CopyAction extends SelectionAction {
	private ColoaneEditor editor;

	/**
	 * Constructor
	 * @param workbench The active workbench (the current editor)
	 */
	public CopyAction(IWorkbenchPart workbench) {
		super(workbench);
		if (workbench instanceof ColoaneEditor) {
			editor = (ColoaneEditor) workbench;
		}
		// force calculateEnabled() to be called in every context
		setLazyEnablementCalculation(true);
	}
	
	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	protected final boolean calculateEnabled() {
		Command cmd = createCopyCommand(getSelectedObjects());
		if (cmd == null) {
			return false;
		}
		return cmd.canExecute();
	}

	/**
	 * Create a copy action that use the current selection
	 * @param selectedObjects List of selected objects
	 * @return A command that will do the copy
	 */
	private Command createCopyCommand(List<Object> selectedObjects) {
		// Some basic checks
		if (editor == null || selectedObjects == null || selectedObjects.isEmpty()) {
			return null;
		}

		// New copy command... to fill
		CopyCommand copyCommand = new CopyCommand(editor);
		
		// Browse all selected objects and update the copy command
		for (Object selectedObject : selectedObjects) {
			if (selectedObject instanceof EditPart) {
				Object model = ((EditPart) selectedObject).getModel();
				if (model instanceof INode) {
					INode node = (INode) model;
					copyCommand.addNode(node);
				} else if (model instanceof IArc) {
					IArc arc = (IArc) model;
					copyCommand.addArc(arc);
				}
			}
		}
		return copyCommand;
	}

	/** {@inheritDoc} */
	@Override
	protected final void init() {
		super.init();
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setText(Messages.CopyAction_1);
		setId(ActionFactory.COPY.getId());
		setHoverImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
		setEnabled(false);
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public final void run() {
		execute(createCopyCommand(getSelectedObjects()));
	}

}
