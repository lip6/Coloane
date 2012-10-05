package fr.lip6.move.coloane.core.formalisms.elements;

/**
 * Class to describe a child of a complex attribute
 * @author Elodie Banel
 */
public class ChildAttribute {

	private String refName;
	private int minOccurs;
	private int maxOccurs;
	
	/**
	 * Create a new child reference
	 * @param refName The reference name of the child attribute
	 * @param minOccurs The minimum number of occurences of the attribute
	 * @param maxOccurs The maximum number of occurences of the attribute
	 */
	public ChildAttribute(String refName, int minOccurs, int maxOccurs) {
		this.refName = refName;
		this.minOccurs = minOccurs;
		this.maxOccurs = maxOccurs;
	}
	
	/** @return The child attribute referenced's name */
	public final String getRefName() { return refName; }

	/** @return The minimum number of occurences of the attribute */
	public final int getMinOccurs() { return minOccurs; }
	
	/**
	 * @return The maximum number of occurences of the attribute (a negative value indicates infinity)
	 */
	public final int getMaxOccurs() { return maxOccurs; }
	
}
