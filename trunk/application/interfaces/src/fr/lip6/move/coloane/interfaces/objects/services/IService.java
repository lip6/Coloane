package fr.lip6.move.coloane.interfaces.objects.services;

import fr.lip6.move.coloane.interfaces.exceptions.ServiceException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Interface that must be implemented by a <b>local tool</b> provider.
 * 
 * @author Jean-Baptiste Voron
 */
public interface IService {
	
	/**
	 * Run the service !
	 * @param model The current session graph model
	 * @param monitor The progress monitor used to visualize the job progress
	 * @return A set of commands that will be executed by the core
	 */
	List<IResult> run(IGraph model, IProgressMonitor monitor) throws ServiceException;
}
