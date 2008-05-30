package fr.lip6.move.coloane.core.copypast;

import fr.lip6.move.coloane.core.ui.model.ArcImplAdapter;
import fr.lip6.move.coloane.core.ui.model.IArcImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;
import fr.lip6.move.coloane.core.ui.model.NodeImplAdapter;

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

public class CopyAction extends SelectionAction {

	public CopyAction(IWorkbenchPart part) {
		super(part);
	}

	private Command createCopyCommand(List<Object> selectedObjects) {
		if (selectedObjects == null || selectedObjects.isEmpty()) {
			return null;
		}

		CopyCommand cmd = new CopyCommand();
		Iterator<Object> it = selectedObjects.iterator();
		while (it.hasNext()) {
			Object model = ((EditPart) it.next()).getModel();
			if (model instanceof NodeImplAdapter) {
				INodeImpl node = (INodeImpl) model;
				cmd.addNode(node);
			} else if (model instanceof ArcImplAdapter) {
				IArcImpl arc = (IArcImpl) model;
				cmd.addArc(arc);
			}
		}
		return cmd;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected final boolean calculateEnabled() {
		Command cmd = createCopyCommand(getSelectedObjects());
		if (cmd == null) {
			return false;
		}
		return cmd.canExecute();
	}

	@Override
	protected final void init() {
		super.init();
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setText("Copy");
		setId(ActionFactory.COPY.getId());
		setHoverImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
		setEnabled(false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final void run() {
		Command cmd = createCopyCommand(getSelectedObjects());
		if (cmd != null && cmd.canExecute()) {
			cmd.execute();
		}
	}

}
