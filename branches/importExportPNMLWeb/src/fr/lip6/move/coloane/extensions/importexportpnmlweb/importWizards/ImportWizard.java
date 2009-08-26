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
package fr.lip6.move.coloane.extensions.importexportpnmlweb.importWizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

public class ImportWizard extends Wizard implements IImportWizard {
	
	ImportWizardPage mainPage;
	ModelsDescriptorPage modelsDescriptorPage;

	public ImportWizard() {
		super();
	}

	
	public boolean canFinish() {
		if (this.getContainer().getCurrentPage() == modelsDescriptorPage) 
			return true;
		//System.out.println("yes !");
        return false;		
	}
	
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() {
		System.out.println("yes!");
		return true;
	}
	 
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle("Pnml Import Wizard"); //NON-NLS-1
		mainPage = new ImportWizardPage("Import Pnml"); //NON-NLS-1
	}
	
	/* (non-Javadoc)
     * @see org.eclipse.jface.wizard.IWizard#addPages()
     */
    public void addPages() {
        addPage(mainPage);  
        modelsDescriptorPage = new ModelsDescriptorPage("Models Descriptor");
        addPage(modelsDescriptorPage);
    }

}
