package fr.lip6.move.coloane.its.checks;

import fr.lip6.move.coloane.its.io.ITSModelWriter;
import fr.lip6.move.coloane.its.obs.SimpleObservable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CheckService extends SimpleObservable implements Iterable<ServiceResult> {

	private String name="ITS reachability";
	private String workdir;
	private CheckList parent;
	private List<ServiceResult> results = new LinkedList<ServiceResult>();
	
	public CheckService(CheckList parent) {
		this.parent = parent;
	}
	
	public String getName() {
		return name;
	}

	public void setWorkdir(String workdir) {
		this.workdir = workdir;
		notifyObservers();
	}
	
	public String getWorkDir() {
		return workdir;
	}

	public String run() {
		ITSModelWriter mw = new ITSModelWriter();
		String report;
		boolean success = true;
		try {
			mw.exportITSModel(parent.getTypes(), parent.getType(), workdir);
			report = "Run successful in folder "+workdir;
		} catch (Exception e) {
			success = false;
			report = "An error occurred during service invocation :" + e + e.getMessage();
		}
		addResult (new ServiceResult(success,report,this));
		return report;
	}

	private void addResult(ServiceResult serviceResult) {
		results.add(serviceResult);
		notifyObservers();
	}

	@Override
	public Iterator<ServiceResult> iterator() {
		return results.iterator();
	}
	
}
