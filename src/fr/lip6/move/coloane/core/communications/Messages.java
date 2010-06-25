package fr.lip6.move.coloane.core.communications;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "fr.lip6.move.coloane.core.communications.messages"; //$NON-NLS-1$
	public static String Com_0;
	public static String Com_1;
	public static String Com_2;
	public static String Com_3;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
