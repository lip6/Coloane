package fr.lip6.move.coloane.core.extensions;

import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Interface that must be implemented by tools provider.<br>
 * For the moment, tools can only return a set of commands.
 * 
 * @author Jean-Baptiste Voron
 */
public interface IColoaneAction {
	String NAME = "name"; //$NON-NLS-1$
	String ICON = "icon"; //$NON-NLS-1$
	String DESCRIPTION = "description"; //$NON-NLS-1$
	String ACTION = "action"; //$NON-NLS-1$
	
	/**
	 * @param model The current session graph model
	 * @param monitor The progress monitor used to visualize the job progress
	 * @return A set of commands that will be executed by the core
	 */
	List<IResult> run(IGraph model, IProgressMonitor monitor);
}
