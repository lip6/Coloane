package fr.lip6.move.coloane.core.ui.wizards;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IImportFrom;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalism.Formalism;
import fr.lip6.move.coloane.core.ui.files.ModelWriter;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;

public class ImportWizardPage extends WizardNewFileCreationPage {

	/** Le composant de choix de fichier */
	private FileFieldEditor fileSelect;

	/** Le composant de choix de formalismes */
	private Combo formSelect;

	/** Le workbench */
	private IWorkbench workbench;

	/** L'instance de conversion */
	private IImportFrom importInstance;

	/**
	 * Constructeur : Nouvelle page du wizard d'importation de modeles
	 * @param workbench
	 * @param selection
	 */
	public ImportWizardPage(IWorkbench usedWorkbench, IStructuredSelection currentSelection, IImportFrom importInstance) {
		super("file import", currentSelection);
		setTitle(Messages.ImportWizardPage_0);
		setDescription(Messages.ImportWizardPage_1);

		this.workbench = usedWorkbench;
		this.importInstance = importInstance;
	}

	@Override
	public final void createControl(Composite parent) {
		super.createControl(parent);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#createAdvancedControls(org.eclipse.swt.widgets.Composite)
	 */
	protected final void createAdvancedControls(Composite parent) {
		super.createAdvancedControls(parent);

		Composite fileSelectionArea = new Composite(parent, SWT.NONE);
		Composite formalismSelectionArea = new Composite(parent, SWT.NONE);
		GridData fileSelectionData = new GridData(GridData.GRAB_HORIZONTAL | GridData.FILL_HORIZONTAL);
		fileSelectionArea.setLayoutData(fileSelectionData);

		// Pour la gestion du choix de fichier
		GridLayout fileSelectionLayout = new GridLayout();
		fileSelectionLayout.numColumns = 3;
		fileSelectionLayout.makeColumnsEqualWidth = false;
		fileSelectionLayout.marginWidth = 0;
		fileSelectionLayout.marginHeight = 0;
		fileSelectionArea.setLayout(fileSelectionLayout);
		formalismSelectionArea.setLayout(fileSelectionLayout);

		// Creation de la partie responsable du chois du formalisme
		Label formLabel = new Label(formalismSelectionArea, SWT.NONE);
		formLabel.setText("Choose the formalism of the model inside your file :");
		formSelect = new Combo(formalismSelectionArea, SWT.BORDER | SWT.READ_ONLY);
		formSelect.addListener(SWT.Modify, this);

		// On recupere la liste des formalismes
		ArrayList<Formalism> listOfFormalisms = Coloane.getDefault().getMotor().getFormalismManager().getListOfFormalisms();
		for (Formalism formalism : listOfFormalisms) {
			formSelect.add(formalism.getName());
		}

		String[] extensions = new String[] {"*.*"}; //$NON-NLS-1$
		fileSelect = new FileFieldEditor("fileSelect", "Select File: ", fileSelectionArea); //$NON-NLS-1$ //$NON-NLS-2$
		fileSelect.getTextControl(fileSelectionArea).addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				IPath path = new Path(ImportWizardPage.this.fileSelect.getStringValue());
				setFileName(path.lastSegment());
			}
		});
		fileSelect.setFileExtensions(extensions);
		fileSelectionArea.moveAbove(null);
		formalismSelectionArea.moveAbove(null);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#getInitialContents()
	 */
	protected final InputStream getInitialContents() {
		try {
			return new FileInputStream(new File(fileSelect.getStringValue()));
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	/**
	 * Methode invoquee lorsque le bouton finish est pressee
	 * @return true si ok
	 * @see NewModelWizard#performFinish()
	 */
	public final boolean finish() throws ColoaneException {
		IFile newFile = null;

		try {
			if (importInstance == null) {
				Coloane.getLogger().warning("Impossible de trouver l'instance de conversion");
				return false;
			}

			// Importe le modele, via l'instance precedement creee
			String path = fileSelect.getStringValue();
			IModelImpl model = importInstance.importFrom(path, this.formSelect.getText());

			// Traduction du modele au format xml
			String xmlString = ModelWriter.translateToXML(model);
			InputStream inputS = new ByteArrayInputStream(xmlString.getBytes());

			// Recupere les informations sur le formalisme choisi
			Formalism importFormalism = Coloane.getDefault().getMotor().getFormalismManager().getFormalismByName(formSelect.getText());

			// Travail sur l'extension du fichier
			String newName = getFileName();
			newName = (String) newName.subSequence(0, newName.lastIndexOf('.'));
			newName = newName.concat("." + importFormalism.getExtension());
			setFileName(newName);

			// Tentative de creation de fichier
			newFile = createNewFile();

			// Verification que tout est OK
			if (newFile == null) {
				setErrorMessage(Messages.ModelCreationPage_3);
				return false;
			}
			newFile.setContents(inputS, true, false, null);

		} catch (CoreException e) {
			e.printStackTrace();
			return false;
		}

		// Ouverture du nouveau fichier
		IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
		if (newFile != null && page != null) {
			try {
				IDE.openEditor(page, newFile, true);
			} catch (CoreException ce) {
				Coloane.getLogger().warning(ce.getMessage());
				Coloane.showErrorMsg(ce.getMessage());
				return false;
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#getNewFileLabel()
	 */
	protected final String getNewFileLabel() {
		return "New File Name:"; //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#validateLinkedResource()
	 */
	protected final IStatus validateLinkedResource() {
		return new Status(IStatus.OK, "fr.lip6.move.coloane.core", IStatus.OK, "", null); //$NON-NLS-1$ //$NON-NLS-2$
	}



	@Override
	public final boolean isPageComplete() {
		if (fileSelect.getStringValue().equals("")) {
			setErrorMessage("You must indicate the file you want to import...");
			return false;
		}

		if (formSelect.getText().equals("")) {
			setErrorMessage("You must indicate the formalism of the imported model...");
			return false;
		}

		if (getFileName().equals("")) {
			setErrorMessage("You must provide a name for your file once it has been imported...");
			return false;
		}

		return super.isPageComplete();
	}
}
