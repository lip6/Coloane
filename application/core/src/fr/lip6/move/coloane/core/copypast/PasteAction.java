package fr.lip6.move.coloane.core.copypast;


import fr.lip6.move.coloane.core.ui.ColoaneEditor;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

public class PasteAction extends SelectionAction {
	private ColoaneEditor editor;

	public PasteAction(IWorkbenchPart part) {
		super(part);
		if (part instanceof ColoaneEditor) {
			editor = (ColoaneEditor) part;
		}
		// force calculateEnabled() to be called in every context
		setLazyEnablementCalculation(true);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#init()
	 */
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
	 * @return commande pour coller
	 */
	private PasteCommand createPasteCommand() {
		if (editor == null) {
			return null;
		}
		return new PasteCommand(editor);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
	 */
	@Override
	protected final boolean calculateEnabled() {
		Command command = createPasteCommand();
        return command != null && command.canExecute();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public final void run() {
		PasteCommand command = createPasteCommand();
		execute(command);
		System.err.println(getSelection());
	}
}
