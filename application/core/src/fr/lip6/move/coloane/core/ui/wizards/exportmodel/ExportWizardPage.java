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
package fr.lip6.move.coloane.core.ui.wizards.exportmodel;

import java.io.File;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.dialogs.WizardExportResourcesPage;

/**
 * Export Wizard Page.<br>
 * In this page, you will find :
 * <ul>
 * 	<li>An area to select files to export</li>
 * 	<li>A field to set the final destination</li>
 * 	<li>An area with options that concern the export process</li>
 * </ul>
 *
 * @author Jean-Baptiste Voron
 */
public class ExportWizardPage extends WizardExportResourcesPage {
	private Button destinationBrowseButton;
	private Text destinationNameField;

	/**
	 * Set up the page
	 * @param pageName The page name
	 * @param selection The current selection
	 */
	protected ExportWizardPage(String pageName, IStructuredSelection selection) {
		// Use a standard template
		super("FileSystemExportPage", selection); //$NON-NLS-1$
		setTitle(Messages.ExportWizardPage_1);
		setDescription(Messages.ExportWizardPage_2);
	}

	/** {@inheritDoc} */
	@Override
	protected final void createDestinationGroup(Composite parent) {
		Font font = parent.getFont();
		// destination specification group
		Composite destinationSelectionGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		destinationSelectionGroup.setLayout(layout);
		destinationSelectionGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL));
		destinationSelectionGroup.setFont(font);

		Label destinationLabel = new Label(destinationSelectionGroup, SWT.NONE);
		destinationLabel.setText(Messages.ExportWizardPage_3);
		destinationLabel.setFont(font);

		// destination name entry field
		destinationNameField = new Text(destinationSelectionGroup, SWT.SINGLE | SWT.BORDER);
		destinationNameField.addListener(SWT.Modify, this);
		destinationNameField.addListener(SWT.Selection, this);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		data.widthHint = SIZING_TEXT_FIELD_WIDTH;
		destinationNameField.setLayoutData(data);
		destinationNameField.setFont(font);
		destinationNameField.setText(System.getProperty("user.home", "")); //$NON-NLS-1$ //$NON-NLS-2$

		// destination browse button
		destinationBrowseButton = new Button(destinationSelectionGroup, SWT.PUSH);
		destinationBrowseButton.setText(Messages.ExportWizardPage_4);
		destinationBrowseButton.addListener(SWT.Selection, this);
		setButtonLayoutData(destinationBrowseButton);

	}

	/** {@inheritDoc} */
	public final void handleEvent(Event e) {
		Widget source = e.widget;
		// Browse button click !
		if (source == destinationBrowseButton) {
			handleDestinationBrowseButtonPressed();
		}
		updatePageCompletion();
	}

	/**
	 * Ask the user for the destination
	 */
	protected final void handleDestinationBrowseButtonPressed() {
		DirectoryDialog dialog = new DirectoryDialog(getContainer().getShell(), SWT.SAVE);
		dialog.setMessage(Messages.ExportWizardPage_6);
		dialog.setText(Messages.ExportWizardPage_7);
		dialog.setFilterPath(getSelectedDirectory());
		String selectedDirectoryName = dialog.open();

		if (selectedDirectoryName != null) {
			setErrorMessage(null);
			setDestinationValue(selectedDirectoryName);
		}
	}
	
	/**
	 * Set the field value
	 * @param value The new destination value
	 */
	protected final void setDestinationValue(String value) {
		destinationNameField.setText(value);
	}


	/**
	 * Set the focus to the destination field
	 */
	protected final void giveFocusToDestination() {
		destinationNameField.setFocus();
	}

	/**
	 * Give back the selected resources
	 * @return A list of resources
	 */
	@SuppressWarnings("unchecked")
	public final List<IResource> getSelectedRessource() {
		List<IResource> selectedResources = getWhiteCheckedResources();
		return selectedResources;
	}

	/**
	 * Give back the selected destination for the exported file
	 * @return The destination path
	 */
	protected final String getSelectedDirectory() {
		return destinationNameField.getText().trim();
	}

	/**
	 * Check that the destination directory is valid
	 * @param targetDirectory The destination directory
	 * @return <code>true</code> if everything is correct.
	 */
	public final boolean ensureTargetIsValid(File targetDirectory) {
		// Check whether the directory is really a directory
		if (targetDirectory.exists() && !targetDirectory.isDirectory()) {
			displayErrorDialog(Messages.ExportWizardPage_11);
			giveFocusToDestination();
			return false;
		}

		// Does the directory exist ?
		if (!targetDirectory.exists()) {

			// Ask the user about the creation of this directory...
			if (!queryYesNoQuestion(Messages.ExportWizardPage_9)) {
				return false;
			}

			// Is it possible to create this directory ?
			if (!targetDirectory.mkdirs()) {
				displayErrorDialog(Messages.ExportWizardPage_10);
				giveFocusToDestination();
				return false;
			}
		}
		return true;
	}
}
