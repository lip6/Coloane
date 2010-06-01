package fr.lip6.move.coloane.projects.its.order;

/**
 * An abstraction to represent Ordering objects in memory.
 * Iterator interface allows to access children.
 * @author Yann
 *
 */
public interface Ordering extends Iterable<Ordering> {

	/**
	 * Gives a logical name for this SDD variable.
	 * @return a name, could be generated.
	 */
	String getName ();

	
	enum Domain { SDD, Integer };
	
	/**
	 * Return this variable's domain. 
	 * @return SDD or Integer
	 */
	Domain getDomain ();

	/** The index of a variable of this order.
	 * 
	 * @param value the variable to look for
	 * @return the index or 0 if not found
	 */
	int getVarIndex(String value);
	
	/** Insert a variable in the order, so its resulting index is the one provided.
	 * 
	 * @param value the variable to look for
	 * @return the index or 0 if not found
	 */
	void insertVarAtIndex(String value, int index);
}
