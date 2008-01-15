package org.monir.test.convert;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class Convert implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		this.window = window;
	}

	public void run(IAction action) {
		// TODO Auto-generated method stub
		int choix;
		System.out.println("CONVERT");
		ConvertDialog convertDialog = new ConvertDialog(window.getShell());
		choix = convertDialog.open();
		System.out.println(choix);
		
		if ( choix == Dialog.OK ){
			Convertiseur convertiseur = ConvertExtension.createConvertInstance(convertDialog.getMonnaie());
			double res = convertiseur.convert(convertDialog.getPrice());
			MessageDialog.openInformation(window.getShell(),
					"Convertiseur",
					"Prix convertie: "+res);
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
