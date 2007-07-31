package fr.lip6.move.coloane.ui.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

import fr.lip6.move.coloane.main.Coloane;

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
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProject newProject = projectCreationPage.getProjectHandle();
		IProjectDescription description = workspace.newProjectDescription(newProject.getName());

		String[] natureIds = { "Model project" };
		description.setNatureIds(natureIds);
		IPath oldPath = Platform.getLocation();
		IPath newPath = projectCreationPage.getLocationPath();
		
		if (!oldPath.equals(newPath)) {
			oldPath = newPath;
			description.setLocation(newPath);
		}

		try {
			if (!newProject.exists())
				newProject.create(description, null);
			
			if (!newProject.isOpen())
				newProject.open(null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		// Select and reveal the project in the workbench window
		BasicNewProjectResourceWizard.updatePerspective(null);
		BasicNewResourceWizard.selectAndReveal(newProject,Coloane.getDefault().getWorkbench().getActiveWorkbenchWindow());
		return true;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setDefaultPageImageDescriptor(ImageDescriptor.createFromFile(Coloane.class, "/icons/coin_new_project.png"));
		setWindowTitle("Create a new modeling project...");
		projectCreationPage = new ProjectCreationPage("newproject", selection);
	}

}
