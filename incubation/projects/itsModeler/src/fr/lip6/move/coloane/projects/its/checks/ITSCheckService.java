package fr.lip6.move.coloane.projects.its.checks;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;

import fr.lip6.move.coloane.projects.its.io.ITSModelWriter;

public abstract class ITSCheckService extends AbstractCheckService {



	public ITSCheckService(CheckList parent, String serviceName) {
		super(parent,serviceName);
	}

	@Override
	protected List<String> buildCommandArguments() {
		ArrayList<String> cmd = new ArrayList<String>();
		cmd.add(getToolPath().toOSString());
		//		cmd.add("--quiet");
		//		cmd.add("-T" + format);
		cmd.add("-xml");
		cmd.add("modelMain.xml");
		return cmd;
	}

	public String run() {
		ITSModelWriter mw = new ITSModelWriter();
		String report;
		boolean success = true;
		try {
			mw.exportITSModel(parent.getTypes(), parent.getType(), getWorkDir());
			report = "Run successful in folder "+getWorkDir();
		} catch (Exception e) {
			success  = false;
			report = "An error occurred during service invocation :" + e + e.getMessage();
		}
	
		// RUN THE SERVICE
		IStatus status = runTool(getWorkDirPath());
		if (! status.isOK()) {
			success  = false;
			report = "An error occurred during service invocation :" + status.getMessage();
		} else {
			report = getReportText();
		}
		addResult (new ServiceResult(success,report,this));
		return report;
	}

	
}
