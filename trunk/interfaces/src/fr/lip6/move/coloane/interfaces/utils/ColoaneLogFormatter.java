package fr.lip6.move.coloane.interfaces.utils;

import java.util.Calendar;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ColoaneLogFormatter extends Formatter {
	private static String version;

	/**
	 * Indique au formatter la version de coloane utilisee
	 */
	public final void setVersion(String v) {
		version = v;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
	 */
	@Override
	public final String format(LogRecord record) {
		Calendar c = Calendar.getInstance();
		String name = "";

		if (record.getLoggerName().equals("fr.lip6.move.coloane.core")) {
			name = "Core";
		} else if (record.getLoggerName().equals("fr.lip6.move.coloane.api")) {
			name = "Api";
		}

		StringBuffer l = new StringBuffer();

		l.append(c.get(Calendar.DAY_OF_MONTH) + "/");
		l.append(c.get(Calendar.MONTH) + "/");
		l.append(c.get(Calendar.YEAR) + " ");

		l.append(c.get(Calendar.HOUR) + ":");
		l.append(c.get(Calendar.MINUTE) + ":");
		l.append(c.get(Calendar.SECOND) + " ");

		l.append("(" + name + ") ");
		l.append(" [" + record.getLevel() + "] ");
		l.append(record.getSourceClassName().substring(record.getSourceClassName().lastIndexOf(".")) + " : ");
		l.append(record.getMessage());
		l.append("\n");

		return l.toString();
	}

	@Override
	public final String getHead(Handler h) {
		return "-------- COLOANE (" + version + ") --------\n\n";
	}
}
