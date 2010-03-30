package fr.lip6.move.coloane.projects.its.checks;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;

import fr.lip6.move.coloane.extension.importExportRomeo.exportToRomeo.ExportToRomeo;
import fr.lip6.move.coloane.extensions.importExportCAMI.exportToCAMI.ExportToImpl;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.projects.its.CompositeTypeDeclaration;
import fr.lip6.move.coloane.projects.its.TypeDeclaration;
import fr.lip6.move.coloane.projects.its.flatten.ModelFlattener;
import fr.lip6.move.coloane.projects.its.ui.forms.ITSEditorPlugin;

public class OrderingService extends CheckService {

	private static final String ORDER_NAME = "Variable Ordering";
	private static final String ORDER_HEURISTIC_PARAM = "Heuristic chosen";
	private static final String ORDER_FILE_NAME = "model.json";
	private static final String CAMI_FILE_NAME = "model.cami";
	private static final String ROMEO_FILE_NAME = "model.xml";
	
	public OrderingService(CheckList parent) {
		super(parent, ORDER_NAME);
		getParameters().addParameter(ORDER_HEURISTIC_PARAM);
	}
	
	protected IPath getToolPath() {
//		return "python";
		return ITSEditorPlugin.getDefault().getPythonPath();
	}
	
	protected List<String> buildCommandArguments() {
		ArrayList<String> cmd = new ArrayList<String>();
		cmd.add(getToolPath().toOSString());
		cmd.add(ITSEditorPlugin.getDefault().getOrderingPath().toOSString());
		//		cmd.add("--quiet");
		//		cmd.add("-T" + format);
		cmd.add("--model");
		cmd.add(CAMI_FILE_NAME);
		cmd.add("--porder");
		cmd.add(ORDER_FILE_NAME);
		cmd.add("--order");
		cmd.add(getParameters().getParameterValue(ORDER_HEURISTIC_PARAM));
		return cmd;
	}
	
	public String run() {
		boolean success = true;
		String report = "";
		IGraph graph;
		// Step 1 : flatten the model if necessary
		TypeDeclaration td = getParent().getType();
		if (td instanceof CompositeTypeDeclaration) {
			ModelFlattener mf = new ModelFlattener();
			mf.setInstantiateOption(true);
			try {
				mf.doFlatten((CompositeTypeDeclaration) td);
			} catch (ModelException e) {
				success  = false;
				report = "An error occurred during flatten model phase :" + e + e.getMessage();
				addResult (new ServiceResult(success,report,this));
				return report;
			}
			graph = mf.getFlatModel();
		} else {
			graph = td.getInstantiatedGraph();
		}
			
		// Step 2 : export to CAMI to build input for Silien's ordering tool
		try {
			new ExportToImpl().export(graph,getWorkDir()+ "/" + CAMI_FILE_NAME, new NullProgressMonitor());
		} catch (Exception e) {
			success  = false;
			report = "An error occurred during export to CAMI phase :" + e + e.getMessage();
			addResult (new ServiceResult(success,report,this));
			return report;
		}
		try {
			// export the TPN to Romeo xml format.
			new ExportToRomeo().export(graph,getWorkDir()+ "/" + ROMEO_FILE_NAME, new NullProgressMonitor());
		} catch (Exception e) {
			success  = false;
			report = "An error occurred during export to Romeo phase :" + e + e.getMessage();
			addResult (new ServiceResult(success,report,this));
			return report;
		}
		
		
		
		// Step 3 : invoke Silien's tool
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
