package fr.lip6.move.coloane.ui.wizards;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;


import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
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

	private Button button = null;
	private Label label = null;
	private Text formalismName = null;
	private IStructuredSelection selection;

	Composite com, rootCom;

	/**
	 * Creer une nouvelle wizard page
	 * @param workbench le workbench courant
	 * @param selection l'object selection courant
	 * @see NewModelWizard#init(IWorkbench, IStructuredSelection)
	 */
	public CreationPage(IWorkbench workbench, IStructuredSelection selection) {
		super("Step 2 - Choose a project", selection);

		this.selection = selection;
		this.workbench = workbench;

		setTitle("Create a new model");
		setDescription("This wizard creates a new model. First, you must choose the formalism");
		setPageComplete(true);

	}

	/**
	 * Créer Texte et Boutton pour parcourir le formalisme
	 * @param parent parent
	 * @return Composant
	 */
	private Composite createFormalismComposite(Composite parent) {

		Composite com = new Composite(parent, SWT.NONE);
		GridLayout gl1 = new GridLayout();
		gl1.numColumns = 3;
		com.setLayout(gl1);
		com.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));

		label = new Label(com, SWT.NONE);
		label.setText("Formalism: ");
		formalismName = new Text(com, SWT.BORDER);
		formalismName.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));

		// set readonly formalism name
		formalismName.setEditable(false);

		button = new Button(com, SWT.NONE);
		button.setText("Browse...");
		button.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {

			}

			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell());
				fd.setText("Open Formalism");
				fd.setFilterExtensions(new String[]{"*.*"});
				String file = fd.open();

				// validate formalism
				validateFormalism(file);

				formalismName.setText(file);
			}

		});

		return com;
	}

	/**
	 * Cette méthode ajoute quelques controles pour parcourir le fichier du formalisme
	 * pour le control standard de WizardNewFileCreationPage
	 * 
	 * @param parent Composant parent
	 * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#createControl(org.eclipse.swt.widgets.Composite)
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
	 * Validation du formalisme
	 * @param formalismFile nom du fichier du formalisme 
	 * 
	 */
	private void validateFormalism(String formalismFile) {
		try {

			//formalism = FormalismsManager.loadFormalism(formalismFile);

		} catch (Exception e) {

			setErrorMessage("Formalism error: " + e.getLocalizedMessage());

		}
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
