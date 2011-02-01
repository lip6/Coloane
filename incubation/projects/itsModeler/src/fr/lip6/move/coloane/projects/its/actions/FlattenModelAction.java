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
package fr.lip6.move.coloane.projects.its.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;

import fr.lip6.move.coloane.projects.its.TypeDeclaration;
import fr.lip6.move.coloane.projects.its.plugin.editors.MultiPageEditor;
import fr.lip6.move.coloane.projects.its.plugin.wizards.FlattenNewModelWizard;

/**
 * Class associated with buttons to handle model flattening.
 */
public class FlattenModelAction extends Action 
{

	private TypeDeclaration td;
	private MultiPageEditor editor;

	public FlattenModelAction(MultiPageEditor editor) {
		setEditor(editor);
	}

	/**
	 * update current editor
	 * @param editor parent editor
	 */
	public void setEditor(MultiPageEditor editor) {
		this.editor = editor;		
	}


	
	public void setTypeDeclaration(TypeDeclaration td) {
		this.td = td;
	}
	
	/**
	 * @see IActionDelegate#run(IAction)
	 * Instantiates the wizard and opens it in the wizard container
	 */
	public void run() {
		
		// Instantiates and initializes the wizard
		FlattenNewModelWizard wizard = new FlattenNewModelWizard(td);
		
		wizard.init(editor.getSite().getWorkbenchWindow().getWorkbench(), 
			(IStructuredSelection)null);
			
		// Instantiates the wizard container with the wizard and opens it
		WizardDialog dialog = new WizardDialog( editor.getSite().getShell(), wizard);
		dialog.create();
		dialog.open();
	}



}
