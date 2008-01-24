package fr.lip6.move.coloane.core.ui.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.ide.IDE;

import fr.lip6.move.coloane.core.extensions.ImportToExtension;
import fr.lip6.move.coloane.core.interfaces.IImportFrom;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.XmlEditor;
import fr.lip6.move.coloane.core.ui.dialogs.ImportFromDialog;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

public class ImportFrom implements IWorkbenchWindowActionDelegate {
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
	 * Actions à effectuer lors d'un demande d'importations.
	 */
	public void run(IAction action) {
		// TODO Auto-generated method stub
		final Shell shell = window.getShell();
		
		// Création et ouverture de la boite de dialogue 'Import To...'
		final ImportFromDialog importDialog = new ImportFromDialog(shell);
		int choix = importDialog.open();
		
		if (choix == Dialog.OK){
			if (importDialog.getFilePath() == null){
				return;
			}
			
			final String filePath = importDialog.getFilePath();
			final String fileName = importDialog.getFileName();
			
			// Création et ouverture de la boite de dialogue permettant d'enregister le model dans un projet
			SaveAsDialog sd = new SaveAsDialog(shell) {
				protected void configureShell(Shell shell) {
					super.configureShell(shell);
					shell.setText(Messages.ImportExportCAMI_2);
				}
			};
			
			// Donne un nom par default au fichier importer
			sd.setOriginalName(fileName);

			
			if (sd.open() == SaveAsDialog.OK) {
				IPath path = sd.getResult();

				final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
				try {
					new ProgressMonitorDialog(shell).run(false, false, new WorkspaceModifyOperation() { // run this operation
						public void execute(final IProgressMonitor monitor) {
							try {

								// Creation de l'instance qui permet d'importer le format selectionner
								IImportFrom importateur = (IImportFrom) ImportToExtension.createConvertInstance(importDialog.getFormat());

								// Importe le modele, via l'instance precedement creee
								IModelImpl model = importateur.importTo(filePath);
								
								
								// traduction du modele au format xml
								String xmlString = XmlEditor.translateToXML(model.getGenericModel());
								InputStream inputS = new ByteArrayInputStream(xmlString.getBytes());

								try {
									// Si le fichier existe alors on l'ecrase sinon on en cree un nouveau
									if (file.exists()) {
										Coloane.showErrorMsg(Messages.ImportExportCAMI_3);
										return;
									} else {
										file.create(inputS, true, monitor);
									}
								} catch (CoreException ce) {
									ce.printStackTrace();
								}

								// Open editor
								IDE.openEditor(window.getActivePage(), file, true);
							} catch (Exception e) {
								Coloane.showErrorMsg(e.getMessage());
								e.printStackTrace();
							}
						}
					}
					);
				} catch (Exception e) {
					Coloane.showErrorMsg(e.getMessage());
					e.printStackTrace();
				}
			}
		}

	}


	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
