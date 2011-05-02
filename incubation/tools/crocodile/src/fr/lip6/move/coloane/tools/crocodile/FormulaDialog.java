/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *   Yann THIERRY-MIEG (LIP6)
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.tools.crocodile;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author colange
 * A dialog class in order to interface Crocodile.
 * This class proposes either to generate the SRG, either to input a list of formulae, either to input a formulae file.
 */
public class FormulaDialog extends Dialog {
	
	private int okID = 0;
	private int cancelID = 1;
	private int srgID = 2;
	private int formID = 3;
	private int fileID = 4;
	
	
	
	/* a button to generate the state space */
	private Button srgGeneration;
	/* a button to check given formulae */
	private Button checkFormulae;
	/* a button to check given a formulae file */
	private Button checkFormulaeFile;
	
	/* a OK button */
	private Button okButton;
	
	/**
	 * The main constructor
	 * Note that the dialog will have no visual representation (no widgets) until it is told to open.
	 * @param parentShell the parent shell, or <code>null</code> to create a top-level shell
	 */
	protected FormulaDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * a function to create a Button and set it in the layout
	 * 
	 * @param parent the parent of the button
	 * @param id the id of the button
	 * @param label the label for the button
	 * @param defaultButton is it the default button ?
	 * @param type SWT type
	 * @return the created button
	 */
	protected final Button createButton(Composite parent, int id, String label,
			boolean defaultButton, int type) {
		// increment the number of columns in the button bar
	//	((GridLayout) parent.getLayout()).numColumns++;
		Button button = new Button(parent, type);
		button.setText(label);
		button.setFont(JFaceResources.getDialogFont());
		button.setData(new Integer(id));
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				buttonPressed(((Integer) event.widget.getData()).intValue());
			}
		});
		if (defaultButton) {
			Shell shell = parent.getShell();
			if (shell != null) {
				shell.setDefaultButton(button);
			}
		}
		//buttons.put(new Integer(id), button);
		setButtonLayoutData(button);
		return button;
	}
	
	/**
	 * {@inheritDoc}
	 */
	protected final void createButtonsForButtonBar(Composite parent) {
		srgGeneration = createButton(parent, srgID, "Simply generate the SRG", false, SWT.RADIO);
		checkFormulae = createButton(parent, formID, "Check the formulae", false, SWT.RADIO);
		checkFormulaeFile = createButton(parent, fileID, "Check formulae in file", false, SWT.RADIO);

		okButton = createButton(parent, okID, IDialogConstants.OK_LABEL, true);
        createButton(parent, cancelID, IDialogConstants.CANCEL_LABEL, false);
    }
}
