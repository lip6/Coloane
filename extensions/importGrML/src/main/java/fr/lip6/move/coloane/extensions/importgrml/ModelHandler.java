/**
 * 
 */
package fr.lip6.move.coloane.extensions.importgrml;

import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import javax.xml.stream.XMLStreamReader;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Interface to implement when developing a GrML import extension with no help.
 */
public interface ModelHandler {
	
	/**
	 * Convert a GrML model to a Coloane model.
	 * 
	 * @param reader A stream reader that allows to walk through the GrML model
	 * @param monitor The progress monitor
	 * @return The corresponding Coloane model
	 * @throws ExtensionException Exception thrown when something bad happens
	 */
	IGraph importFrom(XMLStreamReader reader, IProgressMonitor monitor) throws ExtensionException;

}
