package fr.lip6.move.coloane.core.ui.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

/**
 * Wizard page for new project creation
 * 
 * @see NewProjectWizard
 * @author Jean-Baptiste Voron
 */
public class ProjectCreationPage extends WizardNewProjectCreationPage {

	/**
	 * Constructor
	 * @param pageName The page name
	 * @param selection The current selection <i>(not used here)</i>
	 */
	public ProjectCreationPage(String pageName, IStructuredSelection selection) {
		super(pageName);
		setTitle(Messages.ProjectCreationPage_0);
		setDescription(Messages.ProjectCreationPage_1);
	}

	/**
	 * Can finish ?
	 * @return <code>true</code>
	 */
	public final boolean finish() {
		// Always return true.
		// Default values will be used if necessary
		return true;
	}
}
