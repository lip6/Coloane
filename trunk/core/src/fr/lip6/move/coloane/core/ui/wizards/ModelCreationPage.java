package fr.lip6.move.coloane.core.ui.wizards;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.files.ModelWriter;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;

public class ModelCreationPage extends WizardNewFileCreationPage {

	private final IWorkbench workbench;
	private static int fileCount = 0;

	/**
	 * Creer une nouvelle wizard page
	 * @param workbench le workbench courant
	 * @param selection l'object selection courant
	 */
	public ModelCreationPage(IWorkbench currentWorkbench, IStructuredSelection currentSelection) {
		super("newmodel", currentSelection); //$NON-NLS-1$
		this.workbench = currentWorkbench;
		setTitle(Messages.ModelCreationPage_0);
		setDescription(Messages.ModelCreationPage_1);
		setPageComplete(true);
	}

	/**
	 * Cette methode ajoute les controle pour visualiser les projets ouverts
	 * pour le control standard de WizardNewFileCreationPage
	 */
	@Override
	public final void createControl(Composite parent) {
		super.createControl(parent);
		// On propose un nom par defaut
		setFileName(Coloane.getParam("WIZARD_FILENAME_BASE") + "_" + fileCount); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Permission de passer a la page suivante ?
	 * @return false
	 * @see NewModelWizard#canFlipToNextPage()
	 */
	@Override
	public final boolean canFlipToNextPage() {
		return false;
	}

	/**
	 * Methode invoquee lorsque le bouton finish est pressee
	 * @return true si ok
	 * @see NewModelWizard#performFinish()
	 */
	public final boolean finish() {

		// On ajoute l'extension au fichier fraichement cree
		setFileName(getFileName() + "." + Coloane.getDefault().getMotor().getFormalismManager().getFormalismByName(((NewModelWizard) getWizard()).getFormalismName()).getExtension()); //$NON-NLS-1$

		// Tentative de creation de fichier
		// newFile != null si la creation reussie
		IFile newFile = createNewFile();
		if (newFile == null) {
			setErrorMessage(Messages.ModelCreationPage_3);
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
				Coloane.getLogger().warning(ce.getMessage());
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
		// Formalisme choisi par l'utilisateur dans la boite de dialogue
		String formalismName = ((NewModelWizard) getWizard()).getFormalismName();
		Coloane.getLogger().fine("Choix du formalisme : "+formalismName); //$NON-NLS-1$

		// Creation du fihier sous-jacent par defaut
		Coloane.getLogger().fine("Creation du fichier sous-jacent"); //$NON-NLS-1$
		String xmlString = ModelWriter.createDefault(formalismName);

		// Creation de l'input stream a partir d'une chaine de caractere
		InputStream inputS = new ByteArrayInputStream(xmlString.getBytes());

		return inputS;
	}

}
