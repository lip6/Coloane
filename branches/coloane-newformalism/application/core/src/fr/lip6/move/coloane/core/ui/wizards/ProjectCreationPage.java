package fr.lip6.move.coloane.core.ui.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

public class ProjectCreationPage extends WizardNewProjectCreationPage {

	public ProjectCreationPage(String pageName, IStructuredSelection selection) {
		super("newproject"); //$NON-NLS-1$
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
