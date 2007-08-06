package fr.lip6.move.coloane.ui.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

import fr.lip6.move.coloane.main.Coloane;

public class ProjectCreationPage extends WizardNewProjectCreationPage {

	public ProjectCreationPage(String pageName, IStructuredSelection selection) {
		super("newproject"); //$NON-NLS-1$
		setTitle(Coloane.traduction.getString("ui.wizards.ProjectCreationPage.1")); //$NON-NLS-1$
		setDescription(Coloane.traduction.getString("ui.wizards.ProjectCreationPage.2")); //$NON-NLS-1$
	}

	public boolean finish() {
		return true;
	}

}
