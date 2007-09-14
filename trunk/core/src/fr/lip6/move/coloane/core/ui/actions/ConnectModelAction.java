package fr.lip6.move.coloane.core.ui.actions;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.Motor;
import fr.lip6.move.coloane.core.motor.session.Session;
import fr.lip6.move.coloane.core.ui.MainPerspectiveFactory;
import fr.lip6.move.coloane.core.ui.panels.HistoryView;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;

public class ConnectModelAction implements IWorkbenchWindowActionDelegate {
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

		// La vue HISTORY doit etre initialisee
		try {
			window.getActivePage().showView(MainPerspectiveFactory.HISTORY_VIEW);
		} catch (PartInitException e) {
			MessageDialog.openError(window.getShell(), Messages.ConnectModelAction_0, Messages.ConnectModelAction_1);
		}

		HistoryView.getInstance().addLine(Messages.ConnectModelAction_2);
		Session current = Motor.getInstance().getSessionManager().getCurrentSession();
		if (current != null) {
			// Le modele existe... On peut essayer de le connecter
			HistoryView.getInstance().addText(Messages.ConnectModelAction_4);
			Coloane.getLogger().fine("Session courante : " + current.getName()); //$NON-NLS-1$
			try {
				if (Motor.getInstance().openSession()) {
					HistoryView.getInstance().addLine(Messages.ConnectModelAction_6);
				} else {
					HistoryView.getInstance().addLine(Messages.ConnectModelAction_9);
				}
			} catch (ColoaneException e) {
				Coloane.getLogger().warning("Impossible de connecter le modele : " + e.getMessage()); //$NON-NLS-1$
				HistoryView.getInstance().addLine(Messages.ConnectModelAction_9);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) { }

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
	 */
	public final void dispose() { }
}
