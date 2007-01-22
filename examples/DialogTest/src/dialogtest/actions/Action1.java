package dialogtest.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import fr.lip6.move.coloane.ui.dialogs.DialogFactory;
import fr.lip6.move.coloane.ui.dialogs.IDialog;
import fr.lip6.move.coloane.ui.dialogs.SimpleDialog;
import fr.lip6.move.coloane.ui.dialogs.TextArea;
import fr.lip6.move.coloane.ui.dialogs.UnknowDialogException;

/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class Action1 implements IWorkbenchWindowActionDelegate {
	/**
	 * The constructor.
	 */
	public Action1() {
	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		IDialog d;
		try {
			d = DialogFactory.create(1, SimpleDialog.DLG_WARNING,
					SimpleDialog.DLG_NO_BUTTON,
					"Dialogue sans bouton", "Aide n°1", 
					"Un dialogue sans bouton",
					TextArea.INPUT_FORBIDDEN, TextArea.SINGLE_LINE, "");
			
			AffichageResultat.affiche(d.getDialogResult());
		} catch (UnknowDialogException e) {
			e.printStackTrace();
		} catch (Exception e) {
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