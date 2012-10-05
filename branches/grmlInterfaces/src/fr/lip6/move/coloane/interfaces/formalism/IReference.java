package fr.lip6.move.coloane.interfaces.formalism;

/**
 * Nearly identical to the ChildAttribute class, but
 * kept separate so as to be logical (a child attribute is not a reference)
 * @author Elodie Banel
 *
 */
public interface IReference {
	
	/** @return The reference's url */
	String getHref();
	
	/** @return The minimum number of occurences of the reference */
	int getMinOccurs();
	
	/** @return The maximum number of occurences of the reference (a negative value indicates infinity) */
	int getMaxOccurs();
	
}
