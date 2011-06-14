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
package fr.lip6.move.coloane.core.ui.prefs;

import fr.lip6.move.coloane.core.main.Coloane;

import java.util.logging.Level;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Preferences Page for Coloane.<br>
 * The first page presents a set of global preferences :
 * <ul>
 * 	<li>Stats Preferences</li>
 * 	<li>Debug Preferences</li>
 * </ul>
 */
public class ColoanePrefsPage extends PreferencePage implements IWorkbenchPreferencePage {
	/** Limit size for text field */
	public static final int TXT_LIMIT = 255;

	/** Statistics Field */
	private BooleanFieldEditor statsStatus;

	/** Debug Field */
	private Combo debugLevelCombo;

	/** {@inheritDoc} */
	public final void init(IWorkbench workbench) {
		setPreferenceStore(Coloane.getInstance().getPreferenceStore());
	}

	/**
	 * Creates the composite which contains all the preference controls for this page.
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
		createStatsGroup(composite);
		createLogGroup(composite);
		applyDialogFont(composite);
		return composite;
	}

	/**
	 * Create the group of components dedicated to statistics preferences
	 * @param parent The main composite
	 */
	private void createStatsGroup(Composite parent) {
		Group statsGroup = new Group(parent, SWT.LEFT);
		GridLayout layout = new GridLayout();
		statsGroup.setLayout(layout);

		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		statsGroup.setLayoutData(gridData);
		statsGroup.setText(Messages.ColoanePrefsPage_11);

		Label statsLabel = new Label(statsGroup, SWT.WRAP);
		statsLabel.setText(Messages.ColoanePrefsPage_0);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		gridData.horizontalSpan = 2;
		gridData.verticalIndent = 3;
		statsLabel.setLayoutData(gridData);

		statsStatus = new BooleanFieldEditor("STATS_STATUS", Messages.ColoanePrefsPage_12, BooleanFieldEditor.SEPARATE_LABEL, statsGroup);  //$NON-NLS-1$
		statsStatus.setPreferenceStore(getPreferenceStore());
		statsStatus.load();
	}

	/**
	 * Create the group of components dedicated to log preferences
	 * @param parent The main composite
	 */
	private void createLogGroup(Composite parent) {
		Group logGroup = new Group(parent, SWT.LEFT);
		GridLayout layout = new GridLayout(2, false);
		logGroup.setLayout(layout);

		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		logGroup.setLayoutData(data);
		logGroup.setText(Messages.ColoanePrefsPage_5);

		new Label(logGroup, SWT.NULL).setText(Messages.ColoanePrefsPage_6);
		debugLevelCombo = new Combo(logGroup, SWT.NULL);
		debugLevelCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		String levelList[] = {"NORMAL", "BETA", "DEBUG"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		debugLevelCombo.setItems(levelList);
		debugLevelCombo.setText(Messages.ColoanePrefsPage_10);
		debugLevelCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (debugLevelCombo.getText().equals("NORMAL")) { //$NON-NLS-1$
					Coloane.setVerbosity(Level.INFO);
				} else if (debugLevelCombo.getText().equals("BETA")) { //$NON-NLS-1$
					Coloane.setVerbosity(Level.FINE);
				} else if (debugLevelCombo.getText().equals("DEBUG")) { //$NON-NLS-1$
					Coloane.setVerbosity(Level.ALL);
				}
			}
		});
	}

	/** {@inheritDoc} */
	@Override
	public final boolean performOk() {
		// STATS
		Coloane.getInstance().setPreference("STATS_STATUS", String.valueOf(statsStatus.getBooleanValue())); //$NON-NLS-1$

		// DEBUG
		Coloane.getInstance().setPreference("DEBUG_STATUS", String.valueOf(statsStatus.getBooleanValue())); //$NON-NLS-1$

		return super.performOk();
	}
}
