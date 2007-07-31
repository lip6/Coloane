package fr.lip6.move.coloane.ui.wizards;


import java.util.ArrayList;
import java.util.Iterator;


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

import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.motor.formalism.Formalism;

/**
 * Premiere page de l'assistant qui propose une liste de formalisme * 
 */
public class SelectFormalismPage extends WizardPage {

	/* Valeurs par defaut */
	private Label label = null;
    private Table tableFormalism = null;

	/**
	 * Constructeur de la classe
	 */
	public SelectFormalismPage() {
		super("newmodel");
		setTitle("Select a formalism for your model");
		setDescription("Your model must implement one of the available models shown below :");	
	}

	/**
	 * Construit la page en lui ajoutant des controles
     * @param parent parent
	 */
	public void createControl(Composite parent) {
		Composite com = new Composite(parent, SWT.NONE);
		
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.heightHint = 100;
		gridData.verticalAlignment = GridData.FILL;
		
		// La boite de selection
		label = new Label(com, SWT.NONE);
		label.setText("Select a formalism for your model: ");
		
		tableFormalism = new Table(com, SWT.SINGLE | SWT.BORDER);
		tableFormalism.setLayoutData(gridData);
		tableFormalism.setHeaderVisible(false);
		tableFormalism.setLinesVisible(false);
		tableFormalism.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {}
			public void widgetSelected(SelectionEvent e) { updateStatus(); }
		});
		
		tableFormalism.removeAll();
		        
		// Recupere la liste des formalismes
		ArrayList formalismList = Coloane.getDefault().getMotor().getFormalismManager().getListOfFormalisms();
		Iterator iterator = formalismList.iterator();
        
        while (iterator.hasNext()) {
        	Formalism formalism = (Formalism) iterator.next();			// Parcours de la liste des formalismes
            TableItem item = new TableItem(tableFormalism, SWT.NULL);	// Insertion dans la table
            item.setText(formalism.getName().toUpperCase());			// Determine le nom affiche dans la table
            
            // Determine l'icone associe a l'item dans la table
            //item.setImage(ImageDescriptor.createFromFile(Coloane.class, formalism.getImageName()).createImage());
            // TODO Distinguer les icones des formalismes. (Dessiner les icones en question)
            item.setImage(ImageDescriptor.createFromFile(Coloane.class, "/icons/instance.gif").createImage());
        }
       				
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		
		com.setLayout(gridLayout);	
		setControl(com);
		
	}

	/**
     * Indique si on peut passer a la page suivante
     * La condition est simple : Un formalisme doit etre selectionne
     * @return booleen
	 */
	public boolean canFlipToNextPage() {
		return ((getErrorMessage() == null) && (tableFormalism.getSelectionCount() > 0));
	}
	
	/**
     * DŽbloque un verrou sur le bouton finish quand la condition est verifiee
     * @return booleen
	 */
	@Override
	public boolean isPageComplete() {
		return ((getErrorMessage() == null) && (tableFormalism.getSelectionCount() > 0));
	}

    /**
     * Mettre ˆ jour le formalisme choisi (indication au pere de l'assistant)
     */
	private void updateStatus() {
		String f = tableFormalism.getSelection()[0].getText();
		((NewModelWizard) getWizard()).setFormalismName(f);
		getWizard().getContainer().updateButtons();
	}

}
