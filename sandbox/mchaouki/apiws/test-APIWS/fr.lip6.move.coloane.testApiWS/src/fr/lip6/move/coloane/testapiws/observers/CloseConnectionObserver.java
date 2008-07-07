package fr.lip6.move.coloane.testapiws.observers;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerCloseConnection;
import fr.lip6.move.coloane.apiws.interfaces.observers.ICloseConnectionObserver;

public class CloseConnectionObserver implements ICloseConnectionObserver {

	private Shell shell;
	
	public CloseConnectionObserver(Shell s){
		this.shell = s;
	}
	public void update(IAnswerCloseConnection s) {
		System.out.println("CLOSE CONNECTION id[0] -> "+s.getId()[0]+" id[1] -> "+s.getId()[1]);
		MessageDialog.openInformation(shell, "Apiws", "Connexion fermer");
	}

}
