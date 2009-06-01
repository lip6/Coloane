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
		} else {
			throw new UnknownFormalismException();
		}
	}
}
