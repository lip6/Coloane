package fr.lip6.move.coloane.core.ui;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

/**
* Classe decrivantt la configuration de la perspective Coloane
* <ul>
* 	<li>Description des vues</li>
* 	<li>Positionnement des vues</li>
* </ul>
*/

public class ColoanePerspectiveFactory implements IPerspectiveFactory {

	/** ID for action sets Platform */
	public static final String ACT_PLATFORM = "fr.lip6.move.coloane.actionSets"; //$NON-NLS-1$

	/** ID for the "Coloane results" view */
	public static final String RESULTS_VIEW = "fr.lip6.move.coloane.views.ResultsView"; //$NON-NLS-1$

	/** ID for wizard */
	public static final String PROJECT_WIZARD = "fr.lip6.move.coloane.wizard.projectWizard"; //$NON-NLS-1$
	public static final String MODEL_WIZARD = "fr.lip6.move.coloane.wizard.modelWizard"; //$NON-NLS-1$

	public static final String MODEL_NAVIGATOR = "fr.lip6.move.coloane.views.NavigatorView"; //$NON-NLS-1$

	/**
	 * Creation du plan de travail
	 * <ul>
	 * <li>Navigator View in the top-left corner
	 * <li>Property and Problems View on the bottom
	 * </ul>
	 * @param layout Layout of the perspective
	 */
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
		layout.addActionSet(ACT_PLATFORM);

		//  Add "show views". They will be present in "show view" menu
		layout.addShowViewShortcut(RESULTS_VIEW);

		layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
		layout.addShowViewShortcut(IPageLayout.ID_RES_NAV);
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		layout.addShowViewShortcut(MODEL_NAVIGATOR);
	}

	/**
	 * Defines the initial layout for a page.
	 * @param layout layout
	 */
	public final void defineLayout(IPageLayout layout) {
		// Some ratios for layout
		final float topLeftRatio = 0.25f;
		final float bottomRatio = 0.75f;
		final float outlineRatio = 0.5f;
		final float consoleRatio = 0.6f;

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
		layout.createFolder("CONSOLE", IPageLayout.RIGHT, consoleRatio, "BOTTOM").addView(IConsoleConstants.ID_CONSOLE_VIEW); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
