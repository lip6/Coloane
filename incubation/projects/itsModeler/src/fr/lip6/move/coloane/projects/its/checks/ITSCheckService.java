package fr.lip6.move.coloane.projects.its.checks;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.projects.its.io.ITSModelWriter;
import fr.lip6.move.coloane.projects.its.order.ITSOrderWriter;
import fr.lip6.move.coloane.projects.its.order.Ordering;

public abstract class ITSCheckService extends AbstractCheckService {

	private static final String ORDER_FILE_NAME = "model.ord";
	private static final String QUIET_PARAMETER = "Low Verbosity";
	private OrderParameter order;

	public ITSCheckService(CheckList parent, String serviceName) {
		super(parent,serviceName);
		order = new OrderParameter(parent.getOrders());
		getParameters().addBooleanParameter(QUIET_PARAMETER, false);
	}

	@Override
	protected List<String> buildCommandArguments() {
		ArrayList<String> cmd = new ArrayList<String>();
		cmd.add(getToolPath().toOSString());
		if (getParameters().getBoolParameterValue(QUIET_PARAMETER))
			cmd.add("--quiet");
		cmd.add("-xml");
		cmd.add("modelMain.xml");
		
		// Handle order input
		if (buildOrderFile()) {
			cmd.add("--load-order");
			cmd.add(getOrderFileName());
		}
		
		return cmd;
	}

	private boolean buildOrderFile() {
		Ordering o = order.getSelection();
		if (o == null)
			return false;
		
		// build the file
		ITSOrderWriter ow = new ITSOrderWriter();
		try {
			ow.writeOrder(getOrderFileName(), getParent().getType(), o);
		} catch (ColoaneException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	private String getOrderFileName() {
		return getWorkDir()+"/"+ORDER_FILE_NAME;
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
			report = "An error occurred during Export phase of service invocation :" + e + e.getMessage();
		}
	
		// RUN THE SERVICE
		IStatus status = runTool(getWorkDirPath());
		if (! status.isOK()) {
			success  = false;
			report += "An error occurred during ITS service invocation :" + status.getMessage();
		} else {
			report = getReportText();
		}
		addResult (new ServiceResult(success,report,this));
		return report;
	}

	public OrderParameter getOrder() {
		return order;
	}
}
