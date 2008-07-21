package fr.lip6.move.coloane.core.ui.actions;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.ColoanePerspectiveFactory;
import fr.lip6.move.coloane.core.ui.dialogs.AuthenticationDialog;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;

/**
 * Demande d'authentification aupres de la plateforme<br>
 * Cette action est provoquee par un clic de l'utilisateur sur :
 * <ul>
 * 	<li>Le bouton dans la barre d'outils</li>
 * 	<li>L'item dans le menu de la plateforme</li>
 * </ul>
 */
public class AuthenticationAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;

	/** {@inheritDoc} */
	public final void init(IWorkbenchWindow w) {
		this.window = w;
	}

	/** {@inheritDoc} */
	public final void run(IAction action) {

		try {
			// If we don't call this method here, the view is not initialized and HistoryView.instance is null (and it is bad).
			window.getActivePage().showView(ColoanePerspectiveFactory.HISTORY_VIEW);
		} catch (PartInitException e) {
			MessageDialog.openError(window.getShell(), Messages.AuthenticationAction_0, Messages.AuthenticationAction_1);
		}

		// Affichage de la boite de dialogue d'authentification
		AuthenticationDialog authDialog = new AuthenticationDialog(window.getShell());
		if (authDialog.open() == Dialog.OK) {
			Coloane.getDefault().getMotor().authentication(authDialog.getResults());
		}
	}

	/** {@inheritDoc} */
	public void selectionChanged(IAction action, ISelection selection) { }

	/** {@inheritDoc} */
	public final void dispose() { return; }
}
