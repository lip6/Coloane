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
package fr.lip6.move.coloane.core.ui.commands;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "fr.lip6.move.coloane.core.ui.commands.messages"; //$NON-NLS-1$
	public static String AlternateFigureCmd_0;
	public static String ArcChangeCurve_0;
	public static String ArcCreateCmd_0;
	public static String ArcDeleteCmd_0;
	public static String ArcReconnectCmd_0;
	public static String AttributeEditCommand_0;
	public static String AttributeSetConstraintCmd_0;
	public static String CreateGuideCommand_0;
	public static String DeleteGuideCommand_0;
	public static String InflexCreateCmd_0;
	public static String InflexDeleteCmd_0;
	public static String InflexMoveCmd_0;
	public static String MoveGuideCommand_0;
	public static String NodeCreateCmd_0;
	public static String NodeDeleteCmd_0;
	public static String NodeSetConstraintCmd_0;
	public static String StickyNoteCreateCommand_0;
	public static String StickyNoteDeleteCmd_0;
	public static String StickyNoteEditCommand_0;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
