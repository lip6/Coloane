package fr.lip6.move.coloane.interfaces.utils;

import java.util.Calendar;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Classe responsable du format des logs
 */
public class ColoaneLogFormatter extends Formatter {
	private static String version;

	/**
	 * Indique au formatter la version de coloane utilisee
	 * @param v La version
	 */
	public final void setVersion(String v) {
		version = v;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String format(LogRecord record) {
		Calendar c = Calendar.getInstance();
		String name = ""; //$NON-NLS-1$

		if (record.getLoggerName().equals("fr.lip6.move.coloane.core")) { //$NON-NLS-1$
			name = "Core"; //$NON-NLS-1$
		} else if (record.getLoggerName().equals("fr.lip6.move.coloane.apiws")) { //$NON-NLS-1$
			name = "Apiws"; //$NON-NLS-1$
		} else if (record.getLoggerName().equals("fr.lip6.move.coloane.apicami")) { //$NON-NLS-1$
			name = "Apicami"; //$NON-NLS-1$
		}

		StringBuffer l = new StringBuffer();

		l.append(c.get(Calendar.DAY_OF_MONTH) + "/"); //$NON-NLS-1$
		l.append((c.get(Calendar.MONTH) + 1) + "/"); //$NON-NLS-1$
		l.append(c.get(Calendar.YEAR) + " "); //$NON-NLS-1$

		l.append(c.get(Calendar.HOUR) + ":"); //$NON-NLS-1$
		l.append(c.get(Calendar.MINUTE) + ":"); //$NON-NLS-1$
		l.append(c.get(Calendar.SECOND) + " "); //$NON-NLS-1$

		l.append("(" + name + ") "); //$NON-NLS-1$ //$NON-NLS-2$
		l.append(" [" + record.getLevel() + "] "); //$NON-NLS-1$ //$NON-NLS-2$
		l.append(record.getSourceClassName().substring(record.getSourceClassName().lastIndexOf(".") + 1) + " -> "); //$NON-NLS-1$ //$NON-NLS-2$
		l.append(record.getMessage());
		l.append("\n"); //$NON-NLS-1$

		return l.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getHead(Handler h) {
		return "-------- COLOANE (" + version + ") --------\n\n"; //$NON-NLS-1$ //$NON-NLS-2$
	}
}
