/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.core.results.reports;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "fr.lip6.move.coloane.core.results.reports.messages"; //$NON-NLS-1$
	public static String ComputeBoundsReport_0;
	public static String ComputeBoundsReport_1;
	public static String ComputeBoundsReport_2;
	public static String GenericReport_0;
	public static String GenericReport_1;
	public static String GenericReport_2;
	public static String IsTheNetQuestionReport_0;
	public static String MinimalSyphonTrapReport_0;
	public static String MinimalSyphonTrapReport_1;
	public static String MinimalSyphonTrapReport_2;
	public static String PInvariantsReport_0;
	public static String PInvariantsReport_1;
	public static String PInvariantsReport_2;
	public static String SyntaxCheckerReport_0;
	public static String SyntaxCheckerReport_1;
	public static String SyntaxCheckerReport_2;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
