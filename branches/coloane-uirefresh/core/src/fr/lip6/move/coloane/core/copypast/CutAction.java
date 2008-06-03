package fr.lip6.move.coloane.core.copypast;

import fr.lip6.move.coloane.core.ui.ColoaneEditor;
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

public class CutAction extends SelectionAction {
	private ColoaneEditor editor;

	public CutAction(IWorkbenchPart part) {
		super(part);
		if (part instanceof ColoaneEditor) {
			editor = (ColoaneEditor) part;
		}
		// force calculateEnabled() to be called in every context
		setLazyEnablementCalculation(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected final boolean calculateEnabled() {
		Command cmd = createCutCommand(getSelectedObjects());
		if (cmd == null) {
			return false;
		}
		return cmd.canExecute();
	}

	private Command createCutCommand(List<Object> selectedObjects) {
		if (editor == null || selectedObjects == null || selectedObjects.isEmpty()) {
			return null;
		}

		CutCommand cmd = new CutCommand(editor);
		Iterator<Object> it = selectedObjects.iterator();
		while (it.hasNext()) {
			Object object = it.next();
			if (object instanceof EditPart) {
				Object model = ((EditPart) object).getModel();
				if (model instanceof NodeImplAdapter) {
					INodeImpl node = (INodeImpl) model;
					cmd.addNode(node);
				} else if (model instanceof ArcImplAdapter) {
					IArcImpl arc = (IArcImpl) model;
					cmd.addArc(arc);
				}
			}
		}
		return cmd;
	}

	@Override
	protected final void init() {
		super.init();
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setText("Cut");
		setId(ActionFactory.CUT.getId());
		setHoverImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT_DISABLED));
		setEnabled(false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final void run() {
		execute(createCutCommand(getSelectedObjects()));
	}
}
