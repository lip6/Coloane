package fr.lip6.move.coloane.projects.its.syntax;

import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.requests.IRequest;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** 
 * An adapter for a syntax error result.
 * @author Yann
 */
public final class Result implements IResult {

	private List<ISubResult> subResults = new ArrayList<ISubResult>();

	/**
	 * {@inheritDoc}
	 */
	public IGraph getNewGraph() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getRootName() {
		return "tpn";
	}

	/**
	 * {@inheritDoc}
	 */
	public String getServiceName() {
		return "tpn.syntax";
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ISubResult> getSubResults() {
		return subResults;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ITip> getTipsList() {
		return new ArrayList<ITip>();
	}

	/**
	 * {@inheritDoc}
	 */
	public void addChild(SubResult sr) {
		subResults.add(sr);
	}

	public void addSubResult(ISubResult subResult) {
		// TODO Auto-generated method stub
		
	}

	public void addTip(ITip tip) {
		// TODO Auto-generated method stub
		
	}

	public void addTip(IElement object, String name, String value) {
		// TODO Auto-generated method stub
		
	}

	public void addDeltaRequest(IRequest request) {
		// TODO Auto-generated method stub
		
	}

	public void addDeltaRequests(List<IRequest> requests) {
		// TODO Auto-generated method stub
		
	}

	public void setNewGraph(IGraph newGraph) {
		// TODO Auto-generated method stub
		
	}

	public void addNewGraphDeltaRequest(List<IRequest> requests) {
		// TODO Auto-generated method stub
		
	}

	public String getResultName() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IRequest> getDeltaRequestsList() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IRequest> getNewComputedGraphDeltaRequestsList() {
		// TODO Auto-generated method stub
		return null;
	}

	public IGraph getNewComputedGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<List<String>> getTextualResults() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Integer, List<ITip>> getTips() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean shouldBeDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
