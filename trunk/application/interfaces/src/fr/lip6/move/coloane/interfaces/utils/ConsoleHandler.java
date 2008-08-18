package fr.lip6.move.coloane.interfaces.utils;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Handler permettant d'afficher les log sur la sortie standard.<br>
 * Voici la description d'une ligne de log : <br>
 * [NIVEAU] MESSAGE_DE_LOG (PACKAGE.CLASSE.METHODE)<br>
 * <br>
 * Ce Handler n'utilise pas de Formatter.
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
