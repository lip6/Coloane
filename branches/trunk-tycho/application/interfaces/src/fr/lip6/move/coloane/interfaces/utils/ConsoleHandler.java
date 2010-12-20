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
