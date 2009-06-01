package fr.lip6.move.coloane.extensions.exportToPGF.converters;

import fr.lip6.move.coloane.interfaces.model.IAttribute;

public final class PTNetConverter implements Converter {

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
