/*******************************************************************************
 * Copyright (c) 2007 EclipseGraphviz contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Scott Bronson
 *******************************************************************************/
package fr.lip6.move.coloane.graphviz.ui;

import java.io.File;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import fr.lip6.move.graphviz.GraphVizActivator;
import fr.lip6.move.graphviz.GraphVizActivator.DotAlgo;
import fr.lip6.move.graphviz.GraphVizActivator.DotMethod;


public class GraphVizPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
	/**
	 * Utility method that creates a radio button instance and sets the default
	 * layout data.
	 * 
	 * @param parent
	 *            the parent for the new button
	 * @param label
	 *            the label for the new button
	 * @return the newly-created button
	 */

	protected static Button createRadioButton(Composite parent, String label) {
		Button button = new Button(parent, SWT.RADIO | SWT.LEFT);
		button.setText(label);
		return button;
	}

	private Button automaticDotButton;
	private Button useBundledDotButton;
	private Button autodetectDotButton;
	private Button specifyDotButton;
	private Button specifyAlgoButton;

	private FileBrowserField dotBrowser;
	private Button dotDotButton;
	private Button neatoDotButton;
	private Button circoDotButton; 

	/**
	 * Creates the mildly complex radio buttons that the prefs dialog uses.
	 * 
	 * @param group
	 *            The group to add the button to.
	 * @param label
	 *            The text for the button's label
	 * @param location
	 *            String to append to the label, null to append nothing and
	 *            disable the button.
	 * @param method
	 *            If this matches the current method, the button is
	 *            automatically selected.
	 * @return The new button.
	 */
	private Button createButton(Group group, String label, boolean enabled, DotMethod method) {
		Button button;

		button = createRadioButton(group, label);
		button.setSelection(getCurrentDotMethod() == method);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean enabled = specifyDotButton.getSelection();
				dotBrowser.setEnabled(enabled);
				dotBrowserChanged(null);
			}
		});
		button.setEnabled(enabled);
		return button;
	}

	/**
	 * Creates the mildly complex radio buttons that the prefs dialog uses.
	 * 
	 * @param group
	 *            The group to add the button to.
	 * @param label
	 *            The text for the button's label
	 * @param location
	 *            String to append to the label, null to append nothing and
	 *            disable the button.
	 * @param method
	 *            If this matches the current method, the button is
	 *            automatically selected.
	 * @return The new button.
	 */
	private Button createButton(Group group, String label,
			boolean enabled, DotAlgo method) {
		Button button;

		button = createRadioButton(group, label);
		button.setSelection(getCurrentDotAlgo() == method);
		button.setEnabled(enabled);
		return button;
	}

	/**
	 * Creates the composite which will contain all the preference controls for
	 * this page.
	 * 
	 * @param parent
	 *            the parent composite
	 * @return the composite for this page
	 */
	protected Composite createComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));
		return composite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite composite = createComposite(parent);
		createOpenModeGroup(composite);
		createAlgoModeGroup(composite);
		applyDialogFont(composite);
		return composite;
	}

	private void createAlgoModeGroup(Composite composite) {
		Group buttonComposite = new Group(composite, SWT.LEFT);
		GridLayout layout = new GridLayout();
		buttonComposite.setLayout(layout);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		buttonComposite.setLayoutData(data);
		buttonComposite.setText("Dot layout algorithm");

		dotDotButton = createButton(buttonComposite, "dot (acyclic graph)", true, DotAlgo.DOT);
		dotDotButton.setToolTipText("A mode adapted to acyclic graphs, a good setting for decision diagrams for instance. \n" +
				"Depending on the graph, may work well also for state space graphs, although neato is also a good option.");
		neatoDotButton = createButton(buttonComposite, "neato (spring model)[recommended]", true, DotAlgo.NEATO);
		neatoDotButton.setToolTipText("A mode base on a spring model, well adapted to graphs with cycles. This setting gives best results for Petri net and their variants.");
		circoDotButton = createButton(buttonComposite, "circo (circular layout)", true, DotAlgo.CIRCO);
		circoDotButton.setToolTipText("A circular layout, nodes are placed on a circle. This setting gives poor results with Petri nets.");

	}

	
	protected void createOpenModeGroup(Composite composite) {
		Group buttonComposite = new Group(composite, SWT.LEFT);
		GridLayout layout = new GridLayout();
		buttonComposite.setLayout(layout);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		buttonComposite.setLayoutData(data);
		buttonComposite.setText("Dot executable to use");

		GraphVizActivator graphviz = GraphVizActivator.getInstance();
		automaticDotButton = createButton(buttonComposite, "Choose Automatically", true, DotMethod.AUTO);
		useBundledDotButton = createButton(buttonComposite, "Bundled", graphviz.hasBundledInstall(), DotMethod.BUNDLE);
		String detectedDotLocation = graphviz.autodetectDots();
		final boolean dotDetected = detectedDotLocation != null;
		String detectLabel = "Detected: " + (dotDetected ? detectedDotLocation : "(none)");
		autodetectDotButton = createButton(buttonComposite, detectLabel, dotDetected, DotMethod.DETECT);
		specifyDotButton = createButton(buttonComposite, "Specify Manually:", true, DotMethod.MANUAL);

		dotBrowser = new FileBrowserField(buttonComposite) {
			@Override
			public void valueChanged() {
				dotBrowserChanged(getText());
			}
		};
		dotBrowser.setText(graphviz.getManualDotPath());
		dotBrowser.setEnabled(getCurrentDotMethod() == DotMethod.MANUAL);

		data = new GridData();
		data.horizontalIndent = 20;
		data.horizontalAlignment = GridData.FILL;
		data.grabExcessHorizontalSpace = true;
		dotBrowser.setLayoutData(data);
	}

	public void dotBrowserChanged(String newText) {
		setErrorMessage(null);
		setMessage(null);
		if (newText == null) {
			newText = dotBrowser.getText();
		}
		if (specifyDotButton.getSelection()) {
			if (newText.length() == 0) {
				setErrorMessage("Please enter a path.");
				setValid(false);
				return;
			}
			File dotFile = new File(newText);
			String fileName = dotFile.getName();
			int extensionPos;
			while ((extensionPos = fileName.lastIndexOf('.')) > 0)
				fileName = fileName.substring(0, extensionPos);
			if (!dotFile.exists()) {
				setErrorMessage(newText + " doesn't exist");
				setValid(false);
				return;
			} else if (dotFile.isDirectory()) {
				setErrorMessage(newText + " is a directory");
				setValid(false);
				return;
			} else if (!GraphVizActivator.isExecutable(dotFile))
				setMessage(newText + " is not executable!", IMessageProvider.WARNING);
			else if (!GraphVizActivator.DOT_FILE_NAME.equalsIgnoreCase(fileName))
				setMessage("The file name should be " + GraphVizActivator.DOT_FILE_NAME , IMessageProvider.WARNING);
		}
		setValid(true);
	}

	/** Returns the Dot search method that is currently in effect */
	DotMethod getCurrentDotMethod() {
		return GraphVizActivator.getInstance().getDotSearchMethod();
	}


	private DotAlgo getCurrentDotAlgo() {
		return GraphVizActivator.getInstance().getDotAlgo();
	}

	
	/**
	 * Scans the radio buttons and returns the dot method that the user has
	 * selected.
	 */
	DotMethod getNewDotMethod() {
		return useBundledDotButton.getSelection() ? DotMethod.BUNDLE : autodetectDotButton.getSelection()
						? DotMethod.DETECT : specifyDotButton.getSelection() ? DotMethod.MANUAL : DotMethod.AUTO;
	}

	/* Scans the radio buttons and returns the dot method that the user has
	 * selected.
	 */
	DotAlgo getNewDotAlgo() {
		return dotDotButton.getSelection() ? DotAlgo.DOT :
				neatoDotButton.getSelection() ? DotAlgo.NEATO : 
					circoDotButton.getSelection() ? DotAlgo.CIRCO 
							: DotAlgo.NEATO;
	}

	/**
	 * @see IWorkbenchPreferencePage
	 */
	public void init(IWorkbench workbench) {
	}

	/**
	 * The default button has been pressed.
	 */
	@Override
	protected void performDefaults() {
		DotMethod dotMethod = DotMethod.AUTO;
		automaticDotButton.setSelection(dotMethod == DotMethod.AUTO);
		useBundledDotButton.setSelection(dotMethod == DotMethod.BUNDLE);
		autodetectDotButton.setSelection(dotMethod == DotMethod.DETECT);
		specifyDotButton.setSelection(dotMethod == DotMethod.MANUAL);
		dotBrowser.setText("");

		DotAlgo dotAlgo = DotAlgo.NEATO;
		dotDotButton.setSelection(dotAlgo == DotAlgo.DOT);
		neatoDotButton.setSelection(dotAlgo == DotAlgo.NEATO);
		circoDotButton.setSelection(dotAlgo == DotAlgo.CIRCO);
		
		
		super.performDefaults();
	}

	/**
	 * The user has pressed OK or Apply. Store this page's values.
	 */
	@Override
	public boolean performOk() {
		GraphVizActivator graphviz = GraphVizActivator.getInstance();
		graphviz.setDotSearchMethod(getNewDotMethod());
		graphviz.setManualDotPath(dotBrowser.getText());
		
		graphviz.setDotAlgo(getNewDotAlgo());
		return true;
	}
}
