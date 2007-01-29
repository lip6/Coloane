package fr.lip6.move.coloane.ui.wizards;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;

import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.motor.formalism.FormalismManager;
import fr.lip6.move.coloane.motor.models.ModelImplAdapter;

public class CreationPage extends WizardNewFileCreationPage {

	private final IWorkbench workbench;
	private static int fileCount = 0;

	Composite com, rootCom;

	/**
	 * Creer une nouvelle wizard page
	 * @param workbench le workbench courant
	 * @param selection l'object selection courant
	 */
	public CreationPage(IWorkbench workbench, IStructuredSelection selection) {
		super("Step 2 - Choose a project", selection);

		this.workbench = workbench;

		setTitle("Create a new model");
		setDescription("This wizard creates a new model. First, you must choose the formalism");
		setPageComplete(true);

	}

	/**
	 * Cette méthode ajoute quelques controles pour parcourir le fichier du formalisme
	 * pour le control standard de WizardNewFileCreationPage
	 */
	public void createControl(Composite parent) {
		super.createControl(parent);
		setFileName(Coloane.getParam("file.name") + fileCount);
		setPageComplete(true);
	}

	/**
	 * Cette méthode est invoquée quand le boutton "Finish" est pressé
	 * @return true si ok
	 * @see NewModelWizard#performFinish()
	 */
	boolean finish() {

		FormalismManager formManager = Coloane.getDefault().getMotor().getFormalismManager();

		// Recupere le nom du formalisme deceide la page precedente
		String formalismName = ((NewModelWizard) getWizard()).getFormalismName();
		String extension = formManager.loadFormalism(formalismName).getExtension();
		setFileName(getFileName() + "." + extension);

		// Tentative de creation de fichier
		// newFile != null si la creation reussie
		IFile newFile = createNewFile();
		if (newFile == null) {
			setErrorMessage("Cannot create file 'file'");
			System.err.println("Impossible de creer le fichier");
			return false;
		}
		fileCount++;

		// Ouverture du nouveau fichier
		IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
		if (newFile != null && page != null) {
			try {
				IDE.openEditor(page, newFile, true);
			} catch (CoreException ce) {
				System.err.println("Impossible d'ouvrir le fichier");
				Coloane.showErrorMsg(ce.getMessage());
				return false;
			}
		}
		return true;
	}

	/**
	 * TODO : Description
	 */
	protected InputStream getInitialContents() {
		ByteArrayInputStream bais = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(createDefaultContent()); // argument must be Serializable
			oos.flush();
			oos.close();
			bais = new ByteArrayInputStream(baos.toByteArray());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return bais;
	}

	/** Return a new ShapesDiagram instance.
	 *  @return un objet ModelImplAdapter
	 */
	private ModelImplAdapter createDefaultContent() {
		String formalismName = ((NewModelWizard) getWizard()).getFormalismName();
		FormalismManager formManager = Coloane.getDefault().getMotor().getFormalismManager();
		ModelImplAdapter m = new ModelImplAdapter(formManager.loadFormalism(formalismName));

		return m;
	}

	/**
	 * Valider la page
	 * @return true si la page est validée
	 * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#validatePage()
	 */
	protected boolean validatePage() {
		boolean b = super.validatePage();
		setPageComplete(b && isPageComplete());
		((NewModelWizard) getWizard()).creationFinished = b;
		return b;
	}

	/**
	 * @return false
	 */
	public boolean canFlipToNextPage() {
		return false;
	}

}
