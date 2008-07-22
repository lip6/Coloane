package fr.lip6.move.coloane.apiws.log;

import java.util.Calendar;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class ApiLogFormatter extends Formatter {

	/*
	 * (non-Javadoc)
	 * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
	 */
	@Override
	public final String format(LogRecord record) {
		Calendar c = Calendar.getInstance();
		String name = "ApiWS"; //$NON-NLS-1$

		StringBuffer l = new StringBuffer();

		l.append(c.get(Calendar.DAY_OF_MONTH) + "/"); //$NON-NLS-1$
		l.append(c.get(Calendar.MONTH) + "/"); //$NON-NLS-1$
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

}
