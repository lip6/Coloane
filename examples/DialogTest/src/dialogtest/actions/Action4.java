package dialogtest.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import fr.lip6.move.coloane.ui.dialogs.SimpleDialog;
import fr.lip6.move.coloane.ui.dialogs.DialogFactory;
import fr.lip6.move.coloane.ui.dialogs.IDialog;
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
		try {
			IDialog d = DialogFactory.create(1, SimpleDialog.DLG_WARNING,
					SimpleDialog.DLG_OK_CANCEL,
					"Multi-lignes avec deux boutons", "Veullez entrer un texte",
					"Put the text here",
					TextArea.INPUT_AUTHORIZED, TextArea.MULTI_LINE_WITH_SINGLE_SELECTION,
					"MDéfaut");
			d.open();
			
			AffichageResultat.affiche(d.getDialogResult());
			
		} catch (UnknowDialogException e) {}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
	}

}
