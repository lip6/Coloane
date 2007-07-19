package fr.lip6.move.coloane.api.log;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.Calendar;

import fr.lip6.move.coloane.api.log.LogsUtils;




public class ApiFormatter extends Formatter {

	private LogsUtils logsutils = new LogsUtils();
	@Override
	public String format(LogRecord record) {
		// TODO Auto-generated method stub
		StringBuffer s = new StringBuffer(1000);
		Calendar c = Calendar.getInstance();
		s.append("\n[" + record.getLevel() + "] ");
		s.append("" + c.get(Calendar.DAY_OF_MONTH) + "/"
				+ c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR) + " ");
		s.append("" + c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":"
				+ c.get(Calendar.SECOND));
		s.append("(" + record.getSourceClassName() + " "
				+ record.getSourceMethodName() + ")");
		s.append( "- " + logsutils.sous_chaine(record.getMessage()) + "- ");
		s.append(" " + logsutils.FromArrayToString((record.getParameters())));
		

		return s.toString();

	}
	

}
