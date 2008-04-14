package fr.lip6.move.coloane.core.ui.wizards;

import fr.lip6.move.coloane.core.main.Coloane;

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

public class NewProjectWizard extends Wizard implements INewWizard {

	private ProjectCreationPage projectCreationPage;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public final void addPages() {
		addPage(projectCreationPage);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public final boolean performFinish() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProject newProject = projectCreationPage.getProjectHandle();
		IProjectDescription description = workspace.newProjectDescription(newProject.getName());

		String[] natureIds = {Messages.NewProjectWizard_0};
		description.setNatureIds(natureIds);
		IPath oldPath = Platform.getLocation();
		IPath newPath = projectCreationPage.getLocationPath();

		if (!oldPath.equals(newPath)) {
			oldPath = newPath;
			description.setLocation(newPath);
		}

		try {
			if (!newProject.exists()) {
				newProject.create(description, null);
			}

			if (!newProject.isOpen()) {
				newProject.open(null);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		// Select and reveal the project in the workbench window
		BasicNewProjectResourceWizard.updatePerspective(null);
		BasicNewResourceWizard.selectAndReveal(newProject, Coloane.getDefault().getWorkbench().getActiveWorkbenchWindow());
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public final void init(IWorkbench workbench, IStructuredSelection selection) {
		setDefaultPageImageDescriptor(ImageDescriptor.createFromFile(Coloane.class, "/resources/icons/newproject_corner.png")); //$NON-NLS-1$
		setWindowTitle(Messages.NewProjectWizard_1);
		projectCreationPage = new ProjectCreationPage("newproject", selection); //$NON-NLS-1$
	}

}
