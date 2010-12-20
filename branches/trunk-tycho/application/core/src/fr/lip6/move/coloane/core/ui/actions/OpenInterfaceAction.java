package fr.lip6.move.coloane.core.ui.actions;

import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.core.ui.files.InterfacesHandler.NodeInterface;
import fr.lip6.move.coloane.core.ui.views.Tree;
import fr.lip6.move.coloane.interfaces.model.INode;

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
 * Open the model and select the public node selected.
 *
 * @author Clément Démoulins
 */
public class OpenInterfaceAction extends Action {

	private IWorkbenchPage page;
	private ISelectionProvider provider;
	private NodeInterface data;

	/**
	 * @param page The page to use as context to open the editor.
	 * @param provider The selection provider
	 */
	public OpenInterfaceAction(IWorkbenchPage page, ISelectionProvider provider) {
		setText(Messages.OpenInterfaceAction_0);
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
				if (node.getElement() instanceof NodeInterface) {
					data = (NodeInterface) node.getElement();
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
				IEditorPart editor = IDE.openEditor(page, data.getFile());

				if (editor instanceof ColoaneEditor) {
					ColoaneEditor coloaneEditor	= (ColoaneEditor) editor;
					INode node = coloaneEditor.getGraph().getNode(data.getId());

					// Interface doesn't exist
					if (node == null) {
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
