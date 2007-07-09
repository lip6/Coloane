package fr.lip6.move.coloane.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.ui.Editor;

public class DumpAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void init(IWorkbenchWindow window) {
		this.window = window;
		
	}

	public void run(IAction action) {
		if (!(window.getActivePage().getActiveEditor() instanceof Editor)) {
			Coloane.showErrorMsg(Coloane.traduction.getString("ui.actions.DumpAction.0")); //$NON-NLS-1$
			return;
		}
		
		// Recupere le modele courant
		Editor editor = (Editor) window.getActivePage().getActiveEditor();
		
		// Demande le dump du modele
		editor.getModel().dumpModel();
		
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}

}
