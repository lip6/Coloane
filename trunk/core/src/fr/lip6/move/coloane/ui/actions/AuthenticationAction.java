package fr.lip6.move.coloane.ui.actions;

import fr.lip6.move.coloane.ui.MainPerspectiveFactory;
import fr.lip6.move.coloane.ui.dialogs.AuthenticationDialog;
import fr.lip6.move.coloane.ui.menus.MenuManipulation;
import fr.lip6.move.coloane.ui.panels.HistoryView;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;

public class AuthenticationAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;

	public final void init(IWorkbenchWindow w) {
		this.window = w;
	}

	public final void run(IAction action) {
		/*
		 * If we don't call this method here, the view is not
		 * initialized and HistoryView.instance
		 * is null (and it is bad).
		 */

		try {
			window.getActivePage().showView(MainPerspectiveFactory.HISTORY_VIEW);
		} catch (PartInitException e) {
			MessageDialog.openError(window.getShell(), "Error during view initialization", "The Historic view cannot be opened");
		}

		HistoryView.getInstance().addText("[?] Authentification -> ");

		// Affichage de la boite de dialogue d'authentification
		AuthenticationDialog authDialog = new AuthenticationDialog(window.getShell());

		if (authDialog.open() == Dialog.OK) {
			HistoryView.getInstance().addLine("OK");
			action.setEnabled(false);
			MenuManipulation.setEnabled("Platform", "Connect model", true);
			MenuManipulation.setEnabled("Platform", "Disconnect model", true);
		} else {
			HistoryView.getInstance().addLine("KO");
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {

	}

	public final void dispose() {
		// TODO Auto-generated method stub
		return;
	}
}
