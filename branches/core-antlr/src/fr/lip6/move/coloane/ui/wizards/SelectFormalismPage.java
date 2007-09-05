package fr.lip6.move.coloane.ui.wizards;

import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.motor.formalism.Formalism;

import java.util.ArrayList;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * Premiere page de l'assistant qui propose une liste de formalismes *
 */
public class SelectFormalismPage extends WizardPage {

	/* Valeurs par defaut */
	private Label label = null;
	private Table tableFormalism = null;

	private static final int GRID_HEIGHT = 50;

	/**
	 * Constructeur de la classe
	 */
	public SelectFormalismPage() {
		super("newmodel"); //$NON-NLS-1$
		setTitle(Messages.SelectFormalismPage_0);
		setDescription(Messages.SelectFormalismPage_1);
	}

	/**
	 * Construit la page en lui ajoutant des controles
	 * @param parent parent
	 */
	public final void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);

		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.heightHint = GRID_HEIGHT;
		gridData.verticalAlignment = GridData.FILL;

		// La boite de selection
		label = new Label(composite, SWT.NONE);
		label.setText(Messages.SelectFormalismPage_2);

		tableFormalism = new Table(composite, SWT.SINGLE | SWT.BORDER);
		tableFormalism.setLayoutData(gridData);
		tableFormalism.setHeaderVisible(false);
		tableFormalism.setLinesVisible(false);
		tableFormalism.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) { return; }
			public void widgetSelected(SelectionEvent e) { updateStatus(); }
		});

		tableFormalism.removeAll();

		// Recupere la liste des formalismes
		ArrayList<Formalism> listOfFormalisms = Coloane.getDefault().getMotor().getFormalismManager().getListOfFormalisms();

		for (Formalism formalism : listOfFormalisms) {
			TableItem item = new TableItem(tableFormalism, SWT.NULL);	// Insertion dans la table
			item.setText(formalism.getName().toUpperCase());			// Determine le nom affiche dans la table
			// Determine l'icone associe a l'item dans la table
			item.setImage(ImageDescriptor.createFromFile(Coloane.class, formalism.getImageName()).createImage()); //$NON-NLS-1$
		}

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;

		composite.setLayout(gridLayout);
		setControl(composite);
	}

	/**
	 * Indique si on peut passer a la page suivante
	 * La condition est simple : Un formalisme doit etre selectionne
	 * @return booleen
	 */
	@Override
	public final boolean canFlipToNextPage() {
		return (getErrorMessage() == null) && (tableFormalism.getSelectionCount() > 0);
	}

	/**
	 * Debloque un verrou sur le bouton finish quand la condition est verifiee
	 * @return booleen
	 */
	@Override
	public final boolean isPageComplete() {
		return (getErrorMessage() == null) && (tableFormalism.getSelectionCount() > 0);
	}

	/**
	 * Mettre a jour le formalisme choisi (indication au pere de l'assistant)
	 */
	private void updateStatus() {
		String f = tableFormalism.getSelection()[0].getText();
		((NewModelWizard) getWizard()).setFormalismName(f);
		getWizard().getContainer().updateButtons();
	}

}
