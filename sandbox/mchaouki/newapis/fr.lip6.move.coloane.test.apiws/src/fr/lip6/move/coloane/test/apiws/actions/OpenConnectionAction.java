package fr.lip6.move.coloane.test.apiws.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import fr.lip6.move.coloane.apiws.connection.Api;
import fr.lip6.move.coloane.interfaces.api.connection.IApiConnection;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.coloane.test.apiws.Activator;
import fr.lip6.move.coloane.test.observers.ReceptDialogObserver;
import fr.lip6.move.coloane.test.observers.ReceptMenuObserver;

/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class OpenConnectionAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;

	/**
	 * The constructor.
	 */
	public OpenConnectionAction() {

	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		IApiConnection connection = Api.getApiConnection();

		connection.setLogin("LOGIN");
		connection.setPassword("MDP");

		connection.setReceptMenuObserver(new ReceptMenuObserver(), true);
		connection.setReceptDialogObserver(new ReceptDialogObserver(), true);
		
		Activator.setConnection(connection);
		Activator.getSessionController().setConnectionOpened(true);

		try {

			System.out.println("Ouveture connexion");
			IConnectionInfo connectionInfo = connection.openConnection();
			MessageDialog.openInformation(window.getShell(), "OPEN CONNECTION", connectionInfo.getFkName());

		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Selection in the workbench has been changed. We 
	 * can change the state of the 'real' action here
	 * if we want, but this can only happen after 
	 * the delegate has been created.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system
	 * resources we previously allocated.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to
	 * be able to provide parent shell for the message dialog.
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}
