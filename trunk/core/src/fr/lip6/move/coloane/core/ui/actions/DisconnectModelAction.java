package fr.lip6.move.coloane.core.ui.actions;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.Motor;
import fr.lip6.move.coloane.core.motor.session.Session;
import fr.lip6.move.coloane.core.ui.panels.HistoryView;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class DisconnectModelAction implements IWorkbenchWindowActionDelegate {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
	 */
	public final void init(IWorkbenchWindow w) { }

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public final void run(IAction action) {
		HistoryView.getInstance().addLine(Messages.DisconnectModelAction_2);
		Session current = Motor.getInstance().getSessionManager().getCurrentSession();
		if (current != null) {
			// Le modele existe... On peut essayer de le connecter
			HistoryView.getInstance().addText(Messages.DisconnectModelAction_4);
			Coloane.getLogger().fine("Session courante : " + current.getName()); //$NON-NLS-1$
			if (Motor.getInstance().closeSession()) {
				HistoryView.getInstance().addLine(Messages.DisconnectModelAction_6);
			} else {
				HistoryView.getInstance().addLine(Messages.DisconnectModelAction_9);
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

	public final void dispose() {
		return;
	}
}
