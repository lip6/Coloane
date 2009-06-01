package fr.lip6.move.coloane.extensions.exportToPGF.converters;

import fr.lip6.move.coloane.interfaces.model.IAttribute;

/**
 * Converter for PT-Net formalism.
 */
public final class PTNetConverter implements Converter {

	/**
	 * Convert a PT-Net attribute to a textual representation.
	 * Only replace '\n' characters by '\\' as required in (La)Tex.
	 * @param attribute The attribute to convert.
	 * @return Its textual representation.
	 */
	public String convert(IAttribute attribute) {
		String value = attribute.getValue();
		// Replace new lines with LaTeX \\ only un multiline attributes:
		if (attribute.getAttributeFormalism().isMultiLine()) {
			// 8 '\' because StringTemplate or something else interprets them!
			value = value.replaceAll("\n", " \\\\\\\\ \n");
		}
		return value;
	}

}
