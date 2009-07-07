package fr.lip6.move.coloane.core.copypast;

import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

/**
 * Action COPIER
 */
public class CopyAction extends SelectionAction {
	private ColoaneEditor editor;

	/**
	 * Constructeur
	 * @param part Le workbench actif (en fait l'éditeur)
	 */
	public CopyAction(IWorkbenchPart part) {
		super(part);
		if (part instanceof ColoaneEditor) {
			editor = (ColoaneEditor) part;
		}
		// force calculateEnabled() to be called in every context
		setLazyEnablementCalculation(true);
	}

	/**
	 * @param selectedObjects liste des objets à copier
	 * @return commande qui va effectuer la copie
	 */
	private Command createCopyCommand(List<Object> selectedObjects) {
		if (editor == null || selectedObjects == null || selectedObjects.isEmpty()) {
			return null;
		}

		CopyCommand cmd = new CopyCommand(editor);
		Iterator<Object> it = selectedObjects.iterator();
		while (it.hasNext()) {
			Object object = it.next();
			if (object instanceof EditPart) {
				Object model = ((EditPart) object).getModel();
				if (model instanceof INode) {
					INode node = (INode) model;
					cmd.addNode(node);
				} else if (model instanceof IArc) {
					IArc arc = (IArc) model;
					cmd.addArc(arc);
				}
			}
		}
		return cmd;
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
