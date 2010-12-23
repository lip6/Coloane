/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.extension.importExportPNML.importFromPNML;

import fr.lip6.move.pnml.framework.hlapi.HLAPIRootClass;

/**
 * Dispatches the transformations to the correct PNML type processor.
 *
 * @author Lom Messan Hillah
 */
public final class MainProcessor {
	/**
	 * Hidden constructor.
	 */
	private MainProcessor() {
		super();
	}

	/**
	 * Determines which PNML type processor to call to perform the actual transformation.
	 *
	 * @param rootClass HLAPI root class
	 * @return the PNML type processor which is going to perform the actual transformation.
	 * @throws UnHandledType Unhandled net type.
	 */
	public static Processor getProcessor(HLAPIRootClass rootClass) throws UnHandledType {
		Processor p = null;
		if (rootClass.getClass().equals(fr.lip6.move.pnml.pnmlcoremodel.hlapi.PetriNetDocHLAPI.class)) {
			p = new CoreProcessor();
		} else if (rootClass.getClass().equals(fr.lip6.move.pnml.ptnet.hlapi.PetriNetDocHLAPI.class)) {
			p = new PTProcessor();
		} else {
			throw new UnHandledType("This PNML type is not yet supported.", new Throwable("This PNML type is not yet supported."));
		}

		// TODO : support SN HLPN and PT-HLPN.
		return p;
	}
}
