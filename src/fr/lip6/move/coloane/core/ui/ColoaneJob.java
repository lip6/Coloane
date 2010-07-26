package fr.lip6.move.coloane.core.ui;

import fr.lip6.move.coloane.core.extensions.IColoaneAction;
import fr.lip6.move.coloane.core.ui.actions.LocalAction;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * Jobs are action containers.<br>
 * To use with {@link LocalAction}
 * 
 * @author Jean-Baptiste Voron
 */
public class ColoaneJob extends Job {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	
	/** The set of results */
	List<IResult> results;
	
	/** The action */
	IColoaneAction action;
	
	/** The graph on which the action has to be performed */
	IGraph graph;

	/**
	 * Constructor
	 * @param name The name of the action
	 * @param graph The graph on which the action will be performed
	 * @param actionToPerform The action to run
	 * @see Job
	 */
	public ColoaneJob(String name, IGraph graph, IColoaneAction actionToPerform) {
		super(name);
		setSystem(false);
		this.results = new ArrayList<IResult>();
		this.action = actionToPerform;
		this.graph = graph;
	}
	
	public List<IResult> getResults() {
		return this.results;
	}
	
	@Override
	protected IStatus run(IProgressMonitor monitor) {
		this.results = this.action.run(this.graph,monitor);
		if (this.results == null) {
			return Status.CANCEL_STATUS;
		}
		return Status.OK_STATUS;
	}

	/** {@inheritDoc} */
	@Override
	protected final void canceling() {
		LOGGER.warning("The job " + this.getName() + " has received an INTERRUPT request : "); //$NON-NLS-1$//$NON-NLS-2$
		getThread().interrupt();
		super.canceling();
	}

}
