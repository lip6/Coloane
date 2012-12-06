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
package fr.lip6.move.coloane.extensions.exportToPGF.converters;

import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

/**
 * Factory of converters.
 */
public final class ConverterFactory {

	/**
	 * Forbidden constructor.
	 */
	private ConverterFactory() {
	}

	/**
	 * Create a converter object adapted to formalism.
	 * @param formalism The formalism to handle.
	 * @return A converter object adapted to formalism.
	 * @throws UnknownFormalismException When a blowjob occurs.
	 */
	public static Converter createConverter(IFormalism formalism) throws UnknownFormalismException {
		if (formalism.getId().equals("PT-Net")) {
			return new PTNetConverter();
		} else if (formalism.getId().equals("CPN")) {
			return new CPNConverter();
		} else if (formalism.getId().equals("RG")) {
			return new RGConverter();
		} else {
			throw new UnknownFormalismException();
		}
	}
}
