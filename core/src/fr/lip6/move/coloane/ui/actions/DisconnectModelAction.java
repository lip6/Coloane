package fr.lip6.move.coloane.ui.actions;

import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.ui.Editor;
import fr.lip6.move.coloane.ui.MainPerspectiveFactory;
import fr.lip6.move.coloane.ui.menus.UpdatePlatformMenu;
import fr.lip6.move.coloane.ui.panels.HistoryView;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;

public class DisconnectModelAction implements IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow window;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
	 */
	public final void init(IWorkbenchWindow w) {
		this.window = w;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public final void run(IAction action) {
		try {
			// If we don't call this method here, the view is not initialized and HistoryView.instance is null (and it is bad).
			window.getActivePage().showView(MainPerspectiveFactory.HISTORY_VIEW);
		} catch (PartInitException e) {
			MessageDialog.openError(window.getShell(), Messages.DisconnectModelAction_0, Messages.DisconnectModelAction_1);
		}

		HistoryView.getInstance().addLine(Messages.DisconnectModelAction_2);

		if (window.getActivePage().getActiveEditor() == null) {
			HistoryView.getInstance().addLine(Messages.DisconnectModelAction_3);
		} else {
			Editor editor = (Editor) window.getActivePage().getActiveEditor();
			try {
				if (editor.getModel() != null) {
					// Le modele existe... On peut essayer de le connecter
					HistoryView.getInstance().addText(Messages.DisconnectModelAction_4);

					if (Coloane.getDefault().getMotor().closeSession()) {
						HistoryView.getInstance().addLine("SUCCESS"); //$NON-NLS-1$
						Coloane.getParent().getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("CONNECT_ITEM"), true)); //$NON-NLS-1$
						Coloane.getParent().getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("DISCONNECT_ITEM"), false)); //$NON-NLS-1$
					} else {
						HistoryView.getInstance().addLine("FAILURE"); //$NON-NLS-1$
					}

				} else {
					HistoryView.getInstance().addText(Messages.DisconnectModelAction_7);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

	public final void dispose() {
		return;
	}
}
