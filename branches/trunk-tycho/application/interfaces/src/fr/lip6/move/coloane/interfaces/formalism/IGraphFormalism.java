package fr.lip6.move.coloane.interfaces.formalism;

import java.util.Collection;

/**
 * Describe all properties for a graph.<br>
 * Basically, it contains nodes, arcs and attributes.<br>
 * Every formalism instances have, at least, one instance of this element. 
 */
public interface IGraphFormalism extends IElementFormalism {

	/**
	 * @return all elements that are declared as members of this graph
	 */
	Collection<IElementFormalism> getAllElementFormalism();

	/**
	 * Get the description (formalism) of an object thanks to its name
	 * @param name The name of the object
	 * @return The formalism associated to this object 
	 */
	IElementFormalism getElementFormalism(String name);
}
