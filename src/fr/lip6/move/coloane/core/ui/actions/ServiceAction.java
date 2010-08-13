package fr.lip6.move.coloane.core.ui.actions;

import fr.lip6.move.coloane.core.session.ISession;
import fr.lip6.move.coloane.core.session.SessionManager;
import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.core.ui.ColoaneJob;
import fr.lip6.move.coloane.core.ui.SaveReceivedModel;
import fr.lip6.move.coloane.core.ui.commands.ApplyRequestsCmd;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * Define an action that will execute a service
 */
public class ServiceAction extends Action {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** All information about the service */
	private IServiceMenu serviceDescription;
	
	final IWorkbenchWindow workbenchWindow;

	/**
	 * Constructor
	 * @param service the service description
	 */
	public ServiceAction(IServiceMenu service) {
		super(service.getName(), IAction.AS_PUSH_BUTTON);
		setId(service.getName());
		this.serviceDescription = service;
		workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
	}
	
	/** {@inheritDoc} */
	@Override
	public final void run() {
		final ISession currentSession = SessionManager.getInstance().getCurrentSession();

		LOGGER.fine("Service execution : " + serviceDescription.getAssociatedService()); //$NON-NLS-1$
		IGraph currentGraph = currentSession.getGraph();
		LOGGER.fine("Building the coloane job"); //$NON-NLS-1$		
		ColoaneJob job = new ColoaneJob(this.serviceDescription.getName(), currentGraph, this.serviceDescription.getAssociatedService());
		LOGGER.finer("Executing the coloane job"); //$NON-NLS-1$
		
		job.addJobChangeListener(new JobChangeAdapter() {
			@Override
	        public void done(IJobChangeEvent event) {
				if (event.getResult().isOK()) {
					// Fetch results from the service provider
					List<IResult> results = ((ColoaneJob) event.getJob()).getResults();
					LOGGER.fine("Browsing results..."); //$NON-NLS-1$		
					for (IResult result : results) {
						// Add textual results to the ResultManager
						currentSession.getResultManager().add(result.getResultName(), result);
						
						// Deal with modifications of the current graph
						if (result.getDeltaRequestsList().size() > 0) {
							// Create a new special command to apply incoming request
							LOGGER.finer("Taking into account all requests for the current graph..."); //$NON-NLS-1$		
							final ApplyRequestsCmd command = new ApplyRequestsCmd(result.getDeltaRequestsList(), currentSession.getGraph());
							Display.getDefault().asyncExec(new Runnable() {
								public void run() {
									LOGGER.finer("Applying the delta command..."); //$NON-NLS-1$		
									((ColoaneEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).executeCommand(command);
								}
							});
						}
						
						// Deal with a new graph (if existing)
						if (result.getNewComputedGraph() != null) {
							Display.getDefault().asyncExec(new SaveReceivedModel(result.getNewComputedGraph(), result.getResultName(), workbenchWindow));
						}
					}
				}
			}
		});
		
		job.setRule(ResourcesPlugin.getWorkspace().getRoot());
		job.schedule();
		serviceDescription.getAssociatedService().run(SessionManager.getInstance().getCurrentSession().getGraph(), null);
	}
}
