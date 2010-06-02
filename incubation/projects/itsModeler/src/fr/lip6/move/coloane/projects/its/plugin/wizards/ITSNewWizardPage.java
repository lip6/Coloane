package fr.lip6.move.coloane.projects.its.plugin.wizards;

import org.eclipse.jface.viewers.ISelection;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (fr.lip6.move.coloane.its).
 */
public final class ITSNewWizardPage extends AbstractNewWizardPage {

	/**
	 * Constructor for ITSNewWizardPage.
	 * 
	 * @param selection the workspace item currently selected (should be a folder)
	 */
	public ITSNewWizardPage(ISelection selection) {
		super(selection);
	}

	@Override
	protected String getWizardDescription() {
		return "This wizard creates a new file with *.xmlits extension that can be opened by ITS Composition editor.";
	}

	@Override
	protected String getWizardFileExtension() {
		return "xmlits";
	}

	@Override
	protected String getWizardTitle() {
		return "ITS Composition Model";
	}
}

