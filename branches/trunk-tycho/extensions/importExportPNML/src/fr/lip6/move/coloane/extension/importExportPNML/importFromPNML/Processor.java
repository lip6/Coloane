package fr.lip6.move.coloane.extension.importExportPNML.importFromPNML;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.pnml.framework.hlapi.HLAPIRootClass;

/**
 * Every Petri net type processor must implement this class.
 * @author Lom Messan Hillah
 */
public abstract class Processor {

	/**
	 * Each Petri net type processor must implement this method.
	 *
	 * @param rootClass The Petri net document top-level class.
	 * @param formalism The Coloane Petri net formalism
	 * @throws ModelException Something went wrong during PN models processing
	 * @return A coloane graph model {@link IGraph}
	 */
	public abstract IGraph process(HLAPIRootClass rootClass, IFormalism formalism) throws ModelException;
}
