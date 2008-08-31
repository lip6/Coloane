package fr.lip6.move.coloane.core.ui.wizards.newmodel;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import java.util.List;

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
 * Première étape de l'assistant : Chois du formalisme pour le nouveau modèle
 */
public class SelectFormalismPage extends WizardPage {

	/** Hauteur de boite de sélection de formalisme */
	private static final int GRID_HEIGHT = 20;

	/* Valeurs par defaut */
	private Label label = null;
	private Table tableFormalism = null;

	/**
	 * Constructeur
	 */
	public SelectFormalismPage() {
		super("newmodel"); //$NON-NLS-1$
		setTitle(Messages.SelectFormalismPage_0);
		setDescription(Messages.SelectFormalismPage_1);
	}

	/** {@inheritDoc} */
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
		List<IFormalism> listOfFormalisms = FormalismManager.getInstance().getListOfFormalisms();

		for (IFormalism formalism : listOfFormalisms) {
			TableItem item = new TableItem(tableFormalism, SWT.NULL);	// Insertion dans la table
			item.setText(formalism.getName().toUpperCase()); // Determine le nom affiche dans la table
			// Determine l'icone associe a l'item dans la table
			item.setImage(ImageDescriptor.createFromFile(Coloane.class, formalism.getImageName()).createImage());
		}

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;

		composite.setLayout(gridLayout);
		setControl(composite);
	}

	/** {@inheritDoc} */
	@Override
	public final boolean canFlipToNextPage() {
		return (getErrorMessage() == null) && (tableFormalism.getSelectionCount() > 0);
	}

	/** {@inheritDoc} */
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
