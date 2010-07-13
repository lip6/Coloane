package fr.lip6.move.coloane.core.ui.wizards.newmodel;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * Wizard dedicated to new model creation.<br>
 * This wizard is made of two pages:
 * <ul>
 * 	<li>Choose a formalism among available ones</li>
 * 	<li>Choose a filename and a project</li>
 * </ul>
 * 
 * @author Jean-Baptiste Voron
 */
public class NewModelWizard extends Wizard implements INewWizard {

	/** The formalism */
	private IFormalism formalism;

	/** Wizard pages */
	private SelectFormalismPage selectFormalism;
	private ModelCreationPage createModel;


	/** {@inheritDoc} */
	@Override
	public final void addPages() {
		addPage(selectFormalism);
		addPage(createModel);
	}

	/**
	 * Init the wizard
	 * @param workbench The workbench
	 * @param selection The current selection
	 */
	public final void init(IWorkbench workbench, IStructuredSelection selection) {
		setDefaultPageImageDescriptor(ImageDescriptor.createFromFile(Coloane.class, "/resources/icons/select_form.png")); //$NON-NLS-1$
		setWindowTitle(Messages.NewModelWizard_0);
		selectFormalism = new SelectFormalismPage();
		createModel = new ModelCreationPage(workbench, selection);
	}

	/** {@inheritDoc} */
	@Override
	public final boolean performFinish() {
		return createModel.finish();
	}

	/**
	 * Get the selected formalism
	 * @return retourne le nom du formalisme
	 */
	public final IFormalism getFormalism() {
		return this.formalism;
	}

	/**
	 * Set the formalism that will be used in this process
	 * @param formalism The formalism name
	 */
	public final void setFormalism(IFormalism formalism) {
		this.formalism = formalism;
	}
}
