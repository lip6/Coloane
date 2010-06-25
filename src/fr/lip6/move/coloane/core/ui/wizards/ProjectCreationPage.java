package fr.lip6.move.coloane.core.ui.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

/**
 * Page composant l'assistant de création de nouveaux fichiers modèles
 */
public class ProjectCreationPage extends WizardNewProjectCreationPage {

	/**
	 * Constructeur
	 * @param pageName Le nom de la page à construire
	 * @param selection La sélection courante dans le workbench
	 */
	public ProjectCreationPage(String pageName, IStructuredSelection selection) {
		super(pageName);
		setTitle(Messages.ProjectCreationPage_0);
		setDescription(Messages.ProjectCreationPage_1);
	}

	/**
	 * Permission de finir ?
	 * @return boolean (false)
	 */
	public final boolean finish() {
		return true;
	}

}
