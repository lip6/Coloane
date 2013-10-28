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
package fr.lip6.move.coloane.interfaces.utils;

import java.io.IOException;
import java.util.logging.FileHandler;

/**
 * Log handler dedicated to file export<br>
 * All messages are dumped in a file (in the default temporary folder)
 */
public final class ColoaneLogHandler extends FileHandler {
	private static ColoaneLogHandler instance = null;

	/**
	 * Build the handle (inside the <b>default temporary folder</b>)
	 * @throws IOException If the logger can't write into the log file
	 * @throws SecurityException If the security manager doesn't allow this Handler
	 */
	private ColoaneLogHandler() throws IOException, SecurityException {
		super("%t/coloane-" + System.getProperty("user.name") + "-%u.log", true); //$NON-NLS-1$
	    //super(File.createTempFile("coloane-", ".log").getAbsolutePath(), false);
	}

	/**
	 * @return the handler
	 * @throws IOException If the logger can't write into the log file
	 * @throws SecurityException If the security manager doesn't allow this Handler
	 */
	public static ColoaneLogHandler getInstance() throws IOException, SecurityException {
		if (instance == null) { instance = new ColoaneLogHandler(); }
		return instance;
	}
}
