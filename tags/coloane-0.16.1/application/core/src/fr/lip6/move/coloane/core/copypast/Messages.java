package fr.lip6.move.coloane.core.copypast;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "fr.lip6.move.coloane.core.copypast.messages"; //$NON-NLS-1$
	public static String CopyAction_1;
	public static String CutAction_1;
	public static String PasteAction_0;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
