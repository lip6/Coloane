package fr.lip6.move.coloane.extension.importExportPNML.importFromPNML;

import fr.lip6.move.pnml.framework.hlapi.HLAPIRootClass;

/**
 * Dispatches the transformation to the right PNML type processor.
 * 
 * @author lom
 * 
 */
public final class MainProcessor {
	/**
	 * Hidden constructor.
	 */
	private MainProcessor() {
		super();
	}

	/**
	 * Determines which PNML type processor to call to perform the actual
	 * transformation.
	 * 
	 * @param theclass
	 *            HLAPI root class
	 * @return the PNML type processor which is going to perform the actual
	 *         transformation.
	 * @throws UnHandledType
	 *             Unhandled net type.
	 */
	public static Processor getProcessor(HLAPIRootClass theclass) throws UnHandledType {
		Processor p = null;
		if (theclass.getClass().equals(fr.lip6.move.pnml.pnmlcoremodel.hlapi.PetriNetDocHLAPI.class)) {
			p = new CoreProcessor();
		} else if (theclass.getClass().equals(fr.lip6.move.pnml.ptnet.hlapi.PetriNetDocHLAPI.class)) {
			p = new PTProcessor();
		} else {
			throw new UnHandledType("This PNML type is not yet supported.", new Throwable("This PNML type is not yet supported."));
		}

		// TODO : support SN HLPN and PT-HLPN.
		return p;
	}
}
