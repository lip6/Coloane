package fr.lip6.move.coloane.interfaces.formalism;

/**
 * Define a method that checks some conditions defined by the user about an {@link IAttribute}.<br>
 * Only the value of the attribute is given the the method (at runtime).<br>
 *
 * @author Florian David
 */
public interface IAttributeChecker {
	/**
	 * Method implemented by the user who wants to check some conditions on an {@link IAttribute}.
	 * @param value the value of the attribute to be checked.
	 * @return <code>true</code> if a marker must be created, <code>false</code> otherwise.
	 */
	boolean performCheck(String value);
}
