package fr.lip6.move.coloane.test.observers;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptDialogObserver;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;
import fr.lip6.move.coloane.test.apiws.Activator;

public class ReceptDialogObserver implements IReceptDialogObserver {

	private Shell shell;
	
	public ReceptDialogObserver(Shell shell){
		this.shell = shell;
	}
	public void update(IDialog dialog) {
		System.out.println("update dialog debut");
		// TODO Auto-generated method stub
		//MessageDialog.openInformation(shell, "RECEPT DIALOG", "idDialog = "+dialog.getId()+"\nmessage = "+dialog.getMessage());
		try {
			Activator.getSessionController().getActiveSession().sendDialogAnswer(dialog.getId(), IDialog.DLG_NO_BUTTON, false, "", null, null);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//MessageDialog.openError(shell, "ERROR DIALOG", "Erreur lors de l'envoie");
		}
		//System.out.println("update dialog fin");
	}

}