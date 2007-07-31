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
import fr.lip6.move.coloane.ui.model.IModelImpl;
import fr.lip6.move.coloane.ui.model.ModelImplAdapter;

public class ModelCreationPage extends WizardNewFileCreationPage {

	private final IWorkbench workbench;
	private static int fileCount = 0;

	Composite com, rootCom;

	/**
	 * Creer une nouvelle wizard page
	 * @param workbench le workbench courant
	 * @param selection l'object selection courant
	 */
	public ModelCreationPage(IWorkbench workbench, IStructuredSelection selection) {
		super("newmodel", selection);
		this.workbench = workbench;
		setTitle("Attach your model.");
		setDescription("Choose a modeling project to store your model to.");
		setPageComplete(true);
	}

	/**
	 * Cette methode ajoute les controle pour visualiser les projets ouverts
	 * pour le control standard de WizardNewFileCreationPage
	 */
	public void createControl(Composite parent) {
		super.createControl(parent);
		
		// On propose un nom par defaut
		setFileName(Coloane.getParam("file.name") +"_"+ fileCount);
	}
	
	/**
	 * @return false
	 * @see NewModelWizard#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage() {
		return false;
	}

	/**
	 * Methode invoquee lorsque le bouton finish est pressee
	 * @return true si ok
	 * @see NewModelWizard#performFinish()
	 */
	boolean finish() {

		FormalismManager formManager = Coloane.getDefault().getMotor().getFormalismManager();
		
		// On doit verifier que le chargement du formalismManager est OK
		if (formManager == null) {
			setErrorMessage("Coloane has not been launched correctly...");
			return false;
		}

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
		
		// Changement de l'increment pour le prochain nom temporaire
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
	 *  @return un objet IModelImpl
	 */
	private IModelImpl createDefaultContent() {
		String formalismName = ((NewModelWizard) getWizard()).getFormalismName();
		FormalismManager formManager = Coloane.getDefault().getMotor().getFormalismManager();
		IModelImpl m = new ModelImplAdapter(formManager.loadFormalism(formalismName));

		return m;
	}
}
