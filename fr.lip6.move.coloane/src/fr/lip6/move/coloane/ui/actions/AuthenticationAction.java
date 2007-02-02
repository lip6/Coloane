package fr.lip6.move.coloane.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.ui.MainPerspectiveFactory;
import fr.lip6.move.coloane.ui.panels.HistoryView;

public class AuthenticationAction implements IWorkbenchWindowActionDelegate {
	
	private IWorkbenchWindow window;

	public void dispose() {}

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	public void run(IAction action) {
		/*
		 * If we don't call this method here, the view is not
		 * initialized and HistoryView.instance
		 * is null (and it is bad).
		 */
		try {
			window.getActivePage().
			showView(MainPerspectiveFactory.HISTORY_VIEW);
		} catch (PartInitException e) {
			MessageDialog.openError(window.getShell(),
					"Error during view initialization",
					"The Historic view cannot be opened");
		}
		
		System.out.println("Demande d'authentification");
		
		HistoryView.instance.addText("[?] Authentification -> ");

		// Affichage de la boite de dialogue d'authentification
		//AuthenticationDialog authDialog = new AuthenticationDialog(window.getShell());
		
		try {
			if (Coloane.getDefault().getCom().authentication("aortiz", "123456",
					"127.0.0.1", 7001))
				HistoryView.instance.addLine("OK");
			//PlatformUI.getWorkbench().getActiveWorkbenchWindow().
			else
				HistoryView.instance.addLine("KO");
		} catch (Exception e) {}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
