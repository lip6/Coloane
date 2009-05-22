package fr.lip6.move.coloane.core.motor.formalisms.elements;

import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphicalDescription;

import java.util.ArrayList;
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

public class ElementFormalism implements IElementFormalism {
	/** Name */
	private String name;

	/** Formalism that contains and uses this element */
	private IFormalism formalism;

	/** Attributes list */
	private List<IAttributeFormalism> attributes = new ArrayList<IAttributeFormalism>();

	/** Graphical description list */
	private List<IGraphicalDescription> graphicalDescriptions = new ArrayList<IGraphicalDescription>();

	/**
	 * Constructor
	 * @param name Name of this element (eg. place, transition, event...)
	 * @param formalism Formalism that contains and uses this element
	 */
	public ElementFormalism(String name, IFormalism formalism) {
		this.name = name;
		this.formalism = formalism;
	}

	/**
	 * Add a graphical description to the element
	 * @param graphicalDescription The additional graphical description to be considered
	 */
	public final void addGraphicalDescription(IGraphicalDescription graphicalDescription) {
		this.graphicalDescriptions.add(graphicalDescription);
	}

	/**
	 * Add an new attribute to this element
	 * @param attribute The additional attribute to be considered for this element
	 */
	public final void addAttribute(AttributeFormalism attribute) {
		this.attributes.add(attribute);
	}

	/** {@inheritDoc} */
	public final String getName() {	return name; }

	/** {@inheritDoc} */
	public final List<IAttributeFormalism> getAttributes() { return this.attributes; }

	/** {@inheritDoc} */
	public final List<IGraphicalDescription> getAllGraphicalDescription() { return graphicalDescriptions; }

	/** {@inheritDoc} */
	public final IGraphicalDescription getGraphicalDescription() { return graphicalDescriptions.get(0); }

	/** {@inheritDoc} */
	public final IFormalism getFormalism() { return this.formalism;	}
}
