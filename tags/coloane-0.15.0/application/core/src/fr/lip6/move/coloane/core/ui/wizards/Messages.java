package fr.lip6.move.coloane.core.ui.wizards;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {
	private static final String BUNDLE_NAME = "fr.lip6.move.coloane.core.ui.wizards.messages"; //$NON-NLS-1$
	public static String ProjectCreationPage_0;
	public static String ProjectCreationPage_1;
	public static String NewProjectWizard_1;
	

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
