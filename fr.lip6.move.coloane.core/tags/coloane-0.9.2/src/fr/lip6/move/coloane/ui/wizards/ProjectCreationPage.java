package fr.lip6.move.coloane.ui.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

public class ProjectCreationPage extends WizardNewProjectCreationPage {
	
	public ProjectCreationPage(String pageName, IStructuredSelection selection) {
		super("newproject");
		setTitle("New modeling project");
		setDescription("Create a modeling project to manage all your model files.");
	}

	public boolean finish() {
		return true;
	}

}
