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
 * Action COUPER
 */
public class CutAction extends SelectionAction {
	private ColoaneEditor editor;

	/**
	 * Constructeur
	 * @param part Le workbench actif (en fait l'éditeur)
	 */
	public CutAction(IWorkbenchPart part) {
		super(part);
		if (part instanceof ColoaneEditor) {
			editor = (ColoaneEditor) part;
		}
		// force calculateEnabled() to be called in every context
		setLazyEnablementCalculation(true);
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	protected final boolean calculateEnabled() {
		Command cmd = createCutCommand(getSelectedObjects());
		if (cmd == null) {
			return false;
		}
		return cmd.canExecute();
	}

	/**
	 * @param selectedObjects liste d'objet à couper
	 * @return commande pour couper
	 */
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
	@Override
	protected final void init() {
		super.init();
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setText(Messages.CutAction_1);
		setId(ActionFactory.CUT.getId());
		setHoverImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT_DISABLED));
		setEnabled(false);
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public final void run() {
		execute(createCutCommand(getSelectedObjects()));
	}
}
