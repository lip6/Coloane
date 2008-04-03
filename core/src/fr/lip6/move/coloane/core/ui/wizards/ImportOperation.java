package fr.lip6.move.coloane.core.ui.wizards;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.ide.IDE;

import fr.lip6.move.coloane.core.extensions.ImportFromExtension;
import fr.lip6.move.coloane.core.interfaces.IImportFrom;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.ModelWriter;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

public class ImportOperation extends WorkspaceModifyOperation {

	final private String idWizard;  
	final private ImportWizardPage page;

	public ImportOperation(ImportWizardPage page, String idWizard) {
		this.idWizard = idWizard;
		this.page = page;
	}

	@Override
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		IFile file = null;
		
		IImportFrom importInstance = (IImportFrom) ImportFromExtension.createConvertInstance(this.idWizard);
		
		if (importInstance == null) {
			return;
		}

		try {
			// Importe le modele, via l'instance precedement creee
			String path = page.getSelectedFile();
			IModelImpl model = importInstance.importFrom(path);

			// Traduction du modele au format xml
			String xmlString = ModelWriter.translateToXML(model);
			InputStream inputS = new ByteArrayInputStream(xmlString.getBytes());

			try {
				file = page.createNewFile();
				file.setContents(inputS, true, false, monitor);
			} catch (CoreException ce) {
				ce.printStackTrace();
				return;
			}

			// Open editor
			IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), file, true);
		} catch (Exception e) {
			Coloane.showErrorMsg(e.getMessage());
			e.printStackTrace();
		}
	}
}
