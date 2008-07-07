package fr.lip6.move.coloane.testapiws.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import fr.lip6.move.coloane.apiws.api.Api;
import fr.lip6.move.coloane.apiws.exceptions.ApiConnectionException;
import fr.lip6.move.coloane.apiws.exceptions.WrapperException;
import fr.lip6.move.coloane.apiws.interfaces.api.IApiConnection;
import fr.lip6.move.coloane.testapiws.Activator;
import fr.lip6.move.coloane.testapiws.observers.CloseConnectionObserver;
import fr.lip6.move.coloane.testapiws.observers.CloseSessionObserver;
import fr.lip6.move.coloane.testapiws.observers.OpenConnectionObserver;
import fr.lip6.move.coloane.testapiws.observers.OpenSessionObserver;

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
		
		connection.setOpenConnectionObserver(new OpenConnectionObserver(window.getShell()), false);
		connection.setCloseConnectionObserver(new CloseConnectionObserver(window.getShell()), false);
		connection.setOpenSessionObsever(new OpenSessionObserver(window.getShell()), false);
		connection.setCloseSessionObserver(new CloseSessionObserver(window.getShell()), false);
		Activator.getDefault().setConnection(connection);
		
		try {
			
			System.out.println("1) Ouveture connexion");
			connection.openConnection();
			
		} catch (WrapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApiConnectionException e) {
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