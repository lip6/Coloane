package fr.lip6.move.test.cami.convert;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import fr.lip6.move.test.cami.interfaceConvert.Conversion;

public class ExportTo implements IWorkbenchWindowActionDelegate {
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
		
		ExportToDialog exportDilog = new ExportToDialog(window.getShell());
		choix = exportDilog.open();
		
		if  (choix == Dialog.OK){
			System.out.println("1");
			Conversion convertiseur = ExportToExtension.createConvertInstance(exportDilog.getFormat());
			System.out.println("2");
			System.out.println(exportDilog.getInputFile());
			System.out.println(exportDilog.getOutputFile());
			convertiseur.convert(exportDilog.getInputFile(), exportDilog.getOutputFile());
			System.out.println("OK");
		}
		
		

	}

	
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
