package fr.lip6.move.coloane.core.exceptions;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {
	private static final String BUNDLE_NAME = "fr.lip6.move.coloane.exceptions.messages"; //$NON-NLS-1$
	public static String BuildException_0;
	public static String ColoaneException_0;
	public static String UIException_0;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
