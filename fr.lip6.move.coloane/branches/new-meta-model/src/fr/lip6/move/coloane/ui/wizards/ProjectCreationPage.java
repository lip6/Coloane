package fr.lip6.move.coloane.ui.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

public class ProjectCreationPage extends WizardNewProjectCreationPage {
	
	public ProjectCreationPage(String pageName, IStructuredSelection selection) {
		super("New Model Project");
		// TODO Auto-generated constructor stub
	}

	public boolean finish() {
		return true;
	}

}
