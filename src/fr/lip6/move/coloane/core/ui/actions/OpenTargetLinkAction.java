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
/**
 *
 */
package fr.lip6.move.coloane.core.ui.actions;

import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.core.ui.files.NodeLinksHandler.NodeLink;
import fr.lip6.move.coloane.core.ui.views.Tree;
import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;

/**
 * Open the targeted model and select the node.
 *
 * @author Clément Démoulins
 */
public class OpenTargetLinkAction extends Action {

	private IWorkbenchPage page;
	private ISelectionProvider provider;
	private NodeLink data;

	/**
	 * @param page The page to use as context to open the editor.
	 * @param provider The selection provider
	 */
	public OpenTargetLinkAction(IWorkbenchPage page, ISelectionProvider provider) {
		setText(Messages.OpenTargetLinkAction_0);
		this.page = page;
		this.provider = provider;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isEnabled() {
		ISelection selection = provider.getSelection();
		if (!selection.isEmpty() && selection instanceof IStructuredSelection) {
			IStructuredSelection sSelection = (IStructuredSelection) selection;
			if (sSelection.size() == 1 && sSelection.getFirstElement() instanceof Tree) {
				Tree node = (Tree) sSelection.getFirstElement();
				if (node.getElement() instanceof NodeLink) {
					data = (NodeLink) node.getElement();
					return true;
				}
			}
		}
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final void run() {
		if (isEnabled()) {
			try {
				IResource res = ResourcesPlugin.getWorkspace().getRoot().findMember(data.getPath());
				if (res == null || !(res instanceof IFile)) {
					return;
				}
				IEditorPart editor = IDE.openEditor(page, (IFile) res);

				if (editor instanceof ColoaneEditor) {
					ColoaneEditor coloaneEditor	= (ColoaneEditor) editor;

					INode node = coloaneEditor.getGraph().getNode(data.getTargetId());

					// Interface doesn't exist
					if (node == null || !node.isInterface()) {
						return;
					}

					GraphicalViewer viewer = (GraphicalViewer) editor.getAdapter(GraphicalViewer.class);
					viewer.deselectAll();
					viewer.appendSelection((EditPart) viewer.getEditPartRegistry().get(node));
				}
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
	}

}
