package fr.lip6.move.coloane.interfaces.formalism;

import fr.lip6.move.coloane.interfaces.model.IAttribute;

/**
 * Define a method which check some conditions defined by the user on an {@link IAttribute}.
 * Only the value of the attribute is given. The value correspond to the IAttribute value defined in the extension point.
 * An empty public constructor <b>must</b> be available.
 * @author Florian David
 */
public interface IAttributeChecker {
	/**
	 * Method implemented by the user who wants to check some conditions on an {@link IAttribute}.
	 * @param value the value of the attribute to be checked.
	 * @return <code>true</code> if a marker must be created, <code>false</code> otherwise.
	 */
	public boolean check(String value);
}
