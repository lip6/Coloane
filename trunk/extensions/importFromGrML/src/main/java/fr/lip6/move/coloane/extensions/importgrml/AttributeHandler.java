/**
 * 
 */
package fr.lip6.move.coloane.extensions.importgrml;

import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;

import org.cosyverif.model.Attribute;

/**
 * Interface for Attribute handlers.
 * 
 * An attribute handler converts a GrML attribute to a string that can be used for the attribute in Coloane.
 */
public interface AttributeHandler {
	
	/**
	 * Convert a GrML attribute to a string that can be used for the attribute in Coloane.
	 * @param attribute The attribute to convert
	 * @return Its representation in Coloane
	 * @throws ExtensionException if something goes wrong
	 */
	String importFrom(Attribute attribute) throws ExtensionException;

}
