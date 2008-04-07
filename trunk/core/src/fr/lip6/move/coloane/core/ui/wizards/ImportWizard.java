/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package fr.lip6.move.coloane.core.ui.wizards;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

import fr.lip6.move.coloane.core.main.Coloane;

public class ImportWizard extends Wizard implements IImportWizard, IExecutableExtension {

	/** L'identifiant du wizard qui appelle l'action */
	private String idWizard;

	/** La seule et unique page du wizard */
	private ImportWizardPage mainPage;

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() {
		try {
			new ProgressMonitorDialog(getShell()).run(false,false,new ImportOperation(mainPage, this.idWizard));
		} catch (Exception e) {
			Coloane.showErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		return true;
	}
	

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle("File Import Wizard"); //$NON-NLS-1$
		setNeedsProgressMonitor(true);
		mainPage = new ImportWizardPage("Import File",selection); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	public void addPages() {
		super.addPages(); 
		addPage(mainPage);        
	}

	/**
	 * Indique le format d'export utilise dans cette instance d'assistant
	 * @param exportFormat Le format a utiliser pour l'export
	 */
	protected void setImportFormat(String idWizard) {
		Coloane.getLogger().finer("Wizard selectionne : "+idWizard); //$NON-NLS-1$
		this.idWizard = idWizard;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement, java.lang.String, java.lang.Object)
	 */
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		// Recuperation de l'identitifant de l'appelant permettant ansi de determiner le format d'export
		this.setImportFormat(config.getAttribute("id")); //$NON-NLS-1$
	}

}
