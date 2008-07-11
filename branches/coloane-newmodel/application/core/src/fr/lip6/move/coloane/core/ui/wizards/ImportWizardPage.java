package fr.lip6.move.coloane.core.ui.wizards;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IImportFrom;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalisms.Formalism;
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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
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
		super(Messages.ImportWizardPage_12, currentSelection);
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

		// Creation de la partie responsable du chois du formalisme
		Label formLabel = new Label(formalismSelectionArea, SWT.NONE);
		formLabel.setText(Messages.ImportWizardPage_3);
		formSelect = new Combo(formalismSelectionArea, SWT.BORDER | SWT.READ_ONLY);
		formSelect.addListener(SWT.Modify, this);

		// On recupere la liste des formalismes
		List<Formalism> listOfFormalisms = Coloane.getDefault().getMotor().getFormalismManager().getListOfFormalisms();
		for (IFormalism formalism : listOfFormalisms) {
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
	@Override
	protected final InputStream getInitialContents() {
		try {
			return new FileInputStream(new File(fileSelect.getStringValue()));
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	/**
	 * Calcule le nom du fichier lorsqu'il sera importe dans le workspace
	 * @param name Le nom du fichier initial
	 * @param formalisme Le formalisme du fichier (precise par l'utilisateur)
	 * @return Le nouveau nom du fichier (avec son extension)
	 */
	private String computeModelName(String name, String formalisme) {
		// Recupere les informations sur le formalisme choisi
		IFormalism importFormalism = Coloane.getDefault().getMotor().getFormalismManager().getFormalismByName(formalisme);
		Coloane.getLogger().fine("Formalisme choisi : " + importFormalism.getName()); //$NON-NLS-1$

		// Travail sur l'extension du fichier
		int pos = name.lastIndexOf('.');
		if (pos > 0) {
			name = (String) name.subSequence(0, pos);
		}
		name = name.concat("." + importFormalism.getExtension()); //$NON-NLS-1$
		Coloane.getLogger().fine("Nouveau nom de fichier pour le modele : " + name); //$NON-NLS-1$
		return name;
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
				Coloane.getLogger().warning(Messages.ImportWizardPage_4);
				return false;
			}

			// Importe le modele, via l'instance precedement creee
			String path = fileSelect.getStringValue();
			IGraph graph = importInstance.importFrom(path, this.formSelect.getText());

			// Traduction du modele au format xml
			String xmlString = ModelWriter.translateToXML(graph);
			InputStream inputS = new ByteArrayInputStream(xmlString.getBytes("UTF-8")); //$NON-NLS-1$


			String newName = computeModelName(getFileName(), formSelect.getText());
			setFileName(newName);

			// Tentative de creation de fichier
			newFile = createNewFile();

			Coloane.getLogger().fine("Creation du nouveau fichier dans le workspace"); //$NON-NLS-1$

			// Verification que tout est OK
			if (newFile == null) {
				setErrorMessage(Messages.ModelCreationPage_3);
				return false;
			}
			newFile.setContents(inputS, true, false, null);

		} catch (CoreException e) {
			Coloane.getLogger().warning("Echec lors de la creation du fichier"); //$NON-NLS-1$
			e.printStackTrace();
			return false;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			Coloane.getLogger().warning("Echec lors de la creation du fichier (charset invalide)"); //$NON-NLS-1$
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
	@Override
	protected final String getNewFileLabel() {
		return "New File Name:"; //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#validateLinkedResource()
	 */
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
			return false;
		}

		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getContainerFullPath().segment(0));
		if (project.getFile(computeModelName(getFileName(), formSelect.getText())).exists()) {
			setErrorMessage(Messages.ImportWizardPage_15);
			return false;
		}

		return super.isPageComplete();
	}
}
