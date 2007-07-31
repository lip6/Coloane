package fr.lip6.move.coloane.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;

import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.ui.Editor;
import fr.lip6.move.coloane.ui.MainPerspectiveFactory;
import fr.lip6.move.coloane.ui.panels.HistoryView;

public class DisconnectModelAction implements IWorkbenchWindowActionDelegate {
	
	private IWorkbenchWindow window;

	public void dispose() {
		// TODO Auto-generated method stub

	}

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
		
		System.out.println("Deconnexion d'un modele");
		HistoryView.instance.addLine("[?] Deconnexion d'un modele");

		if(window.getActivePage().getActiveEditor() == null) {
			HistoryView.instance.addLine("[!] Echec: Aucun modele ouvert !");
		} else {
			Editor editor = (Editor) window.getActivePage().getActiveEditor();

			try {
				if (editor.getModel() != null) {
					// Le modele existe... On peut essayer de le connecter
					HistoryView.instance.addText("Deconnexion en cours... ");


					if (Coloane.getDefault().getMotor().closeSession()) {
						// TODO : Griser les menues adequats
						HistoryView.instance.addLine("SUCCESS");
						//MenuManipulation.setEnabled("Platform", "Connect model", false);
						//MenuManipulation.setEnabled("Platform", "Disconnect model", true);
					} else {
						// TODO : GRiser les menus adequats
						HistoryView.instance.addLine("FAILED");
					}

				} else {
					HistoryView.instance.addText("[!] Echec: Le modele n'est pas valide");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
