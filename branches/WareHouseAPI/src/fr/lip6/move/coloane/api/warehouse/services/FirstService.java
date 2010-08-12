package fr.lip6.move.coloane.api.warehouse.services;

import fr.lip6.move.coloane.api.warehouse.WareHouseApi;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.Result;
import fr.lip6.move.coloane.interfaces.objects.result.SubResult;
import fr.lip6.move.coloane.interfaces.objects.services.IApiService;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

public class FirstService implements IApiService {
	private WareHouseApi api;
	
	public FirstService(WareHouseApi api) {
		this.api = api;
	}

	public String getName() {
		return "toto";
	}
	
	public String getDescription() {
		return "bar";
	}

	public List<IResult> run(IGraph model, IProgressMonitor monitor) {
		ArrayList<INode> nodes = new ArrayList<INode>(model.getNodes());
		ArrayList<IArc> arcs = new ArrayList<IArc>(model.getArcs());

		Result result = new Result("Statistics (distant)");
		SubResult subresNodes = new SubResult("Places and Transitions", "Show all nodes");
		SubResult subresArcs = new SubResult("Arcs", "Show all arcs");

		// Deal with the progress monitor : Tells the progress monitor the task title
		monitor.beginTask("Computing statistics...", model.getNodes().size() + model.getArcs().size());

		// New subtask !
		monitor.subTask("Browsing nodes");

		// Add all nodes as objects designation for the first sub-result
		for(int i = 0; i < nodes.size(); i++) { 
			// Check if the user has canceled the tool execution 
			if (monitor.isCanceled()) { return null; }
			subresNodes.addObjectDesignation(nodes.get(i));
			monitor.worked(1); // 1 work unit = 1 node processed
		}

		// New subtask !
		monitor.subTask("Browsing arcs");

		// Add all arcs as objects designation for the second sub-result
		for(int i = 0; i < arcs.size(); i++) { 
			// Check if the user has canceled the tool execution 
			if (monitor.isCanceled()) { return null; }
			subresArcs.addObjectDesignation(arcs.get(i));
			monitor.worked(1); // 1 work unit = 1 arc processed
		}

		
		SubResult subresNodeList = new SubResult("Places and Transitions", "Node list");
		SubResult subresArcList = new SubResult("Arcs", "Arc list");

		for(int i = 0; i < nodes.size(); i++) { subresNodeList.addObjectOutline(nodes.get(i)); }
		for(int i = 0; i < arcs.size(); i++) { subresArcList.addObjectOutline(arcs.get(i));	}

		result.addTextualResult("Number of nodes:",String.valueOf(nodes.size()));
		result.addTextualResult("Number of arcs:",String.valueOf(arcs.size()));

		result.addSubResult(subresNodes);
		result.addSubResult(subresArcs);
		result.addSubResult(subresNodeList);
		result.addSubResult(subresArcList);

		ArrayList<IResult> resultsList = new ArrayList<IResult>();
		resultsList.add(result);
		monitor.done();
		
		this.api.enrichMenus();
		
		
		return resultsList;
	}
}
