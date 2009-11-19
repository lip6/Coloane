package fr.lip6.move.coloane.its;

import fr.lip6.move.coloane.its.obs.SimpleObservable;

import java.util.ArrayList;
import java.util.List;

/**
 * A concept represents instances in a composite.
 * For a type name given in the coloane composite model in an instance, the concept maps
 * to a type declaration in the TypeList.
 * @author Yann
 *
 */
public final class Concept extends SimpleObservable {
	private String name;
	/** The list of labels required in this composite for a given type name */
	private List<String> labels;
	/** The resolved map from Type name in the composite, to effective Type in the Model*/
	private TypeDeclaration effective;
	private CompositeTypeDeclaration parent;

	/**
	 * Ctor.
	 * @param name of the type in the Composite graph object.
	 * @param parent the parent declaration
	 */
	public Concept(String name, CompositeTypeDeclaration parent) {
		this.name = name;
		labels = new ArrayList<String>();
		effective = null;
		this.parent = parent;
	}

	/**
	 * accessor
	 * @return the name of the type in the Composite graph object.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Based on the graph, the labels required from this type.
	 * @return the required labels for this concept
	 */
	public List<String> getLabels() {
		return labels;
	}
	/**
	 * 
	 * @return the effective type assigned to this concept or null if not set
	 */
	public TypeDeclaration getEffective() {
		return effective;
	}
	/**
	 * Update value and notify observers
	 * @param effective new effective type
	 */
	public void setEffective(TypeDeclaration effective) {
		if (this.effective != effective) {
			this.effective = effective;
			notifyObservers();
		}
	}
	
	/**
	 * @return parent of this concept
	 */
	public CompositeTypeDeclaration getParent() {
		return parent;
	}
}
