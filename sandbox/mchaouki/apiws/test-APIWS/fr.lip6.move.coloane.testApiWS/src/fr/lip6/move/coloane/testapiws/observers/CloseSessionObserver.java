package fr.lip6.move.coloane.testapiws.observers;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerCloseSession;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IRootMenu;
import fr.lip6.move.coloane.apiws.interfaces.observers.ICloseSessionObserver;

public class CloseSessionObserver implements ICloseSessionObserver {

	private Shell shell;
	private Menu menu;
	
	public CloseSessionObserver(Shell s){
		this.shell = s;
		this.menu = shell.getMenuBar();
	}
	
	public void update(IAnswerCloseSession s) {
		System.out.println("CLOSE SESSION : idSession -> "+s.getIdSession()+" formalism -> "+s.getFormalism());
		for (IRootMenu m : s.getMenus().getRootsMenus()){
			for (int i=0;i<menu.getItems().length;i++){
				if (menu.getItems()[i].getText().equals(m.getName())){
					menu.getItems()[i].dispose();
				}
			}
		}
		MessageDialog.openInformation(shell, "Apiws", "Session fermer");
	}

}
