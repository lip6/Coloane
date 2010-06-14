package fr.lip6.move.coloane.projects.its;

import java.util.Iterator;

/** A class to represent a model variable, that can be interrogated in the logic.
 * 
 * @author Yann
 *
 */
public interface IModelVariable extends Iterable<IModelVariable> {

	
	/**
	 * The pretty name of this variable, may not be unique or set.
	 * @return the name or ""
	 */
	String getName();
	
	/**
	 * The Children of this ModelVariable, in the composite case.
	 * 
	 */
	Iterator<IModelVariable> iterator();
	
	/** The name with "." separators
	 * 
	 * @return the qualified name of this object
	 */
	String getQualifiedName();
	
	/** The parent of this model variable.
	 * 
	 * @return the parent or "this" if this is the root.
	 */
	IModelVariable getParent();
	
	/** The ITS id of this object, for SDD tools interaction.
	 * 
	 */
	String getId();
	
	/**
	 * Human readable description of what this variable designates.
	 * @return a fixed string for each type of variable.
	 */
	String getDescription();

	void setParent(IModelVariable parent);
}
