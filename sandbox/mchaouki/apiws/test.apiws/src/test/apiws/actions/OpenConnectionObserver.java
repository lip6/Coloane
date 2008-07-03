package test.apiws.actions;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerOpenConnection;
import fr.lip6.move.coloane.apiws.interfaces.observers.IOpenConnectionObserver;

public class OpenConnectionObserver implements IOpenConnectionObserver {

	private Shell shell;
	
	public OpenConnectionObserver(Shell s){
		this.shell = s;
	}
	public void update(IAnswerOpenConnection s) {
		System.out.println("OPEN CONNECTION Version -> "+s.getFkVersion()+" Major -> "+s.getFkMajor()+"Minor -> "+s.getFkMinor());
		MessageDialog.openInformation(shell, "Apiws", "Connexion ouvert");
		
	}

}
