/**
 * 
 */
package fr.lip6.move.coloane.extension.importExportPNML.importFromPNML;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.pnml.framework.hlapi.HLAPIRootClass;

/**
 * Every Petri net type processor must implement this class.
 * 
 * @author lom
 */
public abstract class Processor {

	/**
	 * Each Petri net type processor must implement this method.
	 * 
	 * @param rcl
	 *            the Petri net document top-level class.
	 * @param formalism
	 *            The Coloane Petri net formalism
	 * @throws ModelException
	 *             something went wrong during PN models processing
	 */
	public abstract IGraph process(HLAPIRootClass rcl, String formalism) throws ModelException;

}
