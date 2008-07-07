package fr.lip6.move.coloane.testapiws.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import fr.lip6.move.coloane.apiws.exceptions.ApiConnectionException;
import fr.lip6.move.coloane.apiws.exceptions.ApiSessionException;
import fr.lip6.move.coloane.apiws.exceptions.WrapperException;
import fr.lip6.move.coloane.apiws.interfaces.api.IApiConnection;
import fr.lip6.move.coloane.apiws.interfaces.session.IApiSession;
import fr.lip6.move.coloane.testapiws.Activator;

public class OpenSessionAction implements IWorkbenchWindowActionDelegate {

	/**
	 * The constructor.
	 */
	public OpenSessionAction() {
	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		
		
		try {
			IApiConnection connection = Activator.getDefault().getConnection();
			IApiSession session = connection.getApiSession();
			session.openSession("27-06-2008", "AMI-Net", "maseesion1");
			Activator.getDefault().setSession(session);
		} catch (ApiConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApiSessionException e) {
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
		
	}

}
