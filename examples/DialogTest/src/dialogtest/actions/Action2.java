package dialogtest.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import fr.lip6.move.coloane.ui.dialogs.DialogFactory;
import fr.lip6.move.coloane.ui.dialogs.SimpleDialog;
import fr.lip6.move.coloane.ui.dialogs.IDialog;
import fr.lip6.move.coloane.ui.dialogs.TextArea;
import fr.lip6.move.coloane.ui.dialogs.UnknowDialogException;

public class Action2 implements IWorkbenchWindowActionDelegate {

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub

	}

	public void run(IAction action) {
		/*MessageDialog.openInformation(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				"DialogTest Plug-in",
				"Hello, Eclipse world");*/
		IDialog d;
		try {
			d = DialogFactory.create(1, SimpleDialog.DLG_WARNING,
					SimpleDialog.DLG_OK,
					"Dialogue avec un bouton",
					"Aide n°2",
					"Un dialogue avec un bouton",
					TextArea.INPUT_FORBIDDEN, TextArea.SINGLE_LINE, "");
		d.open();
		
		AffichageResultat.affiche(d.getDialogResult());
		
		} catch (UnknowDialogException e) {
			e.printStackTrace();
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
