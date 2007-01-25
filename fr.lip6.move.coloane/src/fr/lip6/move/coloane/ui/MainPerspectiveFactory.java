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
	private static final String ACT_PLATFORM = "fr.lip6.move.coloane.actionSet";
	
	/** ID for history view */
	private static final String HISTORY_VIEW = "fr.lip6.move.coloane.views.HistoricView";
	
	/** ID for history view */
	private static final String STATE_VIEW = "fr.lip6.move.coloane.views.StateView";
	
	private static final String PNSC_VIEW = "fr.lip6.move.coloane.views.PNSCView";
	
	/** ID for wizard */
	//private static final String NEW_WIZARD = "CPN-AMIoE.NewModelWizard";
			
	/**
	 * Creation du plan de travail
	 * <ul>
	 * <li>Navigator View in the top-left corner
	 * <li>Property and Problems View on the bottom
	 * </ul>
	 * @param layout Layout of the perspective  
	 */
	public void createInitialLayout(IPageLayout layout) {
		defineActions(layout);
		defineLayout(layout);
	}
		
	/**
	 * Defines the initial actions for a page.  
	 */
	public void defineActions(IPageLayout layout) {
		
		// Add wizard 
		//layout.addNewWizardShortcut(NEW_WIZARD);
		//layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.project");
		//layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");
		//layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");
		
		// Add action set Platform
		layout.addActionSet(ACT_PLATFORM);
		
		//  Add "show views". They will be present in "show view" menu
		layout.addShowViewShortcut(HISTORY_VIEW);
		layout.addShowViewShortcut(STATE_VIEW);
		layout.addShowViewShortcut(PNSC_VIEW);
		
		layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
		layout.addShowViewShortcut(IPageLayout.ID_RES_NAV);
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		
	}
		
	/**
	 * Defines the initial layout for a page.  
	 */
	public void defineLayout(IPageLayout layout) {
		// Some ratios for layout
		final float topLeftRatio = 0.25f;
		final float bottomRatio = 0.75f;
		final float outlineRatio = 0.5f;
	
		// Get the editor area.
		String editorArea = layout.getEditorArea();
		
		// Top left: Resource Navigator 
		layout.createFolder("TOPLEFT", IPageLayout.LEFT,topLeftRatio, editorArea).addView(IPageLayout.ID_RES_NAV);
		
		// Outline view is just after
		layout.createFolder("OUTLINE", IPageLayout.BOTTOM,outlineRatio, "TOPLEFT").addView(IPageLayout.ID_OUTLINE);
		
		// Bottom right: History, Property and Problem
		IFolderLayout bottom = layout.createFolder("BOTTOM",IPageLayout.BOTTOM, bottomRatio, editorArea);
		
		bottom.addView(HISTORY_VIEW);
		bottom.addView(STATE_VIEW);
		bottom.addView(PNSC_VIEW);
		bottom.addView(IPageLayout.ID_PROP_SHEET);
	}

}
