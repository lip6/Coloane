package fr.lip6.move.coloane.core.formalisms.constraints;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "fr.lip6.move.coloane.core.motor.formalisms.constraints.messages"; //$NON-NLS-1$
	public static String CapacityConstraint_0;
	public static String CardinalityConstraint_0;
	public static String ConnectionConstraint_0;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
