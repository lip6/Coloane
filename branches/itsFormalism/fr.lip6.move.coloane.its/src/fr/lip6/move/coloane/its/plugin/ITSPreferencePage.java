/*******************************************************************************
 * Copyright (c) 2007 EclipseGraphviz contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Scott Bronson
 *     Yann Thierry-Mieg (added algo choice and some temp file based functionality)
 *******************************************************************************/
package fr.lip6.move.coloane.its.plugin;

import fr.lip6.move.coloane.its.actions.FileBrowserField;
import fr.lip6.move.coloane.its.obs.ISimpleObserver;
import fr.lip6.move.coloane.its.ui.forms.ITSEditorPlugin;

import java.io.File;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * The preferences page contributed by the plugin.
 * @author Yann Thierry-Mieg
 */
public final class ITSPreferencePage
extends PreferencePage
implements IWorkbenchPreferencePage {

	private FileBrowserField fileField;

	/**
	 * Creates the composite which will contain all the preference controls for this page.
	 * 
	 * @param parent the parent composite
	 * @return the composite for this page
	 */
	private Composite createComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));
		return composite;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.jface.preference.PreferencePage
	 */
	@Override
	protected final Control createContents(Composite parent) {
		Composite composite = createComposite(parent);
		createOpenModeGroup(composite);
		applyDialogFont(composite);
		return composite;
	}


	/**
	 * Build the area with dot detection options.
	 * @param composite the parent in which we add stuff.
	 */
	private void createOpenModeGroup(Composite composite) {
		Group buttonComposite = new Group(composite, SWT.LEFT);
		GridLayout layout = new GridLayout();
		buttonComposite.setLayout(layout);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		buttonComposite.setLayoutData(data);
		buttonComposite.setText("Dot executable to use");
		fileField = new FileBrowserField(composite);
		fileField.setToolTipText("Select its-reach executable.");
		fileField.setLayoutData(data);

		fileField.addObserver(new ISimpleObserver() {

			@Override
			public void update() {
				browserChanged(fileField.getText());
			}
		});
	}

	/**
	 * Called when user has done something. Grab the path text.
	 * @param txt the new text
	 */
	public final void browserChanged(String newText) {
		setErrorMessage(null);
		setMessage(null);
		if (newText.length() == 0) {
			setErrorMessage("Please enter a path.");
			setValid(false);
			return;
		}
		File exeFile = new File(newText);
		String fileName = exeFile.getName();
		int extensionPos;
		while ((extensionPos = fileName.lastIndexOf('.')) > 0) {
			fileName = fileName.substring(0, extensionPos);
		}
		if (!exeFile.exists()) {
			setErrorMessage(newText + " doesn't exist");
			setValid(false);
		} else if (exeFile.isDirectory()) {
			setErrorMessage(newText + " is a directory");
			setValid(false);
		} else if (!ITSEditorPlugin.isExecutable(exeFile)) {
			setMessage(newText + " is not executable!", IMessageProvider.WARNING);
			setValid(false);
		} else if (!ITSEditorPlugin.ITS_REACH_NAME.equalsIgnoreCase(fileName)) {
			setMessage("The file name should be " + ITSEditorPlugin.ITS_REACH_NAME , IMessageProvider.WARNING);
			setValid(false);
		} else {
			setValid(true);
		}
	}


	/**
	 * {@inheritDoc}
	 * @see IWorkbenchPreferencePage
	 */
	public void init(IWorkbench workbench) {
	}

	/**
	 * The user has pressed OK or Apply. Store this page's values.
	 * @return true
	 */
	@Override
	public final boolean performOk() {
		ITSEditorPlugin itsplugin = ITSEditorPlugin.getDefault();
		itsplugin.setITSReachPath(fileField.getText());
		return true;
	}

}
