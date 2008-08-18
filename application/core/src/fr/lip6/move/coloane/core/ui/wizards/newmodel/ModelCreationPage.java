package fr.lip6.move.coloane.core.ui.wizards.newmodel;

import fr.lip6.move.coloane.core.extensions.ExampleExtension;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.files.ModelWriter;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;

/**
 * Page d'assistant dédiée à la création d'un modèle.<br>
 * Cette page doit proposer :
 * <ul>
 * 	<li>le rattachement à un projet</li>
 * 	<li>la sélection d'un modèle déjà préconstruit</li>
 * </ul>
 */
public class ModelCreationPage extends WizardNewFileCreationPage {
	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Chaine définissant le texte à afficher pour le modèle vide par défaut dans la liste d'exemples */
	private static final String DEFAULT_MODEL = Messages.ModelCreationPage_4;

	private final IWorkbench workbench;

	/** Le projet auquel on souhaite rajouter le nouveau modele */
	private IProject currentProject;

	/** La liste de choix pour les modèles d'exemples */
	private Combo patternsList = null;

	/** La description de l'exemple choisi */
	private Text patternDescription = null;

	/** Liste des modeles d'exemples disponible */
	private Map<String, String> patternContributions = new HashMap<String, String>();

	/** Le nom du modèle choisi */
	private String modelSelected = DEFAULT_MODEL;

	/** Le formalisme choisi pour le nouveau fichier */
	private String formalismName;

	/**
	 * Creer une nouvelle page de wizard
	 * @param currentWorkbench le workbench courant
	 * @param currentSelection l'object selection courant
	 */
	public ModelCreationPage(IWorkbench currentWorkbench, IStructuredSelection currentSelection) {
		super("modelcreationpage", currentSelection); //$NON-NLS-1$
		this.workbench = currentWorkbench;
		this.currentProject = (IProject) currentSelection.getFirstElement();
		setTitle(Messages.ModelCreationPage_0);
		setDescription(Messages.ModelCreationPage_1);
		setFileExtension(Coloane.getParam("MODEL_EXTENSION")); //$NON-NLS-1$

		// Si l'utilisateur a déjà choisi son projet... On peut calculer le nom par défaut
		if (this.currentProject != null) {
			setFileName(this.computeDefaultModelName());
		}
	}

	/**
	 * Cette methode ajoute les controles pour visualiser les projets ouverts
	 * pour le control standard de WizardNewFileCreationPage
	 * @param parent Le conteneur de la boite de dialogue
	 */
	@Override
	public final void createControl(Composite parent) {
		super.createControl(parent);

		Composite composite = (Composite) getControl();
		// Groupe de sélection pour les exemples
		Group group = new Group(composite, SWT.NONE);
		group.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		group.setLayoutData(gd);
		group.setText(Messages.ModelCreationPage_6);

		Label lblList = new Label(group, SWT.NONE);
		lblList.setText(Messages.ModelCreationPage_7);

		gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.grabExcessHorizontalSpace = true;

		patternsList = new Combo(group, SWT.BORDER | SWT.READ_ONLY);
		patternsList.setLayoutData(gd);
		patternsList.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String description = Messages.ModelCreationPage_8;
				modelSelected = patternsList.getText();
				if (patternContributions.containsKey(modelSelected)) {
					description = patternContributions.get(modelSelected);
				}
				patternDescription.setText(description);
				patternDescription.getParent().layout();
			}
		});

		gd = new GridData();
		gd.horizontalSpan = 2;
		Label lblDescription = new Label(group, SWT.NONE);
		lblDescription.setText(Messages.ModelCreationPage_9);
		lblDescription.setLayoutData(gd);

		gd = new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.verticalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.heightHint = 50;
		patternDescription = new Text(group, SWT.READ_ONLY | SWT.MULTI);
		patternDescription.setLayoutData(gd);

		new Label(composite, SWT.NONE);
		setPageComplete(validatePage());
	}


	/** {@inheritDoc} */
	@Override
	public final void setVisible(boolean visible) {
		super.setVisible(visible);

		if (visible) {
			// On recupere la liste des exemples
			this.formalismName = ((NewModelWizard) getWizard()).getFormalismName();
			this.patternContributions = ExampleExtension.getModelsName(this.formalismName);

			// Ajout de l'exemple vide
			this.patternsList.add(DEFAULT_MODEL);
			this.patternsList.select(0);

			// Contribution à partir des exemples disponibles
			for (String name : this.patternContributions.keySet()) {
				patternsList.add(name);
			}

			// Rafraichissement
			this.patternsList.getParent().layout();

		} else {
			patternsList.removeAll();
		}
	}

	/**
	 * Methode invoquee lorsque le bouton finish est pressee
	 * @return true si ok
	 * @see NewModelWizard#performFinish()
	 */
	public final boolean finish() {
		// Tentative de creation de fichier
		// newFile != null si la creation reussie
		IFile newFile = createNewFile();
		if (newFile == null) {
			setErrorMessage(Messages.ModelCreationPage_3);
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

	/**
	 * Methode remplissant le fichier avec un contenu par defaut
	 * @return InputStream
	 */
	@Override
	protected final InputStream getInitialContents() {
		InputStream inputS;

		try {
			// Formalisme choisi par l'utilisateur dans la boite de dialogue
			String formalismName = ((NewModelWizard) getWizard()).getFormalismName();
			LOGGER.fine("Choix du formalisme : " + formalismName); //$NON-NLS-1$

			// Creation du fihier sous-jacent par defaut
			LOGGER.fine("Creation du fichier sous-jacent"); //$NON-NLS-1$

			String xmlString;
			if (DEFAULT_MODEL.equalsIgnoreCase(modelSelected)) {
				xmlString = ModelWriter.createDefault(formalismName);
				LOGGER.fine("Creation d'un fichier vide par defaut"); //$NON-NLS-1$
			} else {
				xmlString = ModelWriter.translateToXML(ExampleExtension.getModel(patternsList.getText()));
				LOGGER.fine("Creation d'un fichier a partir de l'example :" + patternsList.getText()); //$NON-NLS-1$
			}

			// Creation de l'input stream a partir d'une chaine de caractere
			inputS = new ByteArrayInputStream(xmlString.getBytes());
		} catch (CoreException e) {
			LOGGER.warning("Impossible de creer le nouveau fichier"); //$NON-NLS-1$
			LOGGER.warning(e.getMessage());
			Coloane.showErrorMsg(e.getMessage());
			return null;
		}
		return inputS;
	}

	/**
	 * Calcule le nom du nouveau fichier et installe le nom dans la page du wizard<br>
	 * Cette méthode est uniquement appelé lors d'un finish direct sur la première page de l'assistant
	 * @return le premier nom par défaut disponible pour le nouveau modèle
	 */
	private String computeDefaultModelName() {
		int counter = 0;
		String computedName = Coloane.getParam("WIZARD_FILENAME_BASE") + "_" + counter;  //$NON-NLS-1$ //$NON-NLS-2$
		while (this.checkName(computedName + "." + getFileExtension())) { //$NON-NLS-1$
			counter++;
			computedName = Coloane.getParam("WIZARD_FILENAME_BASE") + "_" + counter;  //$NON-NLS-1$ //$NON-NLS-2$
		}
		return computedName;
	}

	/**
	 * Vérifie que le nom n'est pas déjà pris...
	 * @param modelName Le nom du nouveau modèle (sans extension)
	 * @return <code>true</code> si tout est OK; <code>false</code> sinon
	 */
	private boolean checkName(String modelName) {
		return this.currentProject.getFile(modelName).exists();
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isPageComplete() {
		// Raccourci : Finich dès la première page
		if (this.currentProject != null) {
			return true;
		}

		// Le nouveau modele doit etre attaché a un projet
		if (getContainerFullPath() == null) {
			setErrorMessage(Messages.ModelCreationPage_11);
			return false;
		}

		// Le nom du fichier ne doit pas être vide
		if ((getFileName() == null) || (getFileName().equals(""))) { //$NON-NLS-1$
			setErrorMessage(Messages.ModelCreationPage_13);
			return false;
		}

		// Vérification de la non-existence du fichier
		return super.isPageComplete();
	}
}
