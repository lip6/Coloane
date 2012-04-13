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
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.core.ui.wizards.importmodel;

import fr.lip6.move.coloane.core.extensions.ImportFromExtension;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.interfaces.extensions.IImportFrom;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;

/**
 * Wizard page for import process.<br>
 * This page is composed of:
 * <ul>
 * 	<li>A list of target formalisms</li>
 * 	<li>A list of target projects</li>
 * 	<li>A field to set the model name</li>
 * </ul>
 *
 * @author Jean-Baptiste Voron
 */
public class ImportWizardPage extends WizardNewFileCreationPage {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** File selector */
	private FileFieldEditor fileSelect;

	/** Formalisms selector */
	private Combo formSelect;

	/** Workbench */
	private final IWorkbench workbench;

	/** The import worker */
	private IImportFrom worker;

	/** The import type */
	private String importType;

	/**
	 * Constructor
	 * @param workbench The current workbench
	 * @param selection The current selection
	 * @param worker The import worker
	 * @param importType The kind of import asked by the user.
	 */
	public ImportWizardPage(IWorkbench workbench, IStructuredSelection selection, IImportFrom worker, String importType) {
		super(Messages.ImportWizardPage_12, selection);
		setTitle(Messages.ImportWizardPage_0);
		setDescription(Messages.ImportWizardPage_1);
		setFileExtension(Coloane.getParam("MODEL_EXTENSION")); //$NON-NLS-1$

		this.workbench = workbench;
		this.worker = worker;
		this.importType = importType;
	}

	/** {@inheritDoc} */
	@Override
	public final void createControl(Composite parent) {
		super.createControl(parent);
	}

	/** {@inheritDoc} */
	@Override
	protected final void createAdvancedControls(Composite parent) {
		super.createAdvancedControls(parent);

		Composite fileSelectionArea = new Composite(parent, SWT.NONE);
		Composite formalismSelectionArea = new Composite(parent, SWT.NONE);
		GridData fileSelectionData = new GridData(GridData.GRAB_HORIZONTAL | GridData.FILL_HORIZONTAL);
		fileSelectionArea.setLayoutData(fileSelectionData);

		// Select the file
		GridLayout fileSelectionLayout = new GridLayout();
		fileSelectionLayout.numColumns = 3;
		fileSelectionLayout.makeColumnsEqualWidth = false;
		fileSelectionLayout.marginWidth = 0;
		fileSelectionLayout.marginHeight = 0;
		fileSelectionArea.setLayout(fileSelectionLayout);
		formalismSelectionArea.setLayout(fileSelectionLayout);

		// Select the formalism
		Label formLabel = new Label(formalismSelectionArea, SWT.NONE);
		formLabel.setText(Messages.ImportWizardPage_3);
		formSelect = new Combo(formalismSelectionArea, SWT.BORDER | SWT.READ_ONLY);
		formSelect.addListener(SWT.Modify, this);

		// Fetch available formalisms
		List<IFormalism> listOfFormalisms = ImportFromExtension.getFormalisms(importType);
		LOGGER.fine("Managed formalisms for the import plugin \"" + importType + "\": " + listOfFormalisms); //$NON-NLS-1$ //$NON-NLS-2$
		for (IFormalism formalism : listOfFormalisms) {
			formSelect.add(formalism.getName());
		}

		String[] extensions = new String[] {"*.*"}; //$NON-NLS-1$
		fileSelect = new FileFieldEditor("fileSelect", "Select File: ", fileSelectionArea); //$NON-NLS-1$ //$NON-NLS-2$
		fileSelect.getTextControl(fileSelectionArea).addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				IPath path = new Path(ImportWizardPage.this.fileSelect.getStringValue());
				setFileName(path.removeFileExtension().lastSegment());
			}
		});
		fileSelect.setFileExtensions(extensions);
		fileSelectionArea.moveAbove(null);
		formalismSelectionArea.moveAbove(null);
	}

	/** {@inheritDoc} */
	@Override
	protected final InputStream getInitialContents() {
		try {
			return new FileInputStream(new File(fileSelect.getStringValue()));
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	/**
	 * What to do when finish button is pressed
	 * @return <code>true</code> if everithing went fine
	 * @see NewModelWizard#performFinish()
	 */
	public final boolean finish() {
		if (worker == null) {
			setErrorMessage(Messages.ImportWizard_1);
			return false;
		}

		LOGGER.fine("Importing the file..."); //$NON-NLS-1$

		// Import the model
		String path = fileSelect.getStringValue();

		// Try to create a new file to store the new model
		LOGGER.finer("Create a new file for the result"); //$NON-NLS-1$
		final IFile newFile = createNewFile();

		// Check that the file has been created correctly
		if (newFile == null) {
			setErrorMessage(Messages.ImportWizardPage_14);
			return false;
		}

		// Fetch available formalisms
		IFormalism inputFormalism = null;
		List<IFormalism> listOfFormalisms = ImportFromExtension.getFormalisms(importType);
		for (IFormalism formalism : listOfFormalisms) {
			if (formalism.getName().equals(formSelect.getText())) {
				inputFormalism = formalism;
				break;
			}
		}

		// Use a job to import the new model
		Job job = new ImportJob("Import " + path, //$NON-NLS-1$
						worker,
						inputFormalism,
						fileSelect.getStringValue(),
						newFile);

		job.setPriority(Job.LONG);
		job.setRule(newFile);
		job.setUser(true);
		job.schedule();

		// Open the new model
		job = new Job("Open editor") { //$NON-NLS-1$
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
						if (newFile != null && page != null) {
							try {
								IDE.openEditor(page, newFile, true);
							} catch (CoreException ce) {
								LOGGER.warning(ce.getMessage());
							}
						}
					}
				});
				return Status.OK_STATUS;
			}
		};
		job.setPriority(Job.LONG);
		job.setRule(newFile);
		job.setSystem(true);
		job.schedule();
		return true;
	}

	/** {@inheritDoc} */
	@Override
	protected final String getNewFileLabel() {
		return "New File Name:"; //$NON-NLS-1$
	}

	/** {@inheritDoc} */
	@Override
	protected final IStatus validateLinkedResource() {
		return new Status(IStatus.OK, "fr.lip6.move.coloane.core", IStatus.OK, "", null); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isPageComplete() {
		if (fileSelect.getStringValue().equals("")) { //$NON-NLS-1$
			setErrorMessage(Messages.ImportWizardPage_7);
			return false;
		}

		if (formSelect.getText().equals("")) { //$NON-NLS-1$
			setErrorMessage(Messages.ImportWizardPage_9);
			return false;
		}

		if (getFileName().equals("")) { //$NON-NLS-1$
			setErrorMessage(Messages.ImportWizardPage_11);
			return false;
		}

		if (getContainerFullPath() == null) {
			setErrorMessage(Messages.ImportWizardPage_13);
			return false;
		}

		return super.isPageComplete();
	}
}
