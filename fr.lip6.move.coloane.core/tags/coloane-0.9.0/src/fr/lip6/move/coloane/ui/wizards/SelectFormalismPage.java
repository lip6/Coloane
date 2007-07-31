package fr.lip6.move.coloane.ui.wizards;


import java.util.ArrayList;
import java.util.Iterator;


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
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

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
		super("Step 1 - Choose the formalism");
		setTitle("Select a formalism for model");
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
		gridData.heightHint = 300;
		gridData.verticalAlignment = GridData.FILL;
		label = new Label(com, SWT.NONE);
		label.setText("Select Formalism: ");
		tableFormalism = new Table(com, SWT.SINGLE | SWT.BORDER);
		tableFormalism.setHeaderVisible(false);
		tableFormalism.setLayoutData(gridData);
		tableFormalism.setLinesVisible(false);
		tableFormalism.addSelectionListener(new SelectionListener() {

            /**
			 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetDefaultSelected(SelectionEvent e) {
							
			}

            /**
			 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected(SelectionEvent e) {
				updateStatus();
			}
			
		});
		
		tableFormalism.removeAll();
		        
		// Recupere la liste des formalismes
		ArrayList formalismList = Coloane.getDefault().getMotor().getFormalismManager().getListOfFormalisms();
		Iterator iterator = formalismList.iterator();
        
        while (iterator.hasNext()) {
            // Parcours de la liste des formalismes
        	Formalism formalism = (Formalism) iterator.next();
         
            //Insertion dans la table            
            TableItem item = new TableItem(tableFormalism, SWT.NULL);
            item.setText(formalism.getName().toUpperCase());
            item.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT)); 
        }
       				
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		com.setLayout(gridLayout);
	
		setControl(com);
		
	}

	/**
     * Indique si on peut passer a la page suivante
     * @return booleen
	 */
	public boolean canFlipToNextPage() {
		return ((getErrorMessage() == null) && (tableFormalism.getSelectionCount() > 0));
	}

    /**
     * Mettre ˆ jour le formalism choisi (indication au pere de l'assistant)
     */
	private void updateStatus() {
		String f = tableFormalism.getSelection()[0].getText();
		((NewModelWizard) getWizard()).setFormalismName(f);
		getWizard().getContainer().updateButtons();
	}

}
