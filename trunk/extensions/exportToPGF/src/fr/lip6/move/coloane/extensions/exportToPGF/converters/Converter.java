package fr.lip6.move.coloane.extensions.exportToPGF.converters;

import fr.lip6.move.coloane.interfaces.model.IAttribute;

/**
 * Interface for converter classes.
 * A converter is an object that reads an attribute and outputs its textual
 * representation adapted for TikZ.
 */
public interface Converter {

	/**
	 * Convert attribute to its textual representation for TikZ.
	 * @param attribute The attribute to convert.
	 * @return A textual representation of attribute, suited for TikZ.
	 */
	String convert(IAttribute attribute);

}
