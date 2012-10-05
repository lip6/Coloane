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
package fr.lip6.move.coloane.core.ui;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

/**
 * Define the layout used by the Coloane perspective
 * <ul>
 * 	<li>Active views</li>
 * 	<li>View Layout</li>
 * </ul>
 *
 * @author Jean-Baptiste Voron
 */
public class ColoanePerspectiveFactory implements IPerspectiveFactory {

	/** ID for action sets Platform */
	//public static final String ACT_PLATFORM = "fr.lip6.move.coloane.actionSets"; //$NON-NLS-1$

	/** ID for wizard */
	public static final String PROJECT_WIZARD = "fr.lip6.move.coloane.wizard.projectWizard"; //$NON-NLS-1$
	public static final String MODEL_WIZARD = "fr.lip6.move.coloane.wizard.modelWizard"; //$NON-NLS-1$

	/** ID for the "Coloane results" view */
	public static final String RESULTS_VIEW = "fr.lip6.move.coloane.views.ResultsView"; //$NON-NLS-1$

	/** ID for the "Models Navigator" view */
	public static final String MODEL_NAVIGATOR = "fr.lip6.move.coloane.views.NavigatorView"; //$NON-NLS-1$

	/**
	 * Create the working space
	 * <ul>
	 * <li>Navigator view in the top-left corner
	 * <li>Properties and Problems views on the bottom
	 * </ul>
	 *
	 * @param layout Layout of the perspective
	 */
	@Override
	public final void createInitialLayout(IPageLayout layout) {
		defineActions(layout);
		defineLayout(layout);
	}

	/**
	 * Defines the initial actions for a page.
	 * @param layout layout
	 */
	public final void defineActions(IPageLayout layout) {

		// Add wizard
		layout.addNewWizardShortcut(PROJECT_WIZARD);
		layout.addNewWizardShortcut(MODEL_WIZARD);

		// Add action set Platform
		//layout.addActionSet(ACT_PLATFORM);

		//  Add "show views". They will be present in "show view" menu
		layout.addShowViewShortcut(RESULTS_VIEW);

		layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
		layout.addShowViewShortcut(MODEL_NAVIGATOR);
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
	}

	/**
	 * Defines the initial layout for a page.
	 * @param layout layout
	 */
	public final void defineLayout(IPageLayout layout) {
		// Some ratios for layout
		final float topLeftRatio = 0.2f;
		final float bottomRatio = 0.75f;
		final float outlineRatio = 0.5f;
		final float consoleRatio = 0.55f;

		// Get the editor area.
		String editorArea = layout.getEditorArea();

		// Top left: Resource Navigator
		layout.createFolder("TOPLEFT", IPageLayout.LEFT, topLeftRatio, editorArea).addView(MODEL_NAVIGATOR); //$NON-NLS-1$

		// Outline view is just after
		layout.createFolder("OUTLINE", IPageLayout.BOTTOM, outlineRatio, "TOPLEFT").addView(IPageLayout.ID_OUTLINE); //$NON-NLS-1$ //$NON-NLS-2$

		// Bottom : Property and Result
		IFolderLayout bottom = layout.createFolder("BOTTOM", IPageLayout.BOTTOM, bottomRatio, editorArea); //$NON-NLS-1$

		bottom.addView(IPageLayout.ID_PROP_SHEET);
		bottom.addView(RESULTS_VIEW);

		// Right of bottom folder : Console
		IFolderLayout consoleFolder = layout.createFolder("CONSOLE", IPageLayout.RIGHT, consoleRatio, "BOTTOM");  //$NON-NLS-1$ //$NON-NLS-2$

		consoleFolder.addView(IPageLayout.ID_PROBLEM_VIEW);
		consoleFolder.addView(IConsoleConstants.ID_CONSOLE_VIEW);
	}
}
