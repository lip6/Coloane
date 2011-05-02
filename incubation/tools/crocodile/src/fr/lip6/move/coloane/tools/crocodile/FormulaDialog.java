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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author Maximilien Colange
 * 
 * A dialog class in order to interface Crocodile.
 * This class proposes either to generate the SRG, either to input a list of formulae, either to input a formulae file.
 */
public class FormulaDialog extends Dialog {

	static final int SRG_ID = 0;
	static final int FORM_ID = SRG_ID + 1;
	static final int FILE_ID = FORM_ID + 1;
	
	/**
	 * The main constructor
	 * Note that the dialog will have no visual representation (no widgets) until it is told to open.
	 * 
	 * @param parentShell the parent shell, or <code>null</code> to create a top-level shell
	 */
	protected FormulaDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * creates the buttons :
	 * <ul>
	 * <li>one for the simple generation of the SRG</li>
	 * <li>one in order to type in a formula</li>
	 * <li>one to specify a formulae file</li>
	 * </ul>
	 * 
	 * @param parent the parent Composite
	 */
	protected final void createButtonsForButtonBar(Composite parent) {
		createButton(parent, SRG_ID, "Simply generate the SRG", true);
		createButton(parent, FORM_ID, "Input formulae", false);
		createButton(parent, FILE_ID, "Check formulae in file", false);
    }

	/**
	 * {@inheritDoc}
	 */
	protected final void buttonPressed(int buttonId) {
		setReturnCode(buttonId);
		close();
	}
}
