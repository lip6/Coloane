package fr.lip6.move.coloane.extensions.exportToPGF.converters;

import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

public final class ConverterFactory {

	public static Converter createConverter(IFormalism formalism) throws UnknownFormalismException {
		if (formalism.getId().equals("PT-Net")) {
			return new PTNetConverter();
		} else {
			throw new UnknownFormalismException();
		}
	}
}
