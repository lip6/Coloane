package fr.lip6.move.coloane.test.observers;

import org.eclipse.jface.dialogs.MessageDialog;

import fr.lip6.move.coloane.test.apiws.Activator;

public class ShowMyDialog extends Thread {
	
	public void run(){
		Activator.getDefault().getWorkbench().getDisplay().syncExec(
        new Runnable() {
          public void run(){
              MessageDialog.openInformation(Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(),
            		  "RECEPT DIALOG",
            		  "Dialogue recu");
        	  }
        });
	}
	
	
}
