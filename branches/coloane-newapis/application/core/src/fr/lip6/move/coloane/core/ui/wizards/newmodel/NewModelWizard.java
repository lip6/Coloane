package fr.lip6.move.coloane.core.ui.wizards.newmodel;

import fr.lip6.move.coloane.core.main.Coloane;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * Assistant pour la création d'un nouveau modèle.<br>
 * Cet assistant comporte 2 étapes :
 * <ul>
 * 	<li>Sélection du formalisme pour le nouveau modèle</li>
 * 	<li>Choix du nom de fichier et du projet de rattachement</li>
 * </ul>
 */
public class NewModelWizard extends Wizard implements INewWizard {

	/** Formalisme */
	private String formalismName;

	/** Les pages de l'assistant */
	private SelectFormalismPage selectFormalism;
	private ModelCreationPage createModel;


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public final void addPages() {
		addPage(selectFormalism);
		addPage(createModel);
	}

	/**
	 * Initialisation de l'assistant
	 * @param workbench Workbench
	 * @param selection Selection
	 */
	public final void init(IWorkbench workbench, IStructuredSelection selection) {
		setDefaultPageImageDescriptor(ImageDescriptor.createFromFile(Coloane.class, "/resources/icons/select_form.png")); //$NON-NLS-1$
		setWindowTitle(Messages.NewModelWizard_0);
		selectFormalism = new SelectFormalismPage();
		createModel = new ModelCreationPage(workbench, selection);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public final boolean performFinish() {
		return createModel.finish();
	}

	/**
	 * Obtenir le nom du formalisme
	 * @return retourne le nom du formalisme
	 */
	public final String getFormalismName() {
		return formalismName;
	}

	/**
	 * Donner une valeur au nom du formalisme
	 * @param formalismName la valeur
	 */
	public final void setFormalismName(String name) {
		this.formalismName = name;
	}
}
