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
}
