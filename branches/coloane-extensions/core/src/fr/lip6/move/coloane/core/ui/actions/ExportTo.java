package fr.lip6.move.coloane.core.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import fr.lip6.move.coloane.core.extensions.ExportToExtension;
import fr.lip6.move.coloane.core.interfaces.IExportTo;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.Editor;
import fr.lip6.move.coloane.core.ui.dialogs.ExportToDilaog;

public class ExportTo implements IWorkbenchWindowActionDelegate {
	/** 
	 * Fenetre de travail
	 */
	private IWorkbenchWindow window;
	
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		this.window = window;
	}

	/**
	 * Action a effectuer en demande un exportation du model courant
	 */
	public void run(IAction action) {
		// TODO Auto-generated method stub
		
		// Verification si on est dans un Editor ???
		if (!(window.getActivePage().getActiveEditor() instanceof Editor)) {
			Coloane.showErrorMsg(Messages.ImportExportCAMI_0);
			return;
		}

		// Recupere le modele courant
		Editor editor = (Editor) window.getActivePage().getActiveEditor();

		// Si le modele n'a pas ete sauvegarde... On ne peut pas exporter.
		// On demande la sauvegarde a l'utilisateur
		if (editor.isDirty()) {
			Coloane.showWarningMsg(Messages.ImportExportCAMI_1);
			return;
		}
		
		int choix;
		
		ExportToDilaog exportDialog = new ExportToDilaog(window.getShell());
		choix = exportDialog.open();
		
		if (choix == Dialog.OK){
			System.out.println("Creation d'un instance pour l'exportation au format:"+exportDialog.getFormat());
			IExportTo exportateur = ExportToExtension.createConvertInstance(exportDialog.getFormat());
			
			try {
				exportateur.export(editor.getModel(), exportDialog.getOutputFile());
				MessageDialog.openInformation(window.getShell(), "Export To...", " Votre fichier a bien etait exporter au format: "+exportDialog.getFormat());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				MessageDialog.openWarning(window.getShell(), "Export To...", e.getMessage());
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
