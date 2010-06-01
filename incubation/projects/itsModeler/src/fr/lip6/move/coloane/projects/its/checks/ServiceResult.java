package fr.lip6.move.coloane.projects.its.checks;

import fr.lip6.move.coloane.projects.its.obs.SimpleObservable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ServiceResult extends SimpleObservable {

	private boolean success;
	private String report;
	private Calendar date;
	private AbstractCheckService cs;
	private ParameterList parameters ;
	
	public ServiceResult(boolean success, String report, AbstractCheckService cs) {
		this.success = success;
		this.report = report;
		this.date = new GregorianCalendar();
		this.cs = cs;
		this.parameters = new ParameterList(cs.getParameters());
	}

	
	public ParameterList getParameters () {
		return parameters;
	}
		
	public String getReport() {
		return report;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yy HH:mm:ss");
		return formatter.format(date.getTime());
	}

	public AbstractCheckService getParent() {
		return cs;
	}
	
	/** a nice short label describing this result, used in tree label provider.*/
	@Override
	public String toString() {
		String ret;
		if (success) {
			ret = "OK ";
		} else {
			ret = "KO ";
		}
		ret += getDate();
		return ret;
	}

}
