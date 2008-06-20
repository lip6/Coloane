package fr.lip6.move.coloane.core.ui;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "fr.lip6.move.coloane.core.ui.messages"; //$NON-NLS-1$
	public static String ColoaneEditor_1;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
