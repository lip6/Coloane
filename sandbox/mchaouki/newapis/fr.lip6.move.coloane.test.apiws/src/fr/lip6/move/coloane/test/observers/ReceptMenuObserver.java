package fr.lip6.move.coloane.test.observers;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMenu;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;

public class ReceptMenuObserver implements IReceptMenuObserver {

	private Shell shell;

	public ReceptMenuObserver(Shell shell){
		this.shell = shell;
	}
	public void update(IReceptMenu e) {
		// TODO Auto-generated method stub
		MessageDialog.openInformation(shell, "RECEPT MENU", "Menu recu");
	}

}
