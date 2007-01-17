package dialogtest.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import fr.lip6.move.coloane.ui.dialogs.CAMIDialog;
import fr.lip6.move.coloane.ui.dialogs.CAMIDialogFactory;
import fr.lip6.move.coloane.ui.dialogs.DumbMessageDialog;
import fr.lip6.move.coloane.ui.dialogs.TextArea;
import fr.lip6.move.coloane.ui.dialogs.UnknowDialogException;

public class Action4 implements IWorkbenchWindowActionDelegate {
	IWorkbenchWindow window;

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	public void run(IAction action) {
		//new DumbMessageDialog(window.getShell()).open();
		
		try {
			CAMIDialog d = new CAMIDialogFactory().create(1, CAMIDialog.DLG_WARNING,
					CAMIDialog.DLG_OK_CANCEL, "Un titre", "RTFM", "Here",
					TextArea.INPUT_AUTHORIZED, TextArea.SINGLE_LINE, "");
			d.open();
		} catch (UnknowDialogException e) {}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
