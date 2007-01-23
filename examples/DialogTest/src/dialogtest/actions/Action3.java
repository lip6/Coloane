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
			IDialog d = DialogFactory.create(1, SimpleDialog.DLG_WARNING,
					SimpleDialog.DLG_OK_CANCEL, "Mono-ligne avec deux boutons",
					"Veuillez entrer une fucking ligne", "Entrez une ligne",
					TextArea.INPUT_AUTHORIZED, TextArea.SINGLE_LINE, "Défaut");
			d.open();
			
			AffichageResultat.affiche(d.getDialogResult());
			
		} catch (UnknowDialogException e) {}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
	}

}
