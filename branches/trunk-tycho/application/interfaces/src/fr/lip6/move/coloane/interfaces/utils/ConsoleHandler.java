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

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Basic log handler that print all log messages on the standard output.<br>
 * A log message is displayed as follow : [LEVEL] MESSAGE ... (PACKAGE.CLASS.METHOD)<br>
 */
public class ConsoleHandler extends Handler {
	/** {@inheritDoc} */
	@Override
	public void close() throws SecurityException { }

	/** {@inheritDoc} */
	@Override
	public void flush() { }

	/** {@inheritDoc} */
	@Override
	public final void publish(LogRecord record) {
		String level = "[" + record.getLevel() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
		String source;
		if (record.getSourceClassName().startsWith("fr.lip6.move.coloane.")) { //$NON-NLS-1$
			source = record.getSourceClassName().substring(21) + "." + record.getSourceMethodName(); //$NON-NLS-1$
		} else  {
			source = record.getSourceClassName() + "." + record.getSourceMethodName(); //$NON-NLS-1$
		}
		System.out.println(String.format("%-10s%-70s (%s)", level, record.getMessage(), source)); //$NON-NLS-1$
	}
}
