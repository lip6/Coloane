package fr.lip6.move.coloane.core.ui.wizards;

import fr.lip6.move.coloane.core.main.Coloane;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
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


/**
 * Assistant pour la création de nouveaux fichiers modèle
 */
public class NewProjectWizard extends Wizard implements INewWizard {
	/** L'unique page constituante de cet assistant */
	private ProjectCreationPage projectCreationPage;

	/** {@inheritDoc} */
	@Override
	public final void addPages() {
		addPage(projectCreationPage);
	}

	/** {@inheritDoc} */
	@Override
	public final boolean performFinish() {
		// Recuperation des informations sur le nouveau projet a creer
		IProject newProject = projectCreationPage.getProjectHandle();

		// On recupere le template de description pour les nouveaux projects
		IProjectDescription basicDescription = ResourcesPlugin.getWorkspace().newProjectDescription(newProject.getName());

		String[] natures = basicDescription.getNatureIds();
		String[] newNatures = new String[natures.length + 1];
		System.arraycopy(natures, 0, newNatures, 0, natures.length);
		newNatures[natures.length] = "fr.lip6.move.coloane.core.modelingproject"; //$NON-NLS-1$
		basicDescription.setNatureIds(newNatures);

		IPath platformPath = Platform.getLocation();
		IPath askedPath = projectCreationPage.getLocationPath();

		// On doit prendre en compte la demande de l'utilisateur concernant le chemin de sauvegarde
		if (!platformPath.equals(askedPath)) {
			platformPath = askedPath;
			basicDescription.setLocation(askedPath);
		}

		try {
			// On verifie que le projet n'existe pas deja
			if (!newProject.exists()) {
				newProject.create(basicDescription, null);
			}

			// Si le projet n'est pas ouvert... on l'ouvre !
			if (!newProject.isOpen()) {
				newProject.open(null);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}

		// Select and reveal the project in the workbench window
		BasicNewProjectResourceWizard.updatePerspective(null);
		BasicNewResourceWizard.selectAndReveal(newProject, Coloane.getInstance().getWorkbench().getActiveWorkbenchWindow());
		return true;
	}

	/** {@inheritDoc} */
	public final void init(IWorkbench workbench, IStructuredSelection selection) {
		setDefaultPageImageDescriptor(ImageDescriptor.createFromFile(Coloane.class, "/resources/icons/newproject_corner.png")); //$NON-NLS-1$
		setWindowTitle(Messages.NewProjectWizard_1);
		projectCreationPage = new ProjectCreationPage("newproject", selection); //$NON-NLS-1$
	}

}
