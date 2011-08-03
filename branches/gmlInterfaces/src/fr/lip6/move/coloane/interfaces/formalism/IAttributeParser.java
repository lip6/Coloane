package fr.lip6.move.coloane.interfaces.formalism;

import fr.lip6.move.coloane.interfaces.model.IAttribute;

/**
 * Interface to be implemented by formalism descriptors to describe how to parse and
 * serialize attribute contents.
 * @author Elodie Banel
 */
public interface IAttributeParser {

	/**
	 * Parse the text of an attribute and update the given attribute with any
	 * child attributes found.
	 * @param line The line of text to parse
	 * @param containerAtt The top level attribute
	 * @return True if the line has been parsed, false otherwise
	 */
	boolean parseLine(String line, IAttribute containerAtt);
	
	/**
	 * Return the attribute and its children in text format.
	 * Ideally, the result of this function should be parsable by
	 * parseLine to get the attribute structure back.
	 * @param containerAtt The top level attribute to serialize.
	 * @return The serialized version of the attribute tree.
	 */
	String toString(IAttribute containerAtt);
	
}
