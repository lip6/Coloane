package fr.lip6.move.coloane.core.ui.wizards.importmodel;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IImportFrom;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.core.ui.files.ModelWriter;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Logger;

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
	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Le composant de choix de fichier */
	private FileFieldEditor fileSelect;

	/** Le composant de choix de formalismes */
	private Combo formSelect;

	/** Le workbench */
	private IWorkbench workbench;

	/** L'instance de conversion */
	private IImportFrom importInstance;

	/**
	 * Constructeur de la page d'assistant
	 * @param workbench Le workbench courant
	 * @param selection La selection courante
	 * @param importInstance L'instance de l'extension d'importation
	 */
	public ImportWizardPage(IWorkbench usedWorkbench, IStructuredSelection currentSelection, IImportFrom importInstance) {
		super(Messages.ImportWizardPage_12, currentSelection);
		setTitle(Messages.ImportWizardPage_0);
		setDescription(Messages.ImportWizardPage_1);
		setFileExtension(Coloane.getParam("MODEL_EXTENSION")); //$NON-NLS-1$

		this.workbench = usedWorkbench;
		this.importInstance = importInstance;
	}

	/** {@inheritDoc} */
	@Override
	public final void createControl(Composite parent) {
		super.createControl(parent);
	}

	/** {@inheritDoc} */
	@Override
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

		// Creation de la partie responsable du choix du formalisme
		Label formLabel = new Label(formalismSelectionArea, SWT.NONE);
		formLabel.setText(Messages.ImportWizardPage_3);
		formSelect = new Combo(formalismSelectionArea, SWT.BORDER | SWT.READ_ONLY);
		formSelect.addListener(SWT.Modify, this);

		// On recupere la liste des formalismes
		List<IFormalism> listOfFormalisms = FormalismManager.getInstance().getListOfFormalisms();
		for (IFormalism formalism : listOfFormalisms) {
			formSelect.add(formalism.getName());
		}

		String[] extensions = new String[] {"*.*"}; //$NON-NLS-1$
		fileSelect = new FileFieldEditor("fileSelect", "Select File: ", fileSelectionArea); //$NON-NLS-1$ //$NON-NLS-2$
		fileSelect.getTextControl(fileSelectionArea).addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				IPath path = new Path(ImportWizardPage.this.fileSelect.getStringValue());
				setFileName(path.removeFileExtension().lastSegment());
			}
		});
		fileSelect.setFileExtensions(extensions);
		fileSelectionArea.moveAbove(null);
		formalismSelectionArea.moveAbove(null);
	}

	/** {@inheritDoc} */
	@Override
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
				return false;
			}

			// Importe le modele, via l'instance precedement creee
			String path = fileSelect.getStringValue();
			IGraph model = importInstance.importFrom(path, this.formSelect.getText());

			// Traduction du modele au format xml
			String xmlString = ModelWriter.translateToXML(model);
			InputStream inputS = new ByteArrayInputStream(xmlString.getBytes("UTF-8")); //$NON-NLS-1$

			// Tentative de creation de fichier
			newFile = createNewFile();
			LOGGER.fine("Creation du nouveau fichier dans le workspace"); //$NON-NLS-1$

			// Verification que tout est OK
			if (newFile == null) {
				setErrorMessage(Messages.ImportWizardPage_14);
				return false;
			}
			newFile.setContents(inputS, true, false, null);

		} catch (CoreException e) {
			LOGGER.warning("Echec lors de la creation du fichier"); //$NON-NLS-1$
			e.printStackTrace();
			return false;
		} catch (UnsupportedEncodingException e) {
			LOGGER.warning("Echec lors de la creation du fichier (charset invalide)"); //$NON-NLS-1$
			e.printStackTrace();
			return false;
		}

		// Ouverture du nouveau fichier
		IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
		if (newFile != null && page != null) {
			try {
				IDE.openEditor(page, newFile, true);
			} catch (CoreException ce) {
				LOGGER.warning(ce.getMessage());
				Coloane.showErrorMsg(ce.getMessage());
				return false;
			}
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	protected final String getNewFileLabel() {
		return "New File Name:"; //$NON-NLS-1$
	}

	/** {@inheritDoc} */
	@Override
	protected final IStatus validateLinkedResource() {
		return new Status(IStatus.OK, "fr.lip6.move.coloane.core", IStatus.OK, "", null); //$NON-NLS-1$ //$NON-NLS-2$
	}



	@Override
	public final boolean isPageComplete() {
		if (fileSelect.getStringValue().equals("")) { //$NON-NLS-1$
			setErrorMessage(Messages.ImportWizardPage_7);
			return false;
		}

		if (formSelect.getText().equals("")) { //$NON-NLS-1$
			setErrorMessage(Messages.ImportWizardPage_9);
			return false;
		}


		if (getFileName().equals("")) { //$NON-NLS-1$
			setErrorMessage(Messages.ImportWizardPage_11);
			return false;
		}

		if (getContainerFullPath() == null) {
			setErrorMessage(Messages.ImportWizardPage_13);
			return false;
		}

//		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getContainerFullPath().segment(0));
//		if (project.getFile(computeModelName(getFileName(), formSelect.getText())).exists()) {
//			setErrorMessage(Messages.ImportWizardPage_15);
//			return false;
//		}

		return super.isPageComplete();
	}
}
