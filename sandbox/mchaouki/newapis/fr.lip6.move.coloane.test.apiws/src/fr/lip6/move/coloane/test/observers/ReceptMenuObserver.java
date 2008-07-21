package fr.lip6.move.coloane.test.observers;

import org.eclipse.jface.dialogs.MessageDialog;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMenu;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;
import fr.lip6.move.coloane.test.apiws.Activator;

public class ReceptMenuObserver implements IReceptMenuObserver {


	public void update(IReceptMenu e) {
		// TODO Auto-generated method stub

		Activator.getDefault().getWorkbench().getDisplay().syncExec(
				new Runnable() {
					public void run(){
						MessageDialog.openInformation(
								Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(),
								"RECEPT MENU", 
						"Menu recu");
					}
				});
	}
}
