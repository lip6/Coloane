package fr.lip6.move.coloane.ui.wizards;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import fr.lip6.move.coloane.main.Coloane;

/**
 * Creation d'un nouveau fichier modele
 */
public class NewModelWizard extends Wizard implements INewWizard {

	/** Formalism */
	private String formalismName;

	/** Les pages de l'assistant */
	private SelectFormalismPage page1;
    private ModelCreationPage page2;
	
	// Indication de fin de creation
    protected boolean creationFinished = false;
	
	
	/**
     * Ajouter les pages de l'assistant
	 */
	public void addPages() {
		addPage(page1);
		addPage(page2);
	}

	/**
	 * Initialisation de l'assistant
	 * @param workbench Workbench
	 * @param selection Selection
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setDefaultPageImageDescriptor(ImageDescriptor.createFromFile(Coloane.class, "/icons/select_form.png")); //$NON-NLS-1$
		setWindowTitle(Coloane.traduction.getString("ui.wizards.NewModelWizard.1")); //$NON-NLS-1$
		page1 = new SelectFormalismPage();
		page2 = new ModelCreationPage(workbench, selection);
	}

	/**
	 * Indique l'action a entreprendre lorsque le wizard est fini
	 * @return boolean
	 */
	public boolean performFinish() {
		return page2.finish();
	}

    /**
     * Obtenir le nom du formalisme
	 * @return retourne le nom du formalisme
	 */
	public String getFormalismName() {
		return formalismName;
	}

    /**
     * Donner une valeur au nom du formalisme
	 * @param formalismName la valeur
	 */
	public void setFormalismName(String formalismName) {
		this.formalismName = formalismName;
		System.out.println(Coloane.traduction.getString("ui.wizards.NewModelWizard.2")+formalismName); //$NON-NLS-1$
	}
}
