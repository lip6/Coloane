package fr.lip6.move.coloane.core.formalisms.elements;

import fr.lip6.move.coloane.interfaces.formalism.IReference;

/**
 * Nearly identical to the ChildAttribute class, but
 * kept separate so as to be logical (a child attribute is not a reference)
 * @author Elodie Banel
 */
public class Reference implements IReference {

	private String href;
	private int minOccurs;
	private int maxOccurs;
	
	/**
	 * Create a new reference
	 * @param href The url of the reference
	 * @param minOccurs The minimum number of occurences of the reference
	 * @param maxOccurs The maximum number of occurences of the reference
	 */
	public Reference(String href, int minOccurs, int maxOccurs) {
		this.href = href;
		this.minOccurs = minOccurs;
		this.maxOccurs = maxOccurs;
	}
	
	/** {@inheritDoc} */
	public final String getHref() { return href; }
	
	/** {@inheritDoc} */
	public final int getMinOccurs() { return minOccurs; }
	
	/** {@inheritDoc} */
	public final int getMaxOccurs() { return maxOccurs; }
	
}
