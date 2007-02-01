package fr.lip6.move.coloane.ui.wizards;


import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import fr.lip6.move.coloane.main.Coloane;

/**
 * Creation d'un nouveau fichier modele
 * Ces fichiers peuvent �tre �dites avec l'�diteur
 */
public class NewModelWizard extends Wizard implements INewWizard {

	/** Formalism */
	private String formalismName;

	/** Les pages de l'assistant */
	/** Page 1 */
	private SelectFormalismPage page1;
    /** Page 2 */
	private ModelCreationPage page2;
	
	// Indication de fin de creation
    protected boolean creationFinished = false;
	
	

	/**
     * Ajouter les pages de l'assistant
	 */
	public void addPages() {
		// add pages to this wizard
		addPage(page1);
		addPage(page2);
	}

	/**
	 * Initialisation de l'assistant
	 * @param workbench Workbench
	 * @param selection Selection
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setDefaultPageImageDescriptor(ImageDescriptor.createFromFile(Coloane.class, "icons/poisson.jpg"));
		page1 = new SelectFormalismPage();
		page2 = new ModelCreationPage(workbench, selection);
		System.out.println("Assistant de creation de modeles");
	}

	/**
	 * Indique le moment ou l'assistant est fini.
	 * @return True si la page peut �tre finie
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
		System.out.println("Le formalisme choisi est : "+formalismName);
	}
}
