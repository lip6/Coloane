package fr.lip6.move.coloane.projects.its.syntax;

import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

import java.util.ArrayList;
import java.util.List;

/** 
 * An adapter for a syntax error result.
 * @author Yann
 *
 */
public final class Result implements IResult {

	private List<ISubResult> subResults = new ArrayList<ISubResult>();

	/**
	 * {@inheritDoc}
	 */
	public List<ICommand> getModificationsOnCurrentGraph() {
		return new ArrayList<ICommand>();
	}

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

}
