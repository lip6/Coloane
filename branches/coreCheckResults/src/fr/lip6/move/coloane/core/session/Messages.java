package fr.lip6.move.coloane.core.session;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "fr.lip6.move.coloane.core.motor.session.messages"; //$NON-NLS-1$
	public static String Session_0;
	public static String Session_1;
	public static String Session_2;
	public static String Session_3;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
