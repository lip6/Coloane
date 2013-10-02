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
package fr.lip6.move.coloane.core.ui.palette;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {
	private static final String BUNDLE_NAME = "fr.lip6.move.coloane.core.ui.palette.messages"; //$NON-NLS-1$
	public static String PaletteFactory_0;
	public static String PaletteFactory_1;
	public static String PaletteFactory_2;
	public static String PaletteFactory_3;
	public static String PaletteFactory_4;
	public static String PaletteFactory_5;
	public static String PaletteFactory_6;
	public static String PaletteFactory_7;
	public static String PaletteFactory_8;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
