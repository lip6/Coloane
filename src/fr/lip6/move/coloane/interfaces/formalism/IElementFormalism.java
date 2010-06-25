package fr.lip6.move.coloane.interfaces.formalism;

import java.util.List;

/**
 * This class describes a element of a formalism.<br>
 * A formalism is entirely built from those elements.<br>
 * An element contains all required information:
 * <ul>
 * 	<li>Name</li>
 * 	<li>A list of attributes</li>
 * 	<li>A set of graphical descriptions</li>
 * </ul>
 *
 * @author Jean-Baptiste Voron
 */
public interface IElementFormalism {

	/**
	 * @return The name of the element
	 */
	String getName();

	/**
	 * @return The list of {@link AttributeFormalism} attached to this element.
	 */
	List<IAttributeFormalism> getAttributes();
	
	/**
	 * @return The list of {@link ComputedAttributeFormalism} attached to this element.
	 */
	List<IComputedAttributeFormalism> getComputedAttributes();

	/**
	 * @return The default graphical description of the element
	 * @see {@link #getAllGraphicalDescription()} to get a full list of graphical description for this element.
	 */
	IGraphicalDescription getGraphicalDescription();

	/**
	 * @return The list of all associated graphical descriptions for the element
	 * @see {@link #getGraphicalDescription()} to get the default representation for this element.
	 */
	List<IGraphicalDescription> getAllGraphicalDescription();

	/**
	 * @return The formalism that contains and uses this element
	 */
	IFormalism getFormalism();
}
