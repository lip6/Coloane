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

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

/**
 * Paste action
 *
 * @author Clément Démoulins
 */
public class PasteAction extends SelectionAction {
	private ColoaneEditor editor;

	/**
	 * Constructor
	 * @param part The active workbench (the current editor)
	 */
	public PasteAction(IWorkbenchPart part) {
		super(part);
		if (part instanceof ColoaneEditor) {
			editor = (ColoaneEditor) part;
		}
		// force calculateEnabled() to be called in every context
		setLazyEnablementCalculation(true);
	}

	/** {@inheritDoc} */
	@Override
	protected final void init()	{
		super.init();
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setText(Messages.PasteAction_0);
		setId(ActionFactory.PASTE.getId());
		setHoverImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
		setEnabled(false);
	}

	/**
	 * Create a paste command
	 * @return A command to paste the contents of the clipboard
	 */
	private PasteCommand createPasteCommand() {
		if (editor == null) {
			return null;
		}
		return new PasteCommand(editor);
	}

	/** {@inheritDoc} */
	@Override
	protected final boolean calculateEnabled() {
		Command command = createPasteCommand();
        return command != null && command.canExecute();
	}

	/** {@inheritDoc} */
	@Override
	public final void run() {
		PasteCommand command = createPasteCommand();
		execute(command);
	}
}
