package fr.lip6.move.coloane.interfaces.formalism;

import fr.lip6.move.coloane.interfaces.model.IAttributeFormatter;

/**
 * This class represents all the formalism computed attribute characteristics.<br>
 * An attribute is like a property of a base element.<br>
 * Each base element has to maintain its own list of attributes.
 * <br>
 * Computed Attributes share some properties with Standard Attributes.<br>
 * Those common properties are detailed in Global Attributes definition.
 * 
 * @see IAttributeFormalism
 * @see IGlobalAttributeFormalism
 *
 * @author Jean-Baptiste Voron
 */
public interface IComputedAttributeFormalism extends IGlobalAttributeFormalism {
	
	/** 
	 * @return The class that is supposed to format the attribute value
	 */
	IAttributeFormatter getAttributeFormatter();
}
