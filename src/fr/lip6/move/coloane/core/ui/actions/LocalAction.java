package fr.lip6.move.coloane.core.ui.actions;

import fr.lip6.move.coloane.core.extensions.IColoaneAction;
import fr.lip6.move.coloane.core.session.ISession;
import fr.lip6.move.coloane.core.session.SessionManager;
import fr.lip6.move.coloane.core.ui.ColoaneJob;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * This class defines an action associated to a local tool.
 * 
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class LocalAction extends Action {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	
	/** The name of the action */
	private final String name;
	
	/** The description of the action */
	private final String description;
	
	/** The icon associated to the action */
	private final ImageDescriptor icon;
	
	/** The effective ColoaneAction */
	private final IColoaneAction action;
	
	/**
	 * Constructor
	 * @param name The action name
	 * @param description The action description
	 * @param icon The icon associated to the action
	 * @param action The effective action to be run
	 */
	public LocalAction(String name, String description, ImageDescriptor icon, IColoaneAction action) {
		this.name = name;
		this.icon = icon;
		this.description = description;
		this.action = action;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getDescription() {
		return this.description;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getToolTipText() {
		return this.description;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getText() {
		return this.name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ImageDescriptor getImageDescriptor() {
		return this.icon;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void run() {
		final ISession currentSession = SessionManager.getInstance().getCurrentSession();
		IGraph currentGraph = currentSession.getGraph();
		LOGGER.fine("Building the external coloane JOB"); //$NON-NLS-1$		
		ColoaneJob job = new ColoaneJob(this.name, currentGraph, this.action);
		LOGGER.finer("Executing the external coloane JOB"); //$NON-NLS-1$
		job.setRule(ResourcesPlugin.getWorkspace().getRoot());
		job.schedule();
		
		job.addJobChangeListener(new JobChangeAdapter() {
			@Override
	        public void done(IJobChangeEvent event) {
				if (event.getResult().isOK()) {
					List<IResult> results = ((ColoaneJob) event.getJob()).getResults();
					LOGGER.fine("Browsing results..."); //$NON-NLS-1$		
					for (IResult result : results) {
						//System.out.println("Result..." + result); //$NON-NLS-1$
						currentSession.getResultManager().add(description, result);
					}
				}
			}
		});
	}
}
