package fr.lip6.move.coloane.core.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import fr.lip6.move.coloane.core.extensions.ExportToExtension;
import fr.lip6.move.coloane.core.interfaces.IExportTo;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.Editor;
import fr.lip6.move.coloane.core.ui.dialogs.ExportToDialog;

public class ExportTo implements IWorkbenchWindowActionDelegate {
	/** 
	 * Fenetre de travail
	 */
	private IWorkbenchWindow window;
	
	public void dispose() {
		
	}

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	/**
	 * Actions à effectuer lors d'un demande d'exportation.
	 */
	public void run(IAction action) {
		
		Shell shell = window.getShell();
		
		// Verification si on est dans un Editor
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
		
		// Création et ouverture de la boite de dialogue ExportTo
		ExportToDialog exportDialog = new ExportToDialog(shell,editor);
		int choix = exportDialog.open();
		String filePath = exportDialog.getFilePath();

		if (choix == Dialog.OK){
			//System.out.println("Creation d'un instance pour l'exportation au format:"+exportDialog.getFormat());
			
			try {
				// Creation d'une instance qui permet l'exportation du model courant
				IExportTo exportateur = ExportToExtension.createConvertInstance(exportDialog.getFormat());

				// Exporte le modele, via l'instance precedement creee
				exportateur.export(editor.getModel(), filePath);
				
			} catch (Exception e) {
				Coloane.showErrorMsg( e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {

	}

}
