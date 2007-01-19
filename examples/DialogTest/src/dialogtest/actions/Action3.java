package dialogtest.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import fr.lip6.move.coloane.ui.dialogs.CAMISimpleDialog;
import fr.lip6.move.coloane.ui.dialogs.CAMIDialogFactory;
import fr.lip6.move.coloane.ui.dialogs.ICAMIDialog;
import fr.lip6.move.coloane.ui.dialogs.TextArea;
import fr.lip6.move.coloane.ui.dialogs.UnknowDialogException;

public class Action3 implements IWorkbenchWindowActionDelegate {
	IWorkbenchWindow window;

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	public void run(IAction action) {
		try {
			ICAMIDialog d = CAMIDialogFactory.create(1, CAMISimpleDialog.DLG_WARNING,
					CAMISimpleDialog.DLG_OK_CANCEL, "Un titre", "RTFM", "Here",
					TextArea.INPUT_AUTHORIZED, TextArea.SINGLE_LINE, "");
			d.open();
			System.err.println(d.getDialogResult().getText());
		} catch (UnknowDialogException e) {}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
	}

}
