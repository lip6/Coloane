package fr.lip6.move.coloane.core.ui.wizards.importmodel;

import fr.lip6.move.coloane.core.extensions.IImportFrom;
import fr.lip6.move.coloane.core.extensions.ImportFromExtension;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;

/**
 * Page composant l'assistant d'import de fichier.<br/>
 * Cette page est composée :
 * <ul>
 * 	<li>D'une sélection de formalismes</li>
 * 	<li>D'un choix de projets</li>
 * 	<li>D'un choix de nom de fichier</li>
 * </ul>
 */
public class ImportWizardPage extends WizardNewFileCreationPage {
	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Le composant de choix de fichier */
	private FileFieldEditor fileSelect;

	/** Le composant de choix de formalismes */
	private Combo formSelect;

	/** Le workbench */
	private final IWorkbench workbench;

	/** L'instance de conversion */
	private IImportFrom importInstance;

	private String idWizard;

	/**
	 * Constructeur de la page d'assistant
	 * @param workbench Le workbench courant
	 * @param selection La selection courante
	 * @param importInstance L'instance de l'extension d'importation
	 * @param idWizard Id de l'extension
	 */
	public ImportWizardPage(IWorkbench workbench, IStructuredSelection selection, IImportFrom importInstance, String idWizard) {
		super(Messages.ImportWizardPage_12, selection);
		setTitle(Messages.ImportWizardPage_0);
		setDescription(Messages.ImportWizardPage_1);
		setFileExtension(Coloane.getParam("MODEL_EXTENSION")); //$NON-NLS-1$

		this.workbench = workbench;
		this.importInstance = importInstance;
		this.idWizard = idWizard;
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
		List<IFormalism> listOfFormalisms = ImportFromExtension.getFormalisms(idWizard);
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
	 * Action à effectuer lorsque le bouton finish est pressé
	 * @return <code>true</code> si tout s'est bien passé
	 * @see NewModelWizard#performFinish()
	 * TODO : Beaucoup (trop) d'exceptions sont levées... A vérifier
	 */
	public final boolean finish() {
		if (importInstance == null) {
			return false;
		}

		LOGGER.fine("Traitement de l'assistant"); //$NON-NLS-1$

		// Importe le modele, via l'instance precedement creee
		String path = fileSelect.getStringValue();

		// Tentative de creation de fichier
		LOGGER.fine("Creation du nouveau fichier"); //$NON-NLS-1$
		final IFile newFile = createNewFile();
		LOGGER.fine("Creation du nouveau fichier dans le workspace"); //$NON-NLS-1$

		// Verification que tout est OK
		if (newFile == null) {
			setErrorMessage(Messages.ImportWizardPage_14);
			return false;
		}

		Job job = new ImportJob("Import " + path, //$NON-NLS-1$
						importInstance,
						formSelect.getText(),
						fileSelect.getStringValue(),
						newFile);

		job.setPriority(Job.LONG);
		job.setRule(newFile);
		job.setUser(true);
		job.schedule();

		//		} catch (CoreException e) {
		//			LOGGER.warning("Echec lors de la creation du fichier"); //$NON-NLS-1$
		//			e.printStackTrace();
		//			return false;
		//		} catch (UnsupportedEncodingException e) {
		//			LOGGER.warning("Echec lors de la creation du fichier (charset invalide)"); //$NON-NLS-1$
		//			e.printStackTrace();
		//			return false;
		//		}

//		final IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();

		// Ouverture du nouveau fichier
		job = new Job("Open editor") { //$NON-NLS-1$
			@Override
			protected IStatus run(IProgressMonitor monitor) {
//				System.err.println(page);
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
						if (newFile != null && page != null) {
							try {
								IDE.openEditor(page, newFile, true);
							} catch (CoreException ce) {
								LOGGER.warning(ce.getMessage());
							}
						}
					}
				});
				return Status.OK_STATUS;
			}
		};
		job.setPriority(Job.LONG);
		job.setRule(newFile);
		job.setSystem(true);
		job.schedule();
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

	/** {@inheritDoc} */
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

		return super.isPageComplete();
	}
}
