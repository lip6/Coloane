package fr.lip6.move.coloane.ui.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class NewProjectWizard extends Wizard implements INewWizard {

	ProjectCreationPage projectCreationPage;
	protected boolean creationFinished = false;
	
	public NewProjectWizard() {
		// TODO Auto-generated constructor stub
	}

	
	public void addPages() {
		addPage(projectCreationPage);
	}
	
	@Override
	public boolean performFinish() {
		return projectCreationPage.finish();
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		projectCreationPage = new ProjectCreationPage("Page name", selection);
	}

}
