package fr.lip6.move.coloane.core.ui.actions;

import fr.lip6.move.coloane.core.motor.Motor;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * Demande de deconnexion de la session courante<br>
 * Cette action est provoquee par un clic de l'utilisateur sur :
 * <ul>
 * 	<li>Le bouton dans la barre d'outils</li>
 * 	<li>L'item dans le menu de la plateforme</li>
 * </ul>
 */
public class DisconnectModelAction implements IWorkbenchWindowActionDelegate {
	/** {@inheritDoc} */
	public final void init(IWorkbenchWindow w) { }

	/** {@inheritDoc} */
	public final void run(IAction action) {
		Motor.getInstance().closeSession();
	}

	/** {@inheritDoc} */
	public void selectionChanged(IAction action, ISelection selection) { }

	/** {@inheritDoc} */
	public final void dispose() { return; }
}
