package fr.lip6.move.coloane.ui.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.ui.Editor;
import fr.lip6.move.coloane.ui.XmlEditor;
import fr.lip6.move.coloane.ui.model.IModelImpl;
import fr.lip6.move.coloane.motor.formalism.FormalismManager;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.ide.IDE;

public class ImportExportCAMI implements IWorkbenchWindowActionDelegate {

	/** Fen�tre de travail */
	private IWorkbenchWindow window;

	/** ID  pour le cas d'un nouveau model */
	private static final String ACT_IMPORT = "cami_import"; //$NON-NLS-1$

	/** ID pour le cas d'ouverture d'un model */
	private static final String ACT_EXPORT = "cami_export"; //$NON-NLS-1$

	public void dispose() {
		// TODO Auto-generated method stub
	}

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	public void run(IAction action) {

		final Shell shell = window.getShell();

		// Pour l'export
		if (ACT_EXPORT.equalsIgnoreCase(action.getId())) {

			if (!(window.getActivePage().getActiveEditor() instanceof Editor)) {
				Coloane.showErrorMsg(Coloane.traduction.getString("ui.actions.ImportExportCAMI.2")); //$NON-NLS-1$
				return;
			}
			
			// Recupere le modele courant
			Editor editor = (Editor) window.getActivePage().getActiveEditor();

			// Si le modele n'a pas ete sauvegarde... On ne peut pas exporter.
			// On demande la sauvegarde a l'utilisateur
			if (editor.isDirty()) {
				Coloane.showWarningMsg(Coloane.traduction.getString("ui.actions.ImportExportCAMI.3")); //$NON-NLS-1$
				return;
			}
			
			IFile file = ((IFileEditorInput) editor.getEditorInput()).getFile();

			// Boite de sauvegarde
			FileDialog fd = new FileDialog(shell, SWT.SAVE);
			int fLength = file.getName().length();
			String ext = file.getFileExtension();
			fd.setFileName(file.getName().substring(0,fLength - ext.length() - 1));
			String filePath = fd.open();

			if (filePath != null) {
				doExport(editor.getModel(), filePath);
			}

		// Pour l'import
		} else if (ACT_IMPORT.equalsIgnoreCase(action.getId())) {

			FileDialog fd = new FileDialog(shell, SWT.OPEN | SWT.SINGLE);
			final String filePath = fd.open();
			final String fileName = fd.getFileName();

			if (filePath == null) {
				return;
			}
			
			SaveAsDialog sd = new SaveAsDialog(shell) {
				protected void configureShell(Shell shell) {
					super.configureShell(shell);
					shell.setText(Coloane.getParam("ACT_IMPORT")); //$NON-NLS-1$
				}
			};
			
			sd.setOriginalName(fileName);

			if (sd.open() == SaveAsDialog.OK) {
				IPath path = sd.getResult();

				final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
				try {
					new ProgressMonitorDialog(shell).run(false, // don't fork
							false, // not cancelable
							new WorkspaceModifyOperation() { // run this operation
								public void execute(final IProgressMonitor monitor) {
									try {
										
										// Recupere le formalisme manager et importe le modele dans l'editeur
										FormalismManager fm = Coloane.getDefault().getMotor().getFormalismManager();
										IModelImpl model = fm.importModel(filePath);
										
										// traduction du modele au format xml
										XmlEditor xml = new XmlEditor();
										String xmlString = xml.modelXML(model.getGenericModel());
										InputStream inputS = new ByteArrayInputStream(xmlString.getBytes());

										// Creation d'un nouveau fichier contenant le modele au format xml
										file.create(inputS, // contents
											true, // keep saving, even if IFile is out of
											// sync with
											// the Workspace
											monitor); // progress monitor
										
										// Open editor
										IDE.openEditor(window.getActivePage(),file, true);
									} catch (Exception e) {
										Coloane.showErrorMsg(e.getMessage());
                                        e.printStackTrace();
									}
								}
							});
				} catch (Exception e) {
					Coloane.showErrorMsg(e.getMessage());
                    e.printStackTrace();
				}
			}
		}

	}
	private void doExport(IModelImpl model, String filePath) {
		try {
			FormalismManager fm = Coloane.getDefault().getMotor().getFormalismManager();
			fm.exportModel(model, filePath);
		} catch (Exception e) {
			Coloane.showErrorMsg(e.getMessage());
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
	}

}
