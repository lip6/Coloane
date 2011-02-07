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
package fr.lip6.move.coloane.core.ui.wizards.newmodel;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.ExampleExtension;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.files.ModelWriter;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;

/**
 * Second page of the new model wizard.<br>
 * This page asks the user:
 * <ul>
 * 	<li>to choose a project to store the file into</li>
 * 	<li>to use an existing example for this formalism</li>
 * </ul>
 */
public class ModelCreationPage extends WizardNewFileCreationPage {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** String that designates the default example (often empty) */
	private static final String DEFAULT_MODEL = Messages.ModelCreationPage_4;

	/** The current workbench */
	private final IWorkbench workbench;

	/** The project we want to add the new model to */
	private IProject currentProject;

	/** The list of available example */
	private Combo patternsList = null;

	/** The description of the selected example */
	private Text patternDescription = null;

	/** List of available example models */
	private Map<String, String> patternContributions = new HashMap<String, String>();

	/** Name of the new model */
	private String modelSelected = DEFAULT_MODEL;

	/** The formalism used for this new model */
	private IFormalism formalism;

	/**
	 * Constructor
	 * @param workbench The current workbench
	 * @param selection The current selection
	 */
	public ModelCreationPage(IWorkbench workbench, IStructuredSelection selection) {
		super("modelcreationpage", selection); //$NON-NLS-1$
		this.workbench = workbench;
		this.currentProject = (IProject) selection.getFirstElement();
		setTitle(Messages.ModelCreationPage_0);
		setDescription(Messages.ModelCreationPage_1);
		setFileExtension(Coloane.getParam("MODEL_EXTENSION")); //$NON-NLS-1$

		// IF the user has already selected a project, no need to ask him/her again
		if (this.currentProject != null) {
			setFileName(this.computeDefaultModelName());
		}
	}

	/**
	 * Build the page with all the graphical components
	 * @param parent The main container
	 */
	@Override
	public final void createControl(Composite parent) {
		super.createControl(parent);

		Composite composite = (Composite) getControl();

		// Example part
		Group group = new Group(composite, SWT.NONE);
		group.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		group.setLayoutData(gd);
		group.setText(Messages.ModelCreationPage_6);

		Label lblList = new Label(group, SWT.NONE);
		lblList.setText(Messages.ModelCreationPage_7);

		gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.grabExcessHorizontalSpace = true;

		patternsList = new Combo(group, SWT.BORDER | SWT.READ_ONLY);
		patternsList.setLayoutData(gd);
		patternsList.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String description = Messages.ModelCreationPage_8;
				modelSelected = patternsList.getText();
				if (patternContributions.containsKey(modelSelected)) {
					description = patternContributions.get(modelSelected);
				}
				patternDescription.setText(description);
				patternDescription.getParent().layout();
			}
		});

		gd = new GridData();
		gd.horizontalSpan = 2;
		Label lblDescription = new Label(group, SWT.NONE);
		lblDescription.setText(Messages.ModelCreationPage_9);
		lblDescription.setLayoutData(gd);

		gd = new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.verticalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.heightHint = 50;
		patternDescription = new Text(group, SWT.READ_ONLY | SWT.MULTI);
		patternDescription.setLayoutData(gd);

		new Label(composite, SWT.NONE);
		setPageComplete(validatePage());
	}


	/** {@inheritDoc} */
	@Override
	public final void setVisible(boolean visible) {
		super.setVisible(visible);

		if (visible) {
			// Fetch the example list
			this.formalism = ((NewModelWizard) getWizard()).getFormalism();
			this.patternContributions = ExampleExtension.getModelsName(this.formalism);

			// Add the default model (empty)
			this.patternsList.add(DEFAULT_MODEL);
			this.patternsList.select(0);

			// Fill the list
			for (String name : this.patternContributions.keySet()) {
				patternsList.add(name);
			}

			// Refresh the display
			this.patternsList.getParent().layout();
		} else {
			patternsList.removeAll();
		}
	}

	/**
	 * What to do when the finish button is pressed ?
	 * @return <code>true</code> if everything went fine
	 * @see NewModelWizard#performFinish()
	 */
	public final boolean finish() {
		// Try to create a new file
		IFile newFile = createNewFile();
		if (newFile == null) {
			setErrorMessage(Messages.ModelCreationPage_3);
			return false;
		}

		// Open the new file
		IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
		if (newFile != null && page != null) {
			try {
				IDE.openEditor(page, newFile, true);
			} catch (CoreException ce) {
				LOGGER.warning(ce.getMessage());
				Coloane.showErrorMsg(ce.getMessage());
				return false;
			}
		}
		return true;
	}

	/**
	 * Fill the new file with default contents or contributed contents (in case of example)
	 * @return InputStream
	 */
	@Override
	protected final InputStream getInitialContents() {
		InputStream inputS;

		try {
			// Choosen formalism
			IFormalism formalism = ((NewModelWizard) getWizard()).getFormalism();
			LOGGER.fine("Choosen formalism: " + formalism.getName()); //$NON-NLS-1$

			String xmlString;
			if (DEFAULT_MODEL.equalsIgnoreCase(modelSelected)) {
				xmlString = ModelWriter.createDefault(formalism);
				LOGGER.fine("Create an empty model"); //$NON-NLS-1$
			} else {
				xmlString = ModelWriter.translateToXML(ExampleExtension.getModel(patternsList.getText(), formalism));
				LOGGER.fine("Create a model based on the example:" + patternsList.getText()); //$NON-NLS-1$
			}

			// Build the input stream from the string
			inputS = new ByteArrayInputStream(xmlString.getBytes());
		} catch (CoreException e) {
			LOGGER.warning("Unable to get the example source"); //$NON-NLS-1$
			LOGGER.warning(e.getMessage());
			Coloane.showErrorMsg(e.getMessage());
			return null;
		} catch (ColoaneException pluginException) {
			LOGGER.warning("Plugin failure"); //$NON-NLS-1$
			LOGGER.warning(pluginException.getMessage());
			Coloane.showErrorMsg(pluginException.getMessage());
			return null;
		}
		return inputS;
	}

	/**
	 * Compute a name for the new file.<br>
	 * This method is called only when the user press the finish button on the first page.<br>
	 * @return The first available name for the file
	 */
	private String computeDefaultModelName() {
		int counter = 0;
		String computedName = Coloane.getParam("WIZARD_FILENAME_BASE") + "_" + counter;  //$NON-NLS-1$ //$NON-NLS-2$
		while (this.checkName(computedName + "." + getFileExtension())) { //$NON-NLS-1$
			counter++;
			computedName = Coloane.getParam("WIZARD_FILENAME_BASE") + "_" + counter;  //$NON-NLS-1$ //$NON-NLS-2$
		}
		return computedName;
	}

	/**
	 * Check whether a filename already exist in the current project
	 * @param modelName The model name (without any extension)
	 * @return <code>true</code> if everything went fine; <code>false</code> otherwise
	 */
	private boolean checkName(String modelName) {
		return this.currentProject.getFile(modelName).exists();
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isPageComplete() {
		// The project must not be null
		if (this.currentProject != null) {
			return true;
		}

		// The new model has to been attached to a project
		if (getContainerFullPath() == null) {
			setErrorMessage(Messages.ModelCreationPage_11);
			return false;
		}

		// The file name must not be empty
		if ((getFileName() == null) || (getFileName().equals(""))) { //$NON-NLS-1$
			setErrorMessage(Messages.ModelCreationPage_13);
			return false;
		}

		// Other checks
		return super.isPageComplete();
	}
}
