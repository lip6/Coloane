package fr.lip6.move.coloane.tools.layout;

import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

import java.util.List;

/**
 * An adapter to encapsulate a set of commands in an IResult.
 * @author Yann TM
 *
 */
public final class ResultCommand implements IResult {

	
	
	private List<ICommand> commands;

	/**
	 * A constructor.
	 * @param commands a list of commands to encapsulate
	 */
	public ResultCommand(List<ICommand> commands) {
		this.commands = commands;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ICommand> getModificationsOnCurrentGraph() {
		return this.commands;
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
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getServiceName() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ISubResult> getSubResults() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ITip> getTipsList() {
		return null;
	}

}
