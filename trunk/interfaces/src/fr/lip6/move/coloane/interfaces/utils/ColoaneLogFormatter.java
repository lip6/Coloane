package fr.lip6.move.coloane.interfaces.utils;

import java.util.Date;
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
		Date d = new Date(record.getMillis());

		StringBuffer l = new StringBuffer();
		l.append(d.toString());
		l.append(" [" + record.getLevel() + "] ");
		l.append(record.getMessage());
		l.append("\n");

		return l.toString();
	}

	@Override
	public final String getHead(Handler h) {
		return "-------- COLOANE (" + version + ") --------\n\n";
	}
}
