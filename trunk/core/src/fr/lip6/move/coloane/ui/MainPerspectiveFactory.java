package fr.lip6.move.coloane.ui;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
* Class principal for CPN-AMI Perspective
*
*/

public class MainPerspectiveFactory implements IPerspectiveFactory {

	/** ID for action sets Platform */
	public static final String ACT_PLATFORM = "fr.lip6.move.coloane.actionSet"; //$NON-NLS-1$

	/** ID for history view */
	public static final String HISTORY_VIEW = "fr.lip6.move.coloane.views.HistoricView"; //$NON-NLS-1$

	/** ID for the "Coloane results" view */
	public static final String RESULTS_VIEW = "fr.lip6.move.coloane.views.ResultsView"; //$NON-NLS-1$

	/** ID for wizard */
	public static final String PROJECT_WIZARD = "fr.lip6.move.coloane.wizard.projectWizard"; //$NON-NLS-1$
	public static final String MODEL_WIZARD = "fr.lip6.move.coloane.wizard.modelWizard"; //$NON-NLS-1$


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
	 */
	public final void defineActions(IPageLayout layout) {

		// Add wizard
		layout.addNewWizardShortcut(PROJECT_WIZARD);
		layout.addNewWizardShortcut(MODEL_WIZARD);

		// Add action set Platform
		layout.addActionSet(ACT_PLATFORM);

		//  Add "show views". They will be present in "show view" menu
		layout.addShowViewShortcut(HISTORY_VIEW);
		layout.addShowViewShortcut(RESULTS_VIEW);

		layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
		layout.addShowViewShortcut(IPageLayout.ID_RES_NAV);
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);

	}

	/**
	 * Defines the initial layout for a page.
	 */
	public final void defineLayout(IPageLayout layout) {
		// Some ratios for layout
		final float topLeftRatio = 0.25f;
		final float bottomRatio = 0.75f;
		final float outlineRatio = 0.5f;

		// Get the editor area.
		String editorArea = layout.getEditorArea();

		// Top left: Resource Navigator
		layout.createFolder("TOPLEFT", IPageLayout.LEFT, topLeftRatio, editorArea).addView(IPageLayout.ID_RES_NAV); //$NON-NLS-1$

		// Outline view is just after
		layout.createFolder("OUTLINE", IPageLayout.BOTTOM, outlineRatio, "TOPLEFT").addView(IPageLayout.ID_OUTLINE); //$NON-NLS-1$ //$NON-NLS-2$

		// Bottom right: History, Property and Problem
		IFolderLayout bottom = layout.createFolder("BOTTOM", IPageLayout.BOTTOM, bottomRatio, editorArea); //$NON-NLS-1$

		bottom.addView(HISTORY_VIEW);
		bottom.addView(RESULTS_VIEW);
		bottom.addView(IPageLayout.ID_PROP_SHEET);
	}

}
