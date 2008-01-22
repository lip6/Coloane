package fr.lip6.move.coloane.core.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import fr.lip6.move.coloane.core.ui.dialogs.ExportToDilaog;

public class ExportTo implements IWorkbenchWindowActionDelegate {
	/** Fenetre de travail */
	private IWorkbenchWindow window;
	
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		this.window = window;
	}

	public void run(IAction action) {
		// TODO Auto-generated method stub
		ExportToDilaog exportDialog = new ExportToDilaog(window.getShell());
		exportDialog.open();

	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
